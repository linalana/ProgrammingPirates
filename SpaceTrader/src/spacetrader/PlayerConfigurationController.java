/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Alana Lin
 */
public class PlayerConfigurationController implements Initializable {
    @FXML 
    private Button cancelButton;
    private Button acceptButton;
    private Slider fighterSlider;
    private int fighterPoint;
    private Slider traderSlider;
    private int traderPoint;
    private Slider engineerSlider;
    private int engineerPoint;
    private Slider investorSlider;
    private int investorPoint;
    private Label pointLabel;
    private int pointTotal;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pointTotal = 15;
        fighterPoint = 0;
        traderPoint = 0;
        engineerPoint = 0;
        investorPoint = 0;
    }
    @FXML
    private void FighterSliderUpdate(ActionEvent event) {
        fighterPoint = UpdatePoints(fighterSlider, fighterPoint);
    }
    
    @FXML
    private void TraderSliderUpdate(ActionEvent event) {
        traderPoint = UpdatePoints(traderSlider, traderPoint);
    }
    
    @FXML
    private void EngineerSliderUpdate(ActionEvent event) {
        engineerPoint = UpdatePoints(engineerSlider, engineerPoint);
    }
    
    @FXML
    private void InvestorSliderUpdate(ActionEvent event) {
        investorPoint = UpdatePoints(investorSlider, investorPoint);
    }
    
    @FXML
    private int UpdatePoints(Slider slider, int pastVal) {
        int diff = (int)slider.getValue() - pastVal;
        if (pointTotal + diff <= 15 && pastVal + diff < 10) {
            pointTotal -= diff;
            pointLabel.setText("" + pointTotal);
            pastVal = (int)slider.getValue();
        } else {
            slider.setValue(pastVal);
        }
        return pastVal;
    }
    
    @FXML
    private void createPlayer(ActionEvent event) {

    }

    @FXML
    private void returnToStart(ActionEvent event) {

    }
    
}
