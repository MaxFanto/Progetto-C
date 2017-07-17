package other;

public class Tile {
    private final int TILE_WIDTH, TILE_HEIGHT;
    private boolean blocked, tunnel;
    
    /**
     * 
     * @param width int that indifies the width of each tile.
     * @param height int that indifies the height of each tile.
     * @param blocked boolean that identifies if each tile is accessible or not.
     * @param tunnel boolean that identifies if each tile is a tunnel(connection
     *               with the other side of the map) or not.
     */
    public Tile(int width, int height, boolean blocked, boolean tunnel){
        TILE_HEIGHT= height;
        TILE_WIDTH = width;
        this.blocked = blocked;
        this.tunnel = tunnel;
    }

    /**
     * 
     * @return the width of the tile. 
     */
    public int getTILE_WIDTH() {
        return TILE_WIDTH;
    }

    /**
     * 
     * @return the height of the tile. 
     */
    public int getTILE_HEIGHT() {
        return TILE_HEIGHT;
    }
    
    /**
     * 
     * @return blocked if the tile is inaccesible 
     */
    public boolean isBlocked() {
        return blocked;
    }
    
    /**
     * 
     * @return tunnel if the tile is a tunnel. 
     */
    public boolean isTunnel() {
        return tunnel;
    }

}