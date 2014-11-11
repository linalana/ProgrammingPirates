package spacetrader.engine;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import spacetrader.model.Game;

/**
 * FXML Controller class for tab page.
 *
 * @author Michael
 */
public class PlayerTabPageController implements Initializable {
    @FXML
    private Label moneyLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label fighter;
    @FXML
    private Label investor;
    @FXML
    private Label trader;
    @FXML
    private Label engineer;
 
    /**
     * Initializes the controller class with updated labels for the player info.
     * @param url the url
     * @param rb the resource bundle
     */
    @Override
    public void initialize(final URL url, final ResourceBundle rb) {
        moneyLabel.setText("Money: " + Game.getPlayer().getMoney());
        nameLabel.setText("Name: " + Game.getPlayer().getName());
        fighter.setText("Fighter: " + Game.getPlayer().getFighter());
        investor.setText("Investor: " + Game.getPlayer().getInvestor());
        trader.setText("Trader: " + Game.getPlayer().getTrader());
        engineer.setText("Engineer: " + Game.getPlayer().getEngineer());
    }

}
