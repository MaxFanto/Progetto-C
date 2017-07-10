/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Fantasmi;

import altro.Tile;
import model.Direzioni;
import model.Player;

/**
 *
 * @author cl427927
 */
public abstract class Fantasma extends Player{
    private boolean blu;
    protected final int X_MAIN_POS = 288;
    protected final int Y_MAIN_POS = 256;

    public Fantasma(int tile_width, int tile_heigth, int mapWidth, Tile[][] tiles) {
        super(tile_width, tile_heigth, mapWidth, tiles);
        
    }

    public void movimentoArtificiale(int direzione){
        
        int spostamento = 2;
        
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
                    x = tileWidth * (mapWidth - 1);            
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
    
}
