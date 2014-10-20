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
import javafx.event.ActionEvent;
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
    private ObservableList<String> traderHasCargo;
    private ObservableList<String> traderWantsCargo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Turn.getEncounter().getE().fillCargo();
        traderHold = Turn.getEncounter().getE().getShip().getCargoHold();
        
        updateLists();
    }    
    
    public void updateLists() {
        traderHasCargo = traderHasList.getItems();
        traderWantsCargo = traderWantsList.getItems();
        //goodsForSale = bazaar.getGoodsForSale();
        cargoGoods = Game.getPlayer().getShip().getCargoHold().getGoods();
        traderGoods = Turn.getEncounter().getE().getShip().getCargoHold().getGoods();
        traderHasCargo.clear();
        traderWantsCargo.clear();
        for (TradeGood tg: traderGoods.keySet()) {
            int q = traderGoods.get(tg);
            if (q != 0 ) {
                traderHasCargo.add(tg.toString() + " Quantity: " + 
                    q);
            }
        }
        /*
        for (TradeGood tg: cargoGoods.keySet()) {
            int q = cargoGoods.get(tg);
            if (q > 0) {
                cargo.add(tg.toString() + " Quantity: " + q);
            }
        }
        */
    }
    
    @FXML
    public void tradeButtonPressed(ActionEvent event) {
        ApplicationController.changeScene("GUI/OpeningGameScreen.fxml");
    }
    @FXML
    public void backButtonPressed(ActionEvent event) {
        ApplicationController.changeScene("GUI/OpeningGameScreen.fxml");
    }
}
