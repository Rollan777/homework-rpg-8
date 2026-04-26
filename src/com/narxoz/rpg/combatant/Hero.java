package com.narxoz.rpg.combatant;

import com.narxoz.rpg.state.HeroState;
import com.narxoz.rpg.state.NormalState;

public class Hero {

    private final String name;
    private int hp;
    private final int maxHp;
    private final int attackPower;
    private final int defense;
    private HeroState state;

    public Hero(String name, int hp, int attackPower, int defense) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attackPower = attackPower;
        this.defense = defense;
        this.state = new NormalState();
    }

    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getAttackPower() { return attackPower; }
    public int getDefense() { return defense; }
    public boolean isAlive() { return hp > 0; }

    public HeroState getState() {
        return state;
    }

    public void setState(HeroState state) {
        System.out.println(name + " state changed to " + state.getName());
        this.state = state;
    }

    public void onTurnStart() {
        state.onTurnStart(this);
    }

    public void onTurnEnd() {
        state.onTurnEnd(this);
    }

    public boolean canAct() {
        return state.canAct();
    }

    public int calculateAttackDamage() {
        return Math.max(1, state.modifyOutgoingDamage(attackPower));
    }

    public void takeDamage(int amount) {
        int rawDamage = Math.max(1, amount - defense);
        int finalDamage = Math.max(1, state.modifyIncomingDamage(rawDamage));
        hp = Math.max(0, hp - finalDamage);
        System.out.println(name + " takes " + finalDamage + " damage. HP: " + hp + "/" + maxHp);
    }

    public void heal(int amount) {
        hp = Math.min(maxHp, hp + amount);
        System.out.println(name + " heals " + amount + " HP. HP: " + hp + "/" + maxHp);
    }
}