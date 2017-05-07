/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author mattia
 */
public class PacManAnimation extends Animation implements AnimationsAdapter{

    public PacManAnimation() throws SlickException{
        super(new Image[]{new Image("data/pacman0.png"),new Image("data/pacman1.png")},new int[] {200,200},false);
        
    }
    
    public Animation rotate(int value){
        super.getImage(0).setRotation(value);
        super.getImage(1).setRotation(value);
        return this;
    }    
  
}
