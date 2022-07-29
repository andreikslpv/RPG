package Characters.Humans;

import InterfacesAndThread.Utils;
import Things.Potions.PotionBig;
import Things.Potions.PotionMedium;
import Things.Potions.PotionSmall;

public class Alchemist extends Trader {
    public Alchemist(String name, int globalLevel) {
        super(name, globalLevel);
    }

    @Override
    public void setLevel(int newLevel) {
        super.setLevel(newLevel);
        power = level * 3 + 10;
        dexterity = level * 3 + 10;
        putItInBackpack(new PotionBig(), Utils.getRandom(5));
        putItInBackpack(new PotionMedium(), Utils.getRandom(5));
        putItInBackpack(new PotionSmall(), Utils.getRandom(5));
    }
}
