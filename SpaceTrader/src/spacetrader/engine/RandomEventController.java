package spacetrader.engine;

import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import spacetrader.model.Turn;

/**
 * FXML Controller class
 *
 * @author Danny
 */
public class RandomEventController implements Initializable {

    @FXML
    private Label descriptionLabel;
    @FXML
    private Label titleLabel;

    /**
     * Initializes the controller class. Sets the labels to show the title and
     * description of the event
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        descriptionLabel.setText(Turn.getEventDescription());
        titleLabel.setText(Turn.getEventTitle());
    }

    /**
     * accepts the event by continuing to the main screen
     *
     * @param e accept button pressed
     */
    @FXML
    public void acceptButtonPressed(ActionEvent e) {
        ApplicationController.getInstance().changeScene("GUI/OpeningGameScreen.fxml");
    }

}
