package spacetrader.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * This class represents a Bazaar, AKA a market place where the player can buy
 * and sell goods.
 *
 * @author Danny
 */
public class Bazaar implements Serializable {

    private final Port port;
    //Key: name of good Value: (price, quantity)
    private HashMap<TradeGood, int[]> goodsForSale;

    /**
     * This is the constructor for a Bazaar.
     *
     * @param port the port of the bazaar
     */
    public Bazaar(final Port port) {
        this.port = port;
        goodsForSale = new HashMap<>();
        setGoodsForSale();
    }

    /**
     * @return the goodsForSale
     */
    public HashMap<TradeGood, int[]> getGoodsForSale() {
        return goodsForSale;
    }

    /**
     * calculate price and quantity.
     */
    public void setGoodsForSale() {
        for (TradeGood g : TradeGood.values()) {
            goodsForSale.put(g, new int[]{g.calculatePrice(port),
                g.calculateSellQuantity(port.getTechLevel())});
        }
    }

    /**
     *
     * Update the quantity (input positive quantity for adding, negative for
     * subtracting).
     *
     * @param g the TradeGood in question
     * @param q the quantity changed (postive or negative)
     * @return if the quantity can be updated
     */
    public boolean updateQuantity(TradeGood g, int q) {

        int[] oldVals = goodsForSale.get(g);
        if (oldVals[1] + q >= 1) {
            goodsForSale.put(g, new int[]{oldVals[0], oldVals[1] + q});
            return true;
        }
        return false;
    }
}
