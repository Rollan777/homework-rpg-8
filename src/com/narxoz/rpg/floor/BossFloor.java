package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.Monster;

import java.util.List;

public class BossFloor extends TowerFloor {

    private Monster boss;

    @Override
    protected String getFloorName() {
        return "Dragon Boss";
    }

    @Override
    protected void announce() {
        System.out.println("\n=== FINAL BOSS ===");
    }

    @Override
    protected void setup(List<Hero> party) {
        boss = new Monster("Dragon", 40, 10);
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        System.out.println("CHALLENGE: Boss fight!");

        while (boss.isAlive()) {
            for (Hero hero : party) {
                if (!hero.isAlive()) continue;

                hero.onTurnStart();

                if (hero.canAct()) {
                    int dmg = hero.calculateAttackDamage();
                    boss.takeDamage(dmg);
                    System.out.println(hero.getName() + " hits boss for " + dmg);
                }

                hero.onTurnEnd();

                if (!boss.isAlive()) break;
            }

            if (boss.isAlive()) {
                boss.attack(party.get(0));
            }
        }

        return new FloorResult(true, 0, "Boss defeated");
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {
        System.out.println("LOOT: Legendary reward!");
    }
}