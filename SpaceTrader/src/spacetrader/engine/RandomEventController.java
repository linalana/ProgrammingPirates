/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.engine;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;



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
    }    
    @FXML 
    public void pressAcceptButton(ActionEvent e) {
        
    }
    public void setDescription(String newDescription) {
        descriptionLabel.setText(newDescription);
    }
    public void setTitle(String newTitle) {
        titleLabel.setText(newTitle);
    }
}
