package spacetrader.model;

import java.util.Random;

/**
 *
 * @author alanalin
 */
public class Trader extends Encounterer {

    private int traderPoints;
    /**
     * This is the constructor for a trader.
     */
    public Trader() {
        super();
        super.fillCargo();
        allocateSkillPoints();
    }

    /**
     * determines if the trader will encounter the player or not.
     *
     * @param playerIsPirate whether or not player is pirate
     * @return true if trader will NOT flee
     */
    public final boolean willEncounter(final boolean playerIsPirate) {
        return !playerIsPirate;
    }

    /**
     * distributes skill points.
     */
    private void allocateSkillPoints() {
        Random rand = new Random();
        traderPoints = rand.nextInt(11);
    }

    /**
     * @return the traderPoints
     */
    @Override
    public final int getTraderPoints() {
        return traderPoints;
    }

    /**
     * Returns the trader info required to assess a fight.
     *
     * @return int array of stats for fight
     */
    @Override
    public final int[] getEncountererInfo() {
        return person.getInfo();
    }

}

