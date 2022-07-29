package Menu;

import Characters.Humans.Hero;
import Characters.Humans.Human;
import Characters.Humans.Alchemist;
import Characters.Humans.Smith;
import Characters.Humans.Trader;
import InterfacesAndThread.HaveBackpack;
import InterfacesAndThread.RPGAction;
import InterfacesAndThread.Utils;
import Things.Potions.Potion;
import Things.Thing;
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
        text = Utils.formatTheMenuText(indentLevel, menuItems);
        countOfMenuItems = 4;
    }

    @Override
    public void printMenu() {
        isExitFromMenu = false;
        while (!isExitFromMenu) {
            switch (Utils.getMenuItem(text, countOfMenuItems)) {
                case 1 -> System.out.println(trader);
                case 2 -> switchBuyOrSell(trader, hero, "покупки:");
                case 3 -> {
                    MenuOfChoosingThing menuThing = new MenuOfChoosingThing(trader, indentLevel + 1, "кражи:");
                    menuThing.printMenu();
                    double chance = hero.getChance();
                    if (chance > 0.8d)
                        startTheft(trader, hero, menuThing.getChoosingThing());
                    else {
                        System.out.println(INDENT_3_LEVEL + "Вы попались на воровстве!");
                        isGameOver = startBattle(hero, trader);
                        isExitFromMenu = true;
                    }
                }
                case 4 -> switchBuyOrSell(hero, trader, "продажи:");
                case 0 -> isExitFromMenu = true;
            }
        }
    }

    public boolean gameOver(){
        return isGameOver;
    }

    private void switchBuyOrSell(HaveBackpack trader, HaveBackpack buyer, String purpose) {
        MenuOfChoosingThing menuThing = new MenuOfChoosingThing(trader, indentLevel + 1, purpose);
        menuThing.printMenu();
        Thing choosingThing = menuThing.getChoosingThing();
        if (doesHeBuyThisThing(buyer, choosingThing)) {
            MenuOfChoosingCount menuCount = new MenuOfChoosingCount(trader, choosingThing, indentLevel + 1, purpose);
            menuCount.printMenu();
            startTrade(trader, buyer, choosingThing, menuCount.getChoosingCount());
        } else
            System.out.println(INDENT_3_LEVEL + ((Human) buyer).getName() + " не покупает такие вещи");
    }

    private boolean doesHeBuyThisThing(HaveBackpack buyer, Thing thing) {
        if (buyer instanceof Hero)
            return true;
        if (buyer instanceof Alchemist && thing instanceof Potion)
            return true;
        if (buyer instanceof Smith && thing instanceof Weapon)
            return true;
        return false;
    }
}
