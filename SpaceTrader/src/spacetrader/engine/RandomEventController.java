package spacetrader.engine;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import spacetrader.model.Turn;

/**
 * FXML Controller class.
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
     * description of the event.
     * @param url the url
     * @param rb the resource bundle
     */
    @Override
    public final void initialize(final URL url, final ResourceBundle rb) {
        // TODO
        descriptionLabel.setText(Turn.getEventDescription());
        titleLabel.setText(Turn.getEventTitle());
    }

    /**
     * accepts the event by continuing to the main screen.
     *
     * @param e accept button pressed
     */
    @FXML
    public final void acceptButtonPressed(final ActionEvent e) {
        ApplicationController.changeScene("GUI/OpeningGameScreen.fxml");
    }

}
