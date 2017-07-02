/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import altro.Tile;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author cl427927
 */
public class PacMan extends Player {
    private int vite;
    private boolean power;
    private boolean death;
    
    /*
    TODO: definire le posizioni iniziali di PacMan
    */
    private final int X_MAIN_POS = 288;
    private final int Y_MAIN_POS = 512;

    public PacMan(int tile_width, int tile_heigth, int mapWidth, Tile[][] tiles) {
        super(tile_width, tile_heigth, mapWidth, tiles);
        x = X_MAIN_POS;
        y = Y_MAIN_POS;
        vite = 5;
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


    
    //getter
    public int getxPos() {return x;}
    public int getyPos() {return y;}
    public int getVite() {return vite;}
    public boolean isPower() {return power;}

    public boolean isDeath() {
        return death;
    }
    
    

    //setter
    public void setVite() {vite--;}
    public void setPower(boolean power) {this.power = power;}      

    public void setDeath(boolean death) {
        this.death = death;
    }

    
}
