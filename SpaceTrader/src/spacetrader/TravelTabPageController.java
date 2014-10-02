/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        available.setText("Fuel Available: " + Game.getPlayer().getShip().getFuel());
        ports = portsList.getItems();
        RangeChart range = new RangeChart();
        Continent[] continents = range.getChart();
        for (Continent c: continents) {
            ports.add(c.getMainPort().toString());
        }
    }    
    
    @FXML
    private void travelButtonPressed(ActionEvent event) {

    }
}
