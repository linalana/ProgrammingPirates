package spacetrader.engine;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import spacetrader.model.Game;
import spacetrader.model.Port;
import spacetrader.model.ShipYard;
import spacetrader.model.GadgetHold;
import spacetrader.model.Gadget;

/**
 * FXML for Gadget Controller
 *
 * @author Joe
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
     * updates the labels, and sets up player and gadget information
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
        //gets the selected gadget 
        String longString = yardGadgetList.getSelectionModel().getSelectedItem();
        if (longString == null) {
            yardGadgetList.getSelectionModel().selectFirst();
            longString = yardGadgetList.getSelectionModel().getSelectedItem();
        }
        //picks the name of the gadget out to get info from gadgets
        if (longString != null) {
            int spaceIndex = longString.indexOf(' ');
            String goodToBuy = longString.substring(0, spaceIndex);
            Gadget gadget = Gadget.valueOf(goodToBuy);
            int[] priceQuantity = gadgetsForSale.get(gadget);
            int quant = 1;
            int moneySpent = quant * priceQuantity[0];
            if (Game.getPlayer().getMoney() >= quant * priceQuantity[0] && priceQuantity[1] > quant) {
                if (gadgetHold.addGadget(gadget, quant)) {
                    //adds cargo holds if extracargo was purchased
                    if(gadget.name().equals("EXTRACARGO")){
                        Game.getPlayer().getShip().getCargoHold().addFiveBays();
                    }
                    //finishes purchase (sound and updates)
                    ApplicationController.playSound(getClass().getResource("yourbooty.wav").toString());
                    Game.getPlayer().setMoney(Game.getPlayer().getMoney() - quant * priceQuantity[0]);
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
        //gets selected
        String longString = playerGadgetList.getSelectionModel().getSelectedItem();
        if (longString == null) {
            playerGadgetList.getSelectionModel().selectFirst();
            longString = playerGadgetList.getSelectionModel().getSelectedItem();
        }
        //picks out gadget name
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
