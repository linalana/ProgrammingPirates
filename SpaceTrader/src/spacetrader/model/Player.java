package spacetrader.model;

import java.io.Serializable;

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
    private final Person p;
    private int money = 1000000;
    private PoliceRecord policeRecord;

    /**
     * creates a new player.
     *
     * @param name players name
     * @param fighter players fighter points
     * @param trader players trader points
     * @param engineer players engineer points
     * @param investor players investor points
     */
    public Player(final String name, final int fighter, final int trader,
            final int engineer, final int investor) {
        p = new Person(fighter, trader, engineer, investor, 0, new Ship(0));
        this.name = name;
        policeRecord = new PoliceRecord();

    }

    @Override
    public String toString() {
        return "Player name: " + name + " " + p;
    }

    /**
     * Attack encounterer.
     *
     * @param e the encounterer
     */
    public void attack(final Encounterer e) {
        if (e.getClass().equals(Pirate.class)) {
            //it's a pirate!
            //cast is safe because if statement checks it
            Pirate pirate = (Pirate) e;
            //to implement

        } else {
            //it's a trader!
            Trader t = (Trader) e;
            policeRecord.setIsPirate(true);
            //to implement

        }

    }

    //Getters and setters
    /**
     *
     * Setter for name.
     *
     * @param newName the players new name
     */
    public void setName(final String newName) {
        name = newName;
    }

    /**
     *
     * Getter for name.
     *
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * Getter for Fighter skill points.
     *
     * @return int fighter
     */
    public int getFighter() {
        return p.getFighter();
    }

    /**
     *
     * Getter for Trader skill points.
     *
     * @return int trader
     */
    public int getTrader() {
        return p.getTrader();
    }

    /**
     *
     * Getter for Engineer skill points.
     *
     * @return int engineer
     */
    public int getEngineer() {
        return p.getEngineer();
    }

    /**
     *
     * Getter for Investor skill points.
     *
     * @return int investor
     */
    public int getInvestor() {
        return p.getInvestor();
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
    public void setMoney(final int money) {
        this.money = money;
    }

    /**
     * @return the ship
     */
    public Ship getShip() {
        return p.getShip();
    }

    /**
     * @param type the ship to set
     */
    public void setShip(final int type) {
        p.updateShip(type);
    }

    /**
     * @return the reputation
     */
    public int getReputation() {
        return p.getReputation();
    }

    /**
     * @param exp reputation to be added
     */
    public void addExperience(final int exp) {
        p.addReputation(exp);
    }

    /**
     * @return the policeRecord
     */
    public PoliceRecord getPoliceRecord() {
        return policeRecord;
    }

    /**
     * Returns the player info required to assess a fight.
     *
     * @return int array of stats for fight
     */
    public int[] getPlayerInfo() {
        return p.getInfo();
    }

    /**
     * @return total damage player is capable of
     */
    public int calcDamage() {
        return p.calcDamage();
    }

    /**
     * distributes the damage.
     *
     * @param totalDamage the damage done to the player
     * @return 0 if dead, 1 if survived on lifeboat, 2 if alive
     */
    public int distributeDamage(final int totalDamage) {
        if (p.distributeDamage(totalDamage)) {
            return 2;
        }
        if (p.hasLifeBoat()) {
            return 1;
        }
        return 0;
    }

    /**
     * Checks ship for illegal goods.
     *
     * @return true if contains illegal goods
     */
    public boolean checkCargo() {
        return p.getShip().checkHoldForIllegal();
    }

    /**
     * Lower Inspection History, fine player, confiscate illegal goods.
     *
     * @return amount of fine based on inspection history
     */
    public int failInspection() {
        policeRecord.decrementInspectionHistory();
        //fine - halfs
        int fine = (int) (money * .05);
        if (policeRecord.getInspectionHistory() < 0) {
            //fine divides by 10
            fine = (int) (money * .1);
        }
        setMoney(getMoney() - fine);
        //confiscate
        p.getShip().removeIllegalGoods();

        return fine;
    }

    /**
     * @return player's shield hold
     */
    public ShieldHold getShieldHold() {
        return p.getShieldHold();
    }
}
