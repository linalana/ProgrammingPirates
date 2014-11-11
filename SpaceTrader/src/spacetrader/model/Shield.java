package spacetrader.model;

import java.util.Random;

/**
 * Shield enum storing info on each type of shield and calculating prices
 *
 * @author Murph
 */
public enum Shield {

    /**
     * Energy Shield
     */
    ENERGY(0, 0, 3, 500, 50, 10, 25),
    /**
     * Reflective Shield
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
    private final int BasePrice;
    private final int IPL;
    // Price increase per tech level
    private final int Var;
    // variance is the maximum percentage 
    //that the price can vary above or below the base
    public final int strength;
    // strength of the shield
    /**
     * The shield constructor.
     * @param MTLP Minimum Tech Level to Produce this resource
     * @param MTLU Minimum Tech Level to Use this resource
     * @param TTP Tech Level which produces the most of this item
     * @param BasePrice the base price
     * @param IPL Price increase per tech level
     * @param Var variance is the maximum percentage 
     * @param strength strength of the shield
     */
    Shield(int MTLP, int MTLU, int TTP, int BasePrice, int IPL, int Var,
            int strength) {
        this.MTLP = MTLP;
        this.MTLU = MTLU;
        this.TTP = TTP;
        this.BasePrice = BasePrice;
        this.IPL = IPL;
        this.Var = Var;
        this.strength = strength;
    }

    /**
     * calculates the price of the good at the tech level of the port.
     *
     * @param port
     * @return the price
     */
    public int calculatePrice(final Port port) {
        int price = BasePrice + IPL * (port.getTechLevel() - MTLP);
        if (price < 0) {
            return 0;
        }
        return price;
    }

    /**
     * calculates the quantity to be sold at a specific marketplace.
     *
     * @param techLevel
     * @return the suggested sale quantity
     */
    public int calculateSellQuantity(final int techLevel) {
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
