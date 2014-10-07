/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.engine;

import javafx.scene.media.AudioClip;

/**
 *
 * @author Michael
 */
public class ApplicationController {
    
public static void playSound(String file){
    AudioClip sound = new AudioClip(file);
    sound.play();
}

}
