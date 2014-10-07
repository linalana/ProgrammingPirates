/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.engine;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import spacetrader.SpaceTrader;
import spacetrader.model.Turn;

/**
 * FXML Controller class
 *
 * @author alanalin
 */
public class EncounterController implements Initializable {

    @FXML
    private Label encounterLabel;
    @FXML
    private Button fightButton;
    @FXML
    private Button fleeButton;
    @FXML
    private Label pFightPtsLabel;
    @FXML
    private Label eFightPtsLabel;
    @FXML
    private Label pTradePtsLabel;
    @FXML
    private Label pExpLabel;
    @FXML
    private Label eExpLabel;
    @FXML
    private Label pHullHealthLabel;
    @FXML
    private Label eHullHealthLabel;
    @FXML
    private Label pShieldStrengthLabel;
    @FXML
    private Label eShieldStrengthLabel;
    @FXML
    private Label pWepStrengthLabel;
    @FXML
    private Label eWepStrengthLabel;
    
    //other button is either trade for traders or accept inspection for police
    //disapears for pirate
    @FXML
    private Button otherButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String type = Turn.getEncounter().getType();
        encounterLabel.setText("You encountered a " + type);
        switch (type) {
            case "Pirate":
                otherButton.setVisible(false);
                break;
            case "Trader":
                otherButton.setText("Trade");
                break;
            default:
                otherButton.setText("Accept Inspection");
                break;
        }
    }
    
    @FXML
    private void handleFightButtonAction(ActionEvent event) {
        
    }
    
    @FXML
    private void handleFleeButtonAction(ActionEvent event) {
        try {
        // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SpaceTrader.class.getResource("GUI/OpeningGameScreen.fxml"));
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
    
    @FXML
    private void handleOtherButtonAction(ActionEvent event) {
        
    }
    
}
