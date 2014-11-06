package spacetrader.engine;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import spacetrader.model.Turn;

/**
 * FXML Controller class.
 *
 * @author alanalin
 */
public class EncounterController implements Initializable {

    @FXML
    private Label encounterLabel;
    @FXML
    private Label thingLabel;
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
    private Label eTradePtsLabel;
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
        thingLabel.setText(type);
        fillStats();
        switch (type) {
            case "Pirate":
                otherButton.setText("Surrender");
                pTradePtsLabel.setVisible(false);
                eTradePtsLabel.setVisible(false);
                break;
            case "Trader":
                otherButton.setText("Trade");
                break;
            default:
                otherButton.setText("Accept Inspection");
                otherButton.relocate(210, 349);
                break;
        }
    }

    /**
     * starts the fight in encounter.
     *
     * @param event fight button pressed
     */
    @FXML
    private void handleFightButtonAction(final ActionEvent event) {
        Turn.getEncounter().engageFight();
    }

    /**
     * escapes the encounter.
     *
     * @param event flee button pressed
     */
    @FXML
    private void handleFleeButtonAction(final ActionEvent event) {
        ApplicationController.changeScene("GUI/OpeningGameScreen.fxml");
    }

    /**
     * completes "other" action for each type of encounter.
     *
     * @param event the 3rd button is pressed
     */
    @FXML
    private void handleOtherButtonAction(final ActionEvent event) {
        String type = Turn.getEncounter().getType();
        if (type.equals("Trader")) {
            //trading window
        } else if (type.equals("PoliceForce")) {
            Turn.getEncounter().Inspection();
        }

    }

    /**
     * get's all the data from the player and encounterer and fills the UI to
     * display to player
     */
    private void fillStats() {
        int[] info = Turn.getEncounter().getInfo();
        System.out.println("This is the array:" + Arrays.toString(info));
        pTradePtsLabel.setText(Integer.toString(info[0]));
        pFightPtsLabel.setText(Integer.toString(info[1]));
        pExpLabel.setText(Integer.toString(info[2]));
        pHullHealthLabel.setText(Integer.toString(info[3]));
        pShieldStrengthLabel.setText(Integer.toString(info[4]));
        pWepStrengthLabel.setText(Integer.toString(info[5]));
        eTradePtsLabel.setText(Integer.toString(info[6]));
        eFightPtsLabel.setText(Integer.toString(info[7]));
        eExpLabel.setText(Integer.toString(info[8]));
        eHullHealthLabel.setText(Integer.toString(info[9]));
        eShieldStrengthLabel.setText(Integer.toString(info[10]));
        eWepStrengthLabel.setText(Integer.toString(info[11]));
    }

}
