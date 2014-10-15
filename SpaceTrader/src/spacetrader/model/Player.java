package spacetrader.model;

import java.io.Serializable;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Michael
 */
public class Player implements Serializable {
    private String name;
    private Ship ship;
    private int fighter;
    private int trader;
    private int engineer;
    private int investor;
    private int money = 10000;
    private int reputation;
    private PoliceRecord policeRecord;

    /**
     *
     * @param aName
     * @param aFighter
     * @param aTrader
     * @param aEngineer
     * @param aInvestor
     */
    public Player(String aName, int aFighter, int aTrader, int aEngineer, int aInvestor) {
        name = aName;
        fighter = aFighter;
        trader = aTrader;
        engineer = aEngineer;
        investor = aInvestor;
        this.ship = new Ship(0);
        reputation = 0;
        policeRecord = new PoliceRecord();

    }
    @Override
    public String toString() {
        return "Player name: " + name + ". Fighter: " + fighter + ". Trader: " + trader + ". Engineer: " + engineer + ". Investor: " + investor;
    }
    
    public void attack(Encounterer e) {
        if (e.getClass().equals(Pirate.class)) {
            //it's a pirate!
            Pirate p = (Pirate) e;
            

        } else {
            //it's a trader!
            Trader t = (Trader) e;
            policeRecord.setIsPirate(true);
            
        }
        
    }
    
    //Getters and setters

    /**
     *
     * Setter for name
     * 
     * @param newName
     */
        public void setName(String newName) {
        name = newName;
    }

    /**
     *
     * Getter for name
     * 
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * Setter for Fighter skill points
     * 
     * @param newFighter
     */
    public void setFighter(int newFighter) {
        fighter = newFighter;
    }

    /**
     *
     * Getter for Fighter skill points
     * 
     * @return int fighter
     */
    public int getFighter() {
        return fighter;
    }

    /**
     *
     * Setter for Trader skill points
     * 
     * @param newTrader
     */
    public void setTrader(int newTrader) {
       trader = newTrader;
    }

    /**
     *
     * Getter for Trader skill points
     * 
     * @return int trader
     */
    public int getTrader() {
        return trader;
    }

    /**
     *
     * Setter for Engineer skill points
     * 
     * @param newEngineer
     */
    public void setEngineer(int newEngineer) {
        engineer = newEngineer;
    }

    /**
     *
     * Getter for Engineer skill points
     * 
     * @return int engineer
     */
    public int getEngineer() {
        return engineer;
    }

    /**
     *
     * Setter for Investor skill points
     * 
     * @param newInvestor
     */
    public void setInvestor(int newInvestor) {
        investor = newInvestor;
    }

    /**
     *
     * Getter for Investor skill points
     * 
     * @return int investor
     */
    public int getInvestor() {
        return investor;
    }

    /**
     * @return the money
     */
    public int getMoney() {
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(int money) {
        this.money = money;
    }
    
    /**
     * @return the ship
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * @param type the ship to set
     */
    public void setShip(int type) {
        ship.updateShip(type);
    }

    /**
     * @return the reputation
     */
    public int getReputation() {
        return reputation;
    }
    
    /**
     * @param exp reputation to be added
     */
    public void addExperience(int exp) {
        reputation += exp;
    }
    /**
     * @return the policeRecord
     */
    public PoliceRecord getPoliceRecord() {
        return policeRecord;
    }
    /**
     * Returns the player info required to assess a fight
     * @return int array of stats for fight
     */
    public int[] getPlayerInfo() {
        return new int[]{trader, fighter, reputation,
            ship.getHullStrength(), ship.getShieldStrength(),
            ship.getWeaponStrength()};
    }
    /**
     * @return total damage player is capable of
     */
    public int calcDamage() {
        int damage = ship.getDamage();       
        //implement use of target system later
        Random rand = new Random();
        int r = rand.nextInt(101);
        if (r <= fighter * 10) {
            damage *= .9;
        } else if (r <= fighter * 11) {
            damage *= .5;
        } else {
            damage = 0;
        }
        return damage;
    }
    /**
     * distributes the damage
     * @param totalDamage the damage done to the player
     * @return 0 if dead, 1 if survived on lifeboat, 2 if alive
     */
    public int distributeDamage(int totalDamage) {
        if (!ship.distributeDamage(totalDamage)) {
            if (ship.hasLifeBoat()) {
                return 1;
            }
            return 0;
        }
        return 2;
    }
    /**
     * Checks ship for illegal goods
     * @return true if contains illegal goods
     */
    public boolean checkCargo() {
        return ship.checkHoldForIllegal();
    }
    /**
     * Lower Inspection History, fine player, confiscate illegal goods
     * @return amount of fine based on inspection history
     */
    public int failInspection() {      
        policeRecord.decrementInspectionHistory();
        //fine
        int fine = (int) (money * .05);
        if (policeRecord.getInspectionHistory() < 0) {
            fine = (int) (money * .1);
        }
        setMoney(getMoney() - fine);
        //confiscate
        ship.removeIllegalGoods();
        
        return fine;
    }
}
 
