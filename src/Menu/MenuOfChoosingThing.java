package Menu;

import Characters.Humans.Human;
import Things.RPGThing;

public class MenuOfChoosingThing extends RPGMenu {
    private RPGThing choosingThing = null;
    private final RPGThing[] products;

    public MenuOfChoosingThing(Human character, int indentLevel, String purpose) {
        super(indentLevel);
        menuItems.add(new StringBuilder("Список вещей " + character.getName() + ". Выберите вещь для " + purpose));
        products = character.showBackpack(menuItems);
        menuItems.add(new StringBuilder("0. Выход"));
        text = formatTheMenuText(indentLevel, menuItems);
        if (products != null)
            countOfMenuItems = menuItems.size() - 2;
        else
            countOfMenuItems = 0;
    }

    @Override
    public void printMenu() {
        int enter = getMenuItem(text, countOfMenuItems);
        if (enter != 0)
            choosingThing = products[enter];
    }

    public RPGThing getChoosingThing() {
        return choosingThing;
    }
}
