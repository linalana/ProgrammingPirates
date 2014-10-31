/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author James
 */
public class ModelFacade implements Serializable {
    
    private static  ModelFacade instance = new ModelFacade();
    
    private Game game;
    
    public static ModelFacade getInstance() { return instance; }
    
    
    public void saveModelBinary(File file)  {
        try {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
                out.writeObject(game.getPlayer());
                out.writeObject(game.getWorld());
                out.writeObject(game.getCurrentPort());
            }
        } catch (IOException ex) {
            Logger.getLogger(ModelFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadModelBinary(File file)  {
        try {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                //
                Player p = (Player) in.readObject();
                World w = (World) in.readObject();
                Port cp = (Port) in.readObject();
                game = new Game(p);
                game.setCurrentPort(cp);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ModelFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(ModelFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
