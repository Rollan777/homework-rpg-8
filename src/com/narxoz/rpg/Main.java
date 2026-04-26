package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.floor.BossFloor;
import com.narxoz.rpg.floor.CombatFloor;
import com.narxoz.rpg.floor.PoisonTrapFloor;
import com.narxoz.rpg.floor.RestFloor;
import com.narxoz.rpg.floor.TowerFloor;
import com.narxoz.rpg.state.PoisonedState;
import com.narxoz.rpg.state.StunnedState;
import com.narxoz.rpg.tower.TowerRunner;
import com.narxoz.rpg.tower.TowerRunResult;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Hero arman = new Hero("Arman", 35, 9, 2);
        Hero dana = new Hero("Dana", 30, 7, 3);

        arman.setState(new PoisonedState(2));
        dana.setState(new StunnedState(1));

        List<Hero> party = new ArrayList<>();
        party.add(arman);
        party.add(dana);

        List<TowerFloor> floors = new ArrayList<>();
        floors.add(new PoisonTrapFloor());
        floors.add(new CombatFloor());
        floors.add(new RestFloor());
        floors.add(new BossFloor());

        TowerRunner runner = new TowerRunner(floors);
        TowerRunResult result = runner.run(party);

        System.out.println("\n=== TOWER RUN RESULT ===");
        System.out.println("Floors cleared: " + result.getFloorsCleared());
        System.out.println("Heroes surviving: " + result.getHeroesSurviving());
        System.out.println("Reached top: " + result.isReachedTop());
    }
}