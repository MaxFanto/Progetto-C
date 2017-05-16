/*
Tende a girere sempre a destra quando si ritrova un ostacolo davanti.
Se è impossibilitato a girare a destra gira a sinistra o fa un 180° in modo
casuale.
*/
package model;

/**
 *
 * @author matteo
 */
public class Pinky_ghost extends Fantasma {

    public Pinky_ghost(int xPos, int yPos) {
        super(xPos, yPos);
    }

    @Override
    public void movimento(int xPacMan, int yPacMan) {
        
    }
    
}
