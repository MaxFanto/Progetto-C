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
public class PacMan extends Giocatore {
    private int vite;
    private boolean power;
    
    /*
    TODO: definire le posizioni iniziali di PacMan
    */
    private final int X_MAIN_POS = 288;
    private final int Y_MAIN_POS = 512;
    private final int MAIN_VITE = 5;

    public PacMan(int tile_width, int tile_heigth, int mapWidth, Tile[][] tiles) {
        super(tile_width, tile_heigth, mapWidth, tiles);
        x = X_MAIN_POS;
        y = Y_MAIN_POS;
    }
    
public void movimento(Input input){        
//        System.out.println("coordinata x:   " + x + "    coordinata y:   " + y);
        
        XpmanUPsx = x; YpmanUPsx = y;
        XpmanUPdx = x + tile_width - 1; YpmanUPdx = y;
        XpmanDOWNsx = x; YpmanDOWNsx = y + tile_height - 1;
        XpmanDOWNdx = x + tile_width - 1; YpmanDOWNdx = y + tile_height - 1;

        if ((input.isKeyDown(Input.KEY_UP) || mem_button == 1) && controlloBlockedSu()){                
                mem_button = 1; 
                
                if (AltroTastoPremuto(input, Input.KEY_UP)){
                   mem_button = 0;
                }else{
                    y -= spostamento;
                }
        }
        
        if ((input.isKeyDown(Input.KEY_DOWN) || mem_button == 2) && controlloBlockedGiu()){
                mem_button = 2;
                
                if (AltroTastoPremuto(input, Input.KEY_DOWN)){
                   mem_button = 0;
                }else{
                    y += spostamento;
                }                               
        }
        
        if ((input.isKeyDown(Input.KEY_LEFT) || mem_button == 3) && controlloBlockedSx()){
                mem_button = 3;
            
                if (AltroTastoPremuto(input, Input.KEY_LEFT)){
                   mem_button = 0;
                }else{
                    x -= spostamento;
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
    
    

    //setter
    public void setVite(int vite) {this.vite = vite;}
    public void setPower(boolean power) {this.power = power;}      
}
