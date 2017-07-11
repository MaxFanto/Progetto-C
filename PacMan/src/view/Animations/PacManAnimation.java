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
public class PacManAnimation extends Animation implements AnimationsAdapter{

    private final String PATH_IMAGE_0 = "data/pacman/pacman0.png";
    private final String PATH_IMAGE_1 = "data/pacman/pacman1.png";
    private final String PATH_IMAGE_2 = "data/pacman/pacman2.png";
    private int xPos = 288, yPos = 512;
    final int DURATION = 100;
    
    public PacManAnimation() throws SlickException{
        super.addFrame(new Image(PATH_IMAGE_0), DURATION);
        super.addFrame(new Image(PATH_IMAGE_1), DURATION);
        super.addFrame(new Image(PATH_IMAGE_2), DURATION);
    }
    
    public int getxPos() {return xPos;}
    public void setxPos(int xPos) {this.xPos = xPos;}
    public int getyPos() {return yPos;}
    public void setyPos(int yPos) {this.yPos = yPos;}
    
    @Override
    public void rotate(int value){
        super.getImage(0).setRotation(value);
        super.getImage(1).setRotation(value);
    }

    @Override
    public void changeAnimationSet() throws SlickException {
        /*
        TODO: - modifica dell'animazione di pacman quando muore
              - modifica animazione quando torna in vita
        */
        
        //if(morte)
        super.getImage(0).destroy();
        super.getImage(1).destroy();
        super.getImage(2).destroy();
        String path = "data/pacmanDeath/pacmanDeath";
        //aggiunge n frame in base a quanti sono quelli dell'animazione di morte di pacman
        for (int i = 0; i < 5; i++) {
            this.addFrame(new Image(path.concat(i +".png")), DURATION);
        }     
    }
    
    @Override
    protected void finalize() throws Throwable{
        System.out.println("animazione distrutta");
    }
  
}
