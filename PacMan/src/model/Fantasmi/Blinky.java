package model.Fantasmi;

import altro.Tile;
import java.util.Random;


public class Blinky extends Ghost {
    private int currentDirection;
    int randNumber = 3;
    
    Random rand = new Random();

    /** 
     * @param tile_width identifies the width of a single tile
     * @param tile_heigth identifies the height of a single tile
     * @param mapWidth identifies the width of the map
     * @param tiles informations about the tiles of the map
     */
    public Blinky(int tile_width, int tile_heigth, int mapWidth, Tile[][] tiles) {
        super(tile_width, tile_heigth, mapWidth, tiles);
        x = X_MAIN_POS;
        y = Y_MAIN_POS;
        currentDirection = chooseDirection();
    }
    
    /**
     * @return a random number that idententifies the direction 
     */
    public int chooseDirection() {
        setCorners();                
        direction();
        return randNumber;
    }
    
    /**
     * this method checks if direction is accessible or not
     */    
    private void direction() {
        switch(randNumber){
            case 0:
                if((checkBlockUp()) == false)
                    randNumber = rand.nextInt(2)+2;
                break;
            case 1:
                if((checkBlockDown()) == false)
                    randNumber = rand.nextInt(2)+2;
                break;
            case 2:
                if((checkBlockLeft()) == false){
                    randNumber = rand.nextInt(2);
                }
                break;
            case 3:
                if((checkBlockRight()) == false){
                    randNumber = rand.nextInt(2);
                }
                break;
        }
    }    
}