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
import javafx.scene.control.ListView;
import spacetrader.model.CargoHold;
import spacetrader.model.Game;
import spacetrader.model.TradeGood;
import spacetrader.model.Turn;

/**
 * FXML Controller class
 *
 * @author Murph
 */
public class TradePageController implements Initializable {
    @FXML
    private ListView<String> traderHasList;
    @FXML
    private ListView<String> traderWantsList;
    private HashMap<TradeGood, Integer> traderGoods;
    private HashMap<TradeGood, Integer> cargoGoods;
    private CargoHold traderHold;
    private CargoHold cargoHold;
    private ObservableList<String> traderCargo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Turn.getEncounter().getE().fillCargo();
        traderHold = Turn.getEncounter().getE().getShip().getCargoHold();
    }    
    
    public void updateLists() {
        cargo = cargoGoodsList.getItems();
        market = marketGoodsList.getItems();
        goodsForSale = bazaar.getGoodsForSale();
        cargoGoods = Game.getPlayer().getShip().getCargoHold().getGoods();
        market.clear();
        cargo.clear();
        for (TradeGood tg: goodsForSale.keySet()) {
            int[] pq = goodsForSale.get(tg);
            if (pq[1] != 0 ) {
                market.add(tg.toString() + " Price: " + pq[0] + " Quantity: " + 
                    pq[1]);
            }
        }
        for (TradeGood tg: cargoGoods.keySet()) {
            int q = cargoGoods.get(tg);
            if (q > 0) {
                cargo.add(tg.toString() + " Quantity: " + q);
            }
        }
    }
    
}
