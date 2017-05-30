/*
Tende a girare sempre a destra quando si ritrova un ostacolo davanti.
Se è impossibilitato a girare a destra gira a sinistra o fa un 180° in modo
casuale.
*/
package model.Fantasmi;

import altro.Tile;
import java.util.Random;

/**
 *
 * @author matteo
 */
public class Pinky extends Fantasma {
    private int currentDirection;
    int randNumber = 3;
    private int X_MAIN_POS = 288;
    private int Y_MAIN_POS = 256;
    Random rand = new Random(); 
    

    public Pinky(int tile_width, int tile_heigth, int mapWidth, Tile[][] tiles) {
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
