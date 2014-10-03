/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
    
    }
    
    @FXML
    private void handleOtherButtonAction(ActionEvent event) {
    
    }
    
}
