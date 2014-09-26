/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
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
import javafx.stage.Stage;


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
    private ObservableList<String> cargo;
    private ObservableList<String> market;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        updateMoneyLabel();
        Port port = Game.getCurrentPort();
        nameLabel.setText(port.getName());
        Bazaar bazaar = port.getBazaar();
        cargo = cargoGoodsList.getItems();
        market = marketGoodsList.getItems();
        goodsForSale = bazaar.getGoodsForSale();
        for (TradeGood tg: goodsForSale.keySet()) {
            int[] pq = goodsForSale.get(tg);
            if (pq[1] != 0 ) {
                market.add(tg.toString() + " Price: " + pq[0] + " Quantity: " + 
                           pq[1]);
            }
        }
    }    
    
    @FXML
    public void buyButtonPressed(ActionEvent event) {
        String longString = marketGoodsList.getSelectionModel().getSelectedItem();
        int spaceIndex = longString.indexOf(' ');
        String goodToBuy = longString.substring(0, spaceIndex);
        System.out.println(goodToBuy);
        TradeGood good = TradeGood.valueOf(goodToBuy);
        int[] pq = goodsForSale.get(good);
        if (Game.getPlayer().getMoney() >= pq[0] && pq[1] > 0) {
            Game.getPlayer().setMoney(Game.getPlayer().getMoney() - pq[0]);
            updateMoneyLabel();
        }
    }
    @FXML
    public void sellButtonPressed(ActionEvent event) {
        
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
}
