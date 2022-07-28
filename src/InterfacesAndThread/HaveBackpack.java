package InterfacesAndThread;

import Characters.RPGCharacter;
import Things.Potion.Potion;
import Things.Weapon.Weapon;
import Things.Thing;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public interface HaveBackpack {

    default int drinkPotion(Potion potion) {
        if (removeItFromBackpack(potion, 1)) {
            try {
                Thread.sleep(Utils.DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Utils.INDENT_3_LEVEL + ((RPGCharacter) this).getName() + " выпил зелье " + potion.getName());
            ((RPGCharacter) this).changeCurrentHealth(potion.getEffect());
        }
        return ((RPGCharacter) this).getMaxHealth() - ((RPGCharacter) this).getCurrentHealth();
    }

    default void takeTheSword(Weapon weapon) {
        ((RPGCharacter) this).enableSwordEffect(weapon.getPowerEffect(), weapon.getDexterityEffect());
        System.out.println(Utils.INDENT_3_LEVEL + ((RPGCharacter) this).getName() + " одел меч " + weapon.getName());
    }

    default void removeTheSword(Weapon weapon) {
        ((RPGCharacter) this).enableSwordEffect(0, 0);
        System.out.println(Utils.INDENT_3_LEVEL + ((RPGCharacter) this).getName() + " снял меч " + weapon.getName());
    }

    default void putItInBackpack(Thing thing, int count) {
        Map<Thing, Integer> backpack = ((RPGCharacter) this).getBackpack();
        if (backpack.containsKey(thing)) {
            for (Thing currentThing : backpack.keySet()) {
                if (currentThing.equals(thing)) {
                    backpack.replace(currentThing, backpack.get(currentThing) + count);
                    break;
                }
            }
        } else {
            backpack.put(thing, count);
        }
    }

    default boolean removeItFromBackpack(Thing thing, int count) {
        Map<Thing, Integer> backpack = ((RPGCharacter) this).getBackpack();
        if (backpack.containsKey(thing)) {
            Iterator<Thing> iterator = backpack.keySet().iterator();
            Thing currentThing;
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
                            removeTheSword((Weapon) iterator);
                        iterator.remove();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    default boolean containsThing(Thing thing) {
        return ((RPGCharacter) this).getBackpack().containsKey(thing);
    }

    default int getCountOfThing(Thing thing) {
        Integer i = ((RPGCharacter) this).getBackpack().get(thing);
        if (i != null)
            return i;
        else
            return -1;
    }

    default void eraseBackpack() {
        ((RPGCharacter) this).getBackpack().clear();
    }

    default Thing[] showBackpack(List<StringBuilder> menuItems) {
        Map<Thing, Integer> backpack = ((RPGCharacter) this).getBackpack();
        Thing[] products = null;
        if (backpack.isEmpty()) {
            menuItems.add(new StringBuilder("У " + ((RPGCharacter) this).getName() + " ничего нет"));
        } else {
            int i = 1;
            products = new Thing[backpack.size() + 1];
            for (Thing currentThing : backpack.keySet()) {
                menuItems.add(new StringBuilder(i + ". " + currentThing + " - " + backpack.get(currentThing) + " шт."));
                products[i] = currentThing;
                i++;
            }
        }
        return products;
    }
}
