/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import altro.Tile;
import java.util.Observable;
import model.Fantasmi.Blinky;
import model.Fantasmi.Clyde;
import model.Fantasmi.Inky;
import model.Fantasmi.Pinky;
import org.newdawn.slick.Input;

/**
 *
 * @author cl427927
 */
public abstract class Player extends Observable{
    protected int x, y;
    public int memButton, tileWidth, tileHeight, mapWidth;
    public Tile[][] tiles;
    public int xUpSx, xUpDx, xDownSx, xDownDx;     
    public int yUpSx, yUpDx, yDownSx, yDownDx;
    public int spostamento = 2;
    public int[] comandi = new int[4];
    private boolean death;
    
    protected Direzioni direction = Direzioni.INITIAL;
    
    public Player(int tile_width, int tile_heigth, int mapWidth, Tile[][] tiles) {
        this.tileWidth = tile_width;
        this.tileHeight = tile_heigth;
        this.mapWidth = mapWidth;
        this.tiles = tiles;
    }
    
   public void movimentoManuale(Input input){ 
        setControlKeys();
        setCorners();

        if ((input.isKeyDown(comandi[0]) || memButton == 1) && controlloBlockedSu()){                
                memButton = 1; 
                
                if (AltroTastoPremuto(input, comandi[0])){
                   memButton = 0;
                }else{
                    y -= spostamento;
                    direction = Direzioni.UP;
                }
        }
        
        if ((input.isKeyDown(comandi[1]) || memButton == 2) && controlloBlockedGiu()){
                memButton = 2;
                
                if (AltroTastoPremuto(input, comandi[1])){
                   memButton = 0;
                }else{
                    y += spostamento;
                    direction = Direzioni.DOWN;
                }                               
        }
        
        if ((input.isKeyDown(comandi[2]) || memButton == 3) && controlloBlockedSx()){
                memButton = 3;
            
                if (AltroTastoPremuto(input, comandi[2])){
                   memButton = 0;
                }else{
                    x -= spostamento;
                    direction = Direzioni.LEFT;
                }
                
                if (controlloTunnelSx()) 
                    x = tileWidth * (mapWidth - 1);            
        }
        
        if (((input.isKeyDown(comandi[3]) || memButton == 4) && controlloBlockedDx())){
                memButton = 4;
                
                if (AltroTastoPremuto(input, comandi[3])){
                   memButton = 0;
                }else{
                    x += spostamento;
                    direction = Direzioni.RIGHT;
                }
                
                if (controlloTunnelDx())
                    x = 0;
        }
    }
    
    public boolean AltroTastoPremuto(Input input, int n) {
        int[] UsedKeys = {comandi[1], comandi[0], comandi[2], comandi[3]}; 

        if ((input.isKeyDown(comandi[1])) && controlloBlockedGiu() && comandi[1] != n)
                return true;
                
        else if ((input.isKeyDown(comandi[0])) && controlloBlockedSu() && comandi[0] != n)
                return true;
        
        else if ((input.isKeyDown(comandi[2])) && controlloBlockedSx() && comandi[2] != n)
                return true;
        
        else if((input.isKeyDown(comandi[3])) && controlloBlockedDx() && comandi[3] != n)
                return true;
        else
            return false;
    }

    protected void setCorners(){
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

    public Direzioni getDirection() {
        return direction;
    }
    
        
    public boolean controlloBlockedSu(){
    return !tiles[xUpSx/tileWidth][(yUpSx - spostamento)/tileHeight].isBlocked() &&
           !tiles[xUpDx/tileWidth][(yUpDx - spostamento)/tileHeight].isBlocked();
    }
    
    public boolean controlloBlockedGiu(){
    return !tiles[xDownSx/tileWidth][(yDownSx + spostamento)/tileHeight].isBlocked() && 
           !tiles[xDownDx/tileWidth][(yDownDx + spostamento)/tileHeight].isBlocked();
    }
    
    public boolean controlloBlockedSx() {
    return !tiles[(xUpSx - spostamento)/tileWidth][yUpSx/tileHeight].isBlocked() && 
           !tiles[(xDownSx - spostamento)/tileWidth][yDownSx/tileHeight].isBlocked();
    }
    
    public boolean controlloBlockedDx() {
        return !tiles[(xUpDx + spostamento)/tileWidth][yUpDx/tileHeight].isBlocked() &&
               !tiles[(xDownDx + spostamento)/tileWidth][yDownDx/tileHeight].isBlocked();
    }
    
    public boolean controlloTunnelSx(){
        return tiles[(xUpDx - spostamento)/tileWidth][yUpSx/tileHeight].isTunnel() && 
               tiles[(xUpSx - spostamento)/tileWidth][yDownDx/tileHeight].isTunnel();
    }
    
    public boolean controlloTunnelDx(){
        return tiles[(xUpSx + spostamento)/tileWidth][yUpSx/tileHeight].isTunnel() &&
               tiles[(xDownDx + spostamento)/tileWidth][yDownDx/tileHeight].isTunnel();
    }

    private void setControlKeys() {
        if(this instanceof PacMan) {
            comandi[0] = Input.KEY_UP;
            comandi[1] = Input.KEY_DOWN;
            comandi[2] = Input.KEY_LEFT;
            comandi[3] = Input.KEY_RIGHT;
        }
        
        if(this instanceof Clyde) {
            comandi[0] = Input.KEY_W;
            comandi[1] = Input.KEY_S;
            comandi[2] = Input.KEY_A;
            comandi[3] = Input.KEY_D;
        }
        
        if(this instanceof Inky) {
            comandi[0] = Input.KEY_T;
            comandi[1] = Input.KEY_G;
            comandi[2] = Input.KEY_F;
            comandi[3] = Input.KEY_H;
        }
        
        if(this instanceof Pinky) {
            comandi[0] = Input.KEY_I;
            comandi[1] = Input.KEY_K;
            comandi[2] = Input.KEY_J;
            comandi[3] = Input.KEY_L;
        }
        
        if(this instanceof Blinky) {
            comandi[0] = Input.KEY_1;
            comandi[1] = Input.KEY_2;
            comandi[2] = Input.KEY_3;
            comandi[3] = Input.KEY_4;
        }
            
    }
    
    public boolean isDeath() {return death;}
    
    public void setDeath(boolean death) {this.death = death;}
}
