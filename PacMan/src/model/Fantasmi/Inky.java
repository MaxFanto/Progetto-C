/*
La sua direzione 
si basa su due fattori fondamentali: la posizione di Pac-Man e quella del 
fantasmino rosso, cercherà di accerchiarvi, mirando sempre la posizione di fronte a Pac-Man,
così da chiudergli la strada. Se Blinky (rosso) viene mangiato, si rifugia 
nella parte in basso a sinistra dello schermo.
*/

package model.Fantasmi;

import altro.Tile;
import java.util.Random;

/**
 *
 * @author matteo
 */
public class Inky extends Fantasma {
    
    private int currentDirection;
    int randNumber = 3;
    Random rand = new Random();
    
    public Inky(int tile_width, int tile_heigth, int mapWidth, Tile[][] tiles) {
        super(tile_width, tile_heigth, mapWidth, tiles);
        x = X_MAIN_POS;
        y = Y_MAIN_POS;
        currentDirection = choose_direction();
    }
    
    public int choose_direction() {
        setCorners();                
        direction();
        return randNumber;
    }
    
    /**
     * Algoritmo che fa muovere il fantasma --> inizialmente RANDOM; 
     */
    
    private void direction() {

        switch(randNumber){
            case 0:
                if((controlloBlockedSu()) == false)
                    randNumber = rand.nextInt(2)+2;
                break;
            case 1:
                if((controlloBlockedGiu()) == false)
                    randNumber = rand.nextInt(2)+2;
                break;
            case 2:
                if((controlloBlockedSx()) == false){
                    randNumber = rand.nextInt(2);
                }
                break;
            case 3:
                if((controlloBlockedDx()) == false){
                    randNumber = rand.nextInt(2);
                }
                break;
        }
    }


    
}
