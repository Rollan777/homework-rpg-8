package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.state.PoisonedState;

import java.util.List;

public class PoisonTrapFloor extends TowerFloor {

    @Override
    protected String getFloorName() {
        return "Poison Trap";
    }

    @Override
    protected void setup(List<Hero> party) {
        System.out.println("SETUP: Poison gas fills the room.");
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        System.out.println("CHALLENGE: Everyone gets poisoned.");

        for (Hero hero : party) {
            hero.setState(new PoisonedState(2));
            hero.takeDamage(2);
        }

        return new FloorResult(true, 2, "Poison trap survived");
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {
        System.out.println("LOOT: small heal");
        for (Hero hero : party) {
            hero.heal(2);
        }
    }
}