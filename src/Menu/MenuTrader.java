package Menu;

import Characters.Hero;
import Characters.NPC.Trader;
import InterfacesAndThread.RPGAction;
import InterfacesAndThread.Utils;
import Things.Thing;

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
        menuItems.add(new StringBuilder("0. Вернуться в Город"));
        text = Utils.formatTheMenuText(indentLevel, menuItems);
        countOfMenuItems = 3;
    }

    @Override
    public void printMenu() {
        isExitFromMenu = false;
        while (!isExitFromMenu) {
            switch (Utils.getMenuItem(text, countOfMenuItems)) {
                case 1 -> System.out.println(trader);
                case 2 -> {
                    MenuOfChoosingThing menuThing = new MenuOfChoosingThing(trader, indentLevel + 1);
                    menuThing.printMenu();
                    Thing choosingThing = menuThing.getChoosingThing();
                    if (choosingThing != null) {
                        MenuOfChoosingCount menuCount = new MenuOfChoosingCount(trader, choosingThing, indentLevel + 1);
                        menuCount.printMenu();
                        startTrade(trader, hero, choosingThing, menuCount.getChoosingCount());
                    }
                }
                case 3 -> {
                    MenuOfChoosingThing menuThing = new MenuOfChoosingThing(trader, indentLevel + 1);
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
                case 0 -> isExitFromMenu = true;
            }
        }
    }

    public boolean gameOver(){
        return isGameOver;
    }
}
