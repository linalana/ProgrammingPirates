
package spacetrader.engine;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import spacetrader.SpaceTrader;

/**
 *
 * @author Michael
 */
public class ApplicationController {

    private static ApplicationController instance = new ApplicationController();
    
    public static ApplicationController getInstance() {
        return instance;
    }
    
    /**
     * plays a sound
     *
     * @param file the audio file
     */
    public void playSound(final String file) {
        final AudioClip sound = new AudioClip(file);
        sound.play();
    }

    /**
     * changes scene of center stage
     *
     * @param file the new fxml file
     */
    public void changeScene(final String file) {
        try {
            // Load root layout from fxml file.
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SpaceTrader.class.getResource(file));
            final AnchorPane root = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            final Stage playerStage = SpaceTrader.getPrimaryStage();
            final Scene scene = new Scene(root);
            playerStage.setScene(scene);
            playerStage.show();
        } catch (IOException e) {
            Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
