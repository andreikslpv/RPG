package Characters.Humans;

import InterfacesAndThread.Utils;
import Things.Weapons.Katana;
import Things.Weapons.Tanto;

public class Smith extends Trader {
    public Smith(String name, int globalLevel) {
        super(name, globalLevel);
    }

    @Override
    public void setLevel(int newLevel) {
        super.setLevel(newLevel);
        power = level * 6 + 10;
        dexterity = level * 6 + 10;
        putItInBackpack(new Tanto(), Utils.getRandom(1));
        putItInBackpack(new Katana(), Utils.getRandom(1));
    }
}
