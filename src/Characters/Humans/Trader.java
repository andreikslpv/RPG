package Characters.Humans;

import InterfacesAndThread.Utils;

public abstract class Trader extends Human {

    public Trader(String name, int globalLevel) {
        super(name, globalLevel);
    }

    @Override
    public void setLevel(int newLevel) {
        if (newLevel >= 1) {
            this.level = newLevel;
            currentHealth = maxHealth = level * 5 + 20;
            gold = Utils.getRandom(100) + (level * 10);
        }
    }
}
