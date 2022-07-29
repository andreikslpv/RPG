package Characters.Moncters;

import Characters.RPGCharacter;
import InterfacesAndThread.Utils;

abstract public class Monster extends RPGCharacter {

    public Monster(int globalLevel) {
        setLevel(globalLevel);
        gold = Utils.getRandom(100) + (level * 10);
    }

    @Override
    public void setLevel(int newLevel) {
        if (newLevel >= 1) {
            this.level = newLevel;
            if (newLevel > 2)
                this.level = newLevel + Utils.getRandom(4) - 2;
            currentHealth = maxHealth = level * 5 + 20;
        }
    }
}
