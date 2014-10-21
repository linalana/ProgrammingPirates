/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.engine;

import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.Collections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import org.controlsfx.dialog.Dialogs;
import spacetrader.SpaceTrader;
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
    public void askButtonPressed(ActionEvent event) {
        String longString = traderHasList.getSelectionModel().getSelectedItem();
        if (longString == null) {
            traderHasList.getSelectionModel().selectFirst();
            longString = traderHasList.getSelectionModel().getSelectedItem();
        }
        else{
            int spaceIndex = longString.indexOf(' ');
            String goodToBuy = longString.substring(0, spaceIndex);
            TradeGood good = TradeGood.valueOf(goodToBuy);
            int maxGoodQ = traderGoods.get(good);
            //get quantity desired from player 
            String q = getQuantityFromPlayer();
            int quant = 0;
            try {
                quant = Integer.parseInt(q);
            } catch (NumberFormatException e) {
                quant = 0;
            }
            if(quant <= maxGoodQ){
                calculateTrade(good, quant);
            }
        }
    }
    @FXML
    public void tradeButtonPressed(ActionEvent event) {
        ApplicationController.changeScene("GUI/OpeningGameScreen.fxml");
    }
    @FXML
    public void backButtonPressed(ActionEvent event) {
        ApplicationController.changeScene("GUI/OpeningGameScreen.fxml");
    }
    
    private String getQuantityFromPlayer() {
        
        Optional<String> response = Dialogs.create()
            .owner(SpaceTrader.getPrimaryStage())
            .title("Trading Stuff")
            .masthead("Arr, how much ye want?")
            .message("Enter quantity:")
            .showTextInput("0");

        if (response.isPresent()) {
            String result = response.get();
            return result;
        } else {
            return null;
        }

    }
    
    private void calculateTrade(TradeGood good, int quant){
        int price = quant*good.getMTL();
        int priceMatch = 0;
        HashMap<TradeGood, Integer> goodMatch = new HashMap<TradeGood, Integer>();
        int arrayNum = 0;
        ArrayList<TradeGood> playerGoods = new ArrayList(cargoGoods.keySet());
        Collections.shuffle(playerGoods); //randomize the 
        for(TradeGood g: playerGoods){
            if(priceMatch >= (price-(price/2)) && priceMatch <= (price+(price/2))){
                break;
            }
            if(g.getMTL() <= (price+(price/2)) && !g.equals(good)){
                for(int i=0; i<cargoGoods.get(g); i++){
                    priceMatch += g.getMTL();
                    goodMatch.put(g, i+1);
                    if(priceMatch >= (price-(price/2)) && priceMatch <= (price+(price/2))){
                        break;
                    }
                }
            }
        }
        if(priceMatch < (price-(price/2))){
            for(TradeGood g: playerGoods){
                if(priceMatch >= (price-(price/2)) && priceMatch <= (price+(price/2))){
                    break;
                }
                if(g.getMTL() < price && !g.equals(good)){
                    for(int i=0; i<cargoGoods.get(g); i++){
                        priceMatch += g.getMTL();
                        goodMatch.put(g, i+1);
                        if(priceMatch >= (price-(price/2)) && priceMatch <= (price+(price/2))){
                            break;
                        }
                    }
                }
            }
        }
        traderWantsCargo.clear();
        for(TradeGood g: goodMatch.keySet()){
            traderWantsCargo.add(goodMatch.get(g) + " " + g.name());
        }
        traderWantsCargo.add("for " + quant + " " + good.name());
    }
}
