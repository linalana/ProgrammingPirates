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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import spacetrader.model.Game;
import spacetrader.model.Port;
import spacetrader.model.ShipYard;
import spacetrader.model.GadgetHold;
import spacetrader.model.Gadget;

/**
 * FXML Controller class.
 *
 * @author Murph
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
    private static final double SELL_RATIO = 0.8;
    /**
     * Initializes the controller class.
     * @param url the url
     * @param rb the resource bundle
     */
    @Override
    public final void initialize(final URL url, final ResourceBundle rb) {
        // TODO
        updateMoneyLabel();
        Port port = Game.getCurrentPort();
        shipYard = port.getShipyard();
        gadgetHold = Game.getPlayer().getShip().getGadgetHold();
        updateLists();
    }

    /**
     * buys gadget (update player cargo and gadget list).
     *
     * @param event buy button pressed
     */
    @FXML
    public final void buyButtonPressed(final ActionEvent event) {
        String longString =
                yardGadgetList.getSelectionModel().getSelectedItem();
        if (longString == null) {
            yardGadgetList.getSelectionModel().selectFirst();
            longString = yardGadgetList.getSelectionModel().getSelectedItem();
        }
        if (longString != null) {
            int spaceIndex = longString.indexOf(' ');
            String goodToBuy = longString.substring(0, spaceIndex);
            Gadget gadget = Gadget.valueOf(goodToBuy);
            //don't need to check for null because gadget is selected thru
            //listview which is populated with gadgetsForSale
            int[] pq = gadgetsForSale.get(gadget);
            int quant = 1;
            int moneySpent = quant * pq[0];
            if (Game.getPlayer().getMoney() >= moneySpent && pq[1] > quant) {
                if (gadgetHold.addGadget(gadget, quant)) {
                    if (gadget.toString().equals("EXTRACARGO")) {
                        Game.getPlayer().getShip().getCargoHold().addFiveBays();
                    }
                    //using getResource is fine because Controller classes won't
                    //be extended
                    ApplicationController.playSound(getClass()
                            .getResource("yourbooty.wav").toString());
                    Game.getPlayer().setMoney(Game.getPlayer().getMoney()
                            - quant * pq[0]);
                    updateMoneyLabel();
                    shipYard.updateGadgetQuantity(gadget, -1 * quant);
                    updateLists();
                }

            }
        }
    }

    /**
     * Sells from inventory and puts into shop.
     *
     * @param event sell button clicked
     */
    @FXML
    public final void sellButtonPressed(final ActionEvent event) {
        String longString =
                playerGadgetList.getSelectionModel().getSelectedItem();
        if (longString == null) {
            playerGadgetList.getSelectionModel().selectFirst();
            longString = playerGadgetList.getSelectionModel().getSelectedItem();
        }
        if (longString != null) {
            int spaceIndex = longString.indexOf(' ');
            String goodToSell = longString.substring(0, spaceIndex);
            Gadget gadget = Gadget.valueOf(goodToSell);
            int[] pq = gadgetsForSale.get(gadget);
            int quant = 1;
            if (Game.getCurrentPort().getTechLevel() >= gadget.getMTLU()) {
                if (gadgetHold.subtractGadget(gadget, quant)) {
                    if (gadget.toString().equals("EXTRACARGO")) {
                        Game.getPlayer().getShip().getCargoHold()
                                .subtractFiveBays();
                    }
                    Game.getPlayer().setMoney(Game.getPlayer().getMoney()
                            + (int) (pq[0] * SELL_RATIO * quant));
                    updateMoneyLabel();
                    shipYard.updateGadgetQuantity(gadget, quant);
                    updateLists();
                }
            }
        }
    }

    /**
     * updates the amount of money displayed.
     */
    public final void updateMoneyLabel() {
         moneyLabel.setText("Money: " + Game.getPlayer().getMoney());
    }

    /**
     * updates the cargo and shop lists.
     */
    public final void updateLists() {
        int slots = Game.getPlayer().getShip().getGadgetSlots();
        String gadgetType = "";
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
                market.add(g.toString() + " Price: " + pq[0]);
            }
        }
        for (Gadget g : playerGadgets.keySet()) {
            int q = playerGadgets.get(g);
            //.8 is the percentage of the good's original price that it can
            // be sold for.
            int sellPrice = (int) Math.round(SELL_RATIO
                    * gadgetsForSale.get(g)[0]);
            if (q > 0) {
                cargo.add(g.toString() + " Quantity: " + q + " Sell Price: "
                        + sellPrice);
            }
            slots -= q;
        }
        slotLabel.setText("Slots available: " + slots);
    }
}
