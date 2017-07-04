/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Fantasmi;

import altro.Tile;
import model.Direzioni;
import model.Player;
import org.newdawn.slick.Input;

/**
 *
 * @author cl427927
 */
public abstract class Fantasma extends Player{
    private boolean blu;

    public Fantasma(int tile_width, int tile_heigth, int mapWidth, Tile[][] tiles) {
        super(tile_width, tile_heigth, mapWidth, tiles);
        
    }

    public void movimento(int direzione){
        
        int spostamento = 2;
        
        
        //setCorners();

        if ((direzione == 0) && controlloBlockedSu()){   
                y -= spostamento; 
                direction = Direzioni.UP;
        }
        
        if ((direzione == 1) && controlloBlockedGiu()){
                    y += spostamento;
                    direction = Direzioni.DOWN;
                }                               
       
        if ((direzione == 2) && controlloBlockedSx()){
                    x -= spostamento;
                    direction = Direzioni.LEFT;
                
                if (controlloTunnelSx()) 
                    x = tile_width * (mapWidth - 1);            
        }
        
        if ((direzione == 3) && controlloBlockedDx()){
                    x += spostamento;
                    direction = Direzioni.RIGHT;
        
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
    
    public void esciGabbia(){
        //impostare movimento di uscita
    }
    
}
