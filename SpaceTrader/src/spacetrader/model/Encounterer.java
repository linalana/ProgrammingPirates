package spacetrader.model;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author alanalin
 */
public abstract class Encounterer implements Serializable {

    protected final Person p; //encounterer delegates to Person

    /**
     * creates encounterer, and backing person
     */
    public Encounterer() {
        Random rand = new Random();
        int deviance = rand.nextInt(2 * 10);
        int reputation = Game.getPlayer().getReputation() + (deviance - 10);

        p = new Person(rand.nextInt(11), rand.nextInt(11),
                rand.nextInt(11), rand.nextInt(11), reputation, new Ship());
    }

    /**
     * fills the cargo hold with random stuff
     */
    protected void fillCargo() {
        Random rand = new Random();
        for (TradeGood t : TradeGood.values()) {
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
    public boolean willEncounter(int playerRep, int fightPoints) {
        return (playerRep <= getReputation());
        //take into account fighter skill points
        //NOTE: we might have to move this method into the individual trader/pirate/police classes
    }

    public int attack() {
        Random rand = new Random();
        return rand.nextInt(10) * getReputation() / 100;
    }

    /**
     * @return the reputation
     */
    public int getReputation() {
        return p.getReputation();
    }

    /**
     * @return the ship
     */
    public Ship getShip() {
        return p.getShip();
    }

    /**
     * @return the fighterPoints
     */
    public int getFighterPoints() {
        return p.getFighter();
    }

    /**
     * @return the fighterPoints
     */
    public int getTraderPoints() {
        return p.getTrader();
    }

    /**
     * Returns the encounterer info required to assess a fight
     *
     * @return int array of stats for fight
     */
    public int[] getEncountererInfo() {
        return p.getInfo();
    }

    /**
     * @return total damage encounterer is capable of
     */
    public int calcDamage() {
        return p.calcDamage();
    }

    /**
     * distributes the damage
     *
     * @param totalDamage the damage done to the encounterer
     * @return true if encounterer survives
     */
    boolean distributeDamage(int totalDamage) {
        return p.distributeDamage(totalDamage);
    }

}
