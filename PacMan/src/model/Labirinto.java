/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import altro.Frutto;
import altro.Tile;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import view.VistaLabirinto;

/**
 *
 * @author lorenzo
 */
public class Labirinto extends Observable{
    
    private TiledMap mazeMap;
        
    private final Double[] POWER_PILL_1 = new Double[]{0.3,0.7};
    private final Double[] POWER_PILL_2 = new Double[]{0.3,0.7};
    private final Double[] POWER_PILL_3 = new Double[]{0.3,0.7};
    private final Double[] POWER_PILL_4 = new Double[]{0.3,0.7};
    
    private int  mem_button, x, y;
    private Tile[][] tiles;
    
    private ArrayList<Double[]> powerPills;
    
    private int tile_width, tile_height;
   
    private PacMan pacman;
    
    private int XpmanUPsx, XpmanUPdx, XpmanDOWNsx, XpmanDOWNdx;     
    private int YpmanUPsx, YpmanUPdx, YpmanDOWNsx, YpmanDOWNdx;

    public Labirinto(TiledMap mazeMap) throws SlickException {

        this.mazeMap = mazeMap;
        inizializzazioneTiles();
        pacman = new PacMan();
        tile_width = mazeMap.getTileWidth();
        tile_height = mazeMap.getTileHeight();
        x = pacman.getxPos();
        y = pacman.getyPos();
        

        //generaPowerPills();
        
    }
  
    private void inizializzazioneTiles(){
        boolean[][] blocked = generaMappaProprietà("blocked");
        boolean[][] tunnel = generaMappaProprietà("tunnel");
        boolean[][] eat = generaMappaProprietà("eat");
        tiles = new Tile[mazeMap.getWidth()][mazeMap.getHeight()];
        
        
        for (int i = 0; i < mazeMap.getWidth(); i++) {
            for (int j = 0; j < mazeMap.getHeight(); j++) {
                tiles[i][j] = new Tile(tile_width, tile_height, blocked[i][j], tunnel[i][j], eat[i][j]);
            }
        }
    }
    private void generaPowerPills(){
        powerPills.add(POWER_PILL_1);
        powerPills.add(POWER_PILL_2);
        powerPills.add(POWER_PILL_3);
        powerPills.add(POWER_PILL_4);
    }

    
    public int[] movimento(Input input){
        
        int spostamento = 2;
        
        System.out.println("coordinata x:   " + x + "    coordinata y:   " + y);
        
        XpmanUPsx = x; YpmanUPsx = y;
        XpmanUPdx = x + tile_width - 1; YpmanUPdx = y;
        XpmanDOWNsx = x; YpmanDOWNsx = y + tile_height - 1;
        XpmanDOWNdx = x + tile_width - 1; YpmanDOWNdx = y + tile_height - 1;

        if ((input.isKeyDown(Input.KEY_UP) || mem_button == 1) && ((!tiles[XpmanUPsx/tile_width][(YpmanUPsx - spostamento)/tile_height].isBlocked())) &&
           (!tiles[XpmanUPdx/tile_width][(YpmanUPdx - spostamento)/tile_height].isBlocked())){
                
                //pacman = up; da mettere nel render
                mem_button = 1; 
                
                if (AltroTastoPremuto(input, Input.KEY_UP)){
                   mem_button = 0;
                }else{
                    //pacman.update(spostamento * 10); va nel render
                    y -= spostamento;
                }
        }
        
        if (((input.isKeyDown(Input.KEY_DOWN) || mem_button == 2) && !tiles[XpmanDOWNsx/tile_width][(YpmanDOWNsx + spostamento)/tile_height].isBlocked()) && 
           (!tiles[XpmanDOWNdx/tile_width][(YpmanDOWNdx + spostamento)/tile_height].isBlocked())){
                //pacman = down;
                mem_button = 2;
                
                if (AltroTastoPremuto(input, Input.KEY_DOWN)){
                   mem_button = 0;
                }else{
                    //pacman.update(spostamento * 10);
                    y += spostamento;
                }                               
        }
        
        if (((input.isKeyDown(Input.KEY_LEFT) || mem_button == 3) && !tiles[(XpmanUPsx - spostamento)/tile_width][YpmanUPsx/tile_height].isBlocked()) && 
           (!tiles[(XpmanDOWNsx - spostamento)/tile_width][YpmanDOWNsx/tile_height].isBlocked())){
                //pacman = left;
                mem_button = 3;
            
                if (AltroTastoPremuto(input, Input.KEY_LEFT)){
                   mem_button = 0;
                }else{
                    //pacman.update(spostamento * 10);
                    x -= spostamento;
                }
                
                if ((tiles[(XpmanUPdx - spostamento)/tile_width][YpmanUPsx/tile_height].isTunnel()) && (tiles[(XpmanUPsx - spostamento)/tile_width][YpmanDOWNdx/tile_height].isTunnel())) 
                    x = tile_width * (mazeMap.getWidth() - 1);            
        }
        
        if (((input.isKeyDown(Input.KEY_RIGHT) || mem_button == 4) && !tiles[(XpmanUPdx + spostamento)/tile_width][YpmanUPdx/tile_height].isBlocked()) &&
           (!tiles[(XpmanDOWNdx + spostamento)/tile_width][YpmanDOWNdx/tile_height].isBlocked())){
                //pacman = right;
                mem_button = 4;
                
                if (AltroTastoPremuto(input, Input.KEY_RIGHT)){
                   mem_button = 0;
                }else{
                    //pacman.update(spostamento * 10);
                    x += spostamento;
                }
                
                if ((tiles[(XpmanUPsx + spostamento)/tile_width][YpmanUPsx/tile_height].isTunnel()) && (tiles[(XpmanDOWNdx + spostamento)/tile_width][YpmanDOWNdx/tile_height].isTunnel()))
                    x = 0;
        }
        int[] a = new int[2];
        a[0] = x;
        a[1] = y;
        return a;
    }
    
