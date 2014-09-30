/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;
import java.util.ArrayList;


/**
 *
 * @author Danny
 */
public class RangeChart {
    int playerX;
    int playerY;
    Point[] continents;
    int fuel;
    int[] dists;
    Continent[] continentsInRange;
    
    public RangeChart() {
        continents = new Point[Game.getWorld().getContinents().length];
        for (int i = 0; i < continents.length; i++) {
            continents[i] = new Point(Game.getWorld().getContinents()[i].getX(),
                    Game.getWorld().getContinents()[i].getY());
        }
        updateChart();
    }
    public void updateChart() {
        playerX = Game.getPlayer().getLocation()[0];
        playerY = Game.getPlayer().getLocation()[1];
        fuel = Game.getPlayer().getShip().getFuel();
        ArrayList<Continent> conts = new ArrayList<Continent>();
        dists = new int[continents.length];
        for (int i = 0; i < dists.length; i++) {
            dists[i] = (int)(Math.sqrt(((playerX + continents[i].getXPos())^2) +
                    ((playerY + continents[i].getYPos())^2)) / 25);
        }
        for (int i = 0; i < continents.length; i++) {
            if (fuel < dists[i]) {
                conts.add(Game.getWorld().getContinents()[i]);
            }
        }
        continentsInRange = new Continent[conts.size()];
        continentsInRange = conts.toArray(continentsInRange);
    }
    public Continent[] getChart() {
        updateChart();
        return continentsInRange;
    }
    private class Point {
        private int x;
        private int y;
        public Point(int xPos, int yPos) {
            x = xPos;
            y = yPos;
        }
        public int getXPos() {
            return x;
        }
        public int getYPos() {
            return y;
        }
    }
}
