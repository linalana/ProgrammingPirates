/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.engine;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import spacetrader.model.Game;
import spacetrader.model.Player;
import spacetrader.model.Shield;
import spacetrader.model.ShieldHold;
import spacetrader.model.Ship;


/**
 * FXML Controller class
 *
 * @author Lil B
 */
public class ShieldsController implements Initializable {
    @FXML
    Label emptySlotsLabel;
    @FXML
    Label totalShieldsLabel;
    @FXML
    ListView shieldList;
    @FXML
    ListView holdList;
    @FXML
    Label moneyLabel;
    @FXML
    Button buyButton;
    @FXML
    Button sellButton;
    
    private ObservableList<String> shields;
    private ObservableList<String> hold;
    private ShieldHold shieldHold;
    private Player player;
    private int quanEn;
    private int quanRef;
    private int priceEn;
    private int priceRef;
    /**
     * Initializes the controller class.S
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        shieldHold = Game.getShieldHold();
        player = Game.getPlayer();
        shields = shieldList.getItems();
        hold = holdList.getItems();
        quanEn = Shield.ENERGY.CalculateSellQuantity(Game.getCurrentPort().getTechLevel());
        quanRef = Shield.REFLECTIVE.CalculateSellQuantity(Game.getCurrentPort().getTechLevel());
        priceEn = Shield.ENERGY.CalculatePrice(Game.getCurrentPort());
        priceRef = Shield.REFLECTIVE.CalculatePrice(Game.getCurrentPort());
        updateShieldList();
        updateHoldList();
        updateHoldLabels();
    }   
    /**
     * updates the labels in the fxml
     */
    private void updateHoldLabels() {
        emptySlotsLabel.setText("Empty Shield Slots: " + shieldHold.getEmptySlots());
        totalShieldsLabel.setText("Total Shield Slots: " + shieldHold.getMaxCapacity());
        moneyLabel.setText("Money: " + player.getMoney());
    }
    /**
     * updates the list of shields available
     */
    private void updateShieldList() {
        shields.clear();
        shields.add("Wooden Shield Price: " + priceEn + "(" + quanEn + ")");
        shields.add("Iron Shield Price: " + priceRef  + "(" + quanRef + ")");
    }
    /**
     * updates the list of shields in your shield hold
     */
    private void updateHoldList() {
        hold.clear();
        
            hold.add("Wooden Shield Price: " + (priceEn * 0.8) + " Quantity: " + shieldHold.getTotalEnergyShields());
        
        
            hold.add("Iron Shield Price: " + + (priceRef * 0.8) + " Quantity: " + shieldHold.getTotalReflectiveShields());
        
    }
    /**
     * Buys a shield
     */
    @FXML
    private void buyButtonPressed() {
        int index = shieldList.getSelectionModel().getSelectedIndex();

            if (index == 0 && quanEn > 0 && player.getMoney() > priceEn && shieldHold.addShield(Shield.ENERGY, 1)) {

                player.setMoney(player.getMoney() - priceEn);
                quanEn--;

            } else if (index == 1 && quanRef > 0 && player.getMoney() > priceRef && shieldHold.addShield(Shield.REFLECTIVE, 1)){
                player.setMoney(player.getMoney() - priceRef);
                quanRef--;
            } else {
                
            }
            updateHoldLabels();
            updateHoldList();
            updateShieldList();
        
    }
    /**
     * Sells a shield
     */
    @FXML 
    private void sellButtonPressed() {
    int index = holdList.getSelectionModel().getSelectedIndex();
            if (index == 0 && shieldHold.getTotalEnergyShields() > 0 && Shield.REFLECTIVE.getMTLU() <= Game.getCurrentPort().getTechLevel()) {
                player.setMoney(player.getMoney() + (int)(priceEn * 0.8));
                quanEn++;
                shieldHold.subtractShield(Shield.ENERGY, 1);

            } else if (index == 1 && shieldHold.getTotalReflectiveShields() > 0 && Shield.REFLECTIVE.getMTLU() <= Game.getCurrentPort().getTechLevel()) {
                player.setMoney(player.getMoney() + (int)(priceRef * 0.8));
                quanRef++;
                shieldHold.subtractShield(Shield.REFLECTIVE, 1);
            } else {
                
            }
            updateHoldLabels();
            updateHoldList();
            updateShieldList();
        
    }
    
}
