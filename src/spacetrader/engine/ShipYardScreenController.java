package spacetrader.engine;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import spacetrader.SpaceTrader;

/**
 * FXML Controller class
 *
 * @author Michael
 */
public class ShipYardScreenController implements Initializable {

    @FXML
    private TabPane yardPane;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        yardPane.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<Tab>() {
                @Override
                public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
                    FXMLLoader loader = new FXMLLoader(); 
                    loader.setLocation(SpaceTrader.class.getResource("GUI/" + t1.getText() + ".fxml"));
                    AnchorPane a;
                    try {
                        a = (AnchorPane) loader.load();
                        t1.setContent(a);
                    } catch (IOException ex) {
                        Logger.getLogger(ShipYardScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                   
                }
            }
        );
    }    
    
}
