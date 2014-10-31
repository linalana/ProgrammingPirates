package spacetrader.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * A gadget hold to hold gadgets
 *
 * @author Murph
 */
public class GadgetHold implements Serializable {

    private int amount;
    private int totalGadgets;
    private HashMap<Gadget, Integer> gadgets;

    /**
     * This is the constructor for GadgetHold.
     *
     * @param amount the amount of gadget slots available
     */
    public GadgetHold(int amount) {
        this.amount = amount;
        totalGadgets = 0;
        gadgets = new HashMap<>();
        setGadgets();
    }

    /**
     * @return gadgets
     */
    public HashMap<Gadget, Integer> getGadgets() {
        return gadgets;
    }

    /**
     * Set amount of gadgets in gadget hold
     */
    public void setGadgets() {
        for (Gadget g : Gadget.values()) {
            getGadgets().put(g, 0);
        }
    }

    /**
     * Add gadget to your ship
     *
     * @param g the gadget type to be added
     * @param q the amount of gadget to be added
     */
    public boolean addGadget(Gadget g, int q) {
        int oldVal = gadgets.get(g);
        if (totalGadgets + q <= (amount)) {
            gadgets.put(g, oldVal + q);
            totalGadgets += q;
            return true;
        }
        return false;
    }

    /**
     * Subtract gadget from your ship
     *
     * @param g the gadget type to be subtracted
     * @param q the amount of gadget to be subtracted
     */
    public boolean subtractGadget(Gadget g, int q) {
        int oldVal = gadgets.get(g);
        if (oldVal - q >= 0) {
            gadgets.put(g, oldVal - q);
            totalGadgets -= q;
            return true;
        }
        return false;
    }
}
