/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author cl427927
 */
public abstract class Fantasma extends Giocatore{
    private double xPos, yPos;
    private boolean blu;

    public Fantasma(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
    
    //getter
    public boolean isBlu() {return blu;}

    public double getxPos() {return xPos;}

    public double getyPos() {return yPos;}
    
    //setter
    public void setBlu(boolean blu) {this.blu = blu;}

    public void morte(){
        //fa qualcosa       
    }
    
    public abstract void movimento(double xPacMan, double yPacMan);
    
}
