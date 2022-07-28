package Menu;

import Characters.RPGCharacter;
import InterfacesAndThread.HaveBackpack;
import InterfacesAndThread.Utils;
import Things.Thing;

public class MenuOfChoosingThing extends RPGMenu {
    private Thing choosingThing = null;
    private final Thing[] products;

    public MenuOfChoosingThing(RPGCharacter character, int indentLevel) {
        super(indentLevel);
        menuItems.add(new StringBuilder("Список вещей " + character.getName() + ". Выберите вещь:"));
        products = ((HaveBackpack) character).showBackpack(menuItems);
        menuItems.add(new StringBuilder("0. Выход"));
        text = Utils.formatTheMenuText(indentLevel, menuItems);
        if (products != null)
            countOfMenuItems = menuItems.size() - 2;
        else
            countOfMenuItems = 0;
    }

    @Override
    public void printMenu() {
        int enter = Utils.getMenuItem(text, countOfMenuItems);
        if (enter != 0)
            choosingThing = products[enter];
    }

    public Thing getChoosingThing() {
        return choosingThing;
    }
}
