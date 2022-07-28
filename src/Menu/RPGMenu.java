package Menu;

import InterfacesAndThread.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
}
