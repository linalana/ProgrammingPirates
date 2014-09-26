/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;

import java.util.HashMap;

/**
 * A cargo hold to hold cargo so that you can cargo while you cargo
 * @author Murph
 */
public class CargoHold {
    private int amount;
    private int totalGoods;
    private HashMap<TradeGood, Integer> goods;
    
    /**
     * This is the constructor for CargoHold.
     * @param amount the amount of cargo bays available
     */
    public CargoHold(int amount){
        this.amount = amount;
        totalGoods = 0;
        goods = new HashMap<>();
        setGoods();
    }
    
    /**
     * @return goods
     */
    public HashMap<TradeGood, Integer> getGoods() {
        return goods;
    }
    
    /**
     * Set initial amount of goods in cargo hold
     */
    public void setGoods() {
        for (TradeGood g: TradeGood.values()) {
            getGoods().put(g, 0);
        }   
    }
    
    /**
     * Add cargo to your ship
     * @param g the cargo type to be added
     * @param q the amount of cargo to be added
     */
    public boolean addCargo(TradeGood g, int q) {
        int oldVal = goods.get(g);
        if (totalGoods + q <= (amount*10)){
            goods.put(g, oldVal + q);
            totalGoods += q;
            return true;
        }
        return false;    
    }
    
    /**
     * Subtract cargo from your ship
     * @param g the cargo type to be subtracted
     * @param q the amount of cargo to be subtracted
     */
    public boolean subtractCargo(TradeGood g, int q) {
        int oldVal = goods.get(g);
        if (oldVal - q >= 0){
            goods.put(g, oldVal - q);
            totalGoods -= q;
            return true;
        }
        return false;    
    }
    
    /**
     * Set the amount of cargo bays
     * @param newAmount the updated cargo bay amount
     */
    public void setAmount(int newAmount){
        amount = newAmount;
    }
}
