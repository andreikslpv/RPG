package Things.Potions;

import Things.RPGThing;

abstract public class Potion extends RPGThing {
    protected int effect;

    public int getEffect() {
        return effect;
    }

    @Override
    public String toString() {
        return "Зелье " + name + " (стоимость: " + price + ", эффект: +" + effect + " hp)";
    }
}
