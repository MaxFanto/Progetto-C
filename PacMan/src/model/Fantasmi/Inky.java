package model.Fantasmi;

import Algorithms.RandomStrategy;
import other.Tile;
import java.util.Random;


public class Inky extends Ghost {
    
    private int currentDirection;
    private RandomStrategy search;
    
    /** 
     * @param tile_width identifies the width of a single tile
     * @param tile_heigth identifies the height of a single tile
     * @param mapWidth identifies the width of the map
     * @param tiles informations about the tiles of the map
     */    
    public Inky(int tile_width, int tile_heigth, int mapWidth, Tile[][] tiles){
        super(tile_width, tile_heigth, mapWidth, tiles);
        search = new RandomStrategy();
        x = X_MAIN_POS;
        y = Y_MAIN_POS;
        currentDirection = chooseDirection();
    }
    
    /**
     * This method chooses the Inky's direction
     * @return a random number that idententifies the direction 
     */
    public int chooseDirection() {
        setCorners();
        return search.direction(this);
    }    
}
