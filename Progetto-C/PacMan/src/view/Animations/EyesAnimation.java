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
public class EyesAnimation extends Animation implements AnimationsAdapter{
    private final int X_INIT_POS, Y_INIT_POS;
    private final String IMAGE_PATH = "path";

    public EyesAnimation(int xGhost, int yGhost) throws SlickException {
        this.X_INIT_POS = xGhost;
        this.Y_INIT_POS = yGhost;
        
        this.addFrame(new Image(IMAGE_PATH), DURATION);        
    }

    //getter
    public int getX_INIT_POS() {return X_INIT_POS;}
    public int getY_INIT_POS() {return Y_INIT_POS;}
    
    

    @Override
    public Animation rotate(int value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
