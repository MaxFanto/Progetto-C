/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicModel;

import altro.Frutto;
import altro.Tile;
import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import presentation.VistaLabirinto;

/**
 *
 * @author lorenzo
 */
public class Labirinto {
        
    private final Double[] POWER_PILL_1 = new Double[]{0.3,0.7};
    private final Double[] POWER_PILL_2 = new Double[]{0.3,0.7};
    private final Double[] POWER_PILL_3 = new Double[]{0.3,0.7};
    private final Double[] POWER_PILL_4 = new Double[]{0.3,0.7};
    
    private int larghezza, altezza, numTiles, mem_button;
    private Tile[][] tiles;
    
    private ArrayList<Double[]> powerPills;
    
    private VistaLabirinto vistaLabirinto;
    private static Input input;
    private PacMan pacman;
    
    private int XpmanUPsx, XpmanUPdx, XpmanDOWNsx, XpmanDOWNdx;     
    private int YpmanUPsx, YpmanUPdx, YpmanDOWNsx, YpmanDOWNdx;

    public Labirinto(int larghezza, int altezza, int numTiles, VistaLabirinto vistaLabirinto) throws SlickException {
        this.larghezza = larghezza;
        this.altezza = altezza;
        this.numTiles = numTiles;
        
        this.vistaLabirinto = vistaLabirinto;
        inizializzazioneTiles();
        pacman = new PacMan();
        
        generaPowerPills();

    }
    private void inizializzazioneTiles(){
        boolean[][] blocked = vistaLabirinto.generaMappaProprietà("blocked");
        boolean[][] tunnel = vistaLabirinto.generaMappaProprietà("tunnel");
        boolean[][] eat = vistaLabirinto.generaMappaProprietà("eat");
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tiles[i][j] = new Tile(vistaLabirinto.getTILE_WIDTH(),vistaLabirinto.getTILE_HEIGHT(),blocked[i][j],tunnel[i][j],eat[i][j]);
            }
        }
    }
    private void generaPowerPills(){
        powerPills.add(POWER_PILL_1);
        powerPills.add(POWER_PILL_2);
        powerPills.add(POWER_PILL_3);
        powerPills.add(POWER_PILL_4);
    }
    
    public static void AcquisisciInput(Input input){
        Labirinto.input = input;
    }
    
    
    
    private void movimento(){
        
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
                
                if (AltroTastoPremuto(Labirinto.input, Input.KEY_UP)){
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
                
                if (hasProperty(x - delta + (TILE_WIDTH - 1), y, tunnel) && (hasProperty(x - delta, y + (TILE_HEIGHT - 1), tunnel))) 
                    x = TILE_WIDTH * (mazeMap.getWidth() - 1);            
        }
        
        if (((input.isKeyDown(Input.KEY_RIGHT) || mem_button == 4) && !tiles[(XpmanUPdx + spostamento)/tWidth][YpmanUPdx/tHeight].isBlocked()) &&
           (!tiles[(XpmanDOWNdx + spostamento)/tWidth][YpmanDOWNdx/tHeight].isBlocked())){
                pacman = right;
                mem_button = 4;
                
                if (AltroTastoPremuto(input, Input.KEY_RIGHT)){
                   mem_button = 0;
                }else{
                    pacman.update(spostamento * 10);
                    x += spostamento;
                }
                
                if (hasProperty(x + delta, y, tunnel) && (hasProperty(x + delta + (TILE_WIDTH - 1), y + (TILE_HEIGHT - 1), tunnel))) 
                    x = 0;
        }
    }
    
        public boolean AltroTastoPremuto(Input input, int n) {
        int[] UsedKeys = {Input.KEY_DOWN, Input.KEY_UP, Input.KEY_LEFT, Input.KEY_RIGHT}; 

        if ((input.isKeyDown(Input.KEY_DOWN)) && (!hasProperty(XpmanDOWNsx, YpmanDOWNsx + 1, blocked) &&
            (!hasProperty(XpmanDOWNdx, YpmanDOWNdx + 1, blocked))) && Input.KEY_DOWN != n)
                return true;
                
        else if ((input.isKeyDown(Input.KEY_UP)) && (!hasProperty(XpmanUPsx, YpmanUPsx - 1, blocked)) && 
            (!hasProperty(XpmanUPdx, YpmanUPdx - 1, blocked)) && Input.KEY_UP != n)
                return true;
        
        else if ((input.isKeyDown(Input.KEY_LEFT)) && !hasProperty(XpmanUPsx - 1, YpmanUPsx, blocked) &&
           (!hasProperty(XpmanDOWNsx - 1, YpmanDOWNsx, blocked)) && Input.KEY_LEFT != n)
                return true;
        
        else if((input.isKeyDown(Input.KEY_RIGHT)) && !hasProperty(XpmanUPdx + 1, YpmanUPdx, blocked) && 
          (!hasProperty(XpmanDOWNdx + 1, YpmanDOWNdx, blocked)) && Input.KEY_RIGHT != n)
                return true;
        else
            return false;
    }
    
    private boolean hasProperty(int x, int y, Tile[][] b) {  //ritorna vero o falso, a seconda che la propriotà ci sia o meno
        int xP = x / tiles[0][0].getTILE_WIDTH(); //normalizzazione
        int yP = y / tiles[0][0].getTILE_HEIGHT();
        return b[xP][yP].is;
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
