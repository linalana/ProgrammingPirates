/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.engine;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import spacetrader.model.Continent;
import spacetrader.model.Game;
import spacetrader.model.Port;
import spacetrader.model.RangeChart;
import spacetrader.model.Turn;

/**
 *
 * @author James
 */
public class MapController implements Initializable {
    
    @FXML
    private Canvas canvas;
    @FXML
    private Label continentLabel;
    @FXML
    private Label neededLabel;
    @FXML
    private Label availableLabel;
    @FXML
    private Label techLabel;
    @FXML
    private Label politicalLabel;
    @FXML
    private Button exitButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RangeChart range = new RangeChart();
        Continent[] continents = range.getChart();
        GraphicsContext g2d = canvas.getGraphicsContext2D();
        for (Continent cont: continents) {
            g2d.fillOval(cont.getX(), cont.getY(), 15, 15);
        }
        g2d.setFill(Color.RED);
        Continent cur = Game.getCurrentPort().getContinent();
        g2d.fillOval(cur.getX(), cur.getY(), 15, 15);
        continentLabel.setVisible(false);
        neededLabel.setVisible(false);
        availableLabel.setVisible(false);
        techLabel.setVisible(false);
        politicalLabel.setVisible(false);
        availableLabel.setText("Rum available: " + Game.getPlayer().getShip().getFuel());
        
        canvas.addEventHandler(MouseEvent.MOUSE_MOVED, 
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        //do stuff
                        boolean onPlanet = false;
                        for (Continent cont: continents) {
                            if (cont.isClicked(e.getX(), e.getY())) {
                                onPlanet = true;
                                continentLabel.setText("Continent: " +
                                        cont.getName());
                                neededLabel.setText("Rum needed: " +
                                        range.getDists(cont));
                                techLabel.setText("Tech level: " +
                                        cont.getTechLevelString());
                                politicalLabel.setText("Political system: " +
                                        cont.getPoliticalSystem());
                                continentLabel.setVisible(true);
                                neededLabel.setVisible(true);
                                availableLabel.setVisible(true);
                                techLabel.setVisible(true);
                                politicalLabel.setVisible(true);
                            }
                        }
                        if (!onPlanet) {
                            continentLabel.setVisible(false);
                            neededLabel.setVisible(false);
                            availableLabel.setVisible(false);
                            techLabel.setVisible(false);
                            politicalLabel.setVisible(false);
                        }
                    }
                });
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        for (Continent cont: continents) {
                            if (cont.isClicked(e.getX(), e.getY())) {
                                //travel to this planet
                                System.out.println("Travel to: " + cont);
                                Port newPort = cont.getMainPort();
                                Turn turn = new Turn(newPort);
                                int fuelUsed = range.getDists(cont);
                                String result = turn.travel(fuelUsed);
                                //set new current port
                                Game.setCurrentPort(newPort);
                                //deduct fuel from ship based on distance traveled
                                Game.getPlayer().getShip().setFuel(-fuelUsed);
                                if (result != null) {
                                    ApplicationController.changeScene("GUI/Encounter.fxml");
                                } else {
                                    ApplicationController.changeScene("GUI/RandomEvent.fxml");
                                }
                            }
                        }
                    }
                });
    }
    
    @FXML
    private void exitButtonPressed(ActionEvent event) {
        ApplicationController.changeScene("GUI/OpeningGameScreen.fxml");
    }

}
