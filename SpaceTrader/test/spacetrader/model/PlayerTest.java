package spacetrader.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alana
 */
public class PlayerTest {
    Player p;
    @Before
    public void setUp() {
        p = new Player("Test", 5, 5, 3, 2);
        p.setShip(2); //upgrade to ship with shield hold
        p.getShieldHold().addShield(Shield.ENERGY, 1); //25 strength
    }
 
    /**
     * Test of distributeDamage method, of class Player. When only shield is
     * damaged
     */
    @Test
    public void testDistributeDamageShieldOnly() {
        System.out.println("Shield Only");
        int totalDamage = 10;
        int result = p.distributeDamage(totalDamage);
        //shield damaged only 10?
        int shieldRemaining = p.getShieldHold().getEnergyStrength();
        assertEquals(15, shieldRemaining);
        //hull at full health?
        int hullRemaining = p.getShip().getHullStrength();
        assertEquals(10, hullRemaining);
        //lives?
        int expResult = 2;
        assertEquals(expResult, result);
        
    }
    /**
     * Test of distributeDamage method, of class Player.
     * When the Damage goes through to the hull
     */
    @Test
    public void testDistributeDamageShieldAndHull() {
        System.out.println("Shield and Hull");
        int totalDamage = 26;
        int result = p.distributeDamage(totalDamage);
        //shield damaged completely
        int shieldRemaining = p.getShieldHold().getEnergyStrength();
        assertEquals(0, shieldRemaining);
        //hull took 1 damage
        int hullRemaining = p.getShip().getHullStrength();
        assertEquals(9, hullRemaining);
        
        int expResult = 2; //lives
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of distributeDamage method, of class Player.
     * When no shield
     */
    @Test
    public void testDistributeDamageNoShield() {
        System.out.println("No shield");
        //remove shield
        p.getShieldHold().decreaseStrength(25);
        int totalDamage = 4;
        int result = p.distributeDamage(totalDamage);
        //shield not there
        int shieldRemaining = p.getShieldHold().getEnergyStrength();
        assertEquals(0, shieldRemaining);
        //hull damaged full amount
        int hullRemaining = p.getShip().getHullStrength();
        assertEquals(6, hullRemaining);
        int expResult = 2; //lives
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of distributeDamage method, of class Player.
     * When destroyed but life boat
     */
    @Test
    public void testDistributeDamageLifeBoat() {
        System.out.println("LifeBoat");
        p.getShip().setLifeBoat(true);
        int totalDamage = 35;
        int expResult = 1; //lifeboat
        int result = p.distributeDamage(totalDamage);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of distributeDamage method, of class Player.
     * When destroyed but no life boat
     */
    @Test
    public void testDistributeDamageDestroyed() {
        System.out.println("Death");
        int totalDamage = 35;
        int expResult = 0; //dead
        int result = p.distributeDamage(totalDamage);
        assertEquals(expResult, result);
    }
}