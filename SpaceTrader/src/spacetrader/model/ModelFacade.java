/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.model;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
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
    
    public void saveModelJson() {
        try {
            try (PrintWriter out = new PrintWriter(new File("data.json"))) {
                Gson gs = new Gson();
                String gson = gs.toJson(this);
                out.println(gson);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ModelFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadModelJson(File file) {
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String json = br.readLine();
                System.out.println(json);
                Gson gs = new Gson();
                instance = gs.fromJson(json, ModelFacade.class);
            } 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ModelFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModelFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
