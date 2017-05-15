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
    private boolean blocked, tunnel, eat;
    
    public Tile(int width, int height, boolean blocked, boolean tunnel, boolean eat){
        TILE_HEIGHT= height;
        TILE_WIDTH = width;
        this.blocked = blocked;
        this.tunnel = tunnel;
        this.eat = eat;
    }

    public int getTILE_WIDTH() {
        return TILE_WIDTH;
    }

    public int getTILE_HEIGHT() {
        return TILE_HEIGHT;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public boolean isTunnel() {
        return tunnel;
    }

    public boolean isEat() {
        return eat;
    }
    
    
    
    
    
    
}
