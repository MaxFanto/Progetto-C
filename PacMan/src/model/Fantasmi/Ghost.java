package model.Fantasmi;

import altro.Tile;
import model.Directions;
import model.Player;

public abstract class Ghost extends Player {
    private boolean blue;
    protected final int X_MAIN_POS = 288;
    protected final int Y_MAIN_POS = 320;

    /** 
     * @param tile_width identifies the width of a single tile
     * @param tile_heigth identifies the height of a single tile
     * @param mapWidth identifies the width of the map
     * @param tiles informations about the tiles of the map
     */
    public Ghost(int tile_width, int tile_heigth, int mapWidth, Tile[][] tiles) {
        super(tile_width, tile_heigth, mapWidth, tiles);
    }

    public void AIMovement(int dir){
        if ((dir == 0) && checkBlockUp()){   
            y -= speed; 
            direction = Directions.UP;
        }
        if ((dir == 1) && checkBlockDown()){
            y += speed;
            direction = Directions.DOWN;
        }                               
        if ((dir == 2) && checkBlockLeft()){
            x -= speed;
            direction = Directions.LEFT;
            if (checkTunnelLeft()) 
                x = tileWidth * (mapWidth - 1);            
        }
        if ((dir == 3) && checkBlockRight()){
            x += speed;
            direction = Directions.RIGHT;
            if (checkTunnelRight())
                x = 0;
        }
    }
    
    
    public boolean isBlue() {return blue;}
    
    public void setBlue(boolean blue) {this.blue = blue;}
}
