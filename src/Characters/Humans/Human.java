package Characters.Humans;

import Characters.RPGCharacter;
import InterfacesAndThread.HaveBackpack;
import Things.Thing;

import java.util.HashMap;
import java.util.Map;

abstract public class Human extends RPGCharacter implements HaveBackpack {
    protected Map<Thing, Integer> backpack;

    public Human(String name, int level) {
        this.name = name;
        backpack = new HashMap<>();
        setLevel(level);
    }

    public Map<Thing, Integer> getBackpack() {
        return backpack;
    }
}
