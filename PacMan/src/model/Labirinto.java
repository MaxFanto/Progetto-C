/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Fantasmi.Clyde;
import altro.Frutto;
import altro.Tile;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import model.Fantasmi.Blinky;
import model.Fantasmi.Inky;
import model.Fantasmi.Pinky;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import view.VistaLabirinto;
/**
 *
 * @author lorenzo
 */
public class Labirinto extends Observable {
    
    private TiledMap mazeMap;
        
    private final int[] POWER_PILL_1 = new int[]{3,7};
    private final int[] POWER_PILL_2 = new int[]{3,7};
    private final int[] POWER_PILL_3 = new int[]{3,7};
    private final int[] POWER_PILL_4 = new int[]{3,7};
    
    private Tile[][] tiles;
    
    private ArrayList<int[]> powerPills;
    
    private int tile_width, tile_height;
   
    private PacMan pacman;
    private Clyde clyde;
    private Blinky blinky;
    private Inky inky;
    private Pinky pinky;

    public Labirinto(TiledMap mazeMap, VistaLabirinto vistaLabirinto) throws SlickException {

        this.mazeMap = mazeMap;
        inizializzazioneTiles(vistaLabirinto);
        this.addObserver(vistaLabirinto);
        tile_width = mazeMap.getTileWidth();
        tile_height = mazeMap.getTileHeight();
        
        pacman = new PacMan(tile_width, tile_height, mazeMap.getWidth(), tiles);
        
        clyde = new Clyde(tile_width, tile_height, mazeMap.getWidth(), tiles);
        blinky = new Blinky(tile_width, tile_height, mazeMap.getWidth(), tiles);
        inky = new Inky(tile_width, tile_height, mazeMap.getWidth(), tiles);
        pinky = new Pinky(tile_width, tile_height, mazeMap.getWidth(), tiles);

        //generaPowerPills();
        
    }
  
    private void inizializzazioneTiles(VistaLabirinto vistaLabirinto) {
        boolean[][] blocked = vistaLabirinto.getBlocked();
        boolean[][] tunnel = vistaLabirinto.getTunnel();
        boolean[][] eat = vistaLabirinto.getEat();
        
        tiles = new Tile[mazeMap.getWidth()][mazeMap.getHeight()];
        
        
        for (int i = 0; i < mazeMap.getWidth(); i++) {
            for (int j = 0; j < mazeMap.getHeight(); j++) {
                tiles[i][j] = new Tile(tile_width, tile_height, blocked[i][j], tunnel[i][j], eat[i][j]);
            }
        }
    }
    private void generaPowerPills() {
        powerPills.add(POWER_PILL_1);
        powerPills.add(POWER_PILL_2);
        powerPills.add(POWER_PILL_3);
        powerPills.add(POWER_PILL_4);
    }

    
    public void movimentoGiocatori(Input input) {
        pacman.movimento(input);
        clyde.movimento(clyde.choose_direction());
        blinky.movimento(blinky.choose_direction());
        inky.movimento(inky.choose_direction());
        pinky.movimento(inky.choose_direction());
       
        setChanged();
        notifyObservers();
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

    public Clyde getClyde() {
        return clyde;
    }

    public Blinky getBlinky() {
        return blinky;
    }

    public Inky getInky() {
        return inky;
    }

    public Pinky getPinky() {
        return pinky;
    }
    
    
}
