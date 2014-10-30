/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.engine;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
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
 * FXML Controller class
 *
 * @author Lil B
 */
public class WeaponsController implements Initializable {

    @FXML
    private Label moneyLabel;
    @FXML
    private ListView<String> marketWeaponsList;
    @FXML
    private ListView<String> shipWeaponsList;
    private HashMap<Weapon, int[]> weaponsForSale;
    private HashMap<Weapon, Integer> shipWeapons;
    private WeaponHold weaponHold;
    private ShipYard shipYard;
    private ObservableList<String> ship;
    private ObservableList<String> market;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        updateMoneyLabel();
        Port port = Game.getCurrentPort();
        shipYard = port.getShipyard();
        weaponHold = Game.getPlayer().getShip().getWeaponHold();
        updateLists();
    }

    private void updateLists() {
        ship = shipWeaponsList.getItems();
        market = marketWeaponsList.getItems();
        weaponsForSale = shipYard.getWeaponsForSale();
        shipWeapons = Game.getPlayer().getShip().getWeaponHold().getWeapons();
        market.clear();
        ship.clear();
        for (Weapon w: weaponsForSale.keySet()) {
            int[] pq = weaponsForSale.get(w);
            if (pq[1] != 0 ) {
                market.add(w.toString() + " Price: " + pq[0] + " Quantity: " + 
                    pq[1]);
            }
        }
        for (Weapon tg: shipWeapons.keySet()) {
            int q = shipWeapons.get(tg);
            int sellPrice = (int) Math.round(0.8 * weaponsForSale.get(tg)[0]);
            if (q > 0) {
                ship.add(tg.toString() + " Quantity: " + q + " Sell Price: " +
                        sellPrice);
            }
        }
    }
    
    @FXML
    private void updateMoneyLabel() {
        moneyLabel.setText("Money: " + Game.getPlayer().getMoney());
    }
}
