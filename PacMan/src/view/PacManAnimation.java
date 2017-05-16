/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author mattia
 */
public class PacManAnimation extends Animation implements AnimationsAdapter{

    private final String PATH_IMAGE_0 = "data/pacman0.png";
    private final String PATH_IMAGE_1 = "data/pacman1.png";
    private final int DURATION = 200;
    
    public PacManAnimation() throws SlickException{
        super.addFrame(new Image(PATH_IMAGE_0), DURATION);
        super.addFrame(new Image(PATH_IMAGE_1), DURATION);
    }
    
    public Animation rotate(int value){
        super.getImage(0).setRotation(value);
        super.getImage(1).setRotation(value);
        return this;
    }    
  
}
