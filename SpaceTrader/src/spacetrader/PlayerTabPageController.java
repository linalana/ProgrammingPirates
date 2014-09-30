/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Michael
 */
public class PlayerTabPageController implements Initializable {
    @FXML
    private Label moneyLabel;
    @FXML
    private Label nameLabel;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        moneyLabel.setText("Money: " + Game.getPlayer().getMoney());
        nameLabel.setText("Name: " + Game.getPlayer().getName());
    }    
    
}
