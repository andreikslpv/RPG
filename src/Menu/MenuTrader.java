package Menu;

import Characters.Humans.*;
import InterfacesAndThread.RPGAction;
import Things.Potions.Potion;
import Things.RPGThing;
import Things.Weapons.Weapon;

public class MenuTrader extends RPGMenu implements RPGAction {

    private final Hero hero;
    private final Trader trader;
    private boolean isGameOver = false;

    public MenuTrader(Hero hero, Trader trader, int indentLevel) {
        super(indentLevel);
        this.hero = hero;
        this.trader = trader;
        menuItems.add(new StringBuilder("Вы находитесь у торговца " + trader.getName() + ". Что вы хотите сделать?"));
        menuItems.add(new StringBuilder("1. Посмотреть информацию о торговце"));
        menuItems.add(new StringBuilder("2. Купить товары"));
        menuItems.add(new StringBuilder("3. Попытаться украсть товар"));
        menuItems.add(new StringBuilder("4. Продать свои вещи торговцу"));
        menuItems.add(new StringBuilder("0. Вернуться в Город"));
        text = formatTheMenuText(indentLevel, menuItems);
        countOfMenuItems = 4;
    }

    @Override
    public void printMenu() {
        isExitFromMenu = false;
        while (!isExitFromMenu) {
            switch (getMenuItem(text, countOfMenuItems)) {
                case 1 -> System.out.println(trader);
                // Формируем и запускаем меню покупки вещей у торговца
                case 2 -> switchBuyOrSell(trader, hero, "покупки:");
                case 3 -> {
                    // Формируем и запускаем меню кражи
                    MenuOfChoosingThing menuThing = new MenuOfChoosingThing(trader, indentLevel + 1, "кражи:");
                    menuThing.printMenu();
                    if (menuThing.getChoosingThing() != null) {
                        double chance = hero.getChance();
                        // Если повезло то воруем, нет - деремся с торговцем
                        if (chance > 0.8d)
                            startTheft(trader, hero, menuThing.getChoosingThing());
                        else {
                            System.out.println(INDENT_3_LEVEL + "Вы попались на воровстве!");
                            isGameOver = startBattle(hero, trader);
                            isExitFromMenu = true;
                        }
                    }
                }
                // Формируем и запускаем меню продажи вещей торговцу
                case 4 -> switchBuyOrSell(hero, trader, "продажи:");
                case 0 -> isExitFromMenu = true;
            }
        }
    }

    public boolean gameOver() {
        return isGameOver;
    }

    private void switchBuyOrSell(Human trader, Human buyer, String purpose) {
        // Сначала выбираем товар, с помощью соответствующего меню
        MenuOfChoosingThing menuThing = new MenuOfChoosingThing(trader, indentLevel + 1, purpose);
        menuThing.printMenu();
        RPGThing choosingThing = menuThing.getChoosingThing();
        if (choosingThing != null)
            // Если покупатель берет такой тип товара, формируем и запускаем меню выбора количества товаров для покупки/продажи
            if (doesHeBuyThisThing(buyer, choosingThing)) {
                MenuOfChoosingCount menuCount = new MenuOfChoosingCount(trader, choosingThing, indentLevel + 1, purpose);
                menuCount.printMenu();
                startTrade(trader, buyer, choosingThing, menuCount.getChoosingCount());
            } else
                System.out.println(INDENT_3_LEVEL + buyer.getName() + " не покупает такие вещи");
    }

    private boolean doesHeBuyThisThing(Human buyer, RPGThing thing) {
        // Герой покупает все
        if (buyer instanceof Hero)
            return true;
        // Алхимик только зелья
        if (buyer instanceof Alchemist && thing instanceof Potion)
            return true;
        // Кузнец только оружие
        if (buyer instanceof Smith && thing instanceof Weapon)
            return true;
        return false;
    }
}
