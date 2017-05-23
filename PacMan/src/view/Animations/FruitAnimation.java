/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Animations;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author cl427927
 */
public class FruitAnimation extends Animation implements AnimationsAdapter{

    private final String IMAGE_PATH = "path/"; 

    public FruitAnimation(String type) throws SlickException {
        this.addFrame(new Image(IMAGE_PATH.concat(type)), DURATION);
    }
    
 
    @Override
    public Animation rotate(int value) {
        return null;
        //do nothing
    }
}
