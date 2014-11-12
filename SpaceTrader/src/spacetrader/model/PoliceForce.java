package spacetrader.model;

import java.util.Random;

/**
 * A police force that will be assigned to each continent.
 *
 * @author Murph
 */
public class PoliceForce extends Encounterer {

    private int strength;
    private int bribe;
    private final int policeStrength1 = 4;
    private final int policeStrength2 = 6;
    private final int policeStrength3 = 8;
    private final int policeStrength4 = 10;
    private final int strength1 = 3;
    private final int strength2 = 4;
    private final int strength3 = 5;
    private final int ship3 = 3;
    private final int ship6 = 6;
    private final int ship9 = 9;
    
    private final int strengthCount2 = 2;
    private final int strengthCount3 = 3;
    private final int strengthCount4 = 4;
    private final int strengthCount6 = 6;
    private final int strengthCount7 = 7;
    private final int strengthCount8 = 8;
    private final int strengthCount9 = 9;
    private final int strengthCount14 = 14;

    private final int minBribe1 = 0;
    private final int maxBribe1 = 5;
    private final int minBribe2 = 6;
    private final int maxBribe2 = 10;
    private final int minBribe3 = 11;
    private final int maxBribe3 = 12;
    private final int bribeRange1 = 200;
    private final int bribeRange2 = 2000;
    private final int bribeRange3 = 20000;
    
    /**
     * Constructor for PoliceForce.
     *
     * @param system the political system of the continent
     */
    public PoliceForce(final String system) {
        super();
        this.strength = calculateStrength(system);
        this.bribe = calculateBribe(system);
        if (strength == 1) {
            person.setShip(new Ship(1));
            person.setFighter(2);
        }
        if (strength == 2) {
            person.setShip(new Ship(2));
            //4 corresponds to how strong police should be here
            person.setFighter(policeStrength1);
        }
        if (strength == strength1) {
            //3 is ship type
            person.setShip(new Ship(ship3));
            //6 corresponds to how strong police should be here
            person.setFighter(policeStrength2);
        }
        if (strength == strength2) { //6 corresponds to ship type
            person.setShip(new Ship(ship6));
            //8 corresponds to how strong police should be here
            person.setFighter(policeStrength3);
        }
        if (strength == strength3) {
            //9 corresponds to ship type
            person.setShip(new Ship(ship9));
            //10 corresponds to how strong police should be here
            person.setFighter(policeStrength4);
        }
    }

    /**
     * Decides the appropriate police strength based on the political system.
     * @param system the political system of the continent
     * @return the strength
     */
    private int calculateStrength(final String system) {
        int aStrength;
        int strengthCount = 0;
        String[] strengthArray = {"anarchy", "feudal", "pacifist", "satori",
            "capitalist", "democracy", "socialist", "confederacy", "monarchy",
            "communist", "corporate", "cybernetic", "dictatorship",
            "technocracy", "theocracy", "fascist", "military"};
        for (int i = 0; i < strengthArray.length; i++) {
            if (strengthArray[i].equals(system)) {
                strengthCount = i;
            }
        }
        //these strengths represent the level of police force (0-5)
        if (strengthCount >= 0 && strengthCount <= 1) {
            aStrength = 0;
        } else if (strengthCount >= strengthCount2
                && strengthCount <= strengthCount3) {
            aStrength = 1;
        } else if (strengthCount >= strengthCount4
                && strengthCount <= strengthCount6) {
            aStrength = 2;
        } else if (strengthCount >= strengthCount7
                && strengthCount <= strengthCount8) {
            aStrength = strength1;
        } else if (strengthCount >= strengthCount9
                && strengthCount <= strengthCount14) {
            aStrength = strength2;
        } else {
            aStrength = strength3;
        }
        return aStrength;
    }

    /**
     *
     * @param system the political system of the continent
     * @return the bribe level
     */
    private int calculateBribe(final String system) {
        int aBribe;
        int bribeCount = 0;
        Random rand = new Random();
        String[] bribeArray = {"anarchy", "fascist", "feudal", "military",
            "pacifist", "satori", "capitalist", "communist", "cybernetic",
            "democracy", "dictatorship", "monarchy", "socialist",
            "confederacy", "corporate", "technocracy", "theocracy"};
        for (int i = 0; i < bribeArray.length; i++) {
            if (bribeArray[i].equals(system)) {
                bribeCount = i;
            }
        }

        if (bribeCount >= minBribe1 && bribeCount <= maxBribe1) {
            aBribe = 0;
        } else if (bribeCount >= minBribe2 && bribeCount <= maxBribe2) {
            aBribe = rand.nextInt((bribeRange1) + 1) + bribeRange1 / 2;
        } else if (bribeCount >= minBribe3 && bribeCount <= maxBribe3) {
            aBribe = rand.nextInt((bribeRange2) + 1) + bribeRange2 / 2;
        } else {
            aBribe = rand.nextInt((bribeRange3) + 1) + bribeRange3 / 2;
        }
        return aBribe;
    }

    /**
     * Inspect a player.
     *
     * @param player the player being inspected
     * @return true if they pass the inspection, false otherwise
     */
    private boolean inspect(final Player player) {
        boolean result =  false;
        if (player.getShip().getCargoHold().getGoods().get(TradeGood.FIREARMS)
                != 0 || player.getShip().getCargoHold().getGoods()
                        .get(TradeGood.NARCOTICS) != 0) {
            player.getPoliceRecord().decrementInspectionHistory();
        } else {
            player.getPoliceRecord().incrementInspectionHistory();
            result = true;
        }
        return result;
    }

    /**
     * @return the strength
     */
    public final int getStrength() {
        return strength;
    }

    /**
     * @return the bribe
     */
    public final int getBribe() {
        return bribe;
    }
}
