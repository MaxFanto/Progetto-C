/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import model.Fantasmi.Ghost;

/**
 *
 * @author mattia
 */
public class TraceStrategy implements DirectionStrategy{
    private RandomStrategy randomStrategy;
    
    private int xPacMan,yPacMan;
    private int xGhost,yGhost;
    private boolean[] check;
    private boolean checkY = false;
    private boolean checkX = false;
    
    private int direction;
    
    //private boolean nw=false,ne=false,sw=false,se=false;

    public TraceStrategy() {
        randomStrategy = new RandomStrategy();
    }
    
    @Override
    public int direction(Ghost ghost) {
        setCoordinates(ghost);
        direction = axesControl(ghost);
        direction = cardinalControl(ghost);
        return direction;
    }
    
    
    public int axesControl(Ghost ghost){
        //controllo asse x
        if(yGhost == yPacMan && checkY == false){
            checkY=true;
            checkX = false;
            if(xGhost > xPacMan && check[2]==true)
                return 2;
            if(xGhost < xPacMan && check[3]==true)
                return 3;
        }
        //controllo asse y
        if(xGhost == xPacMan && checkX == false){
            checkX = true;
            checkY = false;
            if(yGhost > yPacMan && check[0]==true)
                return 0;
            if(yGhost < yPacMan && check[1]==true)
                return 1;
        }
        return 0;
    }
    public int cardinalControl(Ghost ghost){
        if(xGhost > xPacMan && yGhost>xPacMan){

            if(check[0] == true){
                return 0;
            }
            else if(check[2] == true){
                return 2;
            }
        }
        if(xGhost > xPacMan && yGhost < yPacMan){

            if(check[1] == true){
                return 1;
            }
            else if(check[2] == true){
                return 2;
            }
        }
        if(xGhost < xPacMan && yGhost < yPacMan){
            checkY=false;
            checkX = false;
            if(check[1] == true){
                return 1;
            }
            else if(check[3] == true){
                return 3;
            }
        }
        if (xGhost < xPacMan && yGhost > yPacMan){
            if(check[0] == true){
                return 0;
            }
            else if(check[3] == true){
                return 3;
            }
        }
      return randomStrategy.direction(ghost);
    }
    private void setCoordinates(Ghost ghost){
        xGhost = ghost.getxPos();
        yGhost = ghost.getyPos();
        xPacMan = ghost.getxPacMan();
        yPacMan = ghost.getyPacMan();
        check = ghost.checkBlocked();
    }
}
