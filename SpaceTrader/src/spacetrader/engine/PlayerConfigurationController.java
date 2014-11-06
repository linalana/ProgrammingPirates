package spacetrader.engine;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import spacetrader.model.Game;
import spacetrader.model.Player;
import spacetrader.model.World;

/**
 * FXML Controller class handles the GUI for player creation
 *
 * @author Alana Lin
 */
public class PlayerConfigurationController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Button acceptButton;
    @FXML
    private Slider fighterSlider;
    @FXML
    private Slider traderSlider;
    @FXML
    private Slider engineerSlider;
    @FXML
    private Slider investorSlider;
    @FXML
    private Label pointLabel;
    @FXML
    private TextField nameText;

    private int traderPoint;
    private int fighterPoint;
    private int engineerPoint;
    private int investorPoint;
    private int pointTotal;

    /**
     * Initializes the controller class Adding listeners to all the sliders to
     * register when changed.
     *
     * @param url the url
     * @param rb the resource bundle
     */
    @Override
    public void initialize(final URL url, final ResourceBundle rb) {
        //15 is how many points you can allocate.
        pointTotal = 15;
        fighterPoint = 0;
        traderPoint = 0;
        engineerPoint = 0;
        investorPoint = 0;

        fighterSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(final ObservableValue<? extends Number> ov,
                    final Number old_val, final Number new_val) {
                fighterPoint = updatePoints(fighterSlider, fighterPoint);
            }
        });
        traderSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov,
                    Number oldVal, Number newVal) {
                traderPoint = updatePoints(traderSlider, traderPoint);
            }
        });
        engineerSlider.valueProperty()
                .addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov,
                    Number oldVal, Number newVal) {
                engineerPoint = updatePoints(engineerSlider, engineerPoint);
            }
        });
        investorSlider.valueProperty()
                .addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov,
                    Number oldVal, Number newVal) {
                investorPoint = updatePoints(investorSlider, investorPoint);
            }
        });

        nameText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov,
                    final String oldValue, final String newValue) {
                //20 is the maximum characters a name can be
                if (nameText.getText().length() > 20) {
                    //20 is the maximum number of characters a name can be
                    String s = nameText.getText().substring(0, 20);
                    nameText.setText(s);
                }
            }
        });
    }

    /**
     * Checks to see if the sliders new position will go over the total points
     * If it wont, updates the points remaining, if it will, sets to max points
     * possible within the limit of the points.
     *
     * @param slider the slider being modified
     * @param pastVal the previous value of the slider
     * @return
     */
    @FXML
    private int updatePoints(final Slider slider, int pastVal) {
        int diff = (int) slider.getValue() - pastVal;
        if (pointTotal - diff >= 0) {
            pointTotal -= diff;
        } else {
            int maxPossible = pointTotal + pastVal;
            //sliders have a max value of ten
            if (maxPossible < 10) {
                slider.setValue(maxPossible);
                pointTotal = 0;
            } else {
                //sliders have a max value of ten
                slider.setValue(10);
                pointTotal = maxPossible - 10;
            }
        }
        pointLabel.setText("" + pointTotal);
        pastVal = (int) slider.getValue();
        return pastVal;
    }

    /**
     * Creates a new player from the current set up on the UI when submit button
     * pressed
     *
     * @param event submit button pressed
     */
    @FXML
    private void submitButtonCreatePlayer(final ActionEvent event) {
        if (nameText.getText() != null && !nameText.getText().isEmpty()
                && pointTotal == 0) {
            Player player = new Player(nameText.getText(), fighterPoint,
                    traderPoint, engineerPoint, investorPoint);
            Game game = new Game(player);
            game.getWorld().setRangeChart();
            System.out.println(player.toString());
            showOpeningScreen();
            World gameWorld = new World();
            System.out.println(gameWorld.toString());
            ApplicationController.playSound(getClass()
                    .getResource("ayeayecapn.wav").toString());
        }
    }

    /**
     * Returns the screen to the start screen if canceled.
     *
     * @param event cancel button pressed
     */
    @FXML
    private void returnToStart(final ActionEvent event) {
        ApplicationController.changeScene("GUI/WelcomeScreen.fxml");
    }

    /**
     * moves to next screen when finished making player.
     *
     * @author Michael
     */
    @FXML
    private void showOpeningScreen() {
        ApplicationController.changeScene("GUI/OpeningGameScreen.fxml");
    }

}
