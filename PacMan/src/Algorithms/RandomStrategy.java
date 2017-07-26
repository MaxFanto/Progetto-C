/**
 * This class implements the interface DirectionStrategy. We can find in this 
 * class the algorithm for the directions of the ghosts.
 */
package Algorithms;

import java.util.Random;
import model.Fantasmi.Ghost;
/**
 * 
 * @author antonio
 */

public class RandomStrategy implements DirectionStrategy{
    private Integer randNumber;
    private Random rand;
    private boolean[] check;

    public RandomStrategy() {
        rand = new Random();
        randNumber = rand.nextInt(2)+2;
        check = null;
    }
    
     /**
     * This method checks if direction is accessible or not
     * @param ghost strategy context
     * @return direction to follow
     */ 
    @Override
    public int direction(Ghost ghost) {
        boolean walkFlag = true;
        check = ghost.checkBlocked();
        do{
        switch(randNumber){
                case 0:
                    if((check[0]) == false){
                        randNumber = rand.nextInt(2)+2;
                        walkFlag = false;
                    }
                    else walkFlag = true;
                    break;
                case 1:
                    if((check[1]) == false){
                        randNumber = rand.nextInt(2)+2;
                        walkFlag = false;
                    }
                    else walkFlag = true;
                    break;
                case 2:
                    if((check[2]) == false){
                        randNumber = rand.nextInt(2);
                        walkFlag = false;
                    }
                    else walkFlag = true;
                    break;
                case 3:
                    if((check[3]) == false){
                        randNumber = rand.nextInt(2);
                        walkFlag = false;
                    }
                    else walkFlag = true;
                    break;
            }
        }while(walkFlag == false);
        return randNumber;
    }
}
