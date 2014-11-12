package spacetrader.model;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author alanalin
 */
public class Person implements Serializable {

    private int fighter;
    private int trader;
    private int engineer;
    private int investor;
    private int reputation;
    private Ship ship;
    private static final int CHANCE_SCALE = 101;
    private static final int CHANCE_SCALER = 10;
    private static final double LARGE_CHANCE = 11;
    private static final double LARGE_DAMAGE = 0.9;
    private static final double SMALL_DAMAGE = 0.5;
    /**
     * Constructor for a person.
     * @param aFighter fighter skill points
     * @param aTrader trader skill points
     * @param aEngineer engineer skill points
     * @param aInvestor investor skill points
     * @param aReputation reputation points
     * @param aShip ship
     */
    public Person(final int aFighter, final int aTrader, final int aEngineer,
                  final int aInvestor, final int aReputation,
                  final Ship aShip) {
        this.fighter = aFighter;
        this.trader = aTrader;
        this.engineer = aEngineer;
        this.investor = aInvestor;
        this.reputation = aReputation;
        this.ship = aShip;
    }

    @Override
    public final String toString() {
        return "Fighter: " + getFighter()
                + ". Trader: " + getTrader() + ". Engineer: "
                + getEngineer() + ". Investor: " + getInvestor();
    }

    /**
     * @return the fighter
     */
    public final int getFighter() {
        return fighter;
    }

    /**
     * @return the trader
     */
    public final int getTrader() {
        return trader;
    }

    /**
     * @return the engineer
     */
    public final int getEngineer() {
        return engineer;
    }

    /**
     * @return the investor
     */
    public final int getInvestor() {
        return investor;
    }

    /**
     * @return the reputation
     */
    public final int getReputation() {
        return reputation;
    }

    /**
     * @param aReputation the reputation to add
     */
    public final void addReputation(final int aReputation) {
        this.setReputation(this.getReputation() + aReputation);
    }

    /**
     * @param aFighter the fighter to set
     */
    public final void setFighter(final int aFighter) {
        this.fighter = aFighter;
    }

    /**
     * @param aTrader the trader to set
     */
    public final void setTrader(final int aTrader) {
        this.trader = aTrader;
    }

    /**
     * @param aEngineer the engineer to set
     */
    public final void setEngineer(final int aEngineer) {
        this.engineer = aEngineer;
    }

    /**
     * @param aInvestor the investor to set
     */
    public final void setInvestor(final int aInvestor) {
        this.investor = aInvestor;
    }

    /**
     * @param aReputation the reputation to set
     */
    public final void setReputation(final int aReputation) {
        this.reputation = aReputation;
    }

    /**
     * @return the ship
     */
    public final Ship getShip() {
        return ship;
    }

    /**
     * @param aShip the ship to set
     */
    public final void setShip(final Ship aShip) {
        this.ship = aShip;
    }

    /**
     * updates the ship.
     *
     * @param aType the new type of ship
     */
    public final void updateShip(final int aType) {
        ship = new Ship(aType);
    }

    /**
     * Returns the info required to assess a fight.
     *
     * @return int array of stats for fight
     */
    public final int[] getInfo() {
        return new int[]{getTrader(), getFighter(),
            getReputation(), getShip().getHullStrength(), getShip()
                    .getShieldStrength(), getShip().getWeaponStrength()};
    }

    /**
     * @return total damage person is capable of
     */
    public final int calcDamage() {
        int damage = getShip().getDamage();
        //implement use of target system later
        Random rand = new Random();
        //chances calculated out of 100
        int r = rand.nextInt(CHANCE_SCALE);
        if (r <= getFighter() * CHANCE_SCALER) {
            damage *= LARGE_DAMAGE;
        } else if (r <= (getFighter() * (LARGE_CHANCE))) {
            damage *= SMALL_DAMAGE;
        } else {
            damage = 0;
        }
        return damage;
    }

    /**
     * distributes damage to ship.
     *
     * @param totalDamage damage done (to distribute)
     * @return true if ship survives
     */
    public final boolean distributeDamage(final int totalDamage) {
        boolean lives = getShip().distributeDamage(totalDamage);
        return lives;
    }

    /**
     *
     * @return true if ship has life boat
     */
    public final boolean hasLifeBoat() {
        return getShip().hasLifeBoat();
    }

    /**
     *
     * @return shieldhold
     */
    public final ShieldHold getShieldHold() {
        return ship.getShieldHold();
    }

}
