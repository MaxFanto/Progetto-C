/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Animations;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

/**
 *
 * @author mattia
 */
public interface AnimationsAdapter{
    final int DURATION = 200; 
    //rotazione animazione
    public void rotate(int value) throws SlickException;
    
    public void changeAnimationSet() throws SlickException;
    
}
