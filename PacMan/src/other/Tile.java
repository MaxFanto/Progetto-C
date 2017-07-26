/**
 * The tiles are the elements who the map are made of.
 */
package other;

public class Tile {
    private final int TILE_WIDTH, TILE_HEIGHT;
    private boolean blocked, tunnel, eat, superP, fruit;
    
    /**
     * 
     * @param width int that indifies the width of each tile.
     * @param height int that indifies the height of each tile.
     * @param blocked boolean that identifies if each tile is accessible or not.
     * @param tunnel boolean that identifies if each tile is a tunnel(connection
     *               with the other side of the map) or not.
     */
    public Tile(int width, int height, boolean blocked, boolean tunnel, boolean eat, boolean superP, boolean fruit){
        TILE_HEIGHT= height;
        TILE_WIDTH = width;
        this.blocked = blocked;
        this.tunnel = tunnel;
        this.eat = eat;
        this.superP = superP;
        this.fruit = fruit;
    }
    
    //for tests
    public Tile(boolean blocked){
        TILE_HEIGHT = 32;
        TILE_WIDTH = 32;
        this.blocked = blocked;
        tunnel = false;
        eat = false;
        superP = false;
        fruit = false;
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

    public boolean isEat() {
        return eat;
    }

    public void setEat(boolean eat) {
        this.eat = eat;
    }

    public boolean isSuperP() {
        return superP;
    }

    public void setSuperP(boolean superP) {
        this.superP = superP;
    }

    public boolean isFruit() {
        return fruit;
    }

    public void setFruit(boolean fruit) {
        this.fruit = fruit;
    }
}
