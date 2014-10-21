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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        opponent.setText(Turn.getEncounter().getType());
        updateProgressBars();

    }

    private void handleAttackButtonAction(ActionEvent event) {
        int attack = Turn.getEncounter().PlayerAttack();
        if (attack > 0) {
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

        }
        if (Turn.getEncounter().EncountererAttack()) {

        } else {

        }
        updateProgressBars();
    }

    private void handleFleeButtonAction(ActionEvent event) {

    }

    private void handleSurrenderButtonAction(ActionEvent event) {

    }
    /**
     * Updates the percent health of the respective items in the UI
     */
    private void updateProgressBars() {
        double[][] healths = Turn.getEncounter().calculateHealth();
        playerShieldBar.setProgress(healths[0][1]);
        playerHullBar.setProgress(healths[0][0]);
        otherShieldBar.setProgress(healths[1][1]);
        otherHullBar.setProgress(healths[1][0]);
    }

}
