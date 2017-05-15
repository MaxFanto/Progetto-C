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
public class PillAnimation extends Animation implements AnimationsAdapter{
    
    public PillAnimation() throws SlickException{
        super(new Image[]{new Image("data/pill_nero.png"),new Image("data/pill_nero.png")},new int[] {200,200},false);
    }
}
