package model.Fantasmi;

import Algorithms.DirectionStrategy;
import Algorithms.RandomStrategy;
import Algorithms.TraceStrategy;
import other.Tile;

public class Inky extends Ghost {
    
    private int currentDirection;
    private DirectionStrategy search;
    
    /** 
     * @param tileWidth identifies the width of a single tile
     * @param tileHeight identifies the height of a single tile
     * @param mapWidth identifies the width of the map
     * @param tiles informations about the tiles of the map
     */    
    public Inky(int tileWidth, int tileHeight, int mapWidth, Tile[][] tiles){
        super(tileWidth, tileHeight, mapWidth, tiles);
        search = new RandomStrategy();
        x = X_MAIN_POS;
        y = Y_MAIN_POS;
        //currentDirection = chooseDirection();
    }
    
    /**
     * This method chooses the Inky's direction
     * @return a random number that idententifies the direction 
     */
    @Override
    public int chooseDirection(int xPacMan, int yPacMan) {
        setCorners();
        return search.direction(this);
    }    

}
