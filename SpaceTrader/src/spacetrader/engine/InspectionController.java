/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.engine;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import spacetrader.model.Turn;

/**
 * FXML Controller class
 *
 * @author alanalin
 */
public class InspectionController implements Initializable {

    @FXML
    private Label header;
    @FXML
    private Label text;
    @FXML
    private Button button;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int[] info = Turn.getEncounter().getInspection();
        if (info[0] == 1) {
            header.setText("Inspection Failed");
            String failText = "The officer has confiscated your illegal goods. "
                    + "Your police record has declined";
            if (info[1] != 0) {
                failText += " You have been fined " + info[1] + " dubloons.";
            }
            text.setText(failText);
        }
    }

    @FXML
    private void handleButtonPressed(ActionEvent event) {
        ApplicationController.changeScene("GUI/OpeningGameScreen.fxml");
    }

}
