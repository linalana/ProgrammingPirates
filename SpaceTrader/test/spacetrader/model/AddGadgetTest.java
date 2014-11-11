package spacetrader.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Test of addGadget method, of class GadgetHold.
 * 
 * @author Murph
 */
public class AddGadgetTest {
    
    /**
     * Test of addGadget method, where no gadgets have been added yet.
     */
    @Test
    public void testNoGadgets() {
        GadgetHold hold = new GadgetHold(5);
        int expResult = 0;
        int result = hold.getTotalGadgets();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of addGadget method, where one gadget has been added.
     */
    @Test
    public void testOneGadget() {
        GadgetHold hold = new GadgetHold(5);
        Gadget gadget = Gadget.valueOf("EXTRACARGO");
        hold.addGadget(gadget, 1);
        int expResult = 1;
        int result = hold.getTotalGadgets();
        assertEquals(expResult, result);
    }

    /**
     * Test of addGadget method, where too many gadgets have been added.
     */
    @Test
    public void testTooManyGadgets() {
        GadgetHold hold = new GadgetHold(5);
        Gadget gadget = Gadget.valueOf("EXTRACARGO");
        hold.addGadget(gadget, 6);
        int expResult = 0;
        int result = hold.getTotalGadgets();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of addGadget method, where different gadgets have been added.
     */
    @Test
    public void testDifferentGadgets() {
        GadgetHold hold = new GadgetHold(5);
        Gadget gadget = Gadget.valueOf("EXTRACARGO");
        Gadget gadget2 = Gadget.valueOf("NAVSYSTEM");
        Gadget gadget3 = Gadget.valueOf("AUTOREPAIR");
        hold.addGadget(gadget, 1);
        hold.addGadget(gadget2, 1);
        hold.addGadget(gadget3, 2);
        hold.addGadget(gadget3, 1);
        int expResult = 5;
        int result = hold.getTotalGadgets();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of addGadget method, where gadgets are added and the method
     * should return True.
     */
    @Test
    public void testTrue() {
        GadgetHold hold = new GadgetHold(5);
        Gadget gadget = Gadget.valueOf("EXTRACARGO");
        boolean expResult = true;
        boolean result = hold.addGadget(gadget, 1);;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of addGadget method, where too many gadgets are added and 
     * the method should return False.
     */
    @Test
    public void testFalse() {
        GadgetHold hold = new GadgetHold(5);
        Gadget gadget = Gadget.valueOf("EXTRACARGO");
        boolean expResult = false;
        boolean result = hold.addGadget(gadget, 6);;
        assertEquals(expResult, result);
    }
}
