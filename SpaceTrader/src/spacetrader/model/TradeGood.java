package spacetrader.model;

import java.util.Random;

/**
 * TradeGood enum storing info on each type of trade good
 * and calculating prices.
 *
 * @author alanalin
 */
public enum TradeGood {

    /**
     * Water Good.
     */
    WATER(0, 0, 2, 30, 3, 4, "DROUGHT", "LOTSOFWATER", "DESERT", 30, 50),
    /**
     * Furs Good.
     */
    FURS(0, 0, 0, 250, 10, 10, "COLD", "RICHFAUNA", "LIFELESS", 230, 280),
    /**
     * Food Good.
     */
    FOOD(1, 0, 1, 100, 5, 5, "CROPFAIL", "RICHSOIL", "POORSOIL", 90, 160),
    /**
     * Ore Good.
     */
    ORE(2, 2, 3, 350, 20, 10, "WAR", "MINERALRICH", "MINERALPOOR", 350, 420),
    /**
     * Games Good.
     */
    GAMES(3, 1, 6, 250, -10, 5, "BOREDOM", "ARTISTIC", "NEVER", 160, 270),
    /**
     * Firearms Good.
     */
    FIREARMS(3, 1, 5, 1250, -75, 100, "WAR", "WARLIKE", "NEVER", 600, 1100),
    /**
     * Medicine Good.
     */
    MEDICINE(4, 1, 6, 650, -20, 10, "PLAGUE", "LOTSOFHERBS", "NEVER", 400, 700),
    /**
     * Machines Good.
     */
    MACHINES(4, 3, 5, 900, -30, 5, "LACKOFWORKERS", "NEVER", "NEVER", 600, 800),
    /**
     * Narcotics Good.
     */
    NARCOTICS(5, 0, 5, 3500, -125, 150, "BOREDOM",
              "WEIRDMUSHROOMS", "NEVER", 2000, 3000),
    /**
     * Minions Good.
     */
    MINIONS(6, 4, 7, 5000, -150, 100, "LACKOFWORKERS",
            "NEVER", "NEVER", 3500, 5000);

    /* Minimum Tech Level to Produce this resource
       (You can't buy on planets below this level) */
    private final int mtlp;
    /* Minimum Tech Level to Use this resource
       (You can't sell on planets below this level) */
    private final int mtlu;
    // Tech Level which produces the most of this item
    private final int ttp;
    private final int basePrice;
    // Price increase per tech level
    private final int ipl;
    /* variance is the maximum percentage
       that the price can vary above or below the base */
    private final int var;
    /* Radical price increase event, when this even happens on a planet,
       the price may increase astronomically */
    private final String ie;
    /* When this condition is present,
       the price of this resource is unusually low */
    private final String cr;
    // When this condition is present, the resource is expensive
    private final String er;
    // Min price offered in space trade with random trader (not on a planet)
    private final int mtl;
    // Max price offered in space trade with random trader (not on a planet)
    private final int mth;
    private static final double HALF = 0.5;
    private static final int QUANTITY_VARIANCE = 8;
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
     * @param aIE Radical price increase event, when this even happens on a
     * planet, the price may increase astronomically
     * @param aCR When this condition is present, the resource is cheap
     * @param aER When this condition is present, the resource is expensive
     * @param aMTL Min price offered in space trade with random trader
     * @param aMTH Max price offered in space trade with random trader
     */
    TradeGood(final int aMTLP, final int aMTLU, final int aTTP,
            final int aBasePrice, final int aIPL, final int aVar,
            final String aIE, final String aCR, final String aER,
            final int aMTL, final int aMTH) {
        this.mtlp = aMTLP;
        this.mtlu = aMTLU;
        this.ttp = aTTP;
        this.basePrice = aBasePrice;
        this.ipl = aIPL;
        this.var = aVar;
        this.ie = aIE;
        this.cr = aCR;
        this.er = aER;
        this.mtl = aMTL;
        this.mth = aMTH;
    }

    /**
     * calculates the price of the good at the tech level of the port.
     *
     * @param aPort the current port
     * @return the price
     */
    public int calculatePrice(final Port aPort) {
        int price = getBasePrice() + ipl * (aPort.getTechLevel() - mtlp);
        if (price < 0) {
            return 0;
        }
        if (aPort.getEvent() == null) {
            return price;
        }
        if (aPort.getEvent().equals(ie)) {
            price *= HALF;
        }
        if (aPort.getResources().equals(cr)) {
            price *= HALF;
        }
        if (aPort.getResources().equals(er)) {
            price *= 1 + HALF;
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
        int random = rand.nextInt(QUANTITY_VARIANCE)
                - rand.nextInt(QUANTITY_VARIANCE);
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
     * @return the mtl
     */
    public int getMTL() {
        return mtl;
    }

    /**
     * @return the mth
     */
    public int getMTH() {
        return mth;
    }

    /**
     * @return the BasePrice
     */
    public int getBasePrice() {
        return basePrice;
    }

    /**
     * @return the Var
     */
    public int getVar() {
        return var;
    }

}
