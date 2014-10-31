
package spacetrader.engine;

import java.io.IOException;
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

    /**
     * plays a sound
     *
     * @param file the audio file
     */
    public static void playSound(String file) {
        AudioClip sound = new AudioClip(file);
        sound.play();
    }

    /**
     * changes scene of center stage
     *
     * @param file the new fxml file
     */
    public static void changeScene(String file) {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SpaceTrader.class.getResource(file));
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
