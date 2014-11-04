package spacetrader.model;

import java.io.Serializable;
import java.util.Random;

/**
 * A police force that will be assigned to each continent
 *
 * @author Murph
 */
public class PoliceForce extends Encounterer implements Serializable {

    final private int strength;
    final private int bribe;

    /**
     * Constructor for PoliceForce
     *
     * @param system the political system of the continent
     */
    public PoliceForce(String system) {
        super();
        this.strength = calculateStrength(system);
        this.bribe = calculateBribe(system);
        if (strength == 1) {
            p.setShip(new Ship(1));
            p.setFighter(2);
        }
        if (strength == 2) {
            p.setShip(new Ship(2));
            p.setFighter(4);
        }
        if (strength == 3) {
            p.setShip(new Ship(3));
            p.setFighter(6);
        }
        if (strength == 4) {
            p.setShip(new Ship(6));
            p.setFighter(8);
        }
        if (strength == 5) {
            p.setShip(new Ship(9));
            p.setFighter(10);
        }
    }

    /**
     *
     * @param system the political system of the continent
     * @return the strength
     */
    private int calculateStrength(final String system) {
        int strength;
        int strengthCount = 0;
        final String[] strengthArray = {"anarchy", "feudal", "pacifist", "satori", "capitalist", "democracy", "socialist",
            "confederacy", "monarchy", "communist", "corporate", "cybernetic", "dictatorship",
            "technocracy", "theocracy", "fascist", "military"};
        for (int i = 0; i < strengthArray.length; i++) {
            if (strengthArray[i].equals(system)) {
                strengthCount = i;
            }
        }
        if (strengthCount >= 0 && strengthCount <= 1) {
            strength = 0;
        } else if (strengthCount >= 2 && strengthCount <= 3) {
            strength = 1;
        } else if (strengthCount >= 4 && strengthCount <= 6) {
            strength = 2;
        } else if (strengthCount >= 7 && strengthCount <= 8) {
            strength = 3;
        } else if (strengthCount >= 9 && strengthCount <= 14) {
            strength = 4;
        } else {
            strength = 5;
        }
        return strength;
    }

    /**
     *
     * @param system the political system of the continent
     * @return the bribe level
     */
    private int calculateBribe(final String system) {
        int bribe;
        int bribeCount = 0;
        final Random rand = new Random();
        final String[] bribeArray = {"anarchy", "fascist", "feudal", "military", "pacifist", "satori", "capitalist",
            "communist", "cybernetic", "democracy", "dictatorship", "monarchy", "socialist",
            "confederacy", "corporate", "technocracy", "theocracy"};
        for (int i = 0; i < bribeArray.length; i++) {
            if (bribeArray[i].equals(system)) {
                bribeCount = i;
            }
        }
        if (bribeCount >= 0 && bribeCount <= 5) {
            bribe = 0;
        } else if (bribeCount >= 6 && bribeCount <= 10) {
            bribe = rand.nextInt((300 - 100) + 1) + 100;
        } else if (bribeCount >= 11 && bribeCount <= 12) {
            bribe = rand.nextInt((3000 - 1000) + 1) + 1000;
        } else {
            bribe = rand.nextInt((30000 - 10000) + 1) + 10000;
        }
        return bribe;
    }

    /**
     * Inspect a player
     *
     * @param player the player being inspected
     * @return true if they pass the inspection, false otherwise
     */
    private boolean inspect(final Player player) {
        if (player.getShip().getCargoHold().getGoods().get(TradeGood.FIREARMS) != 0
                || player.getShip().getCargoHold().getGoods().get(TradeGood.NARCOTICS) != 0) {
            player.getPoliceRecord().decrementInspectionHistory();
            return false;
        } else {
            player.getPoliceRecord().incrementInspectionHistory();
            return true;
        }
    }

    /**
     * @return the strength
     */
    public int getStrength() {
        return strength;
    }

    /**
     * @return the bribe
     */
    public int getBribe() {
        return bribe;
    }
}
