package Things;

import java.io.Serializable;
import java.util.Objects;

abstract public class Thing implements Serializable {
    protected String name;
    protected int price;

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Thing thing = (Thing) o;
        return price == thing.price && name.equals(thing.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
