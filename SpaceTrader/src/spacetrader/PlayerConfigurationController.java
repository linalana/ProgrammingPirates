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
 * handles the GUI for player creation
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
    private Slider traderSlider;
    @FXML 
    private Slider engineerSlider;
    @FXML 
    private Slider investorSlider;
    @FXML 
    private Label pointLabel;
    @FXML 
    private TextField nameText;
    
    private int traderPoint;
    private int fighterPoint;
    private int engineerPoint;
    private int investorPoint;
    private int pointTotal;

    /**
     * Initializes the controller class. 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pointTotal = 15;
        fighterPoint = 0;
        traderPoint = 0;
        engineerPoint = 0;
        investorPoint = 0;

        fighterSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    fighterPoint = UpdatePoints(fighterSlider, fighterPoint);
                }
        });
        traderSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    traderPoint = UpdatePoints(traderSlider, traderPoint);
                }
        });
        engineerSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    engineerPoint = UpdatePoints(engineerSlider, engineerPoint);
                }
        });  
        investorSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    investorPoint = UpdatePoints(investorSlider, investorPoint);
                }
        });         
    }
    /**
     * Checks to see if the slider's new position will go over the total points
     * If it wont, updates the points remaining
     * 
     * @param slider the slider being modified
     * @param pastVal the previous value of the slider
     * @return 
     */
    @FXML
    private int UpdatePoints(Slider slider, int pastVal) {
        int diff = (int)slider.getValue() - pastVal;
        if (pointTotal - diff >= 0) {
            pointTotal -= diff;
            pointLabel.setText("" + pointTotal);
            pastVal = (int)slider.getValue();
        } else {
            int maxPossible = (15 - pointTotal) + pastVal;
            slider.setValue(pointTotal);
        }
        return pastVal;
    }
    
    
    /**
     * Creates a new player from the current set up on the UI when submit button pressed
     * 
     * @param event submit button pressed
     */
    @FXML
    private void submitButtonCreatePlayer(ActionEvent event) {
        
        if (nameText.getText() != null && !nameText.getText().isEmpty() && pointTotal == 0) {
            Player player = new Player(nameText.getText(), fighterPoint, traderPoint, engineerPoint, investorPoint);
            Game game = new Game(player);
            System.out.println(player.toString());
            showOpeningScreen();
            World gameWorld = new World();
            System.out.println(gameWorld.toString());
       }
    }
    /**
     * Returns the screen to the start screen if canceled
     * 
     * @param event cancel button pressed
     */
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
    /**
     * moves to next screen when finished making player
     * 
     * @author Michael
     */
    @FXML
    private void showOpeningScreen() {
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
