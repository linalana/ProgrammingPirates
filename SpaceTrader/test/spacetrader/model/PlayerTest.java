package spacetrader.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author alana
 */
public class PlayerTest {
    private Player player;
    
    /**
     * Set up a player to test damage calculation with.
     */
    @Before
    public final void setUp() {
        player = new Player("Test", 5, 5, 3, 2);
        player.setShip(2); //upgrade to ship with shield hold
        player.getShieldHold().addShield(Shield.ENERGY, 1); //25 strength
    }

    /**
     * Test of distributeDamage method, of class Player. When only shield is
     * damaged
     */
    @Test
    public final void testDistributeDamageShieldOnly() {
        System.out.println("Shield Only");
        int totalDamage = 10;
        int result = player.distributeDamage(totalDamage);
        //shield damaged only 10?
        int shieldRemaining = player.getShieldHold().getEnergyStrength();
        assertEquals(15, shieldRemaining);
        //hull at full health?
        int hullRemaining = player.getShip().getHullStrength();
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
    public final void testDistributeDamageShieldAndHull() {
        System.out.println("Shield and Hull");
        int totalDamage = 26;
        int result = player.distributeDamage(totalDamage);
        //shield damaged completely
        int shieldRemaining = player.getShieldHold().getEnergyStrength();
        assertEquals(0, shieldRemaining);
        //hull took 1 damage
        int hullRemaining = player.getShip().getHullStrength();
        assertEquals(9, hullRemaining);

        int expResult = 2; //lives
        assertEquals(expResult, result);

    }

    /**
     * Test of distributeDamage method, of class Player.
     * When no shield
     */
    @Test
    public final void testDistributeDamageNoShield() {
        System.out.println("No shield");
        //remove shield
        player.getShieldHold().decreaseStrength(25);
        int totalDamage = 4;
        int result = player.distributeDamage(totalDamage);
        //shield not there
        int shieldRemaining = player.getShieldHold().getEnergyStrength();
        assertEquals(0, shieldRemaining);
        //hull damaged full amount
        int hullRemaining = player.getShip().getHullStrength();
        assertEquals(6, hullRemaining);
        int expResult = 2; //lives
        assertEquals(expResult, result);

    }

    /**
     * Test of distributeDamage method, of class Player.
     * When destroyed but life boat
     */
    @Test
    public final void testDistributeDamageLifeBoat() {
        System.out.println("LifeBoat");
        player.getShip().setLifeBoat(true);
        int totalDamage = 35;
        int expResult = 1; //lifeboat
        int result = player.distributeDamage(totalDamage);
        assertEquals(expResult, result);
    }

    /**
     * Test of distributeDamage method, of class Player.
     * When destroyed but no life boat
     */
    @Test
    public final void testDistributeDamageDestroyed() {
        System.out.println("Death");
        int totalDamage = 35;
        int expResult = 0; //dead
        int result = player.distributeDamage(totalDamage);
        assertEquals(expResult, result);
    }
}
