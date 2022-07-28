package Menu;

import Characters.Hero;
import Characters.NPC.*;
import InterfacesAndThread.Utils;
import Things.Potion.Potion;
import Things.Weapon.Weapon;
import Things.Thing;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MenuTown extends RPGMenu {

    private int globalLevel;
    private final Hero hero;
    private final List<Trader> traders;

    public MenuTown(String nameOfTheHero, int indentLevel) {
        super(indentLevel);
        hero = new Hero(nameOfTheHero);
        globalLevel = hero.getLevel();
        traders = new ArrayList<>();
        traders.add(new Alchemist(globalLevel));
        traders.add(new Smith(globalLevel));
        menuItems.add(new StringBuilder("Вы находитесь в Городе. Что вы хотите сделать?"));
        menuItems.add(new StringBuilder("1. Посмотреть информацию о герое " + hero.getName()));
        menuItems.add(new StringBuilder("2. Посмотреть вещи героя"));
        menuItems.add(new StringBuilder("3. Пойти в Тёмный лес"));
        int i = 4;
        for (Trader trader : traders) {
            if (trader != null) {
                menuItems.add(new StringBuilder(i + ". Пойти к торговцу " + trader.getName()));
                i++;
            }
        }
        menuItems.add(new StringBuilder(i + ". Сохранить игру"));
        menuItems.add(new StringBuilder("0. Завершить игру и вернуться в стартовое меню"));
        text = Utils.formatTheMenuText(indentLevel, menuItems);
        countOfMenuItems = i;
    }

    @Override
    public void printMenu() {
        int enter;
        isExitFromMenu = false;
        while (!isExitFromMenu) {
            switch (enter = Utils.getMenuItem(text, countOfMenuItems)) {
                case 1 -> System.out.println(hero);
                case 2 -> {
                    MenuOfChoosingThing menu = new MenuOfChoosingThing(hero, indentLevel + 1);
                    menu.printMenu();
                    Thing thing = menu.getChoosingThing();
                    if (thing instanceof Potion)
                        hero.drinkPotion((Potion) thing);
                    if (thing instanceof Weapon && hero.isSwordTaken())
                        hero.removeTheSword((Weapon) thing);
                    if (thing instanceof Weapon && !hero.isSwordTaken())
                        hero.takeTheSword((Weapon) thing);
                }
                case 3 -> {
                    MenuDarkForest menuDarkForest = new MenuDarkForest(hero, generateMonster(), indentLevel + 1);
                    menuDarkForest.printMenu();
                    if (menuDarkForest.gameOver())
                        isExitFromMenu = true;
                    else
                        levelUp();
                }
                case 0 -> isExitFromMenu = true;
                default -> {
                    if (enter == countOfMenuItems) {
                        saveGameToTheFile();
                    } else {
                        enter -= 4;
                        if (enter >= 0 && enter < traders.size()) {
                            if (traders.get(enter).getCurrentHealth() > 0) {
                                MenuTrader menuTrader = new MenuTrader(hero, traders.get(enter), indentLevel + 1);
                                menuTrader.printMenu();
                                if (menuTrader.gameOver())
                                    isExitFromMenu = true;
                                else
                                    levelUp();
                            } else
                                System.out.println(INDENT_2_LEVEL + "Торговец " + traders.get(enter).getName() + " мёртв");
                        }
                    }
                }
            }
        }
    }

    private NonPlayerCharacter generateMonster() {
        if (Math.random() > 0.5)
            return new Goblin(globalLevel);
        else
            return new Skeleton(globalLevel);
    }

    private void saveGameToTheFile() {
        try (FileOutputStream fos = new FileOutputStream("RPG.save");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this);
            oos.flush();
            System.out.println(Utils.INDENT_2_LEVEL + "Игра успешно сохранена");
        } catch (IOException e) {
            System.out.println(Utils.INDENT_2_LEVEL + "Не удалось сохранить игру");
        }
    }

    private void levelUp() {
        // если вырос уровень героя, то повышаем глобальный уровень и уровень всех торговцев
        if (hero.getLevel() > globalLevel) {
            globalLevel = hero.getLevel();
            for (Trader trader : traders) {
                trader.setLevel(globalLevel);
            }
        }
    }
}