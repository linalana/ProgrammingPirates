package spacetrader.model;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author alanalin
 */
public abstract class Encounterer implements Serializable {

    protected final Person person; //encounterer delegates to Person
    //(used by children)
    private static final int VARIANCE_ALLOWED = 10;
    private static final int MAX_SKILL_POINTS = 10;
    /**
     * creates encounterer, and backing person.
     */
    public Encounterer() {
        Random rand = new Random();
        int deviance = rand.nextInt(2 * VARIANCE_ALLOWED);
        int reputation = Game.getPlayer().getReputation() + (deviance
                - VARIANCE_ALLOWED);

        person = new Person(rand.nextInt(MAX_SKILL_POINTS + 1), rand
                .nextInt(MAX_SKILL_POINTS + 1), rand.nextInt(MAX_SKILL_POINTS
                        + 1), rand.nextInt(MAX_SKILL_POINTS + 1), reputation,
                new Ship());
    }

    /**
     * fills the cargo hold with random stuff.
     */
    protected final void fillCargo() {
        Random rand = new Random();
        for (TradeGood t : TradeGood.values()) {
            if (rand.nextBoolean()) {
                int count = rand.nextInt(5);
                getShip().getCargoHold().addCargo(t, count);
            }
        }
    }

    /**
     * Decide if Encounterer will fight or flee.
     *
     * @param playerRep the exp of the player
     * @param fightPoints the fighter skill points of the player
     * @return true if will fight
     */
    public final boolean willEncounter(final int playerRep,
            final int fightPoints) {
        return (playerRep <= getReputation());
        //take into account fighter skill points
        /* NOTE: we might have to move this method
          into the individual trader/pirate/police classes */
    }
    /**
     * creates an attack.
     * @return the value of the attack
     */
    public final int attack() {
        Random rand = new Random();
        //removed in expansion of encounters
        return rand.nextInt(10) * getReputation() / 100;
    }

    /**
     * @return the reputation
     */
    public final int getReputation() {
        return person.getReputation();
    }

    /**
     * @return the ship
     */
    public final Ship getShip() {
        return person.getShip();
    }

    /**
     * @return the fighterPoints
     */
    public final int getFighterPoints() {
        return person.getFighter();
    }

    /**
     * Definitely designed to be extended.
     * @return the fighterPoints
     */
    public int getTraderPoints() {
        return person.getTrader();
    }

    /**
     * Returns the encounterer info required to assess a fight.
     * 100% confirmed designed to be extended.
     * @return int array of stats for fight
     */
    public int[] getEncountererInfo() {
        return person.getInfo();
    }

    /**
     * @return total damage encounterer is capable of
     */
    public final int calcDamage() {
        return person.calcDamage();
    }

    /**
     * distributes the damage.
     *
     * @param totalDamage the damage done to the encounterer
     * @return true if encounterer survives
     */
    final boolean distributeDamage(final int totalDamage) {
        return person.distributeDamage(totalDamage);
    }

}
