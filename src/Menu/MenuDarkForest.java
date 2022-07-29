package Menu;

import Characters.Humans.Hero;
import Characters.Moncters.Goblin;
import Characters.Moncters.Monster;
import Characters.Moncters.Skeleton;
import InterfacesAndThread.RPGAction;

public class MenuDarkForest extends RPGMenu implements RPGAction {

    private Monster monster;
    private final Hero hero;
    private boolean gameOver;


    public MenuDarkForest(Hero hero, int indentLevel) {
        super(indentLevel);
        this.hero = hero;
        this.monster = generateMonster();
        menuItems.add(new StringBuilder("Вы находитесь в Тёмном лесу и встретили монстра " + monster.getName() + ". Что вы хотите сделать?"));
        menuItems.add(new StringBuilder("1. Посмотреть информацию о монстре"));
        menuItems.add(new StringBuilder("2. Начать битву с монстром"));
        menuItems.add(new StringBuilder("0. Сбежать в Город (при этом Вы потеряете все вещи из рюкзака)"));
        text = formatTheMenuText(indentLevel, menuItems);
        countOfMenuItems = 2;
    }

    @Override
    public void printMenu() {
        isExitFromMenu = false;
        gameOver = false;
        while (!isExitFromMenu && !gameOver) {
            switch (getMenuItem(text, countOfMenuItems)) {
                case 1 -> System.out.println(monster);
                case 2 -> {
                    gameOver = startBattle(hero, monster);
                    if (!gameOver) {
                        MenuOfContinue menuOfContinue = new MenuOfContinue(indentLevel + 1, "Темном лесу");
                        menuOfContinue.printMenu();
                        if (!(isExitFromMenu = menuOfContinue.isLeaveLocation()))
                            refreshMonster(monster.getName());
                    }
                }
                case 0 -> {
                    hero.eraseBackpack();
                    isExitFromMenu = true;
                }
            }
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    private Monster generateMonster() {
        if (Math.random() > 0.5)
            return new Goblin(hero.getLevel());
        else
            return new Skeleton(hero.getLevel());
    }

    private void refreshMonster(String nameOfTheoOldMonster) {
        monster = generateMonster();
        int start = text.indexOf(nameOfTheoOldMonster);
        text.replace(start, start + nameOfTheoOldMonster.length(), monster.getName());
    }
}
