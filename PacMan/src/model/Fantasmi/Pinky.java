/*
It tends to always turn right when it finds a frontal obstacle. If it runs in a circle, turns left once.
*/
package model.Fantasmi;

import other.Tile;
import java.util.Random;

public class Pinky extends Ghost {
    private int currentDirection;
    int randNumber;
    int a = 0, b = 0;
    Random rand = new Random(); 
    

    /** 
     * @param tile_width identifies the width of a single tile
     * @param tile_heigth identifies the height of a single tile
     * @param mapWidth identifies the width of the map
     * @param tiles informations about the tiles of the map
     */    
    public Pinky(int tile_width, int tile_heigth, int mapWidth, Tile[][] tiles) {
        super(tile_width, tile_heigth, mapWidth, tiles);
        x = X_MAIN_POS;
        y = Y_MAIN_POS;
        randNumber = rand.nextInt(3);
        currentDirection = chooseDirection();
    }
    
    /**
     * This method chooses the Pinky's direction
     * @return a random number that idententifies the direction 
     */
    public int chooseDirection() {
        setCorners();                
        direction();
        return randNumber;
    }
    
    /**
     * This method checks if the right direction is accesible or not. Then it controls if pinky runs in a circle and if it is true change direction.
     */
    private void direction() {        
        switch(randNumber){
            case 0:
                if((checkBlockUp()) == false){
                    randNumber = 3;
                    if((checkBlockRight()) == false){
                        randNumber = 2;
                    }
                }
                break;                    
            case 1:
                if((checkBlockDown()) == false){
                    randNumber = 2;
                    if((checkBlockLeft()) == false){
                        randNumber = 3;
                    }
                }
                break;
            case 2:
                if((checkBlockLeft()) == false){
                    randNumber = 0;
                    if((checkBlockUp()) == false){
                        randNumber = 1;
                    }
                }
                break;
            case 3:
                if((checkBlockRight()) == false){
                    randNumber = 1;
                    if((checkBlockDown()) == false){
                        randNumber = 0;
                    }
                }
                break;
        }
        if(x == 544 && y == 32){
            a++;
            if(a == 3){
                randNumber = 2;
                a = 0;
            }
        }
        else if(x == 256 && y == 32){
            b++;
            if(b == 3){
                randNumber = 2;
                b = 0;
            }
        }
    }

}
