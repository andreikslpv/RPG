package Characters.NPC;

public class Goblin extends NonPlayerCharacter {
    public Goblin(int globalLevel) {
        super(globalLevel);
        name = "GOBLIN";
    }

    @Override
    public void setLevel(int newLevel) {
        super.setLevel(newLevel);
        power = level * 3 + 10;
        dexterity = level * 5 + 10;
    }
}
