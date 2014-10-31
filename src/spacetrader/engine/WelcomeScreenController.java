package spacetrader.engine;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import spacetrader.SpaceTrader;
import spacetrader.model.ModelFacade;

/**
 *
 * @author Michael
 */
public class WelcomeScreenController implements Initializable {

    @FXML
    private Label label;

    /**
     * Starts a game, switches to the player configuration screen
     *
     * @param event submit button pressed
     */
    @FXML
    private void handleStartButtonAction(ActionEvent event) {
        ApplicationController.playSound(getClass().getResource("yearr.wav").toString());
        ApplicationController.changeScene("GUI/PlayerConfiguration.fxml");
    }

    /**
     * Starts a game, switches to the player configuration screen
     *
     * @param event submit button pressed
     */
    @FXML
    private void handleLoadButtonAction(ActionEvent event) {
        final FileChooser fc = new FileChooser();
        fc.setTitle("Select stored BIN file");
        File file = fc.showOpenDialog(new Stage());
        ModelFacade.getInstance().loadModelBinary(file);
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SpaceTrader.class.getResource("GUI/OpeningGameScreen.fxml"));
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
