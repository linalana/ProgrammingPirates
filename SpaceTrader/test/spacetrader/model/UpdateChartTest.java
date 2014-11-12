package spacetrader.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author James
 */
public class UpdateChartTest {

    @Before
    public void setUp() {
        new Game(new Player("James", 0, 0, 0, 0));
        World testWorld = new World();
        Continent[] allContinents = testWorld.getContinents();
        Continent testFarAway = new Continent("Test1",
                                              "capitalist", 1000, 1000);
        Continent[] testContinents = new Continent[allContinents.length + 1];
        System.arraycopy(allContinents, 0,
                         testContinents, 0, allContinents.length);
        testContinents[testContinents.length - 1] = testFarAway;
        testWorld.setContinents(testContinents);
        Game.setWorld(testWorld);
    }


    /**
     * test that the continent does not show up in the range chart when it is
     * out of range.
     */
    @Test
    public void testContinentOutOfRange() {
       RangeChart chart = new RangeChart();
       Continent[] resultContinents = chart.getChart();
       for (Continent c : resultContinents) {
           assertFalse(c.getName().equals("Test1"));
       }
    }

    /**
     * test that the continent does show up in the range chart when it is now
     * in range of the ship.
     */
    @Test
    public void testContinentInRange() {
        Game.getPlayer().setShip(0);
        Game.getPlayer().getShip().setMaxRange(100);
        Game.getPlayer().getShip().fillTank();
        RangeChart chart = new RangeChart();
        Continent[] resultContinents = chart.getChart();
        Continent[] allContinents = Game.getWorld().getContinents();
        assertArrayEquals(resultContinents, allContinents);
    }

    /**
     * test that the range chart is empty when the ship has no fuel.
     */
    @Test
    public void testNoFuel() {
        Game.getPlayer().getShip().addFuel(-100);
        RangeChart chart = new RangeChart();
        Continent[] resultContinents = chart.getChart();
        assertEquals(0, resultContinents.length);
    }
}
