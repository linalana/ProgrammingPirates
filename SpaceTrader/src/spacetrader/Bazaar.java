/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import java.util.ArrayList;

/**
 * This class represents a Bazaar, AKA a market place where the player can buy
 * and sell goods.
 * @author Danny
 */
public class Bazaar {
    private int techLevel;
    private ArrayList<TradeGood> goodsForSale;
    /**
     * This is the constructor for a Bazaar.
     * @param newTechLevel
     */
    public Bazaar(int newTechLevel) {
        techLevel = newTechLevel;
        goodsForSale = new ArrayList<>();
        goodsForSale.add(TradeGood.WATER);
    }

    /**
     * @return the goodsForSale
     */
    public ArrayList<TradeGood> getGoodsForSale() {
        return goodsForSale;
    }

    /**
     * @param goodsForSale the goodsForSale to set
     */
    public void setGoodsForSale(ArrayList<TradeGood> goodsForSale) {
        this.goodsForSale = goodsForSale;
    }
}