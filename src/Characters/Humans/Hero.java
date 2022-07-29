package Characters.Humans;

import Characters.RPGCharacter;
import InterfacesAndThread.Utils;
import Things.RPGThing;

public class Hero extends Human {

    private int experience;
    private int experienceForNextLevel;

    public Hero(String name, int level) {
        super(name, level);
        gold = 100;
        experience = 0;
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

    public void changeExperience(int change) {
        experience += change;
        while (experience >= experienceForNextLevel) {
            experience -= experienceForNextLevel;
            setLevel(level + 1);
        }
    }

    @Override
    public String toString() {
        return getInfoPart1()
                .append(", опыт: ").append(experience).append('/').append(experienceForNextLevel)
                .append(getInfoPart2()).toString();
    }

    public void takeWin(RPGCharacter nasty) {
        StringBuilder congratulation = new StringBuilder(Utils.INDENT_3_LEVEL + name + " победил и получает:\n");
        // Герой получает опыт в размере максимального здоровья побежденного
        changeExperience(nasty.getMaxHealth());
        congratulation.append(Utils.INDENT_4_LEVEL).append("+ ").append(nasty.getMaxHealth()).append(" опыта;\n");
        // Герой получает золото побежденного
        changeGold(nasty.getGold());
        congratulation.append(Utils.INDENT_4_LEVEL).append("+ ").append(nasty.getGold()).append(" золота;\n");
        if (nasty instanceof Human) {
            // Герой получает вещи из рюкзака побежденного человека
            for (RPGThing currentThing : ((Human) nasty).getBackpack().keySet()) {
                putItInBackpack(currentThing, ((Human) nasty).getCountOfThing(currentThing));
                congratulation.append(Utils.INDENT_4_LEVEL).append("+ ").append(currentThing).append(";\n");
            }
            ((Human) nasty).eraseBackpack();
        } else if (nasty.isWeaponTaken()) {
            // Герой получает оружие побежденного монстра которое было у него в руках
            putItInBackpack(nasty.getWeapon(), 1);
            congratulation.append(Utils.INDENT_4_LEVEL).append("+ ").append(nasty.getWeapon()).append(";\n");
        }
        System.out.print(congratulation);
    }
}