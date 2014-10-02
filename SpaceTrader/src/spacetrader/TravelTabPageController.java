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
        Game.setCurrentPort(Game.getWorld().getContinents()[1].getMainPort());
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
