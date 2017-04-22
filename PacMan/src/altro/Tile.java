/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altro;

/**
 *
 * @author MassimilianoFanto
 */
public class Tile {
    private final int TILE_WIDTH, TILE_HEIGHT;
    private boolean blocked;
    
    public Tile(int width, int height, boolean blocked){
        TILE_HEIGHT= height;
        TILE_WIDTH = width;
        this.blocked = blocked;
    }
    
}
