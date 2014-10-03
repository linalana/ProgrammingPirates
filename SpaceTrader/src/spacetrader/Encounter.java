/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author alanalin
 */
public class Encounter {
    Player p;
    Encounterer e;
    public Encounter(Player p, Encounterer e) {
        this.p = p;
        this.e = e;
    }
    
    public void startEncounter() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SpaceTrader.class.getResource("Encounter.fxml"));
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
    
    public void PlayerAttack() {
        
    }
    
    public void EncountererAttack() {
        
    }
    
    public String getType() {
        if (e.getClass().equals(Pirate.class)) {
            return "Pirate";
        } else if (e.getClass().equals(Trader.class)) {
            return "Trader";
        } else {
            return "Police Force";
        }
    }
    
    
}
