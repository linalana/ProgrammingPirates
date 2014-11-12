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
            person.setFighter(4);
        }
        if (strength == 3) {
            //3 is ship type
            person.setShip(new Ship(3));
            //6 corresponds to how strong police should be here
            person.setFighter(6);
        }
        if (strength == 4) { //6 corresponds to ship type
            person.setShip(new Ship(6));
            //8 corresponds to how strong police should be here
            person.setFighter(8);
        }
        if (strength == 5) {
            //9 corresponds to ship type
            person.setShip(new Ship(9));
            //10 corresponds to how strong police should be here
            person.setFighter(10);
        }
    }

    /**
     * Decides the appropriate police strength based on the political system
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
        } else if (strengthCount >= 2 && strengthCount <= 3) {
            aStrength = 1;
        } else if (strengthCount >= 4 && strengthCount <= 6) {
            aStrength = 2;
        } else if (strengthCount >= 7 && strengthCount <= 8) {
            aStrength = 3;
        } else if (strengthCount >= 9 && strengthCount <= 14) {
            aStrength = 4;
        } else {
            aStrength = 5;
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
        
        if (bribeCount >= 0 && bribeCount <= 5) {
            aBribe = 0;
        } else if (bribeCount >= 6 && bribeCount <= 10) {
            aBribe = rand.nextInt((300 - 100) + 1) + 100;
        } else if (bribeCount >= 11 && bribeCount <= 12) {
            aBribe = rand.nextInt((3000 - 1000) + 1) + 1000;
        } else {
            aBribe = rand.nextInt((30000 - 10000) + 1) + 10000;
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
