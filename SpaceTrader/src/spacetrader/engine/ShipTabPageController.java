package spacetrader.engine;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import spacetrader.model.CargoHold;
import spacetrader.model.Gadget;
import spacetrader.model.Game;
import spacetrader.model.Shield;
import spacetrader.model.Ship;
import spacetrader.model.TradeGood;
import spacetrader.model.Weapon;

/**
 * FXML Controller class
 *
 * @author Michael
 */
public class ShipTabPageController implements Initializable {

    @FXML
    private Label type;
    @FXML
    private Label fuel;
    @FXML
    private ListView<String> cargoGoodsList;
    @FXML
    private ListView<String> equipmentList;
    private HashMap<TradeGood, Integer> cargoGoods;
    private HashMap<Weapon, Integer> weapons;
    private HashMap<Shield, Integer> shields;
    private HashMap<Gadget, Integer> gadgets;
    private CargoHold cargoHold;
    private ObservableList<String> cargo;
    private ObservableList<String> weaponsList;
    private ObservableList<String> shieldsList;
    private ObservableList<String> gadgetsList;

    /**
     * Initializes the controller class, filling in the info for the ship page
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type.setText("Ship Type: " + Game.getPlayer().getShip().getType());
        fuel.setText("Rum: " + Game.getPlayer().getShip().getFuel());
        cargo = cargoGoodsList.getItems();
        Ship ship = Game.getPlayer().getShip();
        cargoHold = ship.getCargoHold();
        cargoGoods = ship.getCargoHold().getGoods();
        weapons = ship.getWeaponHold().getWeapons();
        shields = ship.getShieldHold().getShields();
        gadgets = ship.getGadgetHold().getGadgets();
        cargo.clear();
        weaponsList = equipmentList.getItems();
        shieldsList = equipmentList.getItems();
        gadgetsList = equipmentList.getItems();
        for (TradeGood tg : cargoGoods.keySet()) {
            int q = cargoGoods.get(tg);
            if (q > 0) {
                cargo.add(tg.toString() + " Quantity: " + q);
            }
        }
        weaponsList.add("WEAPONS");
        for (Weapon w : weapons.keySet()) {
            int q = weapons.get(w);
            if (q > 0) {
                weaponsList.add(w.toString() + " Quantity: " + q);
            }
        }
        shieldsList.add("SHIELDS");
        for (Shield s : shields.keySet()) {
            int q = shields.get(s);
            if (q > 0) {
                shieldsList.add(s.toString() + " Quantity: " + q);
            }
        }
        gadgetsList.add("GADGETS");
        for (Gadget g: gadgets.keySet()) {
            int q = gadgets.get(g);
            if (q > 0) {
                gadgetsList.add(g.toString() + " Quantity: " + q);
            }
        }
    }

}