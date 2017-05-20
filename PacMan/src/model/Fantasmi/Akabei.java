package model.Fantasmi;
import altro.Tile;
import java.util.Random;
import model.Fantasmi.Fantasma;

/*
Blinky è il fantasma più furbo e più pericoloso, segue pacman con
l'obiettivo di mangiarlo, la sua velocità si incrementa con il passare del tempo
e verso la fine del livello va sempre più veloce.
*/
public class Akabei extends Fantasma{
    private int currentDirection;
    private int counter;
    

    public Akabei(int tile_width, int tile_heigth, int mapWidth, Tile[][] tiles) {
        super(tile_width, tile_heigth, mapWidth, tiles);
        currentDirection = choose_direction();
        counter = 64;
    }
    /*
        RED
    */
    
    //Algoritmo di calcolo percorso
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
            }while(validDirection);
            currentDirection = randNumber;
            counter = 0;
        } else{
            counter++;
            return randNumber = currentDirection;
        }
            return randNumber;
    }
}

