package Menu;

import Characters.Hero;
import Characters.NPC.NonPlayerCharacter;
import InterfacesAndThread.RPGAction;
import InterfacesAndThread.Utils;

public class MenuDarkForest extends RPGMenu implements RPGAction {

    private final NonPlayerCharacter monster;
    private final Hero hero;
    private boolean isGameOver = false;


    public MenuDarkForest(Hero hero, NonPlayerCharacter monster, int indentLevel) {
        super(indentLevel);
        this.hero = hero;
        this.monster = monster;
        menuItems.add(new StringBuilder("Вы находитесь в Тёмном лесу и встретили монстра " + monster.getName() + ". Что вы хотите сделать?"));
        menuItems.add(new StringBuilder("1. Посмотреть информацию о монстре"));
        menuItems.add(new StringBuilder("2. Начать битву с монстром"));
        menuItems.add(new StringBuilder("0. Сбежать в Город (при этом Вы потеряете все вещи из рюкзака)"));
        text = Utils.formatTheMenuText(indentLevel, menuItems);
        countOfMenuItems = 2;
    }

    @Override
    public void printMenu() {
        isExitFromMenu = false;
        while (!isExitFromMenu) {
            switch (Utils.getMenuItem(text, countOfMenuItems)) {
                case 1 -> System.out.println(monster);
                case 2 -> {
                    isGameOver = startBattle(hero, monster);
                    isExitFromMenu = true;
                }
                case 0 -> {
                    hero.eraseBackpack();
                    isExitFromMenu = true;
                }
            }
        }
    }

    public boolean gameOver(){
        return isGameOver;
    }
}
