package Characters.NPC;

import InterfacesAndThread.Utils;
import Things.Potion.PotionBig;
import Things.Potion.PotionMedium;
import Things.Potion.PotionSmall;

public class Alchemist extends Trader {
    public Alchemist(int globalLevel) {
        super(globalLevel);
        name = "ALCHEMIST";
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
