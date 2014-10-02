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
    @FXML
    private Label fighter;
    @FXML
    private Label investor;
    @FXML
    private Label trader;
    @FXML
    private Label engineer;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        moneyLabel.setText("Money: " + Game.getPlayer().getMoney());
        nameLabel.setText("Name: " + Game.getPlayer().getName());
        fighter.setText("Fighter: " + Game.getPlayer().getFighter());
        investor.setText("Investor: " + Game.getPlayer().getInvestor());
        trader.setText("Trader: " + Game.getPlayer().getTrader());
        engineer.setText("Engineer: " + Game.getPlayer().getEngineer());
    }    
    
}
