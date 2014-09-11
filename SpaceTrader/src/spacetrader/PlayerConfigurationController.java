/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
    private Slider traderSlider;
    private Slider engineerSlider;
    private Slider investorSlider;
    private Label pointLabel;   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }  
    @FXML
    void UpdatePoints(ActionEvent event) {
        
    }

    @FXML
    void createPlayer(ActionEvent event) {

    }

    @FXML
    void returnToStart(ActionEvent event) {

    }
    
}
