/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

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
    private Label shield;
    @FXML
    private ListView<String> cargoGoodsList;
    private HashMap<TradeGood, Integer> cargoGoods;
    private CargoHold cargoHold;
    private ObservableList<String> cargo;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type.setText("Ship Type: " + Game.getPlayer().getShip().getType());
        fuel.setText("Fuel: " + Game.getPlayer().getShip().getFuel());
        shield.setText("Shields: " + Game.getPlayer().getShip().getShieldSlots());
        cargoHold = Game.getPlayer().getShip().getHold();
        cargo = cargoGoodsList.getItems();
        cargoGoods = Game.getPlayer().getShip().getHold().getGoods();
        cargo.clear();
        for (TradeGood tg: cargoGoods.keySet()) {
            int q = cargoGoods.get(tg);
            if (q > 0) {
                cargo.add(tg.toString() + " Quantity: " + q);
            }
        }
    }    
    
}
