package Characters;

import InterfacesAndThread.HaveBackpack;

import java.util.HashMap;

public class Hero extends RPGCharacter implements HaveBackpack {

    private int experience;
    private int experienceForNextLevel;

    public Hero(String name) {
        this.name = name;
        gold = 100;
        experience = 0;
        backpack = new HashMap<>();
        setLevel(1);
    }

    @Override
    public void setLevel(int newLevel) {
        level = newLevel;
        experienceForNextLevel = level * 20 + 100;
        if (level > 1)
            System.out.println(name + " достиг " + level + " уровня!");
        currentHealth = maxHealth = level * 5 + 30;
        power = level * 5 + 10;
        dexterity = level * 5 + 10;
    }

    @Override
    public StringBuilder getSummaryLine1() {
        return super.getSummaryLine1().append(", опыт: ").append(experience).append('/').append(experienceForNextLevel);
    }

    public void changeExperience(int change) {
        experience += change;
        while (experience >= experienceForNextLevel) {
            experience -= experienceForNextLevel;
            setLevel(level + 1);
        }
    }

    /*@Override
    public String toString() {
        return "\n" + INDENT_2_LEVEL + name
                + ":\n" + INDENT_2_LEVEL + "Уровень: " + level + ", опыт: " + experience + "/" + experienceForNextLevel
                + "\n" + INDENT_2_LEVEL + "Здоровье: " + currentHealth + "/" + maxHealth + ", сила: " + power + ", ловкость: " + dexterity
                + "\n" + INDENT_2_LEVEL + "Золото: " + gold;
    }*/
}