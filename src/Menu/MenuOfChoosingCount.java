package Menu;

import Characters.RPGCharacter;
import InterfacesAndThread.HaveBackpack;
import InterfacesAndThread.Utils;
import Things.Thing;

public class MenuOfChoosingCount extends RPGMenu {
    private int choosingCount;

    public MenuOfChoosingCount(RPGCharacter character, Thing thing, int indentLevel) {
        super(indentLevel);
        int count = ((HaveBackpack) character).getCountOfThing(thing);
        choosingCount = 0;
        menuItems.add(new StringBuilder("Введите количество товара для покупки (1-" + count + ")"));
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
