/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicModel;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author cl427927
 */
public class PacMan extends Giocatore {
    private int vite;
    private double xPos, yPos;
    private boolean power;
    
    /*
    TODO: definire le posizioni iniziali di PacMan
    */
    private final double X_MAIN_POS = 32;
    private final double Y_MAIN_POS = 32;
    private final int MAIN_VITE = 5;

    public PacMan() throws SlickException {
//        super(new Image[]{new Image("data/pacman0.png"), new Image("data/pacman1.png")});
        xPos = X_MAIN_POS;
        yPos = Y_MAIN_POS;
        vite = MAIN_VITE;
    }

    
    //getter
    public double getxPos() {return xPos;}
    public double getyPos() {return yPos;}
    public int getVite() {return vite;}
    public boolean isPower() {return power;}
    
    

    //setter
    public void setVite(int vite) {this.vite = vite;}
    public void setPower(boolean power) {this.power = power;}
    
    
    public void movimento(int x, int y){
        if(x != 0)
            setXPos(x);
        if(y != 0)
            setYPos(y);
    }
    private void setXPos(double x){xPos += x;}
    private void setYPos(double y){yPos += y;}
    
    
    
}
