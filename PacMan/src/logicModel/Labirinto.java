/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicModel;

import altro.Frutto;
import altro.Tile;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import presentation.VistaLabirinto;

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
    
    private int  mem_button;
    private Tile[][] tiles;
    
    private ArrayList<Double[]> powerPills;
    
    private VistaLabirinto vistaLabirinto;//observer
    
    
    private Input input;
    private PacMan pacman;
    
    private int XpmanUPsx, XpmanUPdx, XpmanDOWNsx, XpmanDOWNdx;     
    private int YpmanUPsx, YpmanUPdx, YpmanDOWNsx, YpmanDOWNdx;

    public Labirinto(VistaLabirinto vistaLabirinto) throws SlickException {

        this.vistaLabirinto = vistaLabirinto;
        mazeMap = vistaLabirinto.getMazeMap();
        inizializzazioneTiles();
        pacman = new PacMan();

        //generaPowerPills();
        
    }
    private void inizializzazioneTiles(){
        boolean[][] blocked = vistaLabirinto.getBlocked();
        boolean[][] tunnel = vistaLabirinto.getTunnel();
        boolean[][] eat = vistaLabirinto.getEat();
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tiles[i][j] = new Tile(mazeMap.getTileWidth(), mazeMap.getTileHeight(), blocked[i][j], tunnel[i][j], eat[i][j]);
            }
        }
    }
    private void generaPowerPills(){
        powerPills.add(POWER_PILL_1);
        powerPills.add(POWER_PILL_2);
        powerPills.add(POWER_PILL_3);
        powerPills.add(POWER_PILL_4);
    }

    
    private void movimento(){
        
        this.input = vistaLabirinto.getInput();
        
        int spostamento = 20;
        int x = pacman.getxPos();
        int y = pacman.getyPos();
        
        int tHeight = tiles[0][0].getTILE_HEIGHT();
        int tWidth = tiles[0][0].getTILE_WIDTH();
        
        System.out.println("coordinata x:   " + x + "    coordinata y:   " + y);
        
        XpmanUPsx = x; YpmanUPsx = y;
        XpmanUPdx = x + tWidth - 1; YpmanUPdx = y;
        XpmanDOWNsx = x; YpmanDOWNsx = y + tHeight - 1;
        XpmanDOWNdx = x + tWidth - 1; YpmanDOWNdx = y + tHeight - 1;

        if ((input.isKeyDown(Input.KEY_UP) || mem_button == 1) && ((!tiles[XpmanUPsx/tWidth][(YpmanUPsx - spostamento)/tHeight].isBlocked())) &&
           (!tiles[XpmanUPdx/tWidth][(YpmanUPdx - spostamento)/tHeight].isBlocked())){
                
                //pacman = up; da mettere nel render
                mem_button = 1; 
                
                if (AltroTastoPremuto(input, Input.KEY_UP)){
                   mem_button = 0;
                }else{
                    //pacman.update(spostamento * 10); va nel render
                    y -= spostamento;
                }
        }
        
        if (((input.isKeyDown(Input.KEY_DOWN) || mem_button == 2) && !tiles[XpmanDOWNsx/tWidth][(YpmanDOWNsx + spostamento)/tHeight].isBlocked()) && 
           (!tiles[XpmanDOWNdx/tWidth][(YpmanDOWNdx + spostamento)/tHeight].isBlocked())){
                //pacman = down;
                mem_button = 2;
                
                if (AltroTastoPremuto(input, Input.KEY_DOWN)){
                   mem_button = 0;
                }else{
                    //pacman.update(spostamento * 10);
                    y += spostamento;
                }                               
        }
        
        if (((input.isKeyDown(Input.KEY_LEFT) || mem_button == 3) && !tiles[(XpmanUPsx - spostamento)/tWidth][YpmanUPsx/tHeight].isBlocked()) && 
           (!tiles[(XpmanDOWNsx - spostamento)/tWidth][YpmanDOWNsx/tHeight].isBlocked())){
                //pacman = left;
                mem_button = 3;
            
                if (AltroTastoPremuto(input, Input.KEY_LEFT)){
                   mem_button = 0;
                }else{
                    //pacman.update(spostamento * 10);
                    x -= spostamento;
                }
                
                if ((tiles[XpmanUPdx - spostamento][YpmanUPsx].isTunnel()) && (tiles[XpmanUPsx - spostamento][YpmanDOWNdx].isTunnel())) 
                    x = tWidth * (mazeMap.getWidth() - 1);            
        }
        
        if (((input.isKeyDown(Input.KEY_RIGHT) || mem_button == 4) && !tiles[(XpmanUPdx + spostamento)/tWidth][YpmanUPdx/tHeight].isBlocked()) &&
           (!tiles[(XpmanDOWNdx + spostamento)/tWidth][YpmanDOWNdx/tHeight].isBlocked())){
                //pacman = right;
                mem_button = 4;
                
                if (AltroTastoPremuto(input, Input.KEY_RIGHT)){
                   mem_button = 0;
                }else{
                    //pacman.update(spostamento * 10);
                    x += spostamento;
                }
                
                if ((tiles[XpmanUPsx + spostamento][YpmanUPsx].isTunnel()) && (tiles[XpmanDOWNdx + spostamento][YpmanDOWNdx].isTunnel()))
                    x = 0;
        }
    }
    
    public boolean AltroTastoPremuto(Input input, int n) {
        int[] UsedKeys = {Input.KEY_DOWN, Input.KEY_UP, Input.KEY_LEFT, Input.KEY_RIGHT}; 

        if ((input.isKeyDown(Input.KEY_DOWN)) && (!tiles[XpmanDOWNsx][YpmanDOWNsx + 1].isBlocked()) &&
            (!tiles[XpmanDOWNdx][YpmanDOWNdx + 1].isBlocked()) && Input.KEY_DOWN != n)
                return true;
                
        else if ((input.isKeyDown(Input.KEY_UP)) && (!tiles[XpmanUPsx][YpmanUPsx - 1].isBlocked()) && 
            (!tiles[XpmanUPdx][YpmanUPdx - 1].isBlocked()) && Input.KEY_UP != n)
                return true;
        
        else if ((input.isKeyDown(Input.KEY_LEFT)) && (!tiles[XpmanUPsx - 1][YpmanUPsx].isBlocked()) &&
           (!tiles[XpmanDOWNsx - 1][YpmanDOWNsx].isBlocked()) && Input.KEY_LEFT != n)
                return true;
        
        else if((input.isKeyDown(Input.KEY_RIGHT)) && (!tiles[XpmanUPdx + 1][YpmanUPdx].isBlocked()) && 
          (!tiles[XpmanDOWNdx + 1][YpmanDOWNdx].isBlocked()) && Input.KEY_RIGHT != n)
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
    
//    private void startGame(){
//                try
//        {
//            AppGameContainer app = new AppGameContainer(vistaLabirinto);
//            app.setDisplayMode(608, 704, false);
//            app.setTargetFrameRate(60);
//            app.start();
//        }
//        catch (SlickException e)
//        {
//            e.printStackTrace();
//        }
//    }
}
