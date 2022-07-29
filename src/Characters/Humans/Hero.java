package Characters.Humans;

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
        return getSummaryLine1()
                .append(", опыт: ").append(experience).append('/').append(experienceForNextLevel)
                .append(getSummaryLine2()).toString();
    }
}