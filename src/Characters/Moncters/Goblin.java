package Characters.Moncters;

import Things.Weapons.Cudgel;

public class Goblin extends Monster {
    public Goblin(int globalLevel) {
        super(globalLevel);
        name = "GOBLIN";
        if (Math.random() > 0.8)
            weapon = new Cudgel();
    }

    @Override
    public void setLevel(int newLevel) {
        super.setLevel(newLevel);
        power = level * 3 + 10;
        dexterity = level * 5 + 10;
    }
}
