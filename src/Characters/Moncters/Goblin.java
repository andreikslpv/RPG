package Characters.Moncters;

import Things.Weapons.WeaponCudgel;

public class Goblin extends Monster {
    public Goblin(int globalLevel) {
        super(globalLevel);
        name = "GOBLIN";
        if (Math.random() > 0.9)
            weapon = new WeaponCudgel();
    }

    @Override
    public void setLevel(int newLevel) {
        super.setLevel(newLevel);
        power = level * 3 + 10;
        dexterity = level * 5 + 10;
    }
}
