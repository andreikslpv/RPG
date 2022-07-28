import InterfacesAndThread.Utils;
import Menu.MenuTown;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main implements Utils {

    public static void main(String[] args) {
        MenuTown game;
        List<StringBuilder> menuItems = new ArrayList<>();
        menuItems.add(new StringBuilder("Стартовое меню"));
        menuItems.add(new StringBuilder("1. Новая игра"));
        menuItems.add(new StringBuilder("2. Загрузить игру"));
        menuItems.add(new StringBuilder("0. Выход"));
        while (true) {
            game = null;
            switch (Utils.getMenuItem(Utils.formatTheMenuText(0, menuItems), menuItems.size() - 1)) {
                case 1:
                    System.out.println("Введите имя Вашего Героя");
                    Scanner scanner = new Scanner(System.in);
                    if (scanner.hasNext()) {
                        game = new MenuTown(scanner.next().toUpperCase(Locale.ROOT), 1);
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