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
import view.MazeView;

public class Maze extends Observable {
    
    private TiledMap mazeMap;
    
    private Tile[][] tiles;
    
    private int tileWidth, tileHeight;
    
    private long time = 0;
    
    private final int POWER_TIME = 10000;
    
    private int speedLow = 1;
    private int speedHigh = 2;
   
    private PacMan pacman;
    private Clyde clyde;
    private Blinky blinky;
    private Inky inky;
    private Pinky pinky;
    
    private Sound eatGhost, death, eatSuperPill;
    
    private boolean delayFlag = true;
    private boolean superFlagUL=true,superFlagUR=true, superFlagDL=true, superFlagDR=true;

    /**
     * 
     * @param mazeMap
     * @param mazeView
     * @throws SlickException 
     */
    public Maze(TiledMap mazeMap, MazeView mazeView) throws SlickException {

        this.mazeMap = mazeMap;
        initializationTiles(mazeView);
        this.addObserver(mazeView);
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
  
    /**
     * 
     * @param mazeView 
     */
    private void initializationTiles(MazeView mazeView) {
        boolean[][] blocked = mazeView.getBlocked();
        boolean[][] tunnel = mazeView.getTunnel();
        boolean[][] eat = mazeView.getEat();
        
        tiles = new Tile[mazeMap.getWidth()][mazeMap.getHeight()];
        
        for (int i = 0; i < mazeMap.getWidth(); i++) {
            for (int j = 0; j < mazeMap.getHeight(); j++) {
                tiles[i][j] = new Tile(tileWidth, tileHeight, blocked[i][j], tunnel[i][j], eat[i][j]);
            }
        }
    }
    
    /**
     * This method is for the inizialization of tile's static property 
     * @param vistalabirinto the view of the game
     */
    
    public void notifyModify(Input input, String mode) {
        startMoment();
        
        gameOver();
        
        checkDeath();
        
        pacman.manualMovement(input);
        
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

    /**
     * This method checks if the is a collision between pacman and Clyde
     * @return true if there is collision
     */
    private boolean checkClydeCollision() {
        return (pacman.getxPos() + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() == (clyde.getxPos() + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() && 
               (pacman.getyPos() + 15)/mazeMap.getHeight()*mazeMap.getTileHeight() == (clyde.getyPos() + 15)/mazeMap.getHeight()*mazeMap.getTileHeight();
    }
    
    /**
     * This method checks if the is a collision between pacman and Blinky
     * @return true if there is collision
     */
    private boolean checkBlinkyCollision() {
        return (pacman.getxPos() + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() == (blinky.getxPos() + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() && 
               (pacman.getyPos() + 15)/mazeMap.getHeight()*mazeMap.getTileHeight() == (blinky.getyPos() + 15)/mazeMap.getHeight()*mazeMap.getTileHeight();
    }

    /**
     * This method checks if the is a collision between Pacman and Pinky
     * @return true if there is collision
     */
    private boolean checkPinkyCollision() {
        return (pacman.getxPos() + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() == (pinky.getxPos() + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() && 
               (pacman.getyPos() + 15)/mazeMap.getHeight()*mazeMap.getTileHeight() == (pinky.getyPos() + 15)/mazeMap.getHeight()*mazeMap.getTileHeight();
    }
    
    /**
     * This method checks if the is a collision between pacman and Inky
     * @return true if there is collision
     */
    private boolean checkInkyCollision() {
        return (pacman.getxPos() + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() == (inky.getxPos() + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() && 
               (pacman.getyPos() + 15)/mazeMap.getHeight()*mazeMap.getTileHeight() == (inky.getyPos() + 15)/mazeMap.getHeight()*mazeMap.getTileHeight();
    }
    
    /**
     * This method manages the collision with a super-pill
     */
    private void superPillCollision() {
        if ((pacman.getxPos() + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() == (32 + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() && 
           (pacman.getyPos() + 15)/mazeMap.getHeight()*mazeMap.getTileHeight() == (64 + 15)/mazeMap.getHeight()*mazeMap.getTileHeight()
           && superFlagUL == true) {
            
            pacman.setPower(true);
            time = System.currentTimeMillis();
            superFlagUL = false;
            setGhostsSpeed(speedHigh);
            eatSuperPill.play();
        }
        
        if ((pacman.getxPos() + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() == (544 + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() && 
           (pacman.getyPos() + 15)/mazeMap.getHeight()*mazeMap.getTileHeight() == (64 + 15)/mazeMap.getHeight()*mazeMap.getTileHeight()
           && superFlagUR == true) {
            
            pacman.setPower(true);
            time = System.currentTimeMillis();
            superFlagUR = false;
            setGhostsSpeed(speedHigh);
            eatSuperPill.play();
        }
        
        if ((pacman.getxPos() + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() == (32 + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() && 
           (pacman.getyPos() + 15)/mazeMap.getHeight()*mazeMap.getTileHeight() == (608 + 15)/mazeMap.getHeight()*mazeMap.getTileHeight()
           && superFlagDL == true) {
            
            pacman.setPower(true);
            time = System.currentTimeMillis();
            superFlagDL = false;
            setGhostsSpeed(speedHigh);
            eatSuperPill.play();
        }
        if ((pacman.getxPos() + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() == (544 + 15)/mazeMap.getWidth()*mazeMap.getTileWidth() && 
           (pacman.getyPos() + 15)/mazeMap.getHeight()*mazeMap.getTileHeight() == (608 + 15)/mazeMap.getHeight()*mazeMap.getTileHeight()
           && superFlagDR == true) {
            
            pacman.setPower(true);
            time = System.currentTimeMillis();
            superFlagDR = false;
            setGhostsSpeed(speedHigh);
            eatSuperPill.play();
        }
        
        checkTime(time);
    }

    /**
     * This method resets the positions of pacman and the ghosts
     */
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
            Logger.getLogger(Maze.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method starts the game
     */
    private void startMoment() {
        delay(delayFlag, 4000);
        delayFlag = false;
    }

    /**
     * This method ends the game
     */
    private void gameOver() {
        if(pacman.getLives() == 0){
            delay(true, 3000);
            Display.destroy();
        }
    }

     /**
     * This method is gestion of modify from controller 
     * @param input input from keyboard
     * @param mode chose of modality
     */
    
    private void checkModeGame(Input input, String mode) {
        if(mode.equals("single")) {
            clyde.AIMovement(clyde.chooseDirection());
            blinky.AIMovement(blinky.chooseDirection());
            inky.AIMovement(inky.chooseDirection());
            pinky.AIMovement(pinky.chooseDirection());
        }
        if(mode.equals("multi")){
            clyde.manualMovement(input);
            blinky.manualMovement(input);
            inky.manualMovement(input);
            pinky.manualMovement(input);
        }
    }

    private void checkTime(long time) {
        if (System.currentTimeMillis() >= (time + POWER_TIME) && pacman.isPower()) {
            pacman.setPower(false);
            setGhostsSpeed(speedHigh);
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
    /**
     * This method make Blinky returns to the starting coordinate
     */
    private void blinkyResetPosition() {
        blinky.setX(288); blinky.setY(320); blinky.setDeath(false);
    }
    
    /**
     * This method make Inky returns to the starting coordinate
     */
    private void inkyResetPosition() {
        inky.setX(288);   inky.setY(320); inky.setDeath(false);
    }
    
    /**
     * This method make Clyde returns to the starting coordinate
     */
    private void clydeResetPosition() {
        clyde.setX(288);  clyde.setY(320); clyde.setDeath(false);
    }

    /**
     * This method make Pinky returns to the starting coordinate
     */
    private void pinkyResetPosition() {
        pinky.setX(288); pinky.setY(320); pinky.setDeath(false);
    }

    /**
     * This method reset the position after player's death 
     */
    
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

    /**
     * This method set the speed of the ghost
     * @param speed identify the speed of the ghost
     */
    private void setGhostsSpeed(int speed) {
        clyde.setSpeed(speed);
        inky.setSpeed(speed);
        blinky.setSpeed(speed);
        pinky.setSpeed(speed);
    }
}