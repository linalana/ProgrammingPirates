/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    private ObservableList<String> ports;
    private Continent[] continents;
    private RangeChart range;
    private Port newPort;
    int fuelUsed;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        available.setText("Fuel Available: " + Game.getPlayer().getShip().getFuel());
        ports = portsList.getItems();
        range = new RangeChart();
        continents = range.getChart();
        for (Continent c: continents) {
            ports.add(c.getMainPort().toString());
        }
        
        portsList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            int index = portsList.getSelectionModel().getSelectedIndex();
            int fuelUsed = range.getDists(continents[index]);
            needed.setText("Fuel Needed: " + fuelUsed);
        });
        
        
    }    
    
    @FXML
    private void travelButtonPressed(ActionEvent event) {
        int index = portsList.getSelectionModel().getSelectedIndex();
        newPort = continents[index].getMainPort();
        Turn turn = new Turn(continents[index].getMainPort());
        fuelUsed = range.getDists(continents[index]);
        String result = turn.travel(fuelUsed);
        //set new current port
        Game.setCurrentPort(newPort);
        //deduct fuel from ship based on distance traveled
        Game.getPlayer().getShip().setFuel(-fuelUsed);
        if (result != null) {
            doEncounter();
        } else {
            doTravel();
        }
        
    }

    private void doEncounter() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SpaceTrader.class.getResource("Encounter.fxml"));
            AnchorPane ConfigurationLayout = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Stage playerStage = SpaceTrader.getPrimaryStage();
            Scene scene = new Scene(ConfigurationLayout);
            playerStage.setScene(scene);
            playerStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doTravel() {
        try {
        // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SpaceTrader.class.getResource("OpeningGameScreen.fxml"));
            AnchorPane ConfigurationLayout = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Stage playerStage = SpaceTrader.getPrimaryStage();
            Scene scene = new Scene(ConfigurationLayout);
            playerStage.setScene(scene);
            playerStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
