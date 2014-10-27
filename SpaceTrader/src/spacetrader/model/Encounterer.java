/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.model;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author alanalin
 */
public abstract class Encounterer implements Serializable {
    protected int reputation;
    protected Ship ship;
    protected int fighterPoints;

    public Encounterer() {
        Random rand = new Random();
        int deviance = rand.nextInt(2 * 10);
        reputation = Game.getPlayer().getReputation() + (deviance - 10);
        if (reputation < 0) {
            reputation = 0;
        }
        ship = new Ship();
        fighterPoints = rand.nextInt(11);
    }

    public void fillCargo() {
        Random rand = new Random();
        for (TradeGood t: TradeGood.values()) {
            if (rand.nextBoolean()) {
                int count = rand.nextInt(5);
                getShip().getCargoHold().addCargo(t, count);
            }
        }
    }
    /**
     * Decide if Encounterer will fight or flee
     *
     * @param playerRep the exp of the player
     * @param fightPoints the fighter skill points of the player
     * @return true if will fight
     */
    public boolean willFight(int playerRep) {
        return (playerRep <= reputation);
    }

    public int attack() {
        Random rand = new Random();
        return rand.nextInt(10) * reputation / 100;
    }

    /**
     * @return the reputation
     */
    public int getReputation() {
        return reputation;
    }

    /**
     * @return the ship
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * @return the fighterPoints
     */
    public int getFighterPoints() {
        return fighterPoints;
    }
    /**
     * @return the fighterPoints
     */
    public int getTraderPoints() {
        return 0;
    }
    /**
     * Returns the encounterer info required to assess a fight
     * @return int array of stats for fight
     */
    public int[] getEncountererInfo() {
        return new int[]{0, fighterPoints, reputation,
            ship.getHullStrength(), ship.getShieldStrength(),
            ship.getWeaponStrength()};
    }

    /**
     * @return total damage encounterer is capable of
     */
    public int calcDamage() {
        return ship.getDamage() * (fighterPoints / 10);
    }

     /**
     * distributes the damage
     * @param totalDamage the damage done to the encounterer
     * @return true if encounterer survives
     */
    boolean distributeDamage(int totalDamage) {
        return ship.distributeDamage(totalDamage);
    }

    public double[] checkShipHealth() {
        return ship.getHealthPercentages();
    }


}
