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
public abstract class Giocatore extends Observable{
    private String nome;
    private int punteggio;
    public int x, y;
    public int mem_button, tile_width, tile_height, mapWidth;
    public Tile[][] tiles;
    private int velocita;
    public int XpmanUPsx, XpmanUPdx, XpmanDOWNsx, XpmanDOWNdx;     
    public int YpmanUPsx, YpmanUPdx, YpmanDOWNsx, YpmanDOWNdx;
    public int spostamento = 2;
    
    public Giocatore(int tile_width, int tile_heigth, int mapWidth, Tile[][] tiles) {
        this.tile_width = tile_width;
        this.tile_height = tile_heigth;
        this.mapWidth = mapWidth;
        this.tiles = tiles;
    }    
        
    public boolean controlloBlockedSu(){
    return !tiles[XpmanUPsx/tile_width][(YpmanUPsx - spostamento)/tile_height].isBlocked() &&
           !tiles[XpmanUPdx/tile_width][(YpmanUPdx - spostamento)/tile_height].isBlocked();
    }
    
    public boolean controlloBlockedGiu(){
    return !tiles[XpmanDOWNsx/tile_width][(YpmanDOWNsx + spostamento)/tile_height].isBlocked() && 
           !tiles[XpmanDOWNdx/tile_width][(YpmanDOWNdx + spostamento)/tile_height].isBlocked();
    }
    
    public boolean controlloBlockedSx() {
    return !tiles[(XpmanUPsx - spostamento)/tile_width][YpmanUPsx/tile_height].isBlocked() && 
           !tiles[(XpmanDOWNsx - spostamento)/tile_width][YpmanDOWNsx/tile_height].isBlocked();
    }
    
    public boolean controlloBlockedDx() {
        return !tiles[(XpmanUPdx + spostamento)/tile_width][YpmanUPdx/tile_height].isBlocked() &&
               !tiles[(XpmanDOWNdx + spostamento)/tile_width][YpmanDOWNdx/tile_height].isBlocked();
    }
    
    public boolean controlloTunnelSx(){
        return tiles[(XpmanUPdx - spostamento)/tile_width][YpmanUPsx/tile_height].isTunnel() && 
               tiles[(XpmanUPsx - spostamento)/tile_width][YpmanDOWNdx/tile_height].isTunnel();
    }
    
    public boolean controlloTunnelDx(){
        return tiles[(XpmanUPsx + spostamento)/tile_width][YpmanUPsx/tile_height].isTunnel() &&
               tiles[(XpmanDOWNdx + spostamento)/tile_width][YpmanDOWNdx/tile_height].isTunnel();
    }
}
