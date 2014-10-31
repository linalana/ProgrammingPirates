package spacetrader.engine;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import spacetrader.model.Continent;
import spacetrader.model.Game;
import spacetrader.model.Port;
import spacetrader.model.RangeChart;
import spacetrader.model.Turn;

/**
 * FXML Controller class
 *
 * @author Michael
 */
public class TravelTabPageController implements Initializable {

    @FXML
    private ListView<String> portsList;
    @FXML
    private Label needed;
    @FXML
    private Label available;
    @FXML
    private Label techLabel;
    @FXML
    private Label politicalLabel;
    private ObservableList<String> ports;
    private Continent[] continents;
    private RangeChart range;
    private Port newPort;
    int fuelUsed;

    /**
     * Initializes the controller class with ports in range, and rum available
     * for use, adds listener to display info on the selected port
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        available.setText("Rum Available: " + Game.getPlayer().getShip().getFuel());
        ports = portsList.getItems();
        range = new RangeChart();
        continents = range.getChart();
        for (Continent c : continents) {
            ports.add(c.getMainPort().toString());
        }

        portsList.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    int index = portsList.getSelectionModel().getSelectedIndex();
                    int fuelUsed = range.getDists(continents[index]);
                    String techlevel = continents[index].getTechLevelString();
                    String politicalSystem = continents[index].getPoliticalSystem();
                    needed.setText("Rum Needed: " + fuelUsed);
                    techLabel.setText("Tech Level: " + techlevel);
                    politicalLabel.setText("Political System: " + politicalSystem);
                });

    }

    /**
     * deducts fuel, moves to new continent, refreshes travel page info
     *
     * @param event travel button pressed
     */
    @FXML
    private void travelButtonPressed(ActionEvent event) {
        ApplicationController.playSound(getClass().getResource("raisethesails.wav").toString());
        int index = portsList.getSelectionModel().getSelectedIndex();
        newPort = continents[index].getMainPort();
        Turn turn = new Turn(continents[index].getMainPort());
        fuelUsed = range.getDists(continents[index]);
        String result = turn.travel(fuelUsed);
        //set new current port
        Game.setCurrentPort(newPort);
        //deduct fuel from ship based on distance traveled
        Game.getPlayer().getShip().addFuel(-fuelUsed);
        if (result != null) {
            doEncounter();
        } else {
            doEvent();
        }

    }

    /**
     * changes to encounter screen
     */
    private void doEncounter() {
        ApplicationController.changeScene("GUI/Encounter.fxml");
    }

    /**
     * changes to random event screen
     */
    private void doEvent() {
        ApplicationController.changeScene("GUI/RandomEvent.fxml");

    }

    /**
     * moves to main screen of game
     */
    private void doTravel() {
        ApplicationController.changeScene("GUI/OpeningGameScreen.fxml");

    }

    /**
     * changes to the map view of the places
     *
     * @param event map button pressed
     */
    @FXML
    private void mapButtonPressed(ActionEvent event) {
        ApplicationController.changeScene("GUI/Map.fxml");
    }
}
