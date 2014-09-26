/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import java.util.HashMap;

/**
 * This class represents a Bazaar, AKA a market place where the player can buy
 * and sell goods.
 * @author Danny
 */
public class Bazaar {
    private final Port port;
    //Key: name of good Value: (price, quantity)
    private HashMap<TradeGood, int[]> goodsForSale;
    /**
     * This is the constructor for a Bazaar.
     * @param port
     */
    public Bazaar(Port port) {
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
     * Calculate price and quantity
     */
    public void setGoodsForSale() {
        for (TradeGood g: TradeGood.values()) {
            goodsForSale.put(g, new int[] {g.CalculatePrice(port), g.CalculateSellQuantity(port.getTechLevel())});
        }   
    }
    
    /**
     *
     * Update the quantity (input positive quantity for adding, negative for subtracting)
     * @param g the TradeGood in question
     * @param q the quantity changed (postive or negative)
     * @return
     */
    public boolean updateQuantity(TradeGood g, int q) {
        
        int[] oldVals = goodsForSale.get(g);
        if (oldVals[1] + q > 1) {
            goodsForSale.put(g, new int[]{oldVals[0], oldVals[1] + q});
            return true;
        }
        return false;    
    }
}