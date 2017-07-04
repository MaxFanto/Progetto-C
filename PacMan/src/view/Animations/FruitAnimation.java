/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Animations;

import java.util.Random;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author cl427927
 */
public class FruitAnimation extends Animation implements AnimationsAdapter{

    private final String IMAGE_PATH = "data/frutta/"; 

    public FruitAnimation() throws SlickException {
        String type = generaFrutto();
        this.addFrame(new Image(IMAGE_PATH.concat(type)), DURATION);
    }
 
    @Override
    public void rotate(int value) {
        //do nothing
    }

    @Override
    public void changeAnimationSet() throws SlickException {
        
        //modifica animazione in base all'uscita casuale 
    }
    
    public String generaFrutto(){
        Random random = new Random();
        int indiceFrutto = random.nextInt(6);
        
        switch(indiceFrutto){
            case 0:
                return "ciliegia.png";              
            case 1:
                return "fragola.png";               
            case 2:
                return "arancia.png";              
            case 3:
                return "mela.png";            
            case 4:
                return "pera.png";        
            case 5:
                return "banana.png";
            case 6:
                return "pretzel.png";                
        }
        return null;
    }
}
