package Characters.NPC;

public class Skeleton extends NonPlayerCharacter {
    public Skeleton(int globalLevel) {
        super(globalLevel);
        name = "SKELETON";
    }

    @Override
    public void setLevel(int newLevel) {
        super.setLevel(newLevel);
        power = level * 5 + 10;
        dexterity = level * 3 + 10;
    }
}
