package InterfacesAndThread;

import Characters.Humans.Hero;
import Characters.Humans.Human;
import Characters.Humans.Trader;
import Characters.RPGCharacter;
import Things.Thing;

public interface RPGAction {

    // Возвращает true если герой умер и игра закончена
    default boolean startBattle(Hero hero, RPGCharacter nasty) {
        Thread battle = new Thread(new Battle(hero, nasty));
        battle.start();
        try {
            battle.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        // в случае победы герой получает ...
        if (nasty.getCurrentHealth() <= 0) {
            System.out.println(Utils.INDENT_3_LEVEL + hero.getName() + " победил!");
            // ... золото побежденного
            hero.changeGold(nasty.getGold());
            // ... опыт в размере максимального здоровья побежденного
            hero.changeExperience(nasty.getMaxHealth());
            // ... вещи из рюкзака побежденного
            if (nasty instanceof HaveBackpack) {
                for (Thing currentThing : ((Human) nasty).getBackpack().keySet()) {
                    hero.putItInBackpack(currentThing, ((Human) nasty).getCountOfThing(currentThing));
                }
                ((Human) nasty).eraseBackpack();
            }
            // ... оружие побежденного которое было у него в руках
            if (nasty.isWeaponTaken()) {
                hero.putItInBackpack(nasty.getWeapon(), 1);
            }
        }
        if (hero.getCurrentHealth() <= 0) {
            System.out.println(Utils.INDENT_3_LEVEL + nasty.getName() + " победил Вашего героя. Игра окончена!");
            return true;
        }
        return false;
    }

    default void startTrade(HaveBackpack trader, HaveBackpack buyer, Thing thing, int countForSales) {
        if (thing != null && trader.containsThing(thing) && countForSales != 0) {
            int sum = countForSales * thing.getPrice();
            if (((Human) buyer).changeGold(-sum)) {
                trader.removeItFromBackpack(thing, countForSales);
                ((Human) trader).changeGold(sum);
                buyer.putItInBackpack(thing, countForSales);
                if (buyer instanceof Hero)
                    ((Hero) buyer).changeExperience(sum / 10);
                System.out.println(Utils.INDENT_3_LEVEL + ((Human) buyer).getName() + " успешно купил " + thing.getName() + " (" + countForSales + " шт.)");
            } else
                System.out.println(Utils.INDENT_3_LEVEL + "У " + ((Human) buyer).getName() +" Недостаточно средств для покупки: требуется " + sum
                        + ", есть " + ((Human) buyer).getGold());

        }
    }

    default void startTheft(Trader robbed, RPGCharacter thief, Thing thing) {
        if (thing != null && robbed.containsThing(thing)) {
            int count = robbed.getCountOfThing(thing);
            robbed.removeItFromBackpack(thing, count);
            ((HaveBackpack) thief).putItInBackpack(thing, count);
            if (thief instanceof Hero)
                ((Hero) thief).changeExperience(thing.getPrice() * count / 10);
            System.out.println(Utils.INDENT_3_LEVEL + "Вы успешно украли " + thing.getName() + " (" + count + " шт.)");
        }
    }

}
