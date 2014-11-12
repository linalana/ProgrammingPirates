package spacetrader.model;

import java.util.Random;

/**
 * Shield enum storing info on each type of shield and calculating prices.
 *
 * @author Murph
 */
public enum Shield {

    /**
     * Energy Shield.
     */
    ENERGY(0, 0, 3, 500, 50, 10, 25),
    /**
     * Reflective Shield.
     */
    REFLECTIVE(4, 4, 7, 1000, 100, 10, 50);

    private final int MTLP;
    // Minimum Tech Level to Produce this resource
    //(You can't buy on planets below this level)
    private final int MTLU;
    // Minimum Tech Level to Use this resource
    //(You can't sell on planets below this level)
    private final int TTP;
    // Tech Level which produces the most of this item
    private final int basePrice;
    private final int IPL;
    // Price increase per tech level
    private final int var;
    // variance is the maximum percentage
    //that the price can vary above or below the base
    private final int strength;
    private static final int QUANTITY_VARIANCE = 8;
    private static final int MAXIMUM_QUANTITY = 50;
    private static final int MINIMUM_QUANTITY = 20;
    private static final int MINIMUM_VARIANCE = 5;
    // strength of the shield
    /**
     * The shield constructor.
     * @param aMTLP Minimum Tech Level to Produce this resource
     * @param aMTLU Minimum Tech Level to Use this resource
     * @param aTTP Tech Level which produces the most of this item
     * @param aBasePrice the base price
     * @param aIPL Price increase per tech level
     * @param aVar variance is the maximum percentage
     * @param aStrength strength of the shield
     */
    Shield(final int aMTLP, final int aMTLU, final int aTTP,
            final int aBasePrice, final int aIPL, final int aVar,
            final int aStrength) {
        this.MTLP = aMTLP;
        this.MTLU = aMTLU;
        this.TTP = aTTP;
        this.basePrice = aBasePrice;
        this.IPL = aIPL;
        this.var = aVar;
        this.strength = aStrength;
    }

    /**
     * calculates the price of the good at the tech level of the port.
     *
     * @param port is the current port
     * @return the price
     */
    public int calculatePrice(final Port port) {
        int price = basePrice + IPL * (port.getTechLevel() - MTLP);
        if (price < 0) {
            return 0;
        }
        return price;
    }

    /**
     * calculates the quantity to be sold at a specific marketplace.
     *
     * @param techLevel is the current ports tech level
     * @return the suggested sale quantity
     */
    public int calculateSellQuantity(final int techLevel) {
        Random rand = new Random();
        int random = rand.nextInt(QUANTITY_VARIANCE)
                - rand.nextInt(QUANTITY_VARIANCE);
        int q;
        if (techLevel < MTLP) {
            q = 0;
        } else if (techLevel == TTP) {
            q = MAXIMUM_QUANTITY + random;
        } else {
            q = MINIMUM_QUANTITY + MINIMUM_VARIANCE * (techLevel - MTLP)
                    + random;
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
