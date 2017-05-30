package model.Fantasmi;
import altro.Tile;
import java.util.Random;

/*
Blinky è il fantasma più furbo e più pericoloso, segue pacman con
l'obiettivo di mangiarlo, la sua velocità si incrementa con il passare del tempo
e verso la fine del livello va sempre più veloce.
*/
public class Blinky extends Fantasma{
    private int currentDirection;
    private int counter;
    int randNumber = 3;
    private int X_MAIN_POS = 320;
    private int Y_MAIN_POS = 320;
    Random rand = new Random();

    public Blinky(int tile_width, int tile_heigth, int mapWidth, Tile[][] tiles) {
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
    /*
        RED
    */
    
    
}

