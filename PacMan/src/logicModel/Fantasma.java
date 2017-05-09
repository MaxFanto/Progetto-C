/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicModel;

/**
 *
 * @author cl427927
 */
public abstract class Fantasma extends Giocatore{
    protected int xPos, yPos;
    private boolean blu;

    public Fantasma(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
    
    //getter
    public boolean isBlu() {return blu;}

    public int getxPos() {return xPos;}

    public int getyPos() {return yPos;}
    
    //setter
    public void setBlu(boolean blu) {this.blu = blu;}

    public void morte(){
        //fa qualcosa       
    }
    
    public abstract void movimento(int xPacMan, int yPacMan);
    
}
