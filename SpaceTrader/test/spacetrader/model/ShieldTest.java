package spacetrader.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Danny
 */
public class ShieldTest {
    /**
     * Tests the calculateSellQuantity method in Shield for tech level values
     * below the minimum required value for a shipyard to sell a particular
     * type of shield.
     */
    @Test
    public void testLowTechLevel() {
        int result = Shield.REFLECTIVE.calculateSellQuantity(3);
        int expectedVal = 0;
        assertEquals(result, expectedVal);
        result = Shield.ENERGY.calculateSellQuantity(-1);
        expectedVal = 0;
        assertEquals(result, expectedVal);
    }
    /**
     * Tests the calculateSellQuantity method in Shield for tech level values
     * equaling the tech level required for maximum production of that type of
     * shield.
     */
    @Test
    public void testMaxTechLevel() {
        int result = Shield.REFLECTIVE.calculateSellQuantity(7);
        assertTrue(result > 42 && result < 58);
        result = Shield.ENERGY.calculateSellQuantity(3);
        assertTrue(result > 42 && result < 58);
    }
    
    /**
     * Test the calculateSellQuantity method in Shield for tech level values
     * that are greater than the minimum value required to produce the shield
     * but not equal to the tech level that produces the most of that shield.
     */
    @Test
    public void testOtherTechLevel() {
        int result = Shield.REFLECTIVE.calculateSellQuantity(4);
        assertTrue((20 - 8 <= result) && (20 + 8 >= result));
        result = Shield.ENERGY.calculateSellQuantity(4);
        assertTrue((20 + 5 * (4) - 8 < result) && (20 + 5 * (4) + 8 > result));
        
    }
}