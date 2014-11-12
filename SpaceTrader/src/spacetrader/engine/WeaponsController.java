package spacetrader.engine;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import spacetrader.model.Game;
import spacetrader.model.Port;
import spacetrader.model.ShipYard;
import spacetrader.model.Weapon;
import spacetrader.model.WeaponHold;

/**
 * FXML Controller class.
 *
 * @author James
 */
public class WeaponsController implements Initializable {

    @FXML
    private Label moneyLabel;
    @FXML
    private Label slotsLabel;
    @FXML
    private ListView<String> marketWeaponsList;
    @FXML
    private ListView<String> shipWeaponsList;
    private HashMap<Weapon, int[]> weaponsForSale;
    private HashMap<Weapon, Integer> shipWeapons;
    private WeaponHold weaponHold;
    private ShipYard shipYard;
    private int slotsAvailable;
    private ObservableList<String> ship;
    private ObservableList<String> market;

    /**
     * Initializes the controller class.
     * @param url is the url
     * @param rb is the resource bundle
     */
    @Override
    public final void initialize(final URL url, final ResourceBundle rb) {
        updateMoneyLabel();
        slotsAvailable = Game.getPlayer().getShip().getWeaponHold()
                .getEmptySlots();
        slotsLabel.setText("Slots available: " + slotsAvailable);
        Port port = Game.getCurrentPort();
        shipYard = port.getShipyard();
        weaponHold = Game.getPlayer().getShip().getWeaponHold();
        updateLists();
    }
    /**
     * Updates the lists of weapons.
     */
    private void updateLists() {
        ship = shipWeaponsList.getItems();
        market = marketWeaponsList.getItems();
        weaponsForSale = shipYard.getWeaponsForSale();
        shipWeapons = Game.getPlayer().getShip().getWeaponHold().getWeapons();
        market.clear();
        ship.clear();
        for (Weapon w : weaponsForSale.keySet()) {
            int[] pq = weaponsForSale.get(w);
            if (pq[1] != 0) {
                market.add(w.toString() + " Price: " + pq[0]); // + " Quantity:"
//                        + pq[1]);
            }
        }
        for (Weapon w : shipWeapons.keySet()) {
            int q = shipWeapons.get(w);
            //0.8 is the sell back price fraction
            int sellPrice = (int) Math.round(0.8 * weaponsForSale.get(w)[0]);
            if (q > 0) {
                ship.add(w.toString() + " Sell Price: " + sellPrice);
            }
        }
    }
    /**
     * updates the label for how much money the player has.
     */
    @FXML
    private void updateMoneyLabel() {
        moneyLabel.setText("Money: " + Game.getPlayer().getMoney());
    }
    /**
     * updates the label for slots you have.
     */
    @FXML
    private void updateSlotsLabel() {
        slotsLabel.setText("Slots available: " + slotsAvailable);
    }
    /**
     * when the buy button is pressed.
     * @param event the event
     */
    @FXML
    public final void buyButtonPressed(final ActionEvent event) {
        String longString = marketWeaponsList.getSelectionModel()
                .getSelectedItem();
        if (longString == null) {
            marketWeaponsList.getSelectionModel().selectFirst();
            longString = marketWeaponsList.getSelectionModel()
                    .getSelectedItem();
        }
        if (longString != null) {
            int spaceIndex = longString.indexOf(' ');
            String goodToBuy = longString.substring(0, spaceIndex);
            Weapon weapon = Weapon.valueOf(goodToBuy);
            int[] pq = weaponsForSale.get(weapon);
            //get quantity desired from player
//            String q = getQuantityFromPlayer();
            int quant = 1;
            int moneySpent = quant * pq[0];
            if (Game.getPlayer().getMoney() >= moneySpent && pq[1] > quant) {
                if (weaponHold.addWeapon(weapon, quant)) {
                    ApplicationController.playSound(getClass()
                            .getResource("yourbooty.wav").toString());
                    Game.getPlayer().setMoney(Game.getPlayer()
                            .getMoney() - quant * pq[0]);
                    updateMoneyLabel();
                    slotsAvailable -= quant;
                    updateSlotsLabel();
                    updateLists();
                }
            }
        }
    }
    /**
     * when the sell button is pressed.
     * @param event the event that is passed in
     */
    @FXML
    public final void sellButtonPressed(final ActionEvent event) {
        String longString = shipWeaponsList.getSelectionModel()
                .getSelectedItem();
        if (longString == null) {
            shipWeaponsList.getSelectionModel().selectFirst();
            longString = shipWeaponsList.getSelectionModel().getSelectedItem();
        }
        if (longString != null) {
            int spaceIndex = longString.indexOf(' ');
            String goodToSell = longString.substring(0, spaceIndex);
            Weapon weapon = Weapon.valueOf(goodToSell);
            int[] pq = weaponsForSale.get(weapon);
            //get quantity desired from player
            int quant = 1;
            if (Game.getCurrentPort().getTechLevel() > weapon.getMTLU()) {
                if (Game.getPlayer().getShip().getWeaponHold()
                        .subtractWeapon(weapon, quant)) {
                    //0.8 is the sell back price portion
                    Game.getPlayer().setMoney(Game.getPlayer().getMoney()
                            + (int) (pq[0] * 0.8 * quant));
                    updateMoneyLabel();
                    slotsAvailable += quant;
                    updateSlotsLabel();
                    updateLists();
                }
            }
        }
    }
}
