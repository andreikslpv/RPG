package Things.Weapons;

import Things.RPGThing;

public abstract class Weapon extends RPGThing {
    protected int powerEffect;
    protected int dexterityEffect;

    @Override
    public String toString() {
        return "Оружие " + name + " (стоимость: " + price + ", бонус к силе: +" + powerEffect + ", штраф к ловкости: " + dexterityEffect + ")";
    }

    public int getPowerEffect() {
        return powerEffect;
    }

    public int getDexterityEffect() {
        return dexterityEffect;
    }
}
