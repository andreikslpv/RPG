package InterfacesAndThread;

import Characters.Hero;
import Characters.NPC.NonPlayerCharacter;
import Characters.NPC.Trader;
import Characters.RPGCharacter;
import Things.Thing;

public interface RPGAction {

    // Возвращает true если герой умер и игра закончена
    default boolean startBattle(Hero hero, NonPlayerCharacter nasty) {
        Thread battle = new Thread(new Battle(hero, nasty));
        battle.start();
        try {
            battle.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        // в случае победы герой получает золото побежденного и опыт в размере максимального здоровья побежденного
        if (nasty.getCurrentHealth() <= 0) {
            System.out.println(Utils.INDENT_3_LEVEL + hero.getName() + " победил!");
            hero.changeGold(nasty.getGold());
            hero.changeExperience(nasty.getMaxHealth());
        }
        if (hero.getCurrentHealth() <= 0) {
            System.out.println(Utils.INDENT_3_LEVEL + nasty.getName() + " победил Вашего героя. Игра окончена!");
            return true;
        }
        return false;
    }

    default void startTrade(Trader trader, RPGCharacter buyer, Thing thing, int countForSales) {
        if (thing != null && trader.containsThing(thing)) {
            int sum;
            if (countForSales == 0) {
                System.out.println(Utils.INDENT_3_LEVEL + "Вы отказались от покупки " + thing.getName());
            } else {
                sum = countForSales * thing.getPrice();
                if (buyer.changeGold(-sum)) {
                    trader.removeItFromBackpack(thing, countForSales);
                    trader.changeGold(sum);
                    ((HaveBackpack) buyer).putItInBackpack(thing, countForSales);
                    System.out.println(Utils.INDENT_3_LEVEL + "Вы успешно купили " + thing.getName() + " (" + countForSales + " шт.)");
                } else
                    System.out.println(Utils.INDENT_3_LEVEL + "Недостаточно средств для покупки: требуется " + sum + ", есть " + buyer.getGold());
            }
        }
    }

    default void startTheft(Trader robbed, RPGCharacter thief, Thing thing) {
        if (thing != null && robbed.containsThing(thing)) {
            int count = robbed.getCountOfThing(thing);
            robbed.removeItFromBackpack(thing, count);
            ((HaveBackpack) thief).putItInBackpack(thing, count);
            System.out.println(Utils.INDENT_3_LEVEL + "Вы успешно украли " + thing.getName() + " (" + count + " шт.)");
        }
    }

}
