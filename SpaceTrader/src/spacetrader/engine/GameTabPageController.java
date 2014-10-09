
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.engine;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
    
    @FXML
    private void saveButtonPressed(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Save .bin");
        fc.setInitialFileName("game1.bin");
        File file = fc.showSaveDialog(new Stage());
        ModelFacade.getInstance().saveModelBinary(file);
    }
    
}
