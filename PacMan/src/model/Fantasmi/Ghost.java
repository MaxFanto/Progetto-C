/**
* This class class manages the common things of each ghost. In particular it contains 
* the method for the artificial movement.
*/ 
package model.Fantasmi;

import other.Tile;
import model.Directions;
import model.Character;

public abstract class Ghost extends Character {
    private boolean blue;
    protected final int X_MAIN_POS = 288;
    protected final int Y_MAIN_POS = 320;
    protected int xPacMan, yPacMan;

    /** 
     * @param tileWidth identifies the width of a single tile
     * @param tileHeight identifies the height of a single tile
     * @param mapWidth identifies the width of the map
     * @param tiles informations about the tiles of the map
     */
    public Ghost(int tileWidth, int tileHeight, int mapWidth, Tile[][] tiles) {
        super(tileWidth, tileHeight, mapWidth, tiles);
    }

    /**
     * This methods makes the ghost move random
     * @param dir, int who indicates the directions 
     */
    public int[] AIMovement(int dir){
        int[] coordinates = new int[2];
        if ((dir == 0) && super.checkBlocked()[0]){   
            y -= speed; 
            direction = Directions.UP;
        }
        if ((dir == 1) && super.checkBlockDown()){
            y += speed;
            direction = Directions.DOWN;
        }                               
        if ((dir == 2) && super.checkBlockLeft()){
            x -= speed;
            direction = Directions.LEFT;
            if (checkTunnelLeft()) 
                x = tileWidth * (mapWidth - 1);            
        }
        if ((dir == 3) && super.checkBlockRight()){
            x += speed;
            direction = Directions.RIGHT;
            if (checkTunnelRight())
                x = 0;
        }
        //per test
        coordinates[0] = x;
        coordinates[1] = y;
        return coordinates;
    }
    
    public abstract int chooseDirection(int xPacMan, int yPacMan);
    
    //getter
    public boolean isBlue() {return blue;}
    public int getxPacMan() {return xPacMan;}
    public int getyPacMan() {return yPacMan;}
 
    
    //setter
    public void setBlue(boolean blue) {this.blue = blue;}
    public void setxPacMan(int xPacMan) {this.xPacMan = xPacMan;}
    public void setyPacMan(int yPacMan) {this.yPacMan = yPacMan;}
    
    
}
