package spacetrader.engine;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import spacetrader.model.Game;
import spacetrader.model.Player;
import spacetrader.model.Shield;
import spacetrader.model.ShieldHold;

/**
 * FXML Controller class
 *
 * @author Lil B
 */
public class ShieldsController implements Initializable {

    @FXML
    Label emptySlotsLabel;
    @FXML
    Label totalShieldsLabel;
    @FXML
    ListView shieldList;
    @FXML
    Label moneyLabel;
    @FXML
    Button buyButton;

    private ObservableList<String> shields;
    private ShieldHold shieldHold;
    private Player player;

    /**
     * Initializes the controller class updates shield info in UI
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        shieldHold = Game.getShieldHold();
        player = Game.getPlayer();
        shields = shieldList.getItems();
        updateShieldList();
        updateHoldLabels();
    }

    /**
     * updates the info about the current shield hold on the ship and the
     * player's money
     */
    private void updateHoldLabels() {
        emptySlotsLabel.setText("Empty Shield Slots: "
                + shieldHold.getEmptySlots() + " Price: "
                + Shield.ENERGY.CalculatePrice(Game.getCurrentPort()));
        totalShieldsLabel.setText("Total Shield Slots: "
                + shieldHold.getMaxCapacity() + " Price: "
                + Shield.REFLECTIVE.CalculatePrice(Game.getCurrentPort()));
        moneyLabel.setText("Money: " + player.getMoney());
    }

    private void updateShieldList() {
        shields.add("Wooden Shield");
        shields.add("Iron Shield");
    }

    /**
     * buys the selected shield when the buy button is pressed
     */
    @FXML
    private void buyButtonPressed() {
        int index = shieldList.getSelectionModel().getSelectedIndex();

        if (index == 0) {
            shieldHold.addShield(Shield.ENERGY, 1);
            player.setMoney(player.getMoney()
                    - Shield.ENERGY.CalculatePrice(Game.getCurrentPort()));

        } else if (index == 1) {
            shieldHold.addShield(Shield.REFLECTIVE, 1);
            player.setMoney(player.getMoney() - Shield.REFLECTIVE
                    .CalculatePrice(Game.getCurrentPort()));
        } else {

        }
        updateHoldLabels();

    }

}
