/*
Tende a girare sempre a destra quando si ritrova un ostacolo davanti.
Se è impossibilitato a girare a destra gira a sinistra o fa un 180° in modo
casuale.
*/
package model.Fantasmi;

import altro.Tile;

/**
 *
 * @author matteo
 */
public class Pinky extends Fantasma {
    
    private Tile[][] tiles;
    private int tileWidth = 32, tileHeight = 32;
    
    private int X_MAIN_POS = 256;
    private int Y_MAIN_POS = 320;
    
    private int XghostUPsx, XghostUPdx, XghostDOWNsx, XghostDOWNdx;     
    private int YghostUPsx, YghostUPdx, YghostDOWNsx, YghostDOWNdx;
//
//    public PinkyGhost() {
//        xPos = X_MAIN_POS;
//        yPos = Y_MAIN_POS;
//    }
//
//    @Override
//    public void movimento(int xPacMan, int yPacMan) {
//        XghostUPsx = xPos; YghostUPsx = yPos;
//        XghostUPdx = xPos + tileWidth - 1; YghostUPdx = yPos;
//        XghostDOWNsx = xPos; YghostDOWNsx = yPos + tileHeight - 1;
//        XghostDOWNdx = xPos + tileWidth - 1; YghostDOWNdx = yPos + tileHeight - 1;
//        
//        if (((!tiles[XghostUPsx/tileWidth][(YghostUPsx - 2)/tileHeight].isBlocked())) &&
//           (!tiles[XghostUPdx/tileWidth][(YghostUPdx - 2)/tileHeight].isBlocked())) {
//            xPos += 2;
//        }
//    }

    public Pinky(int tile_width, int tile_heigth, int mapWidth, Tile[][] tiles) {
        super(tile_width, tile_heigth, mapWidth, tiles);
        x = X_MAIN_POS;
        y = Y_MAIN_POS;
    }
    
}
