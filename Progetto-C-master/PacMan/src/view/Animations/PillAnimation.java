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
    
    public PillAnimation() throws SlickException{
        super(new Image[]{new Image("data/pill_nero.png"),new Image("data/pill_nero.png")},new int[] {200,200},false);
    }

    @Override
    public Animation rotate(int value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
