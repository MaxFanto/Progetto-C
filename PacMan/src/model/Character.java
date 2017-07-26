/**
 * This class contains the methods for the manual movement of the various character
 * (pacman and ghosts)
 */
package model;

import other.Tile;
import java.util.Observable;
import model.Fantasmi.Blinky;
import model.Fantasmi.Clyde;
import model.Fantasmi.Inky;
import model.Fantasmi.Pinky;
import org.newdawn.slick.Input;

public abstract class Character extends Observable {
    protected int x, y;
    public int memButton, tileWidth, tileHeight, mapWidth;
    public Tile[][] tiles;
    public int xUpSx, xUpDx, xDownSx, xDownDx;
    public int yUpSx, yUpDx, yDownSx, yDownDx;
    public int speed = 2;
    public int[] comands = new int[4];
    private boolean death;

    protected Directions direction = Directions.INITIAL;
    
    /** 
     * @param tileWidth identifies the width of a single tile
     * @param tileHeight identifies the height of a single tile
     * @param mapWidth identifies the width of the map
     * @param tiles informations about the tiles of the map
     */
    public Character(int tileWidth, int tileHeight, int mapWidth, Tile[][] tiles) {
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.mapWidth = mapWidth;
        this.tiles = tiles;
    }
    
    /**
     * This method moves the character on the map based on the input
     * @param input send by the keybord from user
     */
    public void manualMovement(Input input){ 
        setControlKeys();
        setCorners();

        if ((input.isKeyDown(comands[0]) || memButton == 1) && checkBlockUp()){                
                memButton = 1; 
                
                if (otherKeyPress(input, comands[0])){
                   memButton = 0;
                }else{
                    y -= speed;
                    direction = Directions.UP;
                }
        }
        
        if ((input.isKeyDown(comands[1]) || memButton == 2) && checkBlockDown()){
                memButton = 2;
                
                if (otherKeyPress(input, comands[1])){
                   memButton = 0;
                }else{
                    y += speed;
                    direction = Directions.DOWN;
                }                               
        }
        
        if ((input.isKeyDown(comands[2]) || memButton == 3) && checkBlockLeft()){
                memButton = 3;
            
                if (otherKeyPress(input, comands[2])){
                   memButton = 0;
                }else{
                    x -= speed;
                    direction = Directions.LEFT;
                }
                
                if (checkTunnelLeft()) 
                    x = tileWidth * (mapWidth - 1);            
        }
        
        if (((input.isKeyDown(comands[3]) || memButton == 4) && checkBlockRight())){
                memButton = 4;
                
                if (otherKeyPress(input, comands[3])){
                   memButton = 0;
                }else{
                    x += speed;
                    direction = Directions.RIGHT;
                }
                
                if (checkTunnelRight())
                    x = 0;
        }
    }
    
    /**
     * If user press a new button, this method memorizes it.
     * @param input
     * @param n
     * @return 
     */
    public boolean otherKeyPress(Input input, int n) {
        if ((input.isKeyDown(comands[1])) && checkBlockDown() && comands[1] != n)
                return true;
        else if ((input.isKeyDown(comands[0])) && checkBlockUp() && comands[0] != n)
                return true;
        else if ((input.isKeyDown(comands[2])) && checkBlockLeft() && comands[2] != n)
                return true;
        else if((input.isKeyDown(comands[3])) && checkBlockRight() && comands[3] != n)
                return true;
        else
            return false;
    }

    public void setCorners(){
        xUpSx = x; yUpSx = y;
        xUpDx = x + tileWidth - 1; yUpDx = y;
        xDownSx = x; yDownSx = y + tileHeight - 1;
        xDownDx = x + tileWidth - 1; yDownDx = y + tileHeight - 1;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Directions getDirection() {
        return direction;
    }
     
    /**
     * 
     * @return blocked matrix in order (up, down, left, right)
     */
    public boolean[] checkBlocked(){
        boolean[] check = {checkBlockUp(),checkBlockDown(),checkBlockLeft(),checkBlockRight()};
        return check;
    }
    
    public boolean checkBlockUp(){        
    return !tiles[xUpSx/tileWidth][(yUpSx - speed)/tileHeight].isBlocked() &&
           !tiles[xUpDx/tileWidth][(yUpDx - speed)/tileHeight].isBlocked();
    }
    
    public boolean checkBlockDown(){
    return !tiles[xDownSx/tileWidth][(yDownSx + speed)/tileHeight].isBlocked() && 
           !tiles[xDownDx/tileWidth][(yDownDx + speed)/tileHeight].isBlocked();
    }
    
    public boolean checkBlockLeft() {
    return !tiles[(xUpSx - speed)/tileWidth][yUpSx/tileHeight].isBlocked() && 
           !tiles[(xDownSx - speed)/tileWidth][yDownSx/tileHeight].isBlocked();
    }
    
    public boolean checkBlockRight() {
        return !tiles[(xUpDx + speed)/tileWidth][yUpDx/tileHeight].isBlocked() &&
               !tiles[(xDownDx + speed)/tileWidth][yDownDx/tileHeight].isBlocked();
    }
    
    public boolean checkTunnelLeft(){
        return tiles[(xUpDx - speed)/tileWidth][yUpSx/tileHeight].isTunnel() && 
               tiles[(xUpSx - speed)/tileWidth][yDownDx/tileHeight].isTunnel();
    }
    
    public boolean checkTunnelRight(){
        return tiles[(xUpSx + speed)/tileWidth][yUpSx/tileHeight].isTunnel() &&
               tiles[(xDownDx + speed)/tileWidth][yDownDx/tileHeight].isTunnel();
    }

    private void setControlKeys() {
        if(this instanceof PacMan) {
            comands[0] = Input.KEY_UP;
            comands[1] = Input.KEY_DOWN;
            comands[2] = Input.KEY_LEFT;
            comands[3] = Input.KEY_RIGHT;
        }
        
        if(this instanceof Blinky) {
            comands[0] = Input.KEY_W;
            comands[1] = Input.KEY_S;
            comands[2] = Input.KEY_A;
            comands[3] = Input.KEY_D;
        }
        
        if(this instanceof Clyde) {
            comands[0] = Input.KEY_T;
            comands[1] = Input.KEY_G;
            comands[2] = Input.KEY_F;
            comands[3] = Input.KEY_H;
        }
        
        if(this instanceof Inky) {
            comands[0] = Input.KEY_I;
            comands[1] = Input.KEY_K;
            comands[2] = Input.KEY_J;
            comands[3] = Input.KEY_L;
        }
        
        if(this instanceof Pinky) {
            comands[0] = Input.KEY_5;
            comands[1] = Input.KEY_2;
            comands[2] = Input.KEY_1;
            comands[3] = Input.KEY_3;
        }
            
    }
    
    public boolean isDeath() {return death;}
    
    public void setDeath(boolean death) {this.death = death;}

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public int getxPos() {return x;}
    
    public int getyPos() {return y;}
}
