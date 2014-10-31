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
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import spacetrader.SpaceTrader;
import spacetrader.model.ModelFacade;

/**
 * FXML Controller class
 *
 * @author Michael
 */
public class GameTabPageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Opens save dialog to save a .bin file
     *
     * @param event save button pressed
     */
    @FXML
    private void saveButtonPressed(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Save .bin");
        fc.setInitialFileName("game1.bin");
        File file = fc.showSaveDialog(new Stage());
        ModelFacade.getInstance().saveModelBinary(file);
    }

    /**
     * Starts a game, switches to the player configuration screen
     *
     * @param event submit button pressed
     */
    @FXML
    private void handleLoadGameAction(ActionEvent event) {
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

}
