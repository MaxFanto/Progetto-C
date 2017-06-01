package model.Fantasmi;

import altro.Tile;
import altro.Quadranti;
import java.util.Random;

/*
Blinky è il fantasma più furbo e più pericoloso, segue pacman con
l'obiettivo di mangiarlo, la sua velocità si incrementa con il passare del tempo
e verso la fine del livello va sempre più veloce.
*/
public class Blinky extends Fantasma{
    private int currentDirection;
    int randNumber = 3;
    private int X_MAIN_POS = 288;
    private int Y_MAIN_POS = 256;
    
    //algoritmo var
    
    Random rand = new Random();

    public Blinky(int tile_width, int tile_heigth, int mapWidth, Tile[][] tiles) {
        super(tile_width, tile_heigth, mapWidth, tiles);
        //currentDirection = choose_direction();
        x = X_MAIN_POS;
        y = Y_MAIN_POS;
    }
    
    public int choose_direction(int xTrget, int yTarget) {
        Quadranti ghostZone = checkZone(x, y);
        
        
        
        return 0;
    }
        
       
        private Quadranti checkZone(int currentX, int currentY){
            Quadranti zone = null;
            int yAvg = 320;
            int xAvg = 256;
            
            if(currentY > yAvg){
                if(currentX < xAvg){
                    //terzo quadrante
                    zone = Quadranti.THIRD;
                }
                else{
                    //quarto quadrante
                    zone = Quadranti.FOURTH;
                }
            }else{
                if(currentX < xAvg){
                    //primo quarante
                    zone = Quadranti.FIRST;
                }
                else{
                    //secondo quadrante
                    zone = Quadranti.SECOND;
                }
            }
            return zone;
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

