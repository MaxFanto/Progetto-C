/*
Questo fantasmino seglie la direzione in modo casuale. Se la direzione scelta in modo
casuale Ã¨ bloccata se ne sceglie un'altra fino a quando non ha via libera.
 */
package model.Fantasmi;

import Algorithms.RandomStrategy;
import other.Tile;
import java.util.Random;
import model.PacMan;

public class Clyde extends Ghost {

    private RandomStrategy search; //algoritmo
    
 
    /** 
     * @param tile_width identifies the width of a single tile
     * @param tile_heigth identifies the height of a single tile
     * @param mapWidth identifies the width of the map
     * @param tiles informations about the tiles of the map
     */
    public Clyde(int tile_width, int tile_heigth, int mapWidth, Tile[][] tiles) {
        super(tile_width, tile_heigth, mapWidth, tiles);
        search = new RandomStrategy();
        x = X_MAIN_POS;
        y = Y_MAIN_POS;
    }
    
    public int chooseDirection() {
        setCorners();                
        return search.direction(this);
    }
    
    /**
     * 
     * @param x x position of pacman
     * @param y y position of pacman
     * @return the distance between the ghost and pacman.
     */
    private double radar(int x, int y) {
	return Math.sqrt(
            Math.pow(this.x - x, 2) +
            Math.pow(this.y - y, 2)
        );
    }   
}


