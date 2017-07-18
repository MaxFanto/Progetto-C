package model.Fantasmi;

import Algorithms.RandomSearchStrategy;
import other.Tile;
import java.util.Random;


public class Blinky extends Ghost {
    private int currentDirection;
    private RandomSearchStrategy search;

    /** 
     * @param tile_width identifies the width of a single tile
     * @param tile_heigth identifies the height of a single tile
     * @param mapWidth identifies the width of the map
     * @param tiles informations about the tiles of the map
     */
    public Blinky(int tile_width, int tile_heigth, int mapWidth, Tile[][] tiles) {
        super(tile_width, tile_heigth, mapWidth, tiles);
        search = new RandomSearchStrategy();
        x = X_MAIN_POS;
        y = Y_MAIN_POS;
        currentDirection = chooseDirection();
    }
    
    /**
     * This method chooses the Blinky's direction
     * @return a random number that idententifies the direction 
     */
    public int chooseDirection() {
        setCorners();  
        int prova = search.direction(this);
        return prova;
    }
    
    /**
     * This method checks if direction is accessible or not
     */       
}