package Characters;

import InterfacesAndThread.Utils;
import Things.Thing;
import java.io.Serializable;
import java.util.Map;

abstract public class RPGCharacter implements Serializable, Utils {
    protected String name;
    protected int maxHealth;
    protected int currentHealth;
    protected int power;
    protected int powerBonus;
    protected int dexterity;
    protected int dexterityFine;
    protected int level;
    protected int gold;
    protected Map<Thing, Integer> backpack;

    abstract public void setLevel(int newLevel);

    public String getName() {
        return name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getPower() {
        return power + powerBonus;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getLevel() {
        return level;
    }

    public int getGold() {
        return gold;
    }

    public double getChance() {
        return (dexterity + dexterityFine) / 200d + Math.random();
    }

    public void changeCurrentHealth(int change) {
        currentHealth += change;
        if (currentHealth <= 0)
            System.out.println(Utils.INDENT_3_LEVEL + name + " погиб!");
        if (currentHealth > maxHealth)
            currentHealth = maxHealth;
    }

    public boolean changeGold(int change) {
        if ((gold + change) >= 0) {
            gold += change;
            return true;
        } else
            return false;
    }

    public Map<Thing, Integer> getBackpack() {
        return backpack;
    }

    public void enableSwordEffect (int powerBonus, int dexterityFine) {
        this.powerBonus = powerBonus;
        this.dexterityFine = dexterityFine;
    }

    public boolean isSwordTaken() {
        return powerBonus != 0 && dexterityFine != 0;
    }

    public StringBuilder getSummaryLine1() {
        return new StringBuilder("\n" + INDENT_2_LEVEL + name + ":\n" + INDENT_2_LEVEL + "Уровень: " + level);
    }

    public StringBuilder getSummaryLine2() {
        return new StringBuilder("\n" + INDENT_2_LEVEL + "Здоровье: " + currentHealth + "/" + maxHealth + ", сила: " + power + ", ловкость: " + dexterity
                + "\n" + INDENT_2_LEVEL + "Золото: " + gold);
    }

    @Override
    public String toString() {
        return getSummaryLine1().append(getSummaryLine2()).toString();
    }
}