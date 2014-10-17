/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.engine;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import spacetrader.model.Game;
import spacetrader.model.Ship;
import spacetrader.model.ShipYard;

/**
 * FXML Controller class
 *
 * @author Lil B
 */
public class ShipMarketController implements Initializable {

    @FXML
    private Label selectedLabel;
    @FXML
    private Label currentLabel;
    @FXML
    private ListView shipList;
    private ObservableList<String> ships;
    private ShipYard shipyard = new ShipYard();
    private Ship[] s = shipyard.getShips();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ships = shipList.getItems();
        for (int i = 0; i < 10; i++) {
            ships.add(s[i].getType());
        }
        currentLabel.setText("Current: " + Game.getPlayer().getShip().getType());
        
        shipList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            int index = shipList.getSelectionModel().getSelectedIndex();
            selectedLabel.setText("Selected: " + s[index].getType());
        });
    }    
 
    
    @FXML
    private void outButtonPressed(ActionEvent event) {
        ApplicationController.changeScene("GUI/OpeningGameScreen.fxml");
    }
    
    @FXML
    private void buyButtonPressed(ActionEvent event) {

    }
    
}
