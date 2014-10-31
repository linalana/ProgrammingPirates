package spacetrader.model;

import java.util.Random;

/**
 *
 * @author alanalin
 */
public class Person {

    private int fighter;
    private int trader;
    private int engineer;
    private int investor;
    private int reputation;
    private Ship ship;

    public Person(int fighter, int trader, int engineer, int investor, int reputation, Ship ship) {
        this.fighter = fighter;
        this.trader = trader;
        this.engineer = engineer;
        this.investor = investor;
        this.reputation = reputation;
        this.ship = ship;
    }

    @Override
    public String toString() {
        return "Fighter: " + getFighter()
                + ". Trader: " + getTrader() + ". Engineer: "
                + getEngineer() + ". Investor: " + getInvestor();
    }

    /**
     * @return the fighter
     */
    public int getFighter() {
        return fighter;
    }

    /**
     * @return the trader
     */
    public int getTrader() {
        return trader;
    }

    /**
     * @return the engineer
     */
    public int getEngineer() {
        return engineer;
    }

    /**
     * @return the investor
     */
    public int getInvestor() {
        return investor;
    }

    /**
     * @return the reputation
     */
    public int getReputation() {
        return reputation;
    }

    /**
     * @param reputation the reputation to add
     */
    public void addReputation(int reputation) {
        this.setReputation(this.getReputation() + reputation);
    }

    /**
     * @param fighter the fighter to set
     */
    public void setFighter(int fighter) {
        this.fighter = fighter;
    }

    /**
     * @param trader the trader to set
     */
    public void setTrader(int trader) {
        this.trader = trader;
    }

    /**
     * @param engineer the engineer to set
     */
    public void setEngineer(int engineer) {
        this.engineer = engineer;
    }

    /**
     * @param investor the investor to set
     */
    public void setInvestor(int investor) {
        this.investor = investor;
    }

    /**
     * @param reputation the reputation to set
     */
    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    /**
     * @return the ship
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * @param ship the ship to set
     */
    public void setShip(Ship ship) {
        this.ship = ship;
    }

    /**
     * updates the ship
     *
     * @param type the new type of ship
     */
    public void updateShip(int type) {
        getShip().updateShip(type);
    }

    /**
     * Returns the info required to assess a fight
     *
     * @return int array of stats for fight
     */
    public int[] getInfo() {
        return new int[]{getTrader(), getFighter(),
            getReputation(), getShip().getHullStrength(), getShip().getShieldStrength(), getShip().getWeaponStrength()};
    }

    /**
     * @return total damage person is capable of
     */
    public int calcDamage() {
        int damage = getShip().getDamage();
        //implement use of target system later
        Random rand = new Random();
        int r = rand.nextInt(101);
        if (r <= getFighter() * 10) {
            damage *= .9;
        } else if (r <= getFighter() * 11) {
            damage *= .5;
        } else {
            damage = 0;
        }
        return damage;
    }

    /**
     * distributes damage to ship
     *
     * @param totalDamage damage done (to distribute)
     * @return true if ship survives
     */
    public boolean distributeDamage(int totalDamage) {
        return getShip().distributeDamage(totalDamage);
    }

    /**
     *
     * @return true if ship has life boat
     */
    public boolean hasLifeBoat() {
        return getShip().hasLifeBoat();
    }

    /**
     *
     * @return shieldhold
     */
    public ShieldHold getShieldHold() {
        return ship.getShieldHold();
    }

}
