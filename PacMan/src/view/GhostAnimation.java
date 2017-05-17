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
public class GhostAnimation extends Animation implements AnimationsAdapter{

    private final String[] RED_PATH_IMAGE = {"path/","path/"};
    private final String[] BLU_PATH_IMAGE = {"path/","path/"};
    private final String[] PINK_PATH_IMAGE = {"pinky_up1.png","pinky_up2.png"};
    private final String[] GREEN_PATH_IMAGE = {"path/","path/"};
    private final int DURATION = 200;

    public GhostAnimation(String type) throws SlickException {
        switch(type){
            case "red":
                super.addFrame(new Image(RED_PATH_IMAGE[0]), DURATION);
                super.addFrame(new Image(RED_PATH_IMAGE[1]), DURATION);
                break;
            case "blu":
                super.addFrame(new Image(BLU_PATH_IMAGE[0]), DURATION);
                super.addFrame(new Image(BLU_PATH_IMAGE[1]), DURATION);
                break;
            case "green":
                super.addFrame(new Image(GREEN_PATH_IMAGE[0]), DURATION);
                super.addFrame(new Image(GREEN_PATH_IMAGE[1]), DURATION);
                break;
            case "pinky":
                super.addFrame(new Image(PINK_PATH_IMAGE[0]), DURATION);
                super.addFrame(new Image(PINK_PATH_IMAGE[1]), DURATION);
                break;
        }
    }
    
    
    
    @Override
    public Animation rotate(int value){
        return null;
    }
    
}
