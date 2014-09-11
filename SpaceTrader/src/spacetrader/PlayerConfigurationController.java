/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alana Lin
 */
public class PlayerConfigurationController implements Initializable {
    @FXML 
    private Button cancelButton;
    @FXML 
    private Button acceptButton;
    @FXML 
    private Slider fighterSlider;
    @FXML 
    private int fighterPoint;
    @FXML 
    private Slider traderSlider;
    @FXML 
    private int traderPoint;
    @FXML 
    private Slider engineerSlider;
    @FXML 
    private int engineerPoint;
    @FXML 
    private Slider investorSlider;
    @FXML 
    private int investorPoint;
    @FXML 
    private Label pointLabel;
    @FXML 
    private TextField nameText;
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
    private void submitButtonCreatePlayer(ActionEvent event) {
        
        System.out.println("Accept Button");
        if (nameText.getText() != null && !nameText.getText().isEmpty()) {
            Player player = new Player(nameText.getText(), fighterPoint, traderPoint, engineerPoint, investorPoint);
            System.out.println(player.toString());
       }
    }

    @FXML
    private void returnToStart(ActionEvent event) {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SpaceTrader.class.getResource("WelcomeScreen.fxml"));
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
