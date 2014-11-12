package spacetrader.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * a test class to test the calculateValue method in Ship.
 *
 * @author Michael
 */
public class CalculateValueTest {

    /**
     * Setup method which creates a player
     * and game to base the calculations on.
     */
    @Before
    public void setUp() {
        Player player = new Player("Michael", 0,
                    0, 0, 0);
        Game game = new Game(player);
    }

    /**
     * Test of calculateValue method, of class Ship,
     * in which the ship has no value other than its original price.
     */
    @Test
    public void testPriceOnly() {
        Ship instance = new Ship(5);
        int expResult = 30000;
        int result = instance.calculateValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of calculateValue method, of class Ship,
     * in which the ship has value from its cargo hold.
     */
    @Test
    public void testContainsGoods() {
        Ship instance = new Ship(5);
        CargoHold cargo = new CargoHold(2);
        cargo.addCargo(TradeGood.ORE, 1);
        cargo.addCargo(TradeGood.NARCOTICS, 1);
        instance.setCargoHold(cargo);
        int narcoticPrice =  (int) (.8 * TradeGood.NARCOTICS.calculatePrice(Game.getCurrentPort()));
        int orePrice = (int) (.8 * TradeGood.ORE.calculatePrice(Game.getCurrentPort()));
        int expResult = 30000 + narcoticPrice + orePrice;
        int result = instance.calculateValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of calculateValue method, of class Ship,
     * in which the ship has no value from its cargo hold
     * because they only contain goods that can be sold at
     * a higher tech level.
     */
    @Test
    public void testContainsGoodsOverTechLevel() {
        Ship instance = new Ship(5);
        Game.setCurrentPort(new Port("Tester", 3, "RICH SOIL", new Continent("Tester", "Theocracy", 200, 200)));
        CargoHold cargo = new CargoHold(1);
        cargo.addCargo(TradeGood.MINIONS, 1);
        instance.setCargoHold(cargo);
        int expResult = 30000;
        int result = instance.calculateValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of calculateValue method, of class Ship,
     * in which the ship has value from its gadget hold.
     */
    @Test
    public void testContainsGadget() {
        Ship instance = new Ship(5);
        GadgetHold gadget = new GadgetHold(2);
        gadget.addGadget(Gadget.CLOAK, 1);
        gadget.addGadget(Gadget.NAVSYSTEM, 1);
        instance.setGadgetHold(gadget);
        int cloakPrice =  (int) (.8 * Gadget.CLOAK.calculatePrice(Game.getCurrentPort().getTechLevel()));
        int navPrice = (int) (.8 * Gadget.NAVSYSTEM.calculatePrice(Game.getCurrentPort().getTechLevel()));
        int expResult = 30000 + cloakPrice + navPrice;
        int result = instance.calculateValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of calculateValue method, of class Ship,
     * in which the ship has value from its shield hold.
     */
    @Test
    public void testContainsShield() {
        Ship instance = new Ship(5);
        ShieldHold shield = new ShieldHold(2);
        shield.addShield(Shield.ENERGY, 1);
        shield.addShield(Shield.REFLECTIVE, 1);
        instance.setShieldHold(shield);
        int energyPrice =  (int) (.8 * Shield.ENERGY.calculatePrice(Game.getCurrentPort()));
        int reflectivePrice = (int) (.8 * Shield.REFLECTIVE.calculatePrice(Game.getCurrentPort()));
        int expResult = 30000 + energyPrice + reflectivePrice;
        int result = instance.calculateValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of calculateValue method, of class Ship,
     * in which the ship has value from its weapon hold.
     */
    @Test
    public void testContainsWeapon() {
        Ship instance = new Ship(5);
        WeaponHold weapon = new WeaponHold(2);
        weapon.addWeapon(Weapon.BEAM, 1);
        weapon.addWeapon(Weapon.PULSE, 1);
        instance.setWeaponHold(weapon);
        int beamPrice =  (int) (.8 * Weapon.BEAM.calculatePrice(Game.getCurrentPort().getTechLevel()));
        int pulsePrice = (int) (.8 * Weapon.PULSE.calculatePrice(Game.getCurrentPort().getTechLevel()));
        int expResult = 30000 + beamPrice + pulsePrice;
        int result = instance.calculateValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of calculateValue method, of class Ship,
     * in which the ship has value from its cargo, shield, weapon,
     * and gadget holds.
     */
    @Test
    public void testContainsAll() {
        Ship instance = new Ship(5);
        CargoHold cargo = new CargoHold(2);
        cargo.addCargo(TradeGood.ORE, 1);
        cargo.addCargo(TradeGood.NARCOTICS, 1);
        instance.setCargoHold(cargo);
        int narcoticPrice =  (int) (.8 * TradeGood.NARCOTICS.calculatePrice(Game.getCurrentPort()));
        int orePrice = (int) (.8 * TradeGood.ORE.calculatePrice(Game.getCurrentPort()));
        int expGoodResult = 30000 + narcoticPrice + orePrice;
        ShieldHold shield = new ShieldHold(2);
        shield.addShield(Shield.ENERGY, 1);
        shield.addShield(Shield.REFLECTIVE, 1);
        instance.setShieldHold(shield);
        int energyPrice =  (int) (.8 * Shield.ENERGY.calculatePrice(Game.getCurrentPort()));
        int reflectivePrice = (int) (.8 * Shield.REFLECTIVE.calculatePrice(Game.getCurrentPort()));
        int expResult = expGoodResult + energyPrice + reflectivePrice;
        GadgetHold gadget = new GadgetHold(2);
        gadget.addGadget(Gadget.CLOAK, 1);
        gadget.addGadget(Gadget.NAVSYSTEM, 1);
        instance.setGadgetHold(gadget);
        int cloakPrice =  (int) (.8 * Gadget.CLOAK.calculatePrice(Game.getCurrentPort().getTechLevel()));
        int navPrice = (int) (.8 * Gadget.NAVSYSTEM.calculatePrice(Game.getCurrentPort().getTechLevel()));
        expResult += (cloakPrice + navPrice);
        WeaponHold weapon = new WeaponHold(2);
        weapon.addWeapon(Weapon.BEAM, 1);
        weapon.addWeapon(Weapon.PULSE, 1);
        instance.setWeaponHold(weapon);
        int beamPrice =  (int) (.8 * Weapon.BEAM.calculatePrice(Game.getCurrentPort().getTechLevel()));
        int pulsePrice = (int) (.8 * Weapon.PULSE.calculatePrice(Game.getCurrentPort().getTechLevel()));
        expResult += (beamPrice + pulsePrice);
        int result = instance.calculateValue();
        assertEquals(expResult, result);
    }

}