    public boolean AltroTastoPremuto(Input input, int n) {
        int[] UsedKeys = {Input.KEY_DOWN, Input.KEY_UP, Input.KEY_LEFT, Input.KEY_RIGHT}; 

        if ((input.isKeyDown(Input.KEY_DOWN)) && (!tiles[XpmanDOWNsx/tile_width][(YpmanDOWNsx + 1)/tile_height].isBlocked()) &&
            (!tiles[XpmanDOWNdx/tile_width][(YpmanDOWNdx + 1)/tile_height].isBlocked()) && Input.KEY_DOWN != n)
                return true;
                
        else if ((input.isKeyDown(Input.KEY_UP)) && (!tiles[XpmanUPsx/tile_width][(YpmanUPsx - 1)/tile_height].isBlocked()) && 
            (!tiles[XpmanUPdx/tile_width][(YpmanUPdx - 1)/tile_height].isBlocked()) && Input.KEY_UP != n)
                return true;
        
        else if ((input.isKeyDown(Input.KEY_LEFT)) && (!tiles[(XpmanUPsx - 1)/tile_width][YpmanUPsx/tile_height].isBlocked()) &&
           (!tiles[(XpmanDOWNsx - 1)/tile_width][YpmanDOWNsx/tile_height].isBlocked()) && Input.KEY_LEFT != n)
                return true;
        
        else if((input.isKeyDown(Input.KEY_RIGHT)) && (!tiles[(XpmanUPdx + 1)/tile_width][YpmanUPdx/tile_height].isBlocked()) && 
          (!tiles[(XpmanDOWNdx + 1)/tile_width][YpmanDOWNdx/tile_height].isBlocked()) && Input.KEY_RIGHT != n)
                return true;
        else
            return false;
    }
        
    public boolean[][] generaMappaProprietà(String s) {
        
        int altezza = mazeMap.getHeight();
        int larghezza = mazeMap.getWidth();
        
        boolean[][] b = new boolean[larghezza][altezza];
        for (int i = 0; i < mazeMap.getWidth(); i++) {
            for (int j = 0; j < mazeMap.getHeight(); j++) {
                
                int tileID = mazeMap.getTileId(i, j, mazeMap.getLayerIndex("Livello tile 1"));
                
                String value = mazeMap.getTileProperty(tileID, s , "false");
                if(value.equals("true")) {
                    b[i][j] = true;
                }
            }
        }
        return b;
    }
    
    public Frutto generaFrutto(){
        Random random = new Random();
        int indiceFrutto = random.nextInt(6);
        
        switch(indiceFrutto){
            case 0:
                return Frutto.CILIEGIA;               
            case 1:
                return Frutto.FRAGOLA;               
            case 2:
                return Frutto.ARANCIA;              
            case 3:
                return Frutto.MELA;            
            case 4:
                return Frutto.UVA;        
            case 5:
                return Frutto.CAMPANA;
            case 6:
                return Frutto.CHIAVE;                
        }
        return null;
    }
}
