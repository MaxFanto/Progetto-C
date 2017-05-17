package model;
import altro.Tile;
import java.util.Random;
import model.Fantasma;

/*
Blinky è il fantasma più furbo e più pericoloso, segue pacman con
l'obiettivo di mangiarlo, la sua velocità si incrementa con il passare del tempo
e verso la fine del livello va sempre più veloce.
*/
public class Akabei extends Fantasma{

    public Akabei(int tile_width, int tile_heigth, int mapWidth, Tile[][] tiles) {
        super(tile_width, tile_heigth, mapWidth, tiles);
    }
    /*
        RED
    */
    
    public int choose_direction() {

    int a = 0;

    int b = 3;

    int c = ((b-a) + 1);

    Random rand = new Random();



    int rand_number = rand.nextInt(c) + a;

    return rand_number;
    }

    
}

