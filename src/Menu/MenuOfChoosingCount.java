package Menu;

import Characters.Humans.Human;
import Things.RPGThing;

public class MenuOfChoosingCount extends RPGMenu {
    private int choosingCount;

    public MenuOfChoosingCount(Human character, RPGThing thing, int indentLevel, String purpose) {
        super(indentLevel);
        int count = character.getCountOfThing(thing);
        choosingCount = 0;
        menuItems.add(new StringBuilder("Введите количество товара для " + purpose + " (1-" + count + ")"));
        menuItems.add(new StringBuilder("0. Выход"));
        text = formatTheMenuText(indentLevel, menuItems);
        countOfMenuItems = count;
    }

    @Override
    public void printMenu() {
        int enter = getMenuItem(text, countOfMenuItems);
        if (enter != 0)
            choosingCount = enter;
    }

    public int getChoosingCount() {
        return choosingCount;
    }
}
