package Characters.NPC;

import InterfacesAndThread.HaveBackpack;

import java.util.HashMap;

public abstract class Trader extends NonPlayerCharacter implements HaveBackpack {

    public Trader(int globalLevel) {
        super(globalLevel);

    }
}
