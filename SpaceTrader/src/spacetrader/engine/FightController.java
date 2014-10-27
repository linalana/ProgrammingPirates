/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.engine;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import spacetrader.model.Encounter;
import spacetrader.model.Turn;

/**
 * FXML Controller class
 *
 * @author alanalin
 */
public class FightController implements Initializable {

    @FXML
    private ProgressBar playerShieldBar;
    @FXML
    private ProgressBar playerHullBar;
    @FXML
    private ProgressBar otherShieldBar;
    @FXML
    private ProgressBar otherHullBar;
    @FXML
    private Button attackBtn;
    @FXML
    private Button fleeBtn;
    @FXML
    private Button surrenderBtn;
    @FXML
    private Label opponent;
    @FXML
    private ImageView otherImage;
    @FXML
    private ImageView playerImage;
    @FXML
    private Label damageToPlayer;
    @FXML
    private Label damageToOther;

    private Encounter encounter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        opponent.setText(Turn.getEncounter().getType());
        updateProgressBars();
        encounter = Turn.getEncounter();

    }
    @FXML
    private void handleAttackButtonAction(ActionEvent event) {
        int attack = encounter.PlayerAttack();
        if (attack > 0) {
            //display attack image and damage for 2 seconds
            try {
            otherImage.setVisible(true);
            damageToOther.setText(Integer.toString(attack));
            damageToOther.setVisible(true);
            TimeUnit.SECONDS.sleep(2);
            otherImage.setVisible(false);
            damageToOther.setVisible(false);
            } catch (InterruptedException ex) {
                Logger.getLogger(Encounter.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //you missed
        }
        boolean otherLiving = encounter.DamageEncounterer(attack);
        updateProgressBars();
        if (otherLiving) {
            if (encounter.eWillAttack()) {
                //display encounterer's damage
                int damage = encounter.EncountererAttack();
                    if (damage > 0) {
                        //display attack image and damage for 2 seconds
                        try {
                        playerImage.setVisible(true);
                        damageToPlayer.setText(Integer.toString(damage));
                        damageToPlayer.setVisible(true);
                        TimeUnit.SECONDS.sleep(2);
                        playerImage.setVisible(false);
                        damageToPlayer.setVisible(false);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Encounter.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        //they missed
                    }
                int result = encounter.DamagePlayer(damage);
                if (result == 0) { //D.E.D dead
                    //GAME OVER
                } else if (result == 1) { //life boat escape
                    //transfer to nearest port with no ship but a lifeboat
                } else if (result == 2) {
                    //continue
                }
                updateProgressBars();
            } else {
                //they flee?
            }
        } else {
            //you won! distribute booty
        }

    }
    @FXML
    private void handleFleeButtonAction(ActionEvent event) {
        //flee in encounter
    }
    @FXML
    private void handleSurrenderButtonAction(ActionEvent event) {
        //surrender in encounter
    }
    /**
     * Updates the percent health of the respective items in the UI
     */
    private void updateProgressBars() {
        double[][] healths = encounter.calculateHealth();
        playerShieldBar.setProgress(healths[0][1]);
        playerHullBar.setProgress(healths[0][0]);
        otherShieldBar.setProgress(healths[1][1]);
        otherHullBar.setProgress(healths[1][0]);
    }

}
