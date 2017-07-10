/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import altro.Tile;

/**
 *
 * @author cl427927
 */
public class PacMan extends Player {
    private int vite;
    private boolean power;
    
    
    /*
    TODO: definire le posizioni iniziali di PacMan
    */
    private final int X_MAIN_POS = 288;
    private final int Y_MAIN_POS = 512;

    public PacMan(int tile_width, int tile_heigth, int mapWidth, Tile[][] tiles) {
        super(tile_width, tile_heigth, mapWidth, tiles);
        x = X_MAIN_POS;
        y = Y_MAIN_POS;
        vite = 3;
    }

    //getter
    public int getxPos() {return x;}
    public int getyPos() {return y;}
    public int getVite() {return vite;}
    public boolean isPower() {return power;}

    //setter
    public void setVite() {vite--;}
    public void setPower(boolean power) {this.power = power;}
    
}
