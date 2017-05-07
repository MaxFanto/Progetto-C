/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import org.newdawn.slick.SlickException;

/**
 *
 * @author mattia
 */
public class AnimationsFactory {
    static AnimationsFactory instance;
    
    private AnimationsFactory(){
        
    }
    
    public static synchronized AnimationsFactory getInstance(){
        if(instance == null)
            instance = new AnimationsFactory();
        return instance;
    }
    
    public AnimationsAdapter getPacmanAnimation() throws SlickException{
        return new PacManAnimation();
    }   
    
    public AnimationsAdapter getGhostAnimation(){
        return new GhostAnimation();
    }
    
    public AnimationsAdapter getPillAnimation() throws SlickException{
        return new PillAnimation();
    }
}
