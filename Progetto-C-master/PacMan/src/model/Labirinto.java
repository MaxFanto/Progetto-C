/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Fantasmi.Guzuta;
import altro.Frutto;
import altro.Tile;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
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
        
    private final int[] POWER_PILL_1 = new int[]{3,7};
    private final int[] POWER_PILL_2 = new int[]{3,7};
    private final int[] POWER_PILL_3 = new int[]{3,7};
    private final int[] POWER_PILL_4 = new int[]{3,7};
    
    private Tile[][] tiles;
    
    private ArrayList<int[]> powerPills;
    
    private int tile_width, tile_height;
   
    private PacMan pacman;
    private Guzuta guzuta;

    public Labirinto(TiledMap mazeMap, VistaLabirinto vistaLabirinto) throws SlickException {

        this.mazeMap = mazeMap;
        inizializzazioneTiles();
        this.addObserver(vistaLabirinto);
        tile_width = mazeMap.getTileWidth();
        tile_height = mazeMap.getTileHeight();
        
        pacman = new PacMan(tile_width, tile_height, mazeMap.getWidth(), tiles);
        
        guzuta = new Guzuta(tile_width, tile_height, mazeMap.getWidth(), tiles);

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

    
    public void movimentoGiocatori(Input input){
        pacman.movimento(input);
        guzuta.movimento(guzuta.choose_direction());
       
        setChanged();
        notifyObservers();
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

    public PacMan getPacman() {
        return pacman;
    }

    public Guzuta getGuzuta() {
        return guzuta;
    }
    
    
}
