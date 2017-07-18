/*
It tends to always turn right when it finds a frontal obstacle. If it runs in a circle, turns left once.
*/
package model.Fantasmi;

import Algorithms.RandomStrategy;
import other.Tile;
import java.util.Random;

public class Pinky extends Ghost {
    private RandomStrategy search;
    private int currentDirection;
    int a = 0, b = 0;
    

    /** 
     * @param tile_width identifies the width of a single tile
     * @param tile_heigth identifies the height of a single tile
     * @param mapWidth identifies the width of the map
     * @param tiles informations about the tiles of the map
     */    
    public Pinky(int tile_width, int tile_heigth, int mapWidth, Tile[][] tiles) {
        super(tile_width, tile_heigth, mapWidth, tiles);
        search = new RandomStrategy();
        x = X_MAIN_POS;
        y = Y_MAIN_POS;
        currentDirection = chooseDirection();
    }
    
    /**
     * This method chooses the Pinky's direction
     * @return a random number that idententifies the direction 
     */
    public int chooseDirection() {
        setCorners();                
        return search.direction(this);
    }

//        if(x == 544 && y == 32){
//            a++;
//            if(a == 3){
//                randNumber = 2;
//                a = 0;
//            }
//        }
//        else if(x == 256 && y == 32){
//            b++;
//            if(b == 3){
//                randNumber = 2;
//                b = 0;
//            }
//        }
}
