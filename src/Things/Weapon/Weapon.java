package Things.Weapon;

import Things.Thing;

public abstract class Weapon extends Thing {
    protected int powerEffect;
    protected int dexterityEffect;

    @Override
    public String toString() {
        return "Меч " + name + " (стоимость: " + price + ", бонус к силе: +" + powerEffect + ", штраф к ловкости: " + dexterityEffect + ")";
    }

    public int getPowerEffect() {
        return powerEffect;
    }

    public int getDexterityEffect() {
        return dexterityEffect;
    }
}
