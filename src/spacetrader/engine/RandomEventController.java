/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.engine;



import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import spacetrader.model.Turn;

/**
 * FXML Controller class
 *
 * @author Danny
 */
public class RandomEventController implements Initializable {
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label titleLabel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        descriptionLabel.setText(Turn.getEventDescription());
        titleLabel.setText(Turn.getEventTitle());
    }    
    @FXML 
    public void acceptButtonPressed(ActionEvent e) {
        ApplicationController.changeScene("GUI/OpeningGameScreen.fxml");
    }

}
