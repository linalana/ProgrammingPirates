package spacetrader.model;

import java.util.Random;

/**
 * Gadget enum storing info on each type of gadget and calculating prices.
 *
 * @author Murph
 */
public enum Gadget {

    /**
     * 5 extra cargo bays.
     */
    EXTRACARGO(0, 0, 3, 500, 50, 10),
    /**
     * Navigating system.
     */
    NAVSYSTEM(3, 3, 5, 1000, 100, 10),
    /**
     * Auto-repair system.
     */
    AUTOREPAIR(4, 4, 6, 5000, 500, 10),
    /**
     * Targeting system.
     */
    TARSYSTEM(4, 4, 6, 5000, 500, 10),
    /**
     * Cloaking device.
     */
    CLOAK(7, 7, 7, 10000, 1000, 10);

    private final int MTLP;       // Minimum Tech Level to Produce this resource
    //(You can't buy on planets below this level)
    private final int MTLU;       // Minimum Tech Level to Use this resource
    //(You can't sell on planets below this level)
    private final int TTP;        // Tech Level which produces the most of this
    //item
    private final int basePrice;
    private final int IPL;        // Price increase per tech level
    private final int var;        // variance is the maximum percentage that the
    //price can vary above or below the base
    private static final int QUANTITY_VARIANCE = 8;
    private static final int MAXIMUM_QUANTITY = 50;
    private static final int MINIMUM_QUANTITY = 20;
    private static final int MINIMUM_VARIANCE = 5;
    /**
     * The constructor for a gadget.
     * @param aMTLP Minimum Tech Level to Produce this resource
     * @param aMTLU Minimum Tech Level to Use this resource
     * @param aTTP  Tech Level which produces the most of this item
     * @param aBasePrice base price
     * @param aIPL Price increase per tech level
     * @param aVar is the maximum percentage that the price can vary
     * above or below the base
     */
    Gadget(final int aMTLP, final int aMTLU, final int aTTP,
            final int aBasePrice, final int aIPL, final int aVar) {
        this.MTLP = aMTLP;
        this.MTLU = aMTLU;
        this.TTP = aTTP;
        this.basePrice = aBasePrice;
        this.IPL = aIPL;
        this.var = aVar;
    }

    /**
     * calculates the price of the good at the tech level of the port.
     *
     * @param techLevel tech level of port
     * @return the price
     */
    public int calculatePrice(final int techLevel) {
        Random rand = new Random();
        int price = basePrice + IPL * (techLevel - MTLP);
        if (price < 0) {
            return 0;
        }
        return price;
    }

    /**
     * calculates the quantity to be sold at a specific marketplace.
     *
     * @param techLevel tech level of port
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

}
