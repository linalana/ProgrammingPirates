package spacetrader.model;

import java.util.Random;

/**
 * Gadget enum storing info on each type of gadget and calculating prices
 *
 * @author Murph
 */
public enum Gadget {

    /**
     * 5 extra cargo bays
     */
    EXTRACARGO(0, 0, 3, 500, 50, 10),
    /**
     * Navigating system
     */
    NAVSYSTEM(3, 3, 5, 1000, 100, 10),
    /**
     * Auto-repair system
     */
    AUTOREPAIR(4, 4, 6, 5000, 500, 10),
    /**
     * Targeting system
     */
    TARSYSTEM(4, 4, 6, 5000, 500, 10),
    /**
     * Cloaking device
     */
    CLOAK(7, 7, 7, 10000, 1000, 10);

    private final int MTLP;       // Minimum Tech Level to Produce this resource (You can't buy on planets below this level)
    private final int MTLU;       // Minimum Tech Level to Use this resource (You can't sell on planets below this level)
    private final int TTP;        // Tech Level which produces the most of this item
    private final int BasePrice;
    private final int IPL;        // Price increase per tech level
    private final int Var;        // variance is the maximum percentage that the price can vary above or below the base

    Gadget(int MTLP, int MTLU, int TTP, int BasePrice, int IPL, int Var) {
        this.MTLP = MTLP;
        this.MTLU = MTLU;
        this.TTP = TTP;
        this.BasePrice = BasePrice;
        this.IPL = IPL;
        this.Var = Var;
    }

    /**
     * calculates the price of the good at the tech level of the port
     *
     * @param techLevel
     * @return the price
     */
    public int calculatePrice(int techLevel) {
        int price = BasePrice + IPL * (techLevel - MTLP);
        if (price < 0) {
            return 0;
        }
        return price;
    }

    /**
     * calculates the quantity to be sold at a specific marketplace
     *
     * @param techLevel
     * @return the suggested sale quantity
     */
    public int calculateSellQuantity(int techLevel) {
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

}
