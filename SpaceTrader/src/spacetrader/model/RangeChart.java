package spacetrader.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Danny
 */
public class RangeChart implements Serializable {

    private int playerX;
    private int playerY;
    private Point[] continents;
    private int fuel;
    private int[] dists;
    private int conversionFactor = 40;
    private Continent[] continentsInRange;

    public RangeChart() {
        continents = new Point[Game.getWorld().getContinents().length];
        for (int i = 0; i < continents.length; i++) {
            continents[i] = new Point(Game.getWorld().getContinents()[i].getX(),
                    Game.getWorld().getContinents()[i].getY());
            //System.out.println("x: " + continents[i].getXPos() + " y: " + continents[i].getYPos());
        }
        updateChart();
    }

    /**
     * This updates the list of continents that you can trave to.
     */
    public void updateChart() {
        playerX = Game.getCurrentPort().getContinent().getX();
        playerY = Game.getCurrentPort().getContinent().getY();
        fuel = Game.getPlayer().getShip().getFuel();
        ArrayList<Continent> conts = new ArrayList<Continent>();
        dists = new int[continents.length];
        //System.out.println(playerX + "    " + playerY);
        for (int i = 0; i < dists.length; i++) {
            dists[i] = (int) (Math.sqrt((Math.pow(playerX - continents[i].getXPos(), 2))
                    + (Math.pow(playerY - continents[i].getYPos(), 2))) / conversionFactor);
            //System.out.println("Dist " + dists[i]);
        }
        for (int i = 0; i < continents.length; i++) {

            if (fuel > dists[i] && Game.getWorld().getContinents()[i] != Game.getCurrentPort().getContinent()) {
                conts.add(Game.getWorld().getContinents()[i]);
            }
        }
        continentsInRange = new Continent[conts.size()];
        continentsInRange = conts.toArray(continentsInRange);
    }

    /**
     *
     * @return an array of continents that you can travel to
     */
    public Continent[] getChart() {
        updateChart();
        for (int i = 0; i < continentsInRange.length; i++) {
            System.out.println(continentsInRange[i].toString());
        }
        return continentsInRange;
    }

    public int getDists(Continent cont) {
        return (int) (Math.sqrt((Math.pow(playerX - cont.getX(), 2)) + (Math.pow(playerY - cont.getY(), 2))) / conversionFactor);
    }

    /**
     * This represents a point with an X and Y coordinate
     */
    private static class Point implements Serializable {

        private int x;
        private int y;

        public Point(int xPos, int yPos) {
            x = xPos;
            y = yPos;
        }

        /**
         *
         * @return the x coordinate of a point
         */
        public int getXPos() {
            return x;
        }

        /**
         *
         * @return the y coordinate of a point
         */
        public int getYPos() {
            return y;
        }
    }
}
