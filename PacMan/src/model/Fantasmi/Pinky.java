/*
It tends to always turn right when it finds a frontal obstacle. If it runs in a circle, turns left once.
*/
package model.Fantasmi;

import Algorithms.DirectionStrategy;
import Algorithms.RandomStrategy;
import other.Tile;

public class Pinky extends Ghost {
    private DirectionStrategy search;
    int a = 0, b = 0;
    

    /** 
     * @param tileWidth identifies the width of a single tile
     * @param tileHeight identifies the height of a single tile
     * @param mapWidth identifies the width of the map
     * @param tiles informations about the tiles of the map
     */    
    public Pinky(int tileWidth, int tileHeight, int mapWidth, Tile[][] tiles) {
        super(tileWidth, tileHeight, mapWidth, tiles);
        search = new RandomStrategy();
        x = X_MAIN_POS;
        y = Y_MAIN_POS;

    }
    
    /**
     * This method chooses the Pinky's direction
     * @return a random number that idententifies the direction 
     */
    @Override
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
