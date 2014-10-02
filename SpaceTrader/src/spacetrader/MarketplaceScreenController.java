/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.controlsfx.control.ButtonBar;
import org.controlsfx.control.ButtonBar.ButtonType;
import org.controlsfx.control.action.AbstractAction;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

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
        cargoHold = Game.getPlayer().getShip().getHold();
        updateLists();
    }
    
    @FXML
    public void buyButtonPressed(ActionEvent event) {
        
        String longString = marketGoodsList.getSelectionModel().getSelectedItem();
        if (longString == null) {
            marketGoodsList.getSelectionModel().selectFirst();
            longString = marketGoodsList.getSelectionModel().getSelectedItem();
        }
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
            if (moneySpent > Game.getPlayer().getMoney()) {
                
            }
        if (Game.getPlayer().getMoney() >= quant * pq[0] && pq[1] > quant) {
            if (cargoHold.addCargo(good, quant)) {
                Game.getPlayer().setMoney(Game.getPlayer().getMoney() - quant * pq[0]);
                updateMoneyLabel();
                bazaar.updateQuantity(good, -1 * quant);
                updateLists();
            }
        }
    }
    @FXML
    public void sellButtonPressed(ActionEvent event) {
        String longString = cargoGoodsList.getSelectionModel().getSelectedItem();
        int spaceIndex = longString.indexOf(' ');
        String goodToSell = longString.substring(0, spaceIndex);
        TradeGood good = TradeGood.valueOf(goodToSell);
        int[] pq = goodsForSale.get(good);
        if (Game.getPlayer().getShip().getHold().subtractCargo(good, 1)) {
            Game.getPlayer().setMoney(Game.getPlayer().getMoney() + pq[0]);
            updateMoneyLabel();
            bazaar.updateQuantity(good, 1);
            updateLists();
        }
    }
    @FXML
    public void backButtonPressed(ActionEvent event) {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SpaceTrader.class.getResource("OpeningGameScreen.fxml"));
            AnchorPane ConfigurationLayout = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Stage playerStage = SpaceTrader.getPrimaryStage();
            Scene scene = new Scene(ConfigurationLayout);
            playerStage.setScene(scene);
            playerStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void updateMoneyLabel() {
         moneyLabel.setText("Money: " + Game.getPlayer().getMoney());
    }
    public void updateLists() {
        cargo = cargoGoodsList.getItems();
        market = marketGoodsList.getItems();
        goodsForSale = bazaar.getGoodsForSale();
        cargoGoods = Game.getPlayer().getShip().getHold().getGoods();
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

    private String getQuantityFromPlayer() {
        
        Optional<String> response = Dialogs.create()
            .owner(new Stage())
            .title("Quantity")
            .masthead("How much ya want?")
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
