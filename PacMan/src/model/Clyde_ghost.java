/*
Questo fantasmino seglie la direzione in modo casuale. Se la direzione scelta in modo
casuale è bloccata se ne sceglie un'altra fino a quando non ha via libera.
 */
package model;

import java.util.Random;

/**
 *
 * @author matteo
 */
public class Clyde_ghost extends Fantasma {
    Direzioni dir = null;
    int counter;
    
    public Clyde_ghost() {
        
    }

    @Override
    public void movimento(int xPacMan, int yPacMan) {
        if(counter == 0)
            dir = null;
        
        if(dir == null){
            choose_direction();
        }
        else{
            switch (dir) {
                case UP:
                    xPos = xPos;
                    yPos = yPos - 1;
                    counter --;
                    break;
                case DOWN:
                    xPos = xPos;
                    yPos = yPos + 1;
                    counter --;
                    break;
                case LEFT:
                    xPos = xPos - 1;
                    yPos = yPos;
                    counter --;
                    break;
                case RIGHT:
                    xPos = xPos + 1;
                    yPos = yPos;
                    counter --;
                    break;
            }
            
        }
    }

    private void choose_direction() {
        int a = 0;
        int b = 3;
        int c = ((b-a) + 1);
        Random rand = new Random();

        int rand_number = rand.nextInt(c) + a;

        switch (rand_number) {
            case 0: //up
                dir = Direzioni.UP;
                xPos = xPos;
                yPos = yPos - 1;
                counter = 31;
                break;
            case 1: //down
                dir = Direzioni.DOWN;
                xPos = xPos;
                yPos = yPos + 1;
                counter = 31;
                break;
            case 2: //left
                dir = Direzioni.LEFT;
                xPos = xPos - 1;
                yPos = yPos;
                counter = 31;
                break;
            case 3: //right
                dir = Direzioni.RIGHT;
                xPos = xPos + 1;
                yPos = yPos;
                counter = 31;
                break;
        }
    }

    
    
}

