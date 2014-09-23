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
 *
 * @author Danny
 */
public class MarketplaceScreenController implements Initializable {
   @FXML
    private Label moneyLabel;
   @FXML
    private ListView marketGoodsList;
   @FXML
    private ListView cargoGoodsList;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void buyButtonPressed(ActionEvent event) {
        
    }
    @FXML
    public void sellButtonPressed(ActionEvent event) {
        
    }
    
}
