/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.engine;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
    private ProgressBar otherSheildBar;
    @FXML
    private ProgressBar otherHullBar;
    @FXML
    private Button attackBtn;
    @FXML
    private Button fleeBtn;
    @FXML
    private Button surrenderBtn;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void handleAttackButtonAction(ActionEvent event) {

    }

    private void handleFleeButtonAction(ActionEvent event) {

    }

    private void handleSurrenderButtonAction(ActionEvent event) {

    }

}
