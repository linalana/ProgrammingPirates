/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.engine;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import spacetrader.model.Game;
import spacetrader.model.Player;

/**
 * FXML Controller class
 *
 * @author James
 */
public class ShipUpgradeController implements Initializable {

    @FXML
    private Label rumLabel;
    @FXML
    private Label moneyLabel;
    @FXML
    private Button buyButton;
    
    private Player player;
    private int costToRefuel;
    private int barrelsToBuy;
    private final int COST_OF_FUEL = 100;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        player = Game.getPlayer();
        rumLabel.setText("Rum on ship: " + player.getShip().getFuel());
        moneyLabel.setText("Money: " + player.getMoney());
        int fuelNeeded = player.getShip().getMaxRange() -
                player.getShip().getFuel();
        costToRefuel = fuelNeeded * COST_OF_FUEL;
        if (costToRefuel > player.getMoney()) {
            barrelsToBuy = player.getMoney() / 100;
        } else {
            barrelsToBuy = fuelNeeded;
        }
        buyButton.setText("Refuel for: $" + costToRefuel);
    }    
    
    @FXML
    private void handleRumButtonAction(ActionEvent event) {
        player.getShip().setFuel(barrelsToBuy);
        player.setMoney(player.getMoney()-costToRefuel);
        updateLabels();
    }
    
    public void updateLabels() {
        rumLabel.setText("Rum on ship: " + player.getShip().getFuel());
        moneyLabel.setText("Money: " + player.getMoney());
    }
    
}
