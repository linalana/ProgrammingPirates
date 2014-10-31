/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.engine;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import spacetrader.model.Game;
import spacetrader.model.Port;
import spacetrader.SpaceTrader;
import spacetrader.model.Bazaar;
import spacetrader.model.CargoHold;
import spacetrader.model.TradeGood;

/**
 * FXML Controller class
 *
 * @author Danny
 */
public class MarketplaceScreenController implements Initializable {
    @FXML
    private Label moneyLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private ListView<String> marketGoodsList;
    @FXML
    private ListView<String> cargoGoodsList;
    private HashMap<TradeGood, int[]> goodsForSale;
    private HashMap<TradeGood, Integer> cargoGoods;
    private CargoHold cargoHold;
    private ObservableList<String> cargo;
    private ObservableList<String> market;
    private Bazaar bazaar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        updateMoneyLabel();
        Port port = Game.getCurrentPort();
        nameLabel.setText(port.getName());
        bazaar = port.getBazaar();
        cargoHold = Game.getPlayer().getShip().getCargoHold();
        updateLists();
    }
    
    @FXML
    public void buyButtonPressed(ActionEvent event) {
        String longString = marketGoodsList.getSelectionModel().getSelectedItem();
        if (longString == null) {
            marketGoodsList.getSelectionModel().selectFirst();
            longString = marketGoodsList.getSelectionModel().getSelectedItem();
        }
        if (longString != null) {
            int spaceIndex = longString.indexOf(' ');
            String goodToBuy = longString.substring(0, spaceIndex);
            TradeGood good = TradeGood.valueOf(goodToBuy);
            int[] pq = goodsForSale.get(good);
            //get quantity desired from player 
            String q = getQuantityFromPlayer();
            int quant = 0;
            try {
                quant = Integer.parseInt(q);
            } catch (NumberFormatException e) {
                quant = 0;
            }
            int moneySpent = quant * pq[0];
            if (Game.getPlayer().getMoney() >= quant * pq[0] && pq[1] > quant) {
                if (cargoHold.addCargo(good, quant)) {
                    ApplicationController.playSound(getClass().getResource("yourbooty.wav").toString());
                    Game.getPlayer().setMoney(Game.getPlayer().getMoney() - quant * pq[0]);
                    updateMoneyLabel();
                    bazaar.updateQuantity(good, -1 * quant);
                    updateLists();
                }

            }
        }
    }
    @FXML
    public void sellButtonPressed(ActionEvent event) {
        String longString = cargoGoodsList.getSelectionModel().getSelectedItem();
        if (longString == null) {
            cargoGoodsList.getSelectionModel().selectFirst();
            longString = cargoGoodsList.getSelectionModel().getSelectedItem();
        }
        if (longString != null) {
            int spaceIndex = longString.indexOf(' ');
            String goodToSell = longString.substring(0, spaceIndex);
            TradeGood good = TradeGood.valueOf(goodToSell);
            int[] pq = goodsForSale.get(good);
            //get quantity desired from player 
            String q = getQuantitySellFromPlayer();
            int quant = 0;
            try {
                quant = Integer.parseInt(q);
            } catch (NumberFormatException e) {
                quant = 0;
            }
            int moneySpent = quant * pq[0];
            if (Game.getCurrentPort().getTechLevel() > good.getMTLU()) {
                if (Game.getPlayer().getShip().getCargoHold().subtractCargo(good, quant)) {
                    Game.getPlayer().setMoney(Game.getPlayer().getMoney() + (int)(pq[0] * 0.8 * quant));
                    updateMoneyLabel();
                    bazaar.updateQuantity(good, quant);
                    updateLists();
                }
            }
        }
    }
    @FXML
    public void backButtonPressed(ActionEvent event) {
        ApplicationController.changeScene("GUI/OpeningGameScreen.fxml");
    }
    public void updateMoneyLabel() {
         moneyLabel.setText("Money: " + Game.getPlayer().getMoney());
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
            int sellPrice = (int) Math.round(0.8 * goodsForSale.get(tg)[0]);
            if (q > 0) {
                cargo.add(tg.toString() + " Quantity: " + q + " Sell Price: " +
                        sellPrice);
            }
        }
    }

    private String getQuantityFromPlayer() {
        
        Optional<String> response = Dialogs.create()
            .owner(SpaceTrader.getPrimaryStage())
            .title("Buying Stuff")
            .masthead("Arr, how much ye want to buy?")
            .message("Enter quantity:")
            .showTextInput("0");

        if (response.isPresent()) {
            String result = response.get();
            return result;
        } else {
            return null;
        }

    }
    
    private String getQuantitySellFromPlayer() {
        
        Optional<String> response = Dialogs.create()
            .owner(SpaceTrader.getPrimaryStage())
            .title("Selling Stuff")
            .masthead("Arr, how much ye want to sell?")
            .message("Enter quantity:")
            .showTextInput("0");

        if (response.isPresent()) {
            String result = response.get();
            return result;
        } else {
            return null;
        }

    }
}
