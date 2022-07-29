package Characters.Humans;

import Characters.RPGCharacter;
import InterfacesAndThread.Utils;
import Things.Potions.Potion;
import Things.RPGThing;
import Things.Weapons.Weapon;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

abstract public class Human extends RPGCharacter {
    protected Map<RPGThing, Integer> backpack;

    public Human(String name, int level) {
        this.name = name;
        backpack = new HashMap<>();
        setLevel(level);
    }

    public int drinkPotion(Potion potion) {
        if (removeItFromBackpack(potion, 1)) {
            try {
                Thread.sleep(Utils.DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Utils.INDENT_3_LEVEL + this.getName() + " выпил зелье " + potion.getName());
            this.changeCurrentHealth(potion.getEffect());
        }
        return this.getMaxHealth() - this.getCurrentHealth();
    }

    public void putItInBackpack(RPGThing thing, int count) {
        if (count > 0)
            if (backpack.containsKey(thing)) {
                for (RPGThing currentThing : backpack.keySet()) {
                    if (currentThing.equals(thing)) {
                        backpack.replace(currentThing, backpack.get(currentThing) + count);
                        break;
                    }
                }
            } else {
                backpack.put(thing, count);
            }
    }

    public boolean removeItFromBackpack(RPGThing thing, int count) {
        if (count > 0 && backpack.containsKey(thing)) {
            Iterator<RPGThing> iterator = backpack.keySet().iterator();
            RPGThing currentThing;
            while (iterator.hasNext()) {
                currentThing = iterator.next();
                int temp = backpack.get(currentThing);
                if (currentThing.equals(thing)) {
                    if (temp > count) {
                        backpack.replace(currentThing, temp - count);
                        return true;
                    }
                    if (temp == count) {
                        if (iterator instanceof Weapon)
                            this.removeTheSword((Weapon) iterator);
                        iterator.remove();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean containsThing(RPGThing thing) {
        return backpack.containsKey(thing);
    }

    public int getCountOfThing(RPGThing thing) {
        Integer i = backpack.get(thing);
        if (i != null)
            return i;
        else
            return 0;
    }

    public void eraseBackpack() {
        backpack.clear();
    }

    public RPGThing[] showBackpack(List<StringBuilder> menuItems) {
        RPGThing[] products = null;
        if (backpack.isEmpty()) {
            menuItems.add(new StringBuilder("У " + this.getName() + " ничего нет"));
        } else {
            int i = 1;
            products = new RPGThing[backpack.size() + 1];
            for (RPGThing currentThing : backpack.keySet()) {
                menuItems.add(new StringBuilder(i + ". " + currentThing + " - " + backpack.get(currentThing) + " шт."));
                products[i] = currentThing;
                i++;
            }
        }
        return products;
    }

    public Map<RPGThing, Integer> getBackpack() {
        return backpack;
    }
}
