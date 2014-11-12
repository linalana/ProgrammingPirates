package spacetrader.model;

import java.util.Random;

/**
 * Weapon enum storing info on each type of weapon and calculating prices.
 *
 * @author Murph
 */
public enum Weapon {

    /**
     * Pulse Weapon.
     */
    PULSE(0, 0, 2, 100, 10, 10, 10),
    /**
     * Beam Weapon.
     */
    BEAM(3, 3, 5, 500, 50, 10, 15),
    /**
     * Military Weapon.
     */
    MILITARY(6, 6, 7, 1000, 100, 10, 20);

    /* Minimum Tech Level to Produce this resource
       (You can't buy on planets below this level) */
    private final int mtlp;
    /* Minimum Tech Level to Use this resource
       (You can't sell on planets below this level) */
    private final int mtlu;
    // Tech Level which produces the most of this item
    private final int ttp;
    private final int basePrice;
    private final int ipl;        // Price increase per tech level
    /* variance is the maximum percentage
       that the price can vary above or below the base */
    private final int var;
    private final int strength;   // strength of the weapon
    private static final int SELL_VARIANCE = 8;
    private static final int MAXIMUM_QUANTITY = 50;
    private static final int MINIMUM_QUANTITY = 20;
    private static final int MINIMUM_VARIANCE = 5;
    /**
     * constructor for trade good.
     * @param aMTLP Minimum Tech Level to Use this resource
     * @param aMTLU Minimum Tech Level to Use this resource
     * @param aTTP Tech Level which produces the most of this item
     * @param aBasePrice the base price
     * @param aIPL Price increase per tech level
     * @param aVar maximum percentage that the price can vary above or below
     * the base
     * @param aStrength is the strength of the weapon
     */
    Weapon(final int aMTLP, final int aMTLU, final int aTTP,
            final int aBasePrice, final int aIPL, final int aVar,
            final int aStrength) {
        this.mtlp = aMTLP;
        this.mtlu = aMTLU;
        this.ttp = aTTP;
        this.basePrice = aBasePrice;
        this.ipl = aIPL;
        this.var = aVar;
        this.strength = aStrength;
    }

    /**
     * calculates the price of the good at the tech level of the port.
     *
     * @param techLevel the current tech level
     * @return the price
     */
    public int calculatePrice(final int techLevel) {
        int price = basePrice + ipl * (techLevel - mtlp);
        if (price < 0) {
            return 0;
        }
        return price;
    }

    /**
     * calculates the quantity to be sold at a specific marketplace.
     *
     * @param techLevel the current tech level
     * @return the suggested sale quantity
     */
    public int calculateSellQuantity(final int techLevel) {
        Random rand = new Random();
        int random = rand.nextInt(SELL_VARIANCE);
        int q;
        if (techLevel < mtlp) {
            q = 0;
        } else if (techLevel == ttp) {
            q = MAXIMUM_QUANTITY + random;
        } else {
            q = MINIMUM_QUANTITY + MINIMUM_VARIANCE * (techLevel - mtlp)
                    + random;
        }
        return q;
    }

    /**
     * @return the mtlu
     */
    public int getMTLU() {
        return mtlu;
    }

    /**
     * @return the strength
     */
    public int getStrength() {
        return strength;
    }

}
