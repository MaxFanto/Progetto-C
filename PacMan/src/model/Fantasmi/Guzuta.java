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
public class Guzuta extends Fantasma {

    private int counter;
    private int currentDirection;
    int randNumber = 3;
    int precedente, c;
    public Guzuta(int tile_width, int tile_heigth, int mapWidth, Tile[][] tiles) {
        super(tile_width, tile_heigth, mapWidth, tiles);
        currentDirection = choose_direction();
        counter = 15;
        
    }

    public int choose_direction() {
                        System.out.println(c);

        setCorners();
        int a = 0;
        int b = 3;
        precedente = randNumber;
        
        
        boolean validDirection = false;
        Random rand = new Random();
        if(counter == 15 ){
            do{
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
            }while(validDirection == false);
            currentDirection = randNumber;
            counter = 0;
        } else{
            counter++;
            return randNumber = currentDirection;
        }

            return randNumber;
    }
    
    
}

