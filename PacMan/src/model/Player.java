/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import altro.Tile;
import java.util.Observable;

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

    protected void setCorners(){
        xUpSx = x; yUpSx = y;
        xUpDx = x + tile_width - 1; yUpDx = y;
        xDownSx = x; yDownSx = y + tile_height - 1;
        xDownDx = x + tile_width - 1; yDownDx = y + tile_height - 1;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio += punteggio;
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
