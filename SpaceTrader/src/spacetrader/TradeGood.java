/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;
import java.util.Random;
/**
 *
 * @author alanalin
 */
public enum TradeGood {

    WATER (0, 0, 2, 30 ,3, 4, "DROUGHT", "LOTSOFWATER", "DESERT", 30, 50),
    FURS (0, 0, 0, 250, 10, 10, "COLD", "RICHFAUNA", "LIFELESS", 230, 280),
    FOOD (1, 0, 1, 100, 5, 5, "CROPFAIL", "RICHSOIL", "POORSOIL", 90, 160),
    ORE (2, 2, 3, 350, 20, 10, "WAR", "MINERALRICH", "MINERALPOOR", 350, 420),
    GAMES (3, 1, 6, 250, -10, 5, "BOREDOM", "ARTISTIC", "NEVER", 160, 270),
    FIREARMS (3, 1, 5, 1250, -75, 100, "WAR", "WARLIKE", "NEVER", 600, 1100),
    MEDICINE (4, 1, 6, 650, -20, 10, "PLAGUE", "LOTSOFHERBS", "NEVER", 400, 700),
    MACHINES (4, 3, 5, 900, -30, 5, "LACKOFWORKERS", "NEVER", "NEVER", 600, 800),
    NARCOTICS (5, 0, 5, 3500, -125, 150, "BOREDOM", "WEIRDMUSHROOMS", "NEVER", 2000, 3000),
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
    
    private int currentPrice;     // Current Price at market
    
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
    
    public int CalculatePrice(int techLevel) {
        Random rand = new Random();
        return BasePrice + IPL * (techLevel - MTLP) + BasePrice * (rand.nextInt(Var) / 100);
    }

}