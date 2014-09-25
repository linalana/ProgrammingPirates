/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Danny
 */
public class MarketplaceScreenController implements Initializable {
    @FXML
    private Label moneyLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private ListView<String> marketGoodsList;
    @FXML
    private ListView<String> cargoGoodsList;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        moneyLabel.setText("Money: " + Game.getPlayer().getMoney());
        Port port = Game.getCurrentPort();
        nameLabel.setText(port.getName());
        Bazaar bazaar = port.getBazaar();
        ObservableList<String> cargo = cargoGoodsList.getItems();
        ObservableList<String> market = marketGoodsList.getItems();
    }    
    
    @FXML
    public void buyButtonPressed(ActionEvent event) {
        
    }
    @FXML
    public void sellButtonPressed(ActionEvent event) {
        
    }
    @FXML
    public void backButtonPressed(ActionEvent event) {
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
