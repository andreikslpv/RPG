package Characters.Moncters;

import Things.Weapons.WeaponTanto;

public class Skeleton extends Monster {
    public Skeleton(int globalLevel) {
        super(globalLevel);
        name = "SKELETON";
        if (Math.random() > 0.95)
            weapon = new WeaponTanto();
    }

    @Override
    public void setLevel(int newLevel) {
        super.setLevel(newLevel);
        power = level * 5 + 10;
        dexterity = level * 3 + 10;
    }
}
