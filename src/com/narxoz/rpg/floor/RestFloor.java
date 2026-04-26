package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;

import java.util.List;

public class RestFloor extends TowerFloor {

    @Override
    protected String getFloorName() {
        return "Rest Camp";
    }

    @Override
    protected void announce() {
        System.out.println("\n*** SAFE ZONE: Rest Camp ***");
    }

    @Override
    protected void setup(List<Hero> party) {
        System.out.println("SETUP: Campfire.");
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        System.out.println("CHALLENGE: Resting...");
        for (Hero hero : party) {
            hero.heal(5);
        }
        return new FloorResult(true, 0, "Rested");
    }

    @Override
    protected boolean shouldAwardLoot(FloorResult result) {
        return false;
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {
    }

    @Override
    protected void cleanup(List<Hero> party) {
        System.out.println("CLEANUP: Leaving camp.");
    }
}