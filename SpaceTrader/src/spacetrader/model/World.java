package spacetrader.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Michael
 */
public class World implements Serializable {

    private Continent[] continents;
    private RangeChart rangeChart;

    /**
     * Initializes the World. Creates 10 continents, each with a randomly chosen
     * political system - guaranteed to be different from the others by using an
     * ArrayList.
     */
    public World() {
        continents = new Continent[10];
        initializeContinents();
    }

    /**
     * sets up the continents with appropriate names
     */
    private void initializeContinents() {
        ArrayList<String> politicalSystems = createPoliticalSystems();
        Random generator = new Random();
        String[] systems = new String[10];
        for (int i = 0; i < 10; i++) {
            systems[i] = politicalSystems.remove(generator.nextInt(politicalSystems.size()));
        }
        continents[0] = new Continent("America", "capitalist", 300, 20);
        continents[0].setTechLevel(7);
        continents[1] = new Continent("Tortuga", systems[1], 130, 330);
        continents[2] = new Continent("Istantinople", systems[2], 420, 150);
        continents[3] = new Continent("Westeros", systems[3], 100, 150);
        continents[4] = new Continent("Alabasta", systems[4], 480, 320);
        continents[5] = new Continent("Booty Island", systems[5], 310, 320);
        continents[6] = new Continent("Johto", systems[6], 470, 60);
        continents[7] = new Continent("Zaofu", systems[7], 210, 160);
        continents[8] = new Continent("Dynatopia", systems[8], 170, 150);
        continents[9] = new Continent("Kraken Kove", systems[9], 30, 260);
    }

    /**
     * creates the list of political systems
     *
     * @return the list of political systems
     */
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
     *
     * @return String representation of the World
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("The World contains: \n");
        for (int i = 0; i < continents.length; i++) {
            builder.append(continents[i].toString() + "\n");
        }
        return builder.toString();
    }

    /**
     *
     * @return the array of continents
     */
    public Continent[] getContinents() {
        return continents;
    }

    /**
     * @return the rangeChart
     */
    public RangeChart getRangeChart() {
        return rangeChart;
    }

    /**
     * initializes range chart
     */
    public void setRangeChart() {
        if (rangeChart == null) {
            this.rangeChart = new RangeChart();
        }
    }
}
