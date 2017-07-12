/*
Questo fantasmino seglie la direzione in modo casuale. Se la direzione scelta in modo
casuale Ã¨ bloccata se ne sceglie un'altra fino a quando non ha via libera.
 */
package model.Fantasmi;

import altro.Tile;
import java.util.Random;
import model.PacMan;

/**
 *
 * @author matteo
 */
public class Clyde extends Fantasma {

    private int counter;
    private int currentDirection;
    int randNumber = 3;
    
    Random rand = new Random();
 
    public Clyde(int tile_width, int tile_heigth, int mapWidth, Tile[][] tiles) {
        super(tile_width, tile_heigth, mapWidth, tiles);
        //currentDirection = choose_direction(null);
        x = X_MAIN_POS;
        y = Y_MAIN_POS;
    }

    public int choose_direction(PacMan pacman) {
        setCorners();                
        direction(pacman);
        return randNumber;
    }
    
    //calcola la distanza da pacman
    private double radar(int x, int y){

	return Math.sqrt(
            Math.pow(this.x - x, 2) +
            Math.pow(this.y - y, 2)
        );
	
    }
    

    private void direction(PacMan pacman) {
    
        if(radar(pacman.getxPos(),pacman.getyPos())<100){
            switch(pacman.getDirection()){
                case UP:
                    randNumber = 0;
                    break;
                case DOWN:
                    randNumber = 1;
                    break;
                case LEFT:
                    randNumber = 2;
                    break;
                case RIGHT:
                    randNumber = 3;
            }
        }
        
        else{
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
    
    
}


