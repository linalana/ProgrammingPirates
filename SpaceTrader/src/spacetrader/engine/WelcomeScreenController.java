/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.engine;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import spacetrader.SpaceTrader;

/**
 *
 * @author Michael
 */
public class WelcomeScreenController implements Initializable {
    
    
    @FXML
    private Label label;
    
    /**
     * Starts a game, switches to the player configuration screen
     * 
     * @param event submit button pressed
     */
    @FXML
    private void handleStartButtonAction(ActionEvent event) {
        ApplicationController.playSound(getClass().getResource("yearr.wav").toString());
        ApplicationController.changeScene("GUI/PlayerConfiguration.fxml");
    }
    
    /**
     * Starts a game, switches to the player configuration screen
     * 
     * @param event submit button pressed
     */
    @FXML
    private void handleLoadButtonAction(ActionEvent event) {

    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
}
