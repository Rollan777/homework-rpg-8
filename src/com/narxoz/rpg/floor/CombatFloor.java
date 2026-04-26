package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.Monster;

import java.util.List;

public class CombatFloor extends TowerFloor {

    private Monster monster;

    @Override
    protected String getFloorName() {
        return "Skeleton Crypt";
    }

    @Override
    protected void setup(List<Hero> party) {
        System.out.println("SETUP: A skeleton appears.");
        monster = new Monster("Skeleton", 20, 6);
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        System.out.println("CHALLENGE: Fight!");

        while (monster.isAlive()) {
            for (Hero hero : party) {
                if (!hero.isAlive()) continue;

                hero.onTurnStart();

                if (hero.canAct()) {
                    int dmg = hero.calculateAttackDamage();
                    monster.takeDamage(dmg);
                    System.out.println(hero.getName() + " hits for " + dmg);
                } else {
                    System.out.println(hero.getName() + " can't act");
                }

                hero.onTurnEnd();

                if (!monster.isAlive()) break;
            }

            if (monster.isAlive()) {
                Hero target = party.get(0);
                monster.attack(target);
            }
        }

        return new FloorResult(true, 0, "Monster defeated");
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {
        System.out.println("LOOT: +3 HP");
        for (Hero hero : party) {
            hero.heal(3);
        }
    }
}