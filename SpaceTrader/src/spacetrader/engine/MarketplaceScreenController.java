package spacetrader.engine;

import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.controlsfx.dialog.Dialogs;
import spacetrader.model.Game;
import spacetrader.model.Port;
import spacetrader.SpaceTrader;
import spacetrader.model.Bazaar;
import spacetrader.model.CargoHold;
import spacetrader.model.TradeGood;

/**
 * FXML Controller class.
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
     * Initializes the controller class, gets port bazaar and cargo hold.
     * @param url the url
     * @param rb the resource bundle
     */
    @Override
    public void initialize(final URL url, final ResourceBundle rb) {
        // TODO
        updateMoneyLabel();
        Port port = Game.getCurrentPort();
        nameLabel.setText(port.getName());
        bazaar = port.getBazaar();
        cargoHold = Game.getPlayer().getShip().getCargoHold();
        updateLists();
    }

    /**
     * buys goods.
     *
     * @param event buy button pressed
     */
    @FXML
    public void buyButtonPressed(final ActionEvent event) {
        String longString = marketGoodsList.getSelectionModel()
                .getSelectedItem();
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
            String q = getQuantityFromPlayer("Buying Stuff",
                    "Arr, how much ye want to buy?");
            int quant = 0;
            try {
                quant = Integer.parseInt(q);
            } catch (NumberFormatException e) {
                quant = 0;
            }
            int moneySpent = quant * pq[0];
            if (Game.getPlayer().getMoney() >= moneySpent && pq[1] > quant) {
                if (cargoHold.addCargo(good, quant)) {
                    ApplicationController.playSound(getClass()
                            .getResource("yourbooty.wav").toString());
                    Game.getPlayer().setMoney(Game.getPlayer().getMoney()
                            - quant * pq[0]);
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
            String q = getQuantityFromPlayer("Selling Stuff",
                    "Arr, how much ye want to sell?");
            int quant = 0;
            try {
                quant = Integer.parseInt(q);
            } catch (NumberFormatException e) {
                quant = 0;
            }
            if (Game.getCurrentPort().getTechLevel() > good.getMTLU()) {
                if (Game.getPlayer().getShip().getCargoHold().subtractCargo(good, quant)) {
                    Game.getPlayer().setMoney(Game.getPlayer().getMoney() + (int) (pq[0] * 0.8 * quant));
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
        for (TradeGood tg : goodsForSale.keySet()) {
            int[] pq = goodsForSale.get(tg);
            if (pq[1] != 0) {
                market.add(tg.toString() + " Price: " + pq[0] + " Quantity: "
                        + pq[1]);
            }
        }
        for (TradeGood tg : cargoGoods.keySet()) {
            int q = cargoGoods.get(tg);
            int sellPrice = (int) Math.round(0.8 * goodsForSale.get(tg)[0]);
            if (q > 0) {
                cargo.add(tg.toString() + " Quantity: " + q + " Sell Price: "
                        + sellPrice);
            }
        }
    }

    private String getQuantityFromPlayer(String title, String head) {

        Optional<String> response = Dialogs.create()
                .owner(SpaceTrader.getPrimaryStage())
                .title(title)
                .masthead(head)
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
