package Characters.NPC;

import Characters.RPGCharacter;
import InterfacesAndThread.Utils;

import java.util.HashMap;

abstract public class NonPlayerCharacter extends RPGCharacter {
    public NonPlayerCharacter(int globalLevel) {
        backpack = new HashMap<>();
        setLevel(globalLevel);
        gold = (int) (Math.random() * 100) + (level * 10);
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

    /*@Override
    public String toString() {
        return "\n" + INDENT_2_LEVEL + name
                + ":\n" + INDENT_2_LEVEL + "Уровень: " + level
                + "\n" + INDENT_2_LEVEL + "Здоровье: " + currentHealth + "/" + maxHealth + ", сила: " + power + ", ловкость: " + dexterity
                + "\n" + INDENT_2_LEVEL + "Золото: " + gold;
    }*/
}
