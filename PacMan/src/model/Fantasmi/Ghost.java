package model.Fantasmi;

import other.Tile;
import model.Directions;
import model.Character;

public abstract class Ghost extends Character {
    private boolean blue;
    protected final int X_MAIN_POS = 288;
    protected final int Y_MAIN_POS = 320;

    /** 
     * @param tileWidth identifies the width of a single tile
     * @param tileHeight identifies the height of a single tile
     * @param mapWidth identifies the width of the map
     * @param tiles informations about the tiles of the map
     */
    public Ghost(int tileWidth, int tileHeight, int mapWidth, Tile[][] tiles) {
        super(tileWidth, tileHeight, mapWidth, tiles);
    }

    public void AIMovement(int dir){
        if ((dir == 0) && super.checkBlocked()[0]){   
            y -= speed; 
            direction = Directions.UP;
        }
        if ((dir == 1) && super.checkBlocked()[1]){
            y += speed;
            direction = Directions.DOWN;
        }                               
        if ((dir == 2) && super.checkBlocked()[2]){
            x -= speed;
            direction = Directions.LEFT;
            if (checkTunnelLeft()) 
                x = tileWidth * (mapWidth - 1);            
        }
        if ((dir == 3) && super.checkBlocked()[3]){
            x += speed;
            direction = Directions.RIGHT;
            if (checkTunnelRight())
                x = 0;
        }
    }
    
    public abstract int chooseDirection();
    
    public boolean isBlue() {return blue;}
    
    public void setBlue(boolean blue) {this.blue = blue;}
    
}
