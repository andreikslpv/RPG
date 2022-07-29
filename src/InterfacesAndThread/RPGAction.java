package InterfacesAndThread;

import Characters.Humans.Hero;
import Characters.Humans.Human;
import Characters.RPGCharacter;
import Things.RPGThing;

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
        // в случае победы герой получает выйгрыш
        if (nasty.getCurrentHealth() <= 0) {
            hero.takeWin(nasty);
        }
        if (hero.getCurrentHealth() <= 0) {
            System.out.println(Utils.INDENT_3_LEVEL + nasty.getName() + " победил Вашего героя. Игра окончена!");
            return true;
        }
        return false;
    }

    default void startTrade(Human trader, Human buyer, RPGThing thing, int countForSales) {
        if (thing != null && trader.containsThing(thing) && countForSales != 0) {
            int sum = countForSales * thing.getPrice();
            if (buyer.changeGold(-sum)) {
                trader.removeItFromBackpack(thing, countForSales);
                trader.changeGold(sum);
                buyer.putItInBackpack(thing, countForSales);
                if (buyer instanceof Hero)
                    ((Hero) buyer).changeExperience(sum / 10);
                System.out.println(Utils.INDENT_3_LEVEL + buyer.getName() + " успешно купил " + thing.getName() + " (" + countForSales + " шт.)");
            } else
                System.out.println(Utils.INDENT_3_LEVEL + "У " + buyer.getName() + " Недостаточно средств для покупки: требуется " + sum
                        + ", есть " + buyer.getGold());

        }
    }

    default void startTheft(Human robbed, Human thief, RPGThing thing) {
        if (thing != null && robbed.containsThing(thing)) {
            int count = robbed.getCountOfThing(thing);
            robbed.removeItFromBackpack(thing, count);
            thief.putItInBackpack(thing, count);
            if (thief instanceof Hero)
                ((Hero) thief).changeExperience(thing.getPrice() * count / 10);
            System.out.println(Utils.INDENT_3_LEVEL + "Вы успешно украли " + thing.getName() + " (" + count + " шт.)");
        }
    }

}
