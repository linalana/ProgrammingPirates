/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * This is the main JavaFX Application class which launches the application.
 *
 */
public class SpaceTrader extends Application {

    private static Stage primaryStage;
    private AnchorPane rootLayout;

    @Override
    public final void start(Stage primaryStage) {
        SpaceTrader.primaryStage = primaryStage;
        SpaceTrader.primaryStage.setTitle("Call of Booty: Black Ships");

        initRootLayout();

    }

    /**
     * Initializes the root layout.
     */
    public final void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SpaceTrader.class.
                    getResource("GUI/WelcomeScreen.fxml"));
            rootLayout = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
