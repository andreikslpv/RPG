package Characters.NPC;

import InterfacesAndThread.Utils;
import Things.Weapon.WeaponKatana;
import Things.Weapon.WeaponTanto;

public class Smith extends Trader {
    public Smith(int globalLevel) {
        super(globalLevel);
        name = "SMITH";
    }

    @Override
    public void setLevel(int newLevel) {
        super.setLevel(newLevel);
        power = level * 6 + 10;
        dexterity = level * 6 + 10;
        putItInBackpack(new WeaponTanto(), Utils.getRandom(1));
        putItInBackpack(new WeaponKatana(), Utils.getRandom(1));
    }
}
