package spacetrader.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * A cargo hold to hold cargo so that you can cargo while you cargo.
 *
 * @author Murph
 */
public class CargoHold implements Serializable {

    private int amount;
    private int totalGoods;
    private HashMap<TradeGood, Integer> goods;

    /**
     * This is the constructor for CargoHold.
     *
     * @param amount the amount of cargo bays available
     */
    public CargoHold(final int amount) {
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
     * Set initial amount of goods in cargo hold.
     */
    private void setGoods() {
        for (TradeGood g : TradeGood.values()) {
            getGoods().put(g, 0);
        }
    }

    /**
     * Add cargo to your ship.
     *
     * @param g the cargo type to be added
     * @param q the amount of cargo to be added
     * @return true if completed
     */
    public boolean addCargo(TradeGood g, int q) {
        int oldVal = goods.get(g);
        //10 is a balancing number to create a fair game
        if (totalGoods + q <= (getAmount() * 10)) {
            goods.put(g, oldVal + q);
            totalGoods += q;
            return true;
        }
        return false;
    }

    /**
     * Subtract cargo from your ship.
     *
     * @param g the cargo type to be subtracted
     * @param q the amount of cargo to be subtracted
     * @return true if completed
     */
    public boolean subtractCargo(TradeGood g, int q) {
        int oldVal = goods.get(g);
        if (oldVal - q >= 0) {
            goods.put(g, oldVal - q);
            totalGoods -= q;
            return true;
        }
        return false;
    }

    /**
     * Set the amount of cargo bays.
     *
     * @param newAmount the updated cargo bay amount
     */
    public void setAmount(final int newAmount) {
        amount = newAmount;
    }

    /**
     * Add five new cargo bays.
     */
    public void addFiveBays() {
        //adds five new cargo bays
        amount += 5;
    }
    /**
     * Subtract five new cargo bays.
     */
    public void subtractFiveBays(){
        amount -= 5;
    }

    /**
     * Checks for illegal goods.
     *
     * @return true if found
     */
    public boolean findIllegal() {
        return goods.get(TradeGood.NARCOTICS) != 0
                || goods.get(TradeGood.FIREARMS) != 0;
    }

    /**
     * removes narcotics and firearms from cargohold.
     */
    public void removeIllegal() {
        goods.put(TradeGood.NARCOTICS, 0);
        goods.put(TradeGood.FIREARMS, 0);
    }

    /**
     * @return the amount.
     */
    public int getAmount() {
        return amount;
    }
}
