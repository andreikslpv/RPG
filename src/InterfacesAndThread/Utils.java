package InterfacesAndThread;

import java.util.List;
import java.util.Scanner;

public interface Utils {
    String INDENT_1_LEVEL = "  ";
    String INDENT_2_LEVEL = "    ";
    String INDENT_3_LEVEL = "      ";
    long DELAY = 500;

    static StringBuilder formatTheMenuText(int indentLevel, List<StringBuilder> menuItems) {
        String indent = "\n";
        StringBuilder text = new StringBuilder();
        switch (indentLevel) {
            case 1 -> indent += INDENT_1_LEVEL;
            case 2 -> indent += INDENT_2_LEVEL;
            case 3 -> indent += INDENT_3_LEVEL;
        }
        for (StringBuilder str : menuItems) {
            text.append(indent).append(str);
        }
        return text;
    }

    static int getMenuItem(StringBuilder text, int countOfMenuItems) {
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

    static int getRandom(int bound) {
        return (int) (Math.random() * (bound + 1));
    }
}