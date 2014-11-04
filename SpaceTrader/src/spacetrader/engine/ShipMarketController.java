package spacetrader.engine;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import spacetrader.model.Game;
import spacetrader.model.Ship;
import spacetrader.model.ShipYard;

/**
 * FXML Controller class
 *
 * @author Lil B
 */
public class ShipMarketController implements Initializable {

    @FXML
    private Label selectedLabel;
    @FXML
    private Label currentLabel;
    @FXML
    private Label money;
    @FXML
    private Label price;
    @FXML
    private Label value;
    @FXML
    private Label sCargo;
    @FXML
    private Label sFuel;
    @FXML
    private Label sHull;
    @FXML
    private Label cCargo;
    @FXML
    private Label cFuel;
    @FXML
    private Label cHull;
    @FXML
    private Label mNeeded;
    @FXML
    private Label sGadget;
    @FXML
    private Label sShield;
    @FXML
    private Label sWeapon;
    @FXML
    private Label sQuarter;
    @FXML
    private Label cGadget;
    @FXML
    private Label cShield;
    @FXML
    private Label cWeapon;
    @FXML
    private Label cQuarter;
    @FXML
    private ListView shipList;
    private ObservableList<String> ships;
    private ShipYard shipyard = Game.getCurrentPort().getShipyard();
    private Ship[] s = shipyard.getShips();

    /**
     * Initializes the controller class.fills out UI
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateCurrentLabels();
        ships = shipList.getItems();
        for (int i = 0; i < (Game.getCurrentPort().getTechLevel() + 3); i++) {
            ships.add(s[i].getType());
        }
        currentLabel.setText("Current: " + Game.getPlayer().getShip().getType());

        shipList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            int index = shipList.getSelectionModel().getSelectedIndex();
            selectedLabel.setText("Selected: " + s[index].getType());
            price.setText("Price: " + s[index].getPrice());
            sCargo.setText("Cargo Bays: " + s[index].getCargoBays());
            sFuel.setText("Max Fuel: " + s[index].getMaxRange());
            sHull.setText("Hull Strength: " + s[index].getHullStrength());
            sGadget.setText("Gadget Slots: " + s[index].getGadgetSlots());
            sWeapon.setText("Weapon Slots: " + s[index].getWeaponSlots());
            sShield.setText("Shield Slots: " + s[index].getShieldSlots());
            sQuarter.setText("Shield Slots: " + s[index].getQuarters());
            mNeeded.setText("Money Needed: " + (s[index].getPrice() - Game.getPlayer().getShip().calculateValue()));
        });
    }

    /**
     * updates the information in the UI on the labels
     */
    private void updateCurrentLabels() {
        value.setText("Value: " + Game.getPlayer().getShip().calculateValue());
        money.setText("Money: " + Game.getPlayer().getMoney());
        cCargo.setText("Cargo Bays: " + Game.getPlayer().getShip().getCargoBays());
        cFuel.setText("Max Fuel: " + Game.getPlayer().getShip().getMaxRange());
        cHull.setText("Hull Strength: " + Game.getPlayer().getShip().getHullStrength());
        cGadget.setText("Gadget Slots: " + Game.getPlayer().getShip().getGadgetSlots());
        cWeapon.setText("Weapon Slots: " + Game.getPlayer().getShip().getWeaponSlots());
        cShield.setText("Shield Slots: " + Game.getPlayer().getShip().getShieldSlots());
        cQuarter.setText("Shield Slots: " + Game.getPlayer().getShip().getQuarters());
    }

    /**
     * returns to the main game screen
     *
     * @param event out button pressed
     */
    @FXML
    private void outButtonPressed(ActionEvent event) {
        ApplicationController.getInstance().changeScene("GUI/OpeningGameScreen.fxml");
    }

    /**
     * purchases the selected ship, updates the labels to values after purchase
     * returns to the shipyard from the ship market
     *
     * @param event buy button pressed
     */
    @FXML
    private void buyButtonPressed(ActionEvent event) {
        int index = shipList.getSelectionModel().getSelectedIndex();
        if (Game.getPlayer().getMoney() >= (s[index].getPrice() - Game.getPlayer().getShip().calculateValue())) {
            int v = Game.getPlayer().getShip().calculateValue();
            Game.getPlayer().setShip(index);
            cCargo.setText("Cargo Bays: " + Game.getPlayer().getShip().getCargoBays());
            cFuel.setText("Max Fuel: " + Game.getPlayer().getShip().getMaxRange());
            cHull.setText("Hull Strength: " + Game.getPlayer().getShip().getHullStrength());
            mNeeded.setText("Money Needed: " + (s[index].getPrice() - Game.getPlayer().getShip().calculateValue()));
            currentLabel.setText("Current: " + Game.getPlayer().getShip().getType());
            value.setText("Value: " + Game.getPlayer().getShip().calculateValue());
            int newMoney = Game.getPlayer().getMoney() - s[index].getPrice() + v;
            Game.getPlayer().setMoney(newMoney);
            money.setText("Money: " + Game.getPlayer().getMoney());
        }
    }

}
