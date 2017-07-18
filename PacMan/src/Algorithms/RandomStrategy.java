/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import java.util.Random;
import model.Fantasmi.Ghost;

/**
 *
 * @author mattia
 */
public class RandomStrategy implements DirectionStrategy{
    private Integer randNumber;
    private Random rand;
    private boolean[] check;

    public RandomStrategy() {
        this.randNumber = new Integer(0);
        rand = new Random();
        check = null;
    }
    
     /**
     * This method checks if direction is accessible or not
     * @param ghost strategy context
     * @return direction to follow
     */ 
    @Override
    public int direction(Ghost ghost) {
        check = ghost.checkBlocked();
        switch(randNumber){
            case 0:
                if((check[0]) == false)
                    randNumber = rand.nextInt(2)+2;
                break;
            case 1:
                if((check[1]) == false)
                    randNumber = rand.nextInt(2)+2;
                break;
            case 2:
                if((check[2]) == false){
                    randNumber = rand.nextInt(2);
                }
                break;
            case 3:
                if((check[3]) == false){
                    randNumber = rand.nextInt(2);
                }
                break;
        }
        return randNumber;
    }
}
