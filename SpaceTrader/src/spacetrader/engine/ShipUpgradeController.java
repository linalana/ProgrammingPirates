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
 * FXML Controller class.
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
    private static final int COST_OF_ESCAPE = 5000;

    /**
     * Initializes the controller class.
     * @param url the url
     * @param rb the resource bundle
     */
    @Override
    public final void initialize(final URL url, final ResourceBundle rb) {
        // TODO
        player = Game.getPlayer();
        rumLabel.setText("Rum on ship: " + player.getShip().getFuel());
        moneyLabel.setText("Money: " + player.getMoney());
        int fuelNeeded = player.getShip().getMaxRange()
                - player.getShip().getFuel();
        costToRefuel = fuelNeeded * COST_OF_FUEL;
        if (costToRefuel > player.getMoney()) {
            barrelsToBuy = player.getMoney() / COST_OF_FUEL;
        } else {
            barrelsToBuy = fuelNeeded;
        }
        buyButton.setText("Refuel for $" + costToRefuel);
        if (player.getShip().hasLifeBoat()) {
            escapeBuyButton.setDisable(true);
        }
    }

    /**
     * refuels, deducts money, update.
     *
     * @param event rum button pressed
     */
    @FXML
    private void handleRumButtonAction(final ActionEvent event) {
        player.getShip().addFuel(barrelsToBuy);
        player.setMoney(player.getMoney() - costToRefuel);
        updateLabels();
    }

    /**
     * updates the money and rum labels.
     */
    public final void updateLabels() {
        rumLabel.setText("Rum on ship: " + player.getShip().getFuel());
        moneyLabel.setText("Money: " + player.getMoney());
    }

    /**
     * purchases an escape pod, deducts money and returns to shipyard.
     *
     * @param event escape pod button pressed
     */
    @FXML
    private void handleEscapeButtonAction(final ActionEvent event) {
        int money = Game.getPlayer().getMoney();
        //5000 is cost of life boat
        if (money >= 5000) {
            Game.getPlayer().setMoney(money - COST_OF_ESCAPE);
            Game.getPlayer().getShip().setLifeBoat(true);
            moneyLabel.setText("Money: " + (money - COST_OF_ESCAPE));
            escapeBuyButton.setDisable(true);
        }
    }

}
