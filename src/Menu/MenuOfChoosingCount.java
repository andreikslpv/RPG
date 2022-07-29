package Menu;

import InterfacesAndThread.HaveBackpack;
import InterfacesAndThread.Utils;
import Things.Thing;

public class MenuOfChoosingCount extends RPGMenu {
    private int choosingCount;

    public MenuOfChoosingCount(HaveBackpack character, Thing thing, int indentLevel, String purpose) {
        super(indentLevel);
        int count = character.getCountOfThing(thing);
        choosingCount = 0;
        menuItems.add(new StringBuilder("Введите количество товара для " + purpose + " (1-" + count + ")"));
        menuItems.add(new StringBuilder("0. Выход"));
        text = Utils.formatTheMenuText(indentLevel, menuItems);
        countOfMenuItems = count;
    }

    @Override
    public void printMenu() {
        int enter = Utils.getMenuItem(text, countOfMenuItems);
        if (enter != 0)
            choosingCount = enter;
    }

    public int getChoosingCount() {
        return choosingCount;
    }
}
