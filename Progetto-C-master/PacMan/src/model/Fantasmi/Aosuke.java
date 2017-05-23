/*
La sua direzione 
si basa su due fattori fondamentali: la posizione di Pac-Man e quella del 
fantasmino rosso, cercherà di accerchiarvi, mirando sempre la posizione di fronte a Pac-Man,
così da chiudergli la strada. Se Blinky (rosso) viene mangiato, si rifugia 
nella parte in basso a sinistra dello schermo.
*/

package model.Fantasmi;

import altro.Tile;

/**
 *
 * @author matteo
 */
public class Aosuke extends Fantasma {

    public Aosuke(int tile_width, int tile_heigth, int mapWidth, Tile[][] tiles) {
        super(tile_width, tile_heigth, mapWidth, tiles);
    }


    
}
