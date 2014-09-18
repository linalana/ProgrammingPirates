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
            systems[i] = politicalSystems.remove(generator.nextInt(17));
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
    
    public ArrayList<String> createPoliticalSystems() {
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
    
    public String toString() {
        return "Not filled out yet";
    }
    
}
