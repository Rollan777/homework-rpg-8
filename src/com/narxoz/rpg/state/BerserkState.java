package com.narxoz.rpg.state;

import com.narxoz.rpg.combatant.Hero;

public class BerserkState implements HeroState {

    @Override
    public String getName() {
        return "Berserk";
    }

    @Override
    public int modifyOutgoingDamage(int basePower) {
        return basePower + 5;
    }

    @Override
    public int modifyIncomingDamage(int rawDamage) {
        return rawDamage + 3;
    }

    @Override
    public void onTurnStart(Hero hero) {
        System.out.println(hero.getName() + " burns with berserk rage!");
    }

    @Override
    public void onTurnEnd(Hero hero) {
    }

    @Override
    public boolean canAct() {
        return true;
    }
}