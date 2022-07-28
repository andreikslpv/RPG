package InterfacesAndThread;

import Characters.Hero;
import Characters.RPGCharacter;
import Things.Potion.PotionBig;
import Things.Potion.PotionMedium;
import Things.Potion.PotionSmall;

import java.io.Serializable;

public record Battle(RPGCharacter warrior1, RPGCharacter warrior2) implements Runnable, Serializable {

    private void kick(RPGCharacter batter, RPGCharacter battered) {
        double chance = batter.getChance();
        int kickPower = 0, damage; // в случае промаха (chance < 0.2)
        // критический удар
        if (chance > 0.8d)
            kickPower = batter.getPower() * 2;
        // обычный удар
        if (chance >= 0.2d && chance <= 0.8d)
            kickPower = batter.getPower();
        System.out.println(Utils.INDENT_3_LEVEL + batter.getName() + " (" + batter.getCurrentHealth() + " hp) наносит "
                + battered.getName() + " (" + battered.getCurrentHealth() + " hp) "
                + kickPower + " повреждений");
        battered.changeCurrentHealth(-kickPower);
        if (battered instanceof Hero && battered.getCurrentHealth() > 0) {
            damage = battered.getMaxHealth() - battered.getCurrentHealth();
            if (damage >= 20)
                damage = ((HaveBackpack) battered).drinkPotion(new PotionBig());
            if (damage >= 10)
                damage = ((HaveBackpack) battered).drinkPotion(new PotionMedium());
            if (damage > 0)
                ((HaveBackpack) battered).drinkPotion(new PotionSmall());
        }
    }

    @Override
    public void run() {
        if (warrior1 != null && warrior2 != null) {
            System.out.println(Utils.INDENT_3_LEVEL + "Начинается битва между " + warrior1.getName() + " и " + warrior2.getName() + "!");
            // определяем кто будет бить первым
            boolean isWarrior1Kick = Math.random() > 0.5;
            // начинаем обмен ударами
            while (warrior1.getCurrentHealth() > 0 && warrior2.getCurrentHealth() > 0) {
                if (isWarrior1Kick)
                    kick(warrior1, warrior2);
                else
                    kick(warrior2, warrior1);
                isWarrior1Kick = !isWarrior1Kick;
                try {
                    Thread.sleep(Utils.DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println(Utils.INDENT_3_LEVEL + "Кто-то из бойцов не явился на битву ...");
        }
    }
}
