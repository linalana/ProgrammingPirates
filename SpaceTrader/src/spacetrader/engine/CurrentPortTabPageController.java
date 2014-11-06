
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
 * FXML Controller class.
 *
 * @author Michael
 */
public class CurrentPortTabPageController implements Initializable {
    /**
     * Anchor pane for the current tab controller.
     */
    @FXML
    private AnchorPane anchorPane;
    /**
     * The label within the tab controller that displays the port's name.
     */
    @FXML
    private Label portName;
    /**
     * The label within the tab controller that displays the location of the
     * port.
     */
    @FXML
    private Label location;
    /**
     * The label within the tab controller that displays the tech level of
     * the port.
     */
    @FXML
    private Label techLevel;
    /**
     * The label that displays the political system of the port.
     */
    @FXML
    private Label politicalSystem;
    /**
     * The label that displays the random event.
     */
    @FXML
    private Label randomEvent;
    /**
     * The button that brings you to the ship yard.
     */
    @FXML
    private Button shipyard;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Port currentPort = Game.getCurrentPort();
        portName.setText(currentPort.getName());
        location.setText("Location: " + "(" + currentPort.getContinent().getX()
                         + ", " + currentPort.getContinent().getY() + ")");
        techLevel.setText("Tech Level: "
                + currentPort.getContinent().getTechLevelString());
        politicalSystem.setText("Political System: "
                + currentPort.getContinent().getPoliticalSystem());
        if (currentPort.getEvent() == null) {
            randomEvent.setText("Normal Conditions");
        } else {
            randomEvent.setText(currentPort.getEvent());
        }
        //5 is representative of the unchanging tech level required for ships
        if (currentPort.getTechLevel() < 5) {
            shipyard.setDisable(true);
        }
    }

    /**
     * Opens the marketplace screen.
     *
     * @param event marketplace button pressed
     */
    @FXML
    private void handleMarketplaceButtonAction(final ActionEvent event) {
        ApplicationController.changeScene("GUI/MarketplaceScreen.fxml");
    }

    /**
     * opens the shipyard screen.
     * @param event  ship yard button pressed
     */
    @FXML
    private void handleShipYardButtonAction(final ActionEvent event) {
        ApplicationController.changeScene("GUI/ShipYardScreen.fxml");
    }
}
