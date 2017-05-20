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

    public Guzuta(int tile_width, int tile_heigth, int mapWidth, Tile[][] tiles) {
        super(tile_width, tile_heigth, mapWidth, tiles);
        currentDirection = choose_direction();
        counter = 64;
    }

    public int choose_direction() {

        int a = 0;
        int b = 3;
        int c = ((b-a) + 1);
        
        boolean validDirection = false;
        Random rand = new Random();
        int randNumber;
        if(counter == 64){
            do{
                randNumber = rand.nextInt(c)+a;
                System.out.println("SU"+controlloBlockedSu());
                System.out.println("GIU"+controlloBlockedGiu());
                System.out.println("DX"+controlloBlockedDx());
                System.out.println("SX"+controlloBlockedSx());
                switch(randNumber){
                    case 0:
                        if((validDirection=controlloBlockedSu()) == false)
                            a = 2;
                        break;
                    case 1:
                        if((validDirection=controlloBlockedGiu()) == false)
                            a = 2;
                        break;
                    case 2:
                        if((validDirection=controlloBlockedSx()) == false)
                            b = 1;
                        break;
                    case 3:
                        if((validDirection=controlloBlockedDx()) == false)
                            b = 1;
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

