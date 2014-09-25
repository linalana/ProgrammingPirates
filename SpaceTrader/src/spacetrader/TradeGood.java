package spacetrader;
import java.util.Random;
/**
 * TradeGood enum storing info on each type of trade good and calculating prices
 * 
 * @author alanalin
 */
public enum TradeGood {

    /**
     * Water Good
     */
    WATER (0, 0, 2, 30 ,3, 4, "DROUGHT", "LOTSOFWATER", "DESERT", 30, 50),

    /**
     * Furs Good
     */
    FURS (0, 0, 0, 250, 10, 10, "COLD", "RICHFAUNA", "LIFELESS", 230, 280),

    /**
     * Food Good
     */
    FOOD (1, 0, 1, 100, 5, 5, "CROPFAIL", "RICHSOIL", "POORSOIL", 90, 160),

    /**
     * Ore Good
     */
    ORE (2, 2, 3, 350, 20, 10, "WAR", "MINERALRICH", "MINERALPOOR", 350, 420),

    /**
     * Games Good
     */
    GAMES (3, 1, 6, 250, -10, 5, "BOREDOM", "ARTISTIC", "NEVER", 160, 270),

    /**
     * Firearms Good
     */
    FIREARMS (3, 1, 5, 1250, -75, 100, "WAR", "WARLIKE", "NEVER", 600, 1100),

    /**
     * Medicine Good
     */
    MEDICINE (4, 1, 6, 650, -20, 10, "PLAGUE", "LOTSOFHERBS", "NEVER", 400, 700),

    /**
     * Machines Good
     */
    MACHINES (4, 3, 5, 900, -30, 5, "LACKOFWORKERS", "NEVER", "NEVER", 600, 800),

    /**
     *
     */
    NARCOTICS (5, 0, 5, 3500, -125, 150, "BOREDOM", "WEIRDMUSHROOMS", "NEVER", 2000, 3000),

    /**
     * Minions Good
     */
    MINIONS (6, 4, 7, 5000, -150, 100, "LACKOFWORKERS", "NEVER", "NEVER", 3500, 5000);
    
    private final int MTLP;       // Minimum Tech Level to Produce this resource (You can't buy on planets below this level)
    private final int MTLU;       // Minimum Tech Level to Use this resource (You can't sell on planets below this level)
    private final int TTP;        // Tech Level which produces the most of this item
    private final int BasePrice;
    private final int IPL;        // Price increase per tech level
    private final int Var;        // variance is the maximum percentage that the price can vary above or below the base
    private final String IE;      // Radical price increase event, when this even happens on a planet, the price may increase astronomically
    private final String CR;      // When this condition is present, the price of this resource is unusually low
    private final String ER;      // When this condition is present, the resource is expensive
    private final int MTL;        // Min price offered in space trade with random trader (not on a planet)
    private final int MTH;        // Max price offered in space trade with random trader (not on a planet)
    
    TradeGood(int MTLP, int MTLU, int TTP, int BasePrice, int IPL, int Var, String IE, String CR, String ER, int MTL, int MTH) {
        this.MTLP = MTLP;
        this.MTLU = MTLU;
        this.TTP = TTP;
        this.BasePrice = BasePrice;
        this.IPL = IPL;
        this.Var = Var;
        this.IE = IE;
        this.CR = CR;
        this.ER = ER;
        this.MTL = MTL;
        this.MTH = MTH;
    }
    /**
     * Calculates the price of the good at the tech level of the port
     * 
     * @param port
     * @return the price
     */
    public int CalculatePrice(Port port) {
        Random rand = new Random();
        int price = BasePrice + IPL * (port.getTechLevel() - MTLP) + BasePrice
                     * ((rand.nextInt(2*Var) - Var / 2) / 100);
        if (price < 0) {
            return 0;
        } 
        if (port.getEvent().equals(IE)) {
            price *= 5;
        }
        if (port.getResources().equals(CR)) {
            price *= .5;
        }
        if (port.getResources().equals(ER)) {
            price *= 1.5;
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
        int q;
        if (techLevel < MTLP) {
            q = 0;
        } else if (techLevel == TTP) {
            q = 50;
        } else {
            q = 20 + 5 * (techLevel - MTLP);
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
     * @return the MTL
     */
    public int getMTL() {
        return MTL;
    }

    /**
     * @return the MTH
     */
    public int getMTH() {
        return MTH;
    }

}