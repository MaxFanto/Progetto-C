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
 * @author mattia
 */
public class PillAnimation extends Animation implements AnimationsAdapter{
    
    private final String IMAGE_PATH_0 = "data/pill/superPill0.png";
    private final String IMAGE_PATH_1 = "data/pill/superPill1.png";
    private final String IMAGE_PATH = "data/pill/";
    
    public PillAnimation() throws SlickException{
        super.addFrame(new Image(IMAGE_PATH_0), DURATION);
        super.addFrame(new Image(IMAGE_PATH_1), DURATION);
    }
    public PillAnimation(String type) throws SlickException{
        super.addFrame(new Image(IMAGE_PATH.concat(type)), DURATION);
    }

    @Override
    public void rotate(int value) {
        //do nothing
    }

    @Override
    public void changeAnimationSet() throws SlickException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        /*
        TODO: modifica animazione quando sparisce la pillola
        */
    }
}
