package Menu;

import InterfacesAndThread.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class RPGMenu implements Serializable, Utils {
    protected boolean isExitFromMenu;
    protected List<StringBuilder> menuItems;
    protected StringBuilder text;
    protected int countOfMenuItems;
    protected int indentLevel;

    public RPGMenu(int indentLevel) {
        menuItems = new ArrayList<>();
        this.indentLevel = indentLevel;
    }

    abstract public void printMenu();

    protected StringBuilder formatTheMenuText(int indentLevel, List<StringBuilder> menuItems) {
        String indent = "\n";
        StringBuilder text = new StringBuilder();
        switch (indentLevel) {
            case 1 -> indent += INDENT_1_LEVEL;
            case 2 -> indent += INDENT_2_LEVEL;
            case 3 -> indent += INDENT_3_LEVEL;
            case 4 -> indent += INDENT_4_LEVEL;
        }
        for (StringBuilder str : menuItems) {
            text.append(indent).append(str);
        }
        return text;
    }

    protected int getMenuItem(StringBuilder text, int countOfMenuItems) {
        Scanner scanner = new Scanner(System.in);
        int enter;
        while (true) {
            System.out.println(text);
            if (scanner.hasNextInt()) {
                enter = scanner.nextInt();
                if (enter >= 0 && enter <= countOfMenuItems)
                    return enter;
            } else
                scanner.next();
        }
    }
}
