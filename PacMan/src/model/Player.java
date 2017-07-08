/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import altro.Tile;
import java.util.Observable;
import org.newdawn.slick.Input;

/**
 *
 * @author cl427927
 */
public abstract class Player extends Observable{
    private String nome;
    private int punteggio;
    protected int x, y;
    public int mem_button, tile_width, tile_height, mapWidth;
    public Tile[][] tiles;
    public int xUpSx, xUpDx, xDownSx, xDownDx;     
    public int yUpSx, yUpDx, yDownSx, yDownDx;
    public int spostamento = 2;
    
    protected Direzioni direction = Direzioni.INITIAL;
    
    public Player(int tile_width, int tile_heigth, int mapWidth, Tile[][] tiles) {
        this.tile_width = tile_width;
        this.tile_height = tile_heigth;
        this.mapWidth = mapWidth;
        this.tiles = tiles;
    }
    
   public void movimento(Input input){        
        setCorners();

        if ((input.isKeyDown(Input.KEY_UP) || mem_button == 1) && controlloBlockedSu()){                
                mem_button = 1; 
                
                if (AltroTastoPremuto(input, Input.KEY_UP)){
                   mem_button = 0;
                }else{
                    y -= spostamento;
                    direction = Direzioni.UP;
                }
        }
        
        if ((input.isKeyDown(Input.KEY_DOWN) || mem_button == 2) && controlloBlockedGiu()){
                mem_button = 2;
                
                if (AltroTastoPremuto(input, Input.KEY_DOWN)){
                   mem_button = 0;
                }else{
                    y += spostamento;
                    direction = Direzioni.DOWN;
                }                               
        }
        
        if ((input.isKeyDown(Input.KEY_LEFT) || mem_button == 3) && controlloBlockedSx()){
                mem_button = 3;
            
                if (AltroTastoPremuto(input, Input.KEY_LEFT)){
                   mem_button = 0;
                }else{
                    x -= spostamento;
                    direction = Direzioni.LEFT;
                }
                
                if (controlloTunnelSx()) 
                    x = tile_width * (mapWidth - 1);            
        }
        
        if (((input.isKeyDown(Input.KEY_RIGHT) || mem_button == 4) && controlloBlockedDx())){
                mem_button = 4;
                
                if (AltroTastoPremuto(input, Input.KEY_RIGHT)){
                   mem_button = 0;
                }else{
                    x += spostamento;
                    direction = Direzioni.RIGHT;
                }
                
                if (controlloTunnelDx())
                    x = 0;
        }
    }
    
    public boolean AltroTastoPremuto(Input input, int n) {
        int[] UsedKeys = {Input.KEY_DOWN, Input.KEY_UP, Input.KEY_LEFT, Input.KEY_RIGHT}; 

        if ((input.isKeyDown(Input.KEY_DOWN)) && controlloBlockedGiu() && Input.KEY_DOWN != n)
                return true;
                
        else if ((input.isKeyDown(Input.KEY_UP)) && controlloBlockedSu() && Input.KEY_UP != n)
                return true;
        
        else if ((input.isKeyDown(Input.KEY_LEFT)) && controlloBlockedSx() && Input.KEY_LEFT != n)
                return true;
        
        else if((input.isKeyDown(Input.KEY_RIGHT)) && controlloBlockedDx() && Input.KEY_RIGHT != n)
                return true;
        else
            return false;
    }

    protected void setCorners(){
        xUpSx = x; yUpSx = y;
        xUpDx = x + tile_width - 1; yUpDx = y;
        xDownSx = x; yDownSx = y + tile_height - 1;
        xDownDx = x + tile_width - 1; yDownDx = y + tile_height - 1;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio += punteggio;
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
    return !tiles[xUpSx/tile_width][(yUpSx - spostamento)/tile_height].isBlocked() &&
           !tiles[xUpDx/tile_width][(yUpDx - spostamento)/tile_height].isBlocked();
    }
    
    public boolean controlloBlockedGiu(){
    return !tiles[xDownSx/tile_width][(yDownSx + spostamento)/tile_height].isBlocked() && 
           !tiles[xDownDx/tile_width][(yDownDx + spostamento)/tile_height].isBlocked();
    }
    
    public boolean controlloBlockedSx() {
    return !tiles[(xUpSx - spostamento)/tile_width][yUpSx/tile_height].isBlocked() && 
           !tiles[(xDownSx - spostamento)/tile_width][yDownSx/tile_height].isBlocked();
    }
    
    public boolean controlloBlockedDx() {
        return !tiles[(xUpDx + spostamento)/tile_width][yUpDx/tile_height].isBlocked() &&
               !tiles[(xDownDx + spostamento)/tile_width][yDownDx/tile_height].isBlocked();
    }
    
    public boolean controlloTunnelSx(){
        return tiles[(xUpDx - spostamento)/tile_width][yUpSx/tile_height].isTunnel() && 
               tiles[(xUpSx - spostamento)/tile_width][yDownDx/tile_height].isTunnel();
    }
    
    public boolean controlloTunnelDx(){
        return tiles[(xUpSx + spostamento)/tile_width][yUpSx/tile_height].isTunnel() &&
               tiles[(xDownDx + spostamento)/tile_width][yDownDx/tile_height].isTunnel();
    }
}
