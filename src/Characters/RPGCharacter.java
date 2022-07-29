package Characters;

import InterfacesAndThread.Utils;
import Things.Weapons.Weapon;

import java.io.Serializable;

abstract public class RPGCharacter implements Serializable, Utils {
    protected String name;
    protected int maxHealth;
    protected int currentHealth;
    protected int power;
    protected int dexterity;
    protected int level;
    protected int gold;
    protected Weapon weapon;

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
        if (weapon != null)
            return power + weapon.getPowerEffect();
        else
            return power;
    }

    public int getLevel() {
        return level;
    }

    public int getGold() {
        return gold;
    }

    public double getChance() {
        if (weapon != null)
            return (dexterity + weapon.getDexterityEffect()) / 200d + Math.random();
        else
            return dexterity;
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

    public void takeTheSword(Weapon weapon) {
        if (weapon != null) {
            this.weapon = weapon;
            System.out.println(Utils.INDENT_3_LEVEL + name + " взял в руки оружие " + this.weapon.getName());
        }
    }

    public void removeTheSword(Weapon weapon) {
        if (this.weapon == weapon) {
            System.out.println(Utils.INDENT_3_LEVEL + name + " убрал оружие " + this.weapon.getName());
            this.weapon = null;
        }
    }

    public boolean isWeaponTaken(Weapon weapon) {
        return this.weapon == weapon;
    }

    public boolean isWeaponTaken() {
        return this.weapon != null;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    public StringBuilder getSummaryLine1() {
        return new StringBuilder("\n" + INDENT_2_LEVEL + name + ":\n" + INDENT_2_LEVEL + "Уровень: " + level);
    }

    public StringBuilder getSummaryLine2() {
        StringBuilder sb = new StringBuilder("\n" + INDENT_2_LEVEL + "Здоровье: " + currentHealth + "/" + maxHealth + ", сила: " + power + ", ловкость: " + dexterity
                + "\n" + INDENT_2_LEVEL + "Золото: " + gold);
        if (weapon != null)
            return sb.append("\nОружие в руках: ").append(weapon.getName());
        else
            return sb;
    }

    @Override
    public String toString() {
        return getSummaryLine1().append(getSummaryLine2()).toString();
    }
}