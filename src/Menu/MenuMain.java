package Menu;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Locale;
import java.util.Scanner;

public class MenuMain extends RPGMenu{
    public MenuMain(int indentLevel) {
        super(indentLevel);
        menuItems.add(new StringBuilder("Стартовое меню"));
        menuItems.add(new StringBuilder("1. Новая игра"));
        menuItems.add(new StringBuilder("2. Загрузить игру"));
        menuItems.add(new StringBuilder("0. Выход"));
        text = formatTheMenuText(indentLevel, menuItems);
        countOfMenuItems = 2;
    }

    @Override
    public void printMenu() {
        MenuTown game;
        while (true) {
            game = null;
            switch (getMenuItem(text, countOfMenuItems)) {
                case 1:
                    System.out.println("Введите имя Вашего Героя");
                    Scanner scanner = new Scanner(System.in);
                    if (scanner.hasNext()) {
                        game = new MenuTown(scanner.next().toUpperCase(Locale.ROOT), indentLevel + 1);
                    }
                    break;
                case 2:
                    try (FileInputStream fis = new FileInputStream("RPG.save");
                         ObjectInputStream ois = new ObjectInputStream(fis)) {
                        game = (MenuTown) ois.readObject();
                        System.out.println("Игра успешно загружена");
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Не удалось загрузить файл с сохранением игры");
                    }
                    break;
                case 0:
                default:
                    System.exit(0);
            }
            if (game != null) {
                game.printMenu();
            }
        }
    }
}
