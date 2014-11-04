package spacetrader.engine;

import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.controlsfx.dialog.Dialogs;
import spacetrader.model.Game;
import spacetrader.model.Port;
import spacetrader.SpaceTrader;
import spacetrader.model.ShipYard;
import spacetrader.model.GadgetHold;
import spacetrader.model.Gadget;

/**
 * FXML Controller class
 *
 * @author Danny
 */
public class GadgetsController implements Initializable {

    @FXML
    private Label moneyLabel;
    @FXML
    private Label slotLabel;
    @FXML
    private ListView<String> yardGadgetList;
    @FXML
    private ListView<String> playerGadgetList;
    private HashMap<Gadget, int[]> gadgetsForSale;
    private HashMap<Gadget, Integer> playerGadgets;
    private GadgetHold gadgetHold;
    private ObservableList<String> cargo;
    private ObservableList<String> market;
    private ShipYard shipYard;
    private int slots;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        slots = Game.getPlayer().getShip().getGadgetSlots();
        updateMoneyLabel();
        Port port = Game.getCurrentPort();
        shipYard = port.getShipyard();
        gadgetHold = Game.getPlayer().getShip().getGadgetHold();
        updateLists();
    }

    /**
     * buys gadget (update player cargo and gadget list)
     *
     * @param event buy button pressed
     */
    @FXML
    public void buyButtonPressed(ActionEvent event) {
        String longString = yardGadgetList.getSelectionModel().getSelectedItem();
        if (longString == null) {
            yardGadgetList.getSelectionModel().selectFirst();
            longString = yardGadgetList.getSelectionModel().getSelectedItem();
        }
        if (longString != null) {
            int spaceIndex = longString.indexOf(' ');
            String goodToBuy = longString.substring(0, spaceIndex);
            Gadget gadget = Gadget.valueOf(goodToBuy);
            int[] pq = gadgetsForSale.get(gadget);
            //get quantity desired from player 
//            String q = getQuantityFromPlayer("Buying Stuff", "Arr, how much ye want to buy?");
            int quant = 1;
            if (Game.getPlayer().getMoney() >= quant * pq[0] && pq[1] > quant) {
                if (gadgetHold.addGadget(gadget, quant)) {
                    ApplicationController.playSound(getClass().getResource("yourbooty.wav").toString());
                    Game.getPlayer().setMoney(Game.getPlayer().getMoney() - quant * pq[0]);
                    updateMoneyLabel();
                    shipYard.updateGadgetQuantity(gadget, -1 * quant);
                    slots -= quant;
                    updateLists();
                }

            }
        }
    }

    /**
     * Sells from inventory and puts into shop
     *
     * @param event sell button clicked
     */
    @FXML
    public void sellButtonPressed(ActionEvent event) {
        String longString = playerGadgetList.getSelectionModel().getSelectedItem();
        if (longString == null) {
            playerGadgetList.getSelectionModel().selectFirst();
            longString = playerGadgetList.getSelectionModel().getSelectedItem();
        }
        if (longString != null) {
            int spaceIndex = longString.indexOf(' ');
            String goodToSell = longString.substring(0, spaceIndex);
            Gadget gadget = Gadget.valueOf(goodToSell);
            int[] pq = gadgetsForSale.get(gadget);
            //get quantity desired from player 
//            String q = getQuantityFromPlayer("Selling Stuff", "Arr, how much ye want to sell?");
            int quant = 1;
            if (Game.getCurrentPort().getTechLevel() > gadget.getMTLU()) {
                if (Game.getPlayer().getShip().getGadgetHold().subtractGadget(gadget, quant)) {
                    Game.getPlayer().setMoney(Game.getPlayer().getMoney() + (int) (pq[0] * 0.8 * quant));
                    updateMoneyLabel();
                    shipYard.updateGadgetQuantity(gadget, quant);
                    slots += quant;
                    updateLists();
                }
            }
        }
    }

    /**
     * updates the amount of money displayed
     */
    public void updateMoneyLabel() {
        moneyLabel.setText("Money: " + Game.getPlayer().getMoney());
    }

    /**
     * updates the cargo and shop lists
     */
    public void updateLists() {
        slotLabel.setText("Slots available: " + slots);
        cargo = playerGadgetList.getItems();
        market = yardGadgetList.getItems();
        gadgetsForSale = shipYard.getGadgetsForSale();
        playerGadgets = Game.getPlayer().getShip().getGadgetHold().getGadgets();
        market.clear();
        cargo.clear();
        for (Gadget g : gadgetsForSale.keySet()) {
            int[] pq = gadgetsForSale.get(g);
            if (pq[1] != 0) {
                market.add(g.toString() + " Price: " + pq[0]);// + " Quantity: "
//                        + pq[1]);
            }
        }
        for (Gadget g : playerGadgets.keySet()) {
            int q = playerGadgets.get(g);
            int sellPrice = (int) Math.round(0.8 * gadgetsForSale.get(g)[0]);
            if (q > 0) {
                cargo.add(g.toString() + " Sell Price: " + sellPrice);
            }
        }
    }
}