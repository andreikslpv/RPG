package Things.Potions;

import Things.Thing;

abstract public class Potion extends Thing {
    protected int effect;

    public int getEffect() {
        return effect;
    }

    @Override
    public String toString() {
        return "Зелье " + name + " (стоимость: " + price + ", эффект: +" + effect + " hp)";
    }
}
