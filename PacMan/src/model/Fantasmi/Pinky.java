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
    int randNumber;
    int a = 0, b = 0;
    Random rand = new Random(); 
    

    public Pinky(int tile_width, int tile_heigth, int mapWidth, Tile[][] tiles) {
        super(tile_width, tile_heigth, mapWidth, tiles);
        x = X_MAIN_POS;
        y = Y_MAIN_POS;
        randNumber = rand.nextInt(3);
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
                if((controlloBlockedSu()) == false){
                    randNumber = 3;
                    if((controlloBlockedDx()) == false){
                        randNumber = 2;
                    }
                }
                break;                    
            case 1:
                if((controlloBlockedGiu()) == false){
                    randNumber = 2;
                    if((controlloBlockedSx()) == false){
                        randNumber = 3;
                    }
                }
                break;
            case 2:
                if((controlloBlockedSx()) == false){
                    randNumber = 0;
                    if((controlloBlockedSu()) == false){
                        randNumber = 1;
                    }
                }
                break;
            case 3:
                if((controlloBlockedDx()) == false){
                    randNumber = 1;
                    if((controlloBlockedGiu()) == false){
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
