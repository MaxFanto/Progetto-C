/*
Questo fantasmino seglie la direzione in modo casuale. Se la direzione scelta in modo
casuale è bloccata se ne sceglie un'altra fino a quando non ha via libera.
 */
package model.Fantasmi;

import altro.Tile;
import java.util.Random;

/**
 *
 * @author matteo
 */
public class Clyde extends Fantasma {

    private int counter;
    private int currentDirection;
    int randNumber = 3;
    int precedente, c;
    private int X_MAIN_POS = 224;
    private int Y_MAIN_POS = 256;
    
    public Clyde(int tile_width, int tile_heigth, int mapWidth, Tile[][] tiles) {
        super(tile_width, tile_heigth, mapWidth, tiles);
        currentDirection = choose_direction();
        x = X_MAIN_POS;
        y = Y_MAIN_POS;
    }

    public int choose_direction() {
        setCorners();        
        boolean validDirection = false;
        Random rand = new Random();
        
        switch(randNumber){
            case 0:
                if((validDirection=controlloBlockedSu()) == false)
                    randNumber = rand.nextInt(2)+2;
                break;
            case 1:
                if((validDirection=controlloBlockedGiu()) == false)
                    randNumber = rand.nextInt(2)+2;
                break;
            case 2:
                if((validDirection=controlloBlockedSx()) == false){
                    randNumber = rand.nextInt(2);
                }
                break;
            case 3:
                if((validDirection=controlloBlockedDx()) == false){
                    randNumber = rand.nextInt(2);
                }
                break;
        }
        
        return randNumber;
    }
    
    
}

