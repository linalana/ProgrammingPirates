/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Michael
 */
public class World {
    
    private Continent[] continents;
    private RangeChart rangeChart;
    
    /**
     * Initializes the World. Creates 10 continents, each with a randomly chosen
     * political system - guaranteed to be different from the others by using an 
     * ArrayList.
     */
    public World() {
        continents = new Continent[10];
        rangeChart = new RangeChart();
        initializeContinents();
    }
    
    private void initializeContinents() {
        ArrayList<String> politicalSystems = createPoliticalSystems();
        Random generator = new Random();
        String[] systems = new String[10];
        for (int i = 0; i < 10; i++) {
            systems[i] = politicalSystems.remove(generator.nextInt(politicalSystems.size()));
        }
        continents[0] = new Continent("Zaofu", systems[0]);
        continents[1] = new Continent("Tortuga", systems[1]);
        continents[2] = new Continent("Istantinople", systems[2]);
        continents[3] = new Continent("Westeros", systems[3]);
        continents[4] = new Continent("Alabasta", systems[4]);
        continents[5] = new Continent("Booty Island", systems[5]);
        continents[6] = new Continent("Johto", systems[6]);
        continents[7] = new Continent("Fraxos", systems[7]);
        continents[8] = new Continent("Dynatopia", systems[8]);
        continents[9] = new Continent("Caspiar", systems[9]);
    }
    
    private ArrayList<String> createPoliticalSystems() {
        ArrayList<String> list = new ArrayList<>();
        list.add("anarchy");
        list.add("capitalist");
        list.add("communist");
        list.add("confederacy");
        list.add("corporate");
        list.add("cybernetic");
        list.add("democracy");
        list.add("dictatorship");
        list.add("fascist");
        list.add("feudal");
        list.add("military");
        list.add("monarchy");
        list.add("pacifist");
        list.add("socialist");
        list.add("satori");
        list.add("technocracy");
        list.add("theocracy");
        return list;
    }
    
    /**
     * Overrides the toString() method. Prints out each Continent as a String.
     * @return String representation of the World
     */
    @Override
    public String toString() {
        String result = "The World contains: \n";
        for (int i = 0; i < continents.length; i++) {
            result += continents[i].toString() + "\n";
        }
        return result;
    }
    
}
