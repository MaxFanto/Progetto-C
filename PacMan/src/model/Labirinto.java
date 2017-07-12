/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Fantasmi.Clyde;
import altro.Tile;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Fantasmi.Blinky;
import model.Fantasmi.Inky;
import model.Fantasmi.Pinky;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.tiled.TiledMap;
import view.VistaLabirinto;
/**
 *
 * @author lorenzo
 */
public class Labirinto extends Observable {
    
    private TiledMap mazeMap;
    
    private Tile[][] tiles;
    
    private int tileWidth, tileHeight;
    
    private long time = 0;
   
    private PacMan pacman;
    private Clyde clyde;
    private Blinky blinky;
    private Inky inky;
    private Pinky pinky;
    
    private Sound eatGhost, death, eatSuperPill;
    
    private boolean delayFlag = true;
    private boolean superFlagUL=true,superFlagUR=true, superFlagDL=true, superFlagDR=true;

    public Labirinto(TiledMap mazeMap, VistaLabirinto vistaLabirinto) throws SlickException {

        this.mazeMap = mazeMap;
        inizializzazioneTiles(vistaLabirinto);
        this.addObserver(vistaLabirinto);
        tileWidth = mazeMap.getTileWidth();
        tileHeight = mazeMap.getTileHeight();
        
        pacman = new PacMan(tileWidth, tileHeight, mazeMap.getWidth(), tiles);
        
        clyde = new Clyde(tileWidth, tileHeight, mazeMap.getWidth(), tiles);
        blinky = new Blinky(tileWidth, tileHeight, mazeMap.getWidth(), tiles);
        inky = new Inky(tileWidth, tileHeight, mazeMap.getWidth(), tiles);
        pinky = new Pinky(tileWidth, tileHeight, mazeMap.getWidth(), tiles);
        
        eatGhost = new Sound("data/pacmanSound/eatGhost.wav");
        death = new Sound("data/pacmanSound/death.wav");
        eatSuperPill = new Sound("data/pacmanSound/eatSuperPill.wav");
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
    
    public void movimentoGiocatori(Input input, String mode) {
        startMoment();
        
        gameOver();
        
        checkDeath();
        
        pacman.movimentoManuale(input);
        
        checkModeGame(input, mode);
        checkModeCollision();
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
            death.play();
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
           (pacman.getyPos() + 15)/mazeMap.getHeight()*mazeMap.getTileHeight() == (64 + 15)/mazeMap.getHeight()*mazeMap.getTileHeight()
           && superFlagUL==true) {
            
            pacman.setPower(true);
            time = System.currentTimeMillis();
            superFlagUL = false;
            setGhostsSpeed(1);
            eatSuperPill.play();
        }
        
        if ((pacman.getxPos() + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() == (544 + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() && 
           (pacman.getyPos() + 15)/mazeMap.getHeight()*mazeMap.getTileHeight() == (64 + 15)/mazeMap.getHeight()*mazeMap.getTileHeight()
           && superFlagUR==true) {
            
            pacman.setPower(true);
            time = System.currentTimeMillis();
            superFlagUR = false;
            setGhostsSpeed(1);
            eatSuperPill.play();
        }
        
        if ((pacman.getxPos() + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() == (32 + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() && 
           (pacman.getyPos() + 15)/mazeMap.getHeight()*mazeMap.getTileHeight() == (608 + 15)/mazeMap.getHeight()*mazeMap.getTileHeight()
           && superFlagDL==true) {
            
            pacman.setPower(true);
            time = System.currentTimeMillis();
            superFlagDL = false;
            setGhostsSpeed(1);
            eatSuperPill.play();
        }
        if ((pacman.getxPos() + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() == (544 + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() && 
           (pacman.getyPos() + 15)/mazeMap.getHeight()*mazeMap.getTileHeight() == (608 + 15)/mazeMap.getHeight()*mazeMap.getTileHeight()
           && superFlagDR==true) {
            
            pacman.setPower(true);
            time = System.currentTimeMillis();
            superFlagDR = false;
            setGhostsSpeed(1);
            eatSuperPill.play();
        }
        
        checkTime(time);
    }

    private void resetPosition() {
        delay(true, 2000);
        pacman.setX(288); pacman.setY(512); pacman.setDeath(false);
        blinkyResetPosition();
        inkyResetPosition();
        clydeResetPosition();
        pinkyResetPosition();
    }
    
    private void delay(boolean flag, int time){
        try {
            if (flag == true)
                Thread.sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(Labirinto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void startMoment() {
        delay(delayFlag, 4000);
        delayFlag = false;
    }

    private void gameOver() {
        if(pacman.getVite() == 0){
            delay(true, 3000);
            Display.destroy();
        }
    }

    private void checkModeGame(Input input, String mode) {
        if(mode.equals("single")) {
            clyde.movimentoArtificiale(clyde.choose_direction(pacman));
//            blinky.movimentoArtificiale(blinky.choose_direction());
            inky.movimentoArtificiale(inky.choose_direction());
            pinky.movimentoArtificiale(pinky.choose_direction());
        }
        if(mode.equals("multi")){
            clyde.movimentoManuale(input);
            blinky.movimentoManuale(input);
            inky.movimentoManuale(input);
            pinky.movimentoManuale(input);
        }
    }

    private void checkTime(long time) {
        if (System.currentTimeMillis() >= (time + 10000) && pacman.isPower()) {
            pacman.setPower(false);
            setGhostsSpeed(2);
        }
    }

    private void checkModeCollision() {
        if (pacman.isPower())
            collisionPower();
        else 
            collision();
    }

    private void collisionPower() {
        if (checkClydeCollision()) {
            clyde.setDeath(true);
            eatGhost.play();
        }
        if (checkBlinkyCollision()) {
            blinky.setDeath(true);
            eatGhost.play();
        }
        if (checkPinkyCollision()) {
            pinky.setDeath(true);
            eatGhost.play();
        }
        if (checkInkyCollision()) {
            inky.setDeath(true);
            eatGhost.play();
        }
    }

    private void blinkyResetPosition() {
        blinky.setX(288); blinky.setY(320); blinky.setDeath(false);
    }

    private void inkyResetPosition() {
        inky.setX(288);   inky.setY(320); inky.setDeath(false);
    }

    private void clydeResetPosition() {
        clyde.setX(288);  clyde.setY(320); clyde.setDeath(false);
    }

    private void pinkyResetPosition() {
        pinky.setX(288); pinky.setY(320); pinky.setDeath(false);
    }

    private void checkDeath() {
        if(pacman.isDeath())
            resetPosition();
        if(blinky.isDeath())
            blinkyResetPosition();
        if(inky.isDeath())
            inkyResetPosition();
        if(clyde.isDeath())
            clydeResetPosition();
        if(pinky.isDeath())
            pinkyResetPosition();
    }

    private void setGhostsSpeed(int speed) {
        clyde.setSpostamento(speed);
        inky.setSpostamento(speed);
        blinky.setSpostamento(speed);
        pinky.setSpostamento(speed);
    }
}
