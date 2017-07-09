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
    private String nome;
    private int punteggio;
    protected int x, y;
    public int mem_button, tile_width, tile_height, mapWidth;
    public Tile[][] tiles;
    public int xUpSx, xUpDx, xDownSx, xDownDx;     
    public int yUpSx, yUpDx, yDownSx, yDownDx;
    public int spostamento = 2;
    public int[] comandi = new int[4];
    
    protected Direzioni direction = Direzioni.INITIAL;
    
    public Player(int tile_width, int tile_heigth, int mapWidth, Tile[][] tiles) {
        this.tile_width = tile_width;
        this.tile_height = tile_heigth;
        this.mapWidth = mapWidth;
        this.tiles = tiles;
    }
    
   public void movimentoManuale(Input input){ 
        setControlKeys();
        setCorners();

        if ((input.isKeyDown(comandi[0]) || mem_button == 1) && controlloBlockedSu()){                
                mem_button = 1; 
                
                if (AltroTastoPremuto(input, comandi[0])){
                   mem_button = 0;
                }else{
                    y -= spostamento;
                    direction = Direzioni.UP;
                }
        }
        
        if ((input.isKeyDown(comandi[1]) || mem_button == 2) && controlloBlockedGiu()){
                mem_button = 2;
                
                if (AltroTastoPremuto(input, comandi[1])){
                   mem_button = 0;
                }else{
                    y += spostamento;
                    direction = Direzioni.DOWN;
                }                               
        }
        
        if ((input.isKeyDown(comandi[2]) || mem_button == 3) && controlloBlockedSx()){
                mem_button = 3;
            
                if (AltroTastoPremuto(input, comandi[2])){
                   mem_button = 0;
                }else{
                    x -= spostamento;
                    direction = Direzioni.LEFT;
                }
                
                if (controlloTunnelSx()) 
                    x = tile_width * (mapWidth - 1);            
        }
        
        if (((input.isKeyDown(comandi[3]) || mem_button == 4) && controlloBlockedDx())){
                mem_button = 4;
                
                if (AltroTastoPremuto(input, comandi[3])){
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
}
