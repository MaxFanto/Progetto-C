/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Fantasmi;

import altro.Tile;
import model.Giocatore;
import org.newdawn.slick.Input;

/**
 *
 * @author cl427927
 */
public abstract class Fantasma extends Giocatore{
    private boolean blu;
    private int X_MAIN_POS = 288;
    private int Y_MAIN_POS = 512;

    public Fantasma(int tile_width, int tile_heigth, int mapWidth, Tile[][] tiles) {
        super(tile_width, tile_heigth, mapWidth, tiles);
        x = X_MAIN_POS;
        y = Y_MAIN_POS;
    }

    public void movimento(int direzione){
        
        int spostamento = 2;
        
        
        //setCorners();

        if ((direzione == 0) && controlloBlockedSu()){   
                y -= spostamento;                
        }
        
        if ((direzione == 1) && controlloBlockedGiu()){
                    y += spostamento;
                }                               
       
        if ((direzione == 2) && controlloBlockedSx()){
                    x -= spostamento;
                
                if (controlloTunnelSx()) 
                    x = tile_width * (mapWidth - 1);            
        }
        
        if ((direzione == 3) && controlloBlockedDx()){
                    x += spostamento;
        
                if (controlloTunnelDx())
                    x = 0;
        }
    }
    
    //getter
    public boolean isBlu() {return blu;}

    public int getxPos() {return x;}

    public int getyPos() {return y;}
    
    //setter
    public void setBlu(boolean blu) {this.blu = blu;}

    public void morte(){
        //fa qualcosa       
    }
    
}
