package Menu;

public class MenuOfContinue extends RPGMenu {
    private boolean leaveLocation = false;

    public MenuOfContinue(int indentLevel, String location) {
        super(indentLevel);
        menuItems.add(new StringBuilder("Хотите остаться в " + location + " или вернуться в Город?"));
        menuItems.add(new StringBuilder("1. Остаться"));
        menuItems.add(new StringBuilder("0. Вернуться в Город"));
        text = formatTheMenuText(indentLevel, menuItems);
        countOfMenuItems = 1;
    }

    @Override
    public void printMenu() {
        int enter = getMenuItem(text, countOfMenuItems);
        leaveLocation = enter == 0;
    }

    public boolean isLeaveLocation() {
        return leaveLocation;
    }
}
