/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Fantasmi.Clyde;
import altro.Tile;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Fantasmi.Blinky;
import model.Fantasmi.Inky;
import model.Fantasmi.Pinky;
import org.lwjgl.opengl.Display;
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
    
    private int NormFactorX, NormFactorY;
    
    private ArrayList<int[]> powerPills;
    
    private int tileWidth, tileHeight;
   
    private PacMan pacman;
    private Clyde clyde;
    private Blinky blinky;
    private Inky inky;
    private Pinky pinky;
    
    private boolean delayFlag = true;

    public Labirinto(TiledMap mazeMap, VistaLabirinto vistaLabirinto) throws SlickException {

        this.mazeMap = mazeMap;
        inizializzazioneTiles(vistaLabirinto);
        this.addObserver(vistaLabirinto);
        tileWidth = mazeMap.getTileWidth();
        tileHeight = mazeMap.getTileHeight();
        
        NormFactorX = mazeMap.getWidth()*tileWidth;
        
        
        pacman = new PacMan(tileWidth, tileHeight, mazeMap.getWidth(), tiles);
        
        clyde = new Clyde(tileWidth, tileHeight, mazeMap.getWidth(), tiles);
        blinky = new Blinky(tileWidth, tileHeight, mazeMap.getWidth(), tiles);
        inky = new Inky(tileWidth, tileHeight, mazeMap.getWidth(), tiles);
        pinky = new Pinky(tileWidth, tileHeight, mazeMap.getWidth(), tiles);

        //generaPowerPills();
        
    }
  
    private void inizializzazioneTiles(VistaLabirinto vistaLabirinto) {
        boolean[][] blocked = vistaLabirinto.getBlocked();
        boolean[][] tunnel = vistaLabirinto.getTunnel();
        boolean[][] eat = vistaLabirinto.getEat();
        
        tiles = new Tile[mazeMap.getWidth()][mazeMap.getHeight()];
        
        for (int i = 0; i < mazeMap.getWidth(); i++) {
            for (int j = 0; j < mazeMap.getHeight(); j++) {
                tiles[i][j] = new Tile(tileWidth, tileHeight, blocked[i][j], tunnel[i][j], eat[i][j]);
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
        startMoment();
        
        gameOver();
        
        if(pacman.isDeath())
            resetPosition();
        
        pacman.movimento(input);
        clyde.movimento(clyde.choose_direction(pacman));
        //blinky.movimento(blinky.choose_direction());
        inky.movimento(inky.choose_direction());
        pinky.movimento(pinky.choose_direction());
        collision();
        superPillCollision();
        setChanged();
        notifyObservers();
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

    private void collision() {
        if(checkClydeCollision() || checkBlinkyCollision() || checkPinkyCollision() || checkInkyCollision()) {
            if(pacman.isDeath() == false)
                pacman.setVite();
            
            pacman.setDeath(true);
        }
        
    }

    private boolean checkClydeCollision() {
        return (pacman.getxPos() + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() == (clyde.getxPos() + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() && 
               (pacman.getyPos() + 15)/mazeMap.getHeight()*mazeMap.getTileHeight() == (clyde.getyPos() + 15)/mazeMap.getHeight()*mazeMap.getTileHeight();
    }

    private boolean checkBlinkyCollision() {
        return (pacman.getxPos() + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() == (blinky.getxPos() + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() && 
               (pacman.getyPos() + 15)/mazeMap.getHeight()*mazeMap.getTileHeight() == (blinky.getyPos() + 15)/mazeMap.getHeight()*mazeMap.getTileHeight();
    }

    private boolean checkPinkyCollision() {
        return (pacman.getxPos() + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() == (pinky.getxPos() + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() && 
               (pacman.getyPos() + 15)/mazeMap.getHeight()*mazeMap.getTileHeight() == (pinky.getyPos() + 15)/mazeMap.getHeight()*mazeMap.getTileHeight();
    }

    private boolean checkInkyCollision() {
        return (pacman.getxPos() + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() == (inky.getxPos() + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() && 
               (pacman.getyPos() + 15)/mazeMap.getHeight()*mazeMap.getTileHeight() == (inky.getyPos() + 15)/mazeMap.getHeight()*mazeMap.getTileHeight();
    }
    
    private void superPillCollision() {
        if ((pacman.getxPos() + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() == (32 + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() && 
           (pacman.getyPos() + 15)/mazeMap.getHeight()*mazeMap.getTileHeight() == (64 + 15)/mazeMap.getHeight()*mazeMap.getTileHeight())
            pacman.setPower(true);
        if ((pacman.getxPos() + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() == (544 + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() && 
           (pacman.getyPos() + 15)/mazeMap.getHeight()*mazeMap.getTileHeight() == (64 + 15)/mazeMap.getHeight()*mazeMap.getTileHeight())
            pacman.setPower(true);
        if ((pacman.getxPos() + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() == (32 + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() && 
           (pacman.getyPos() + 15)/mazeMap.getHeight()*mazeMap.getTileHeight() == (608 + 15)/mazeMap.getHeight()*mazeMap.getTileHeight())
            pacman.setPower(true);
        if ((pacman.getxPos() + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() == (544 + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() && 
           (pacman.getyPos() + 15)/mazeMap.getHeight()*mazeMap.getTileHeight() == (608 + 15)/mazeMap.getHeight()*mazeMap.getTileHeight())
            pacman.setPower(true);
    }

    private void resetPosition() {
        delay(true);
        pacman.setX(288); pacman.setY(512); pacman.setDeath(false);
        blinky.setX(288); blinky.setY(256);
        inky.setX(288);   inky.setY(256);
        clyde.setX(288);  clyde.setY(256);
        pinky.setX(288); pinky.setY(256);
    }
    
    private void delay(boolean flag){
        try {
            if (flag == true)
                Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Labirinto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void startMoment() {
        delay(delayFlag);
        delayFlag = false;
    }

    private void gameOver() {
        if(pacman.getVite() == 0){
            delay(true);
            Display.destroy();
        }
    }
}
