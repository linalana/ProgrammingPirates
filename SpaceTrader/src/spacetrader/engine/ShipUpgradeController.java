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
    @FXML
    private Button escapeBuyButton;

    private Player player;
    private int costToRefuel;
    private int barrelsToBuy;
    private static final int COST_OF_FUEL = 100;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        player = Game.getPlayer();
        rumLabel.setText("Rum on ship: " + player.getShip().getFuel());
        moneyLabel.setText("Money: " + player.getMoney());
        int fuelNeeded = player.getShip().getMaxRange()
                - player.getShip().getFuel();
        costToRefuel = fuelNeeded * COST_OF_FUEL;
        if (costToRefuel > player.getMoney()) {
            barrelsToBuy = player.getMoney() / 100;
        } else {
            barrelsToBuy = fuelNeeded;
        }
        buyButton.setText("Refuel for $" + costToRefuel);
        if (player.getShip().hasLifeBoat()) {
            escapeBuyButton.setDisable(true);
        }
    }

    /**
     * refuels, deducts money, update
     *
     * @param event rum button pressed
     */
    @FXML
    private void handleRumButtonAction(ActionEvent event) {
        player.getShip().addFuel(barrelsToBuy);
        player.setMoney(player.getMoney() - costToRefuel);
        updateLabels();
    }

    /**
     * updates the money and rum labels
     */
    public void updateLabels() {
        rumLabel.setText("Rum on ship: " + player.getShip().getFuel());
        moneyLabel.setText("Money: " + player.getMoney());
    }

    /**
     * purchases an escape pod, deducts money and returns to shipyard
     *
     * @param event escape pod button pressed
     */
    @FXML
    private void handleEscapeButtonAction(ActionEvent event) {
        int money = Game.getPlayer().getMoney();
        if (money >= 5000) {
            Game.getPlayer().setMoney(money - 5000);
            Game.getPlayer().getShip().setLifeBoat(true);
            moneyLabel.setText("Money: " + (money - 5000));
            escapeBuyButton.setDisable(true);
        }
    }

}
