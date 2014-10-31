package spacetrader.model;

import java.util.Random;

/**
 * Weapon enum storing info on each type of weapon and calculating prices
 *
 * @author Murph
 */
public enum Weapon {

    /**
     * Pulse Weapon
     */
    PULSE(0, 0, 2, 100, 10, 10, 10),
    /**
     * Beam Weapon
     */
    BEAM(3, 3, 5, 500, 50, 10, 15),
    /**
     * Military Weapon
     */
    MILITARY(6, 6, 7, 1000, 100, 10, 20);

    private final int MTLP;       // Minimum Tech Level to Produce this resource (You can't buy on planets below this level)
    private final int MTLU;       // Minimum Tech Level to Use this resource (You can't sell on planets below this level)
    private final int TTP;        // Tech Level which produces the most of this item
    private final int BasePrice;
    private final int IPL;        // Price increase per tech level
    private final int Var;        // variance is the maximum percentage that the price can vary above or below the base
    private final int strength;   // strength of the weapon

    Weapon(int MTLP, int MTLU, int TTP, int BasePrice, int IPL, int Var, int strength) {
        this.MTLP = MTLP;
        this.MTLU = MTLU;
        this.TTP = TTP;
        this.BasePrice = BasePrice;
        this.IPL = IPL;
        this.Var = Var;
        this.strength = strength;
    }

    /**
     * Calculates the price of the good at the tech level of the port
     *
     * @param port
     * @return the price
     */
    public int CalculatePrice(int techLevel) {
        Random rand = new Random();
        int price = BasePrice + IPL * (techLevel - MTLP) + BasePrice
                * ((rand.nextInt(2 * Var) - Var / 2) / 100);
        if (price < 0) {
            return 0;
        }
        return price;
    }

    /**
     * Calculates the quantity to be sold at a specific marketplace
     *
     * @param techLevel
     * @return the suggested sale quantity
     */
    public int CalculateSellQuantity(int techLevel) {
        Random rand = new Random();
        int random = rand.nextInt(8) - rand.nextInt(8);
        int q;
        if (techLevel < MTLP) {
            q = 0;
        } else if (techLevel == TTP) {
            q = 50 + random;
        } else {
            q = 20 + 5 * (techLevel - MTLP) + random;
        }
        return q;
    }

    /**
     * @return the MTLU
     */
    public int getMTLU() {
        return MTLU;
    }

    /**
     * @return the strength
     */
    public int getStrength() {
        return strength;
    }

}
