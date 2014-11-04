
package spacetrader.engine;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import spacetrader.model.Game;
import spacetrader.model.Port;

/**
 * FXML Controller class
 *
 * @author Michael
 */
public class CurrentPortTabPageController implements Initializable {
   
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label portName;
    @FXML
    private Label location;
    @FXML
    private Label techLevel;
    @FXML
    private Label politicalSystem;
    @FXML
    private Label randomEvent;
    @FXML
    private Button shipyard;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Port currentPort = Game.getCurrentPort();
        portName.setText(currentPort.getName());
        location.setText("Location: " + "(" + currentPort.getContinent().getX()
                         + ", " + currentPort.getContinent().getY() + ")");
        techLevel.setText("Tech Level: " + currentPort.getContinent().getTechLevelString());
        politicalSystem.setText("Political System: " + currentPort.getContinent().getPoliticalSystem());
        if (currentPort.getEvent() == null) {
            randomEvent.setText("Normal Conditions");
        } else {
            randomEvent.setText(currentPort.getEvent());
        }
        if (currentPort.getTechLevel() < 5) {
            shipyard.setDisable(true);
        }
    }    
    
    /**
     * Opens the marketplace screen
     * 
     * @param event marketplace button pressed
     */
    @FXML
    private void handleMarketplaceButtonAction(ActionEvent event) {
        ApplicationController.changeScene("GUI/MarketplaceScreen.fxml");
    }
    
    /**
     * opens the shipyard screen
     * @param event  ship yard button pressed
     */
    @FXML
    private void handleShipYardButtonAction(ActionEvent event) {
        ApplicationController.changeScene("GUI/ShipYardScreen.fxml");
    }
}