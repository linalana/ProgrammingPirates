/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.engine;

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
import spacetrader.SpaceTrader;
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
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        available.setText("Rum Available: " + Game.getPlayer().getShip().getFuel());
        ports = portsList.getItems();
        range = new RangeChart();
        continents = range.getChart();
        for (Continent c: continents) {
            ports.add(c.getMainPort().toString());
        }
        
        portsList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            int index = portsList.getSelectionModel().getSelectedIndex();
            int fuelUsed = range.getDists(continents[index]);
            int techlevel = continents[index].getTechLevel();
            String politicalSystem = continents[index].getPoliticalSystem();
            needed.setText("Rum Needed: " + fuelUsed);
            techLabel.setText("Tech Level: " + techlevel);
            politicalLabel.setText("Political System: " + politicalSystem);
        });
        
        
    }    
    
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
        Game.getPlayer().getShip().setFuel(-fuelUsed);
        if (result != null) {
            doEncounter();
        } else {
            doEvent();
        }
        
    }

    private void doEncounter() {
        ApplicationController.changeScene("GUI/Encounter.fxml");
    }
    private void doEvent() {
        ApplicationController.changeScene("GUI/RandomEvent.fxml");

    }
    private void doTravel() {
        ApplicationController.changeScene("GUI/OpeningGameScreen.fxml");

    }
}
