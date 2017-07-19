package model;

import model.Fantasmi.Clyde;
import other.Tile;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Fantasmi.Blinky;
import model.Fantasmi.Ghost;
import model.Fantasmi.Inky;
import model.Fantasmi.Pinky;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.tiled.TiledMap;
import view.MazeView;

public abstract class MazeModality extends Observable {
    
    private Tile[][] tiles;
    private int mazeWidth,mazeHeight;
    private int tileWidth, tileHeight;
    
    private long time = 0;
    
    protected int POWER_TIME;
    
    private int speedLow = 1;
    private int speedHigh = 2;
   
    protected PacMan pacman;
    protected Clyde clyde;
    protected Blinky blinky;
    protected Inky inky;
    protected Pinky pinky;
    
    private Sound eatGhost, death, eatSuperPill;
    
    private boolean delayFlag = true;
    private int countWin;

    /**
     * @param mazeMap
     * @param mazeView
     * @throws SlickException 
     */
    public MazeModality(TiledMap mazeMap,MazeView mazeView) throws SlickException {

        this.addObserver(mazeView);
        tileWidth = mazeMap.getTileWidth();
        tileHeight = mazeMap.getTileHeight();
        mazeWidth = mazeMap.getWidth();
        mazeHeight = mazeMap.getHeight();   
        initializationTiles(mazeView);
        

        
        pacman = new PacMan(tileWidth, tileHeight, mazeWidth, tiles);
        
        clyde = new Clyde(tileWidth, tileHeight, mazeWidth, tiles);
        blinky = new Blinky(tileWidth, tileHeight, mazeWidth, tiles);
        inky = new Inky(tileWidth, tileHeight, mazeWidth, tiles);
        pinky = new Pinky(tileWidth, tileHeight, mazeWidth, tiles);
        
        eatGhost = new Sound("data/pacmanSound/eatGhost.wav");
        death = new Sound("data/pacmanSound/death.wav");
        eatSuperPill = new Sound("data/pacmanSound/eatSuperPill.wav");
    }
  
    /**
     * This method is for the inizialization of tile's static property
     * @param mazeView the view of the game
     */
    private void initializationTiles(MazeView mazeView) {
        boolean[][] blocked = mazeView.getBlocked();
        boolean[][] tunnel = mazeView.getTunnel();
        boolean[][] eat = mazeView.getEat();
        boolean[][] superP = mazeView.getSuperP();
        boolean[][] fruit = mazeView.getFruit();
        
        tiles = new Tile[mazeWidth][mazeHeight];
        
        for (int i = 0; i < mazeWidth; i++) {
            for (int j = 0; j < mazeHeight; j++) {
                tiles[i][j] = new Tile(tileWidth, tileHeight, blocked[i][j], tunnel[i][j], eat[i][j],superP[i][j],fruit[i][j]);
            }
        }
  
    }
    
    /**
     * This method notifies the changes
     * @param input from keybord by player
     */   
    public void notifyModify(Input input) {
        startMoment();
        
        gameOver();
        YouWin();
        
        checkDeath();
        
        pacman.manualMovement(input);
        checkPowerTime();
        checkModeGame(input);
        checkModeCollision();
        eatCollision();
        
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

    public Tile[][] getTiles() {
        return tiles;
    }
    

    /**
     * This method checks if there is collision between one of the ghosts and pacman.
     * If it happens pacman will kill.
     */
    private void collision() {
        if(checkGhostCollision(clyde) || checkGhostCollision(blinky) 
           || checkGhostCollision(pinky) || checkGhostCollision(inky)) {
            death.play();
            if(pacman.isDeath() == false){
                pacman.setVite();
            }
            
            pacman.setDeath(true);
        }
    }

    /**
     * This method manages the collision with pill and superPill
     */
    private void eatCollision(){
        int x = pacman.getxPos();
        int y = pacman.getyPos();
        int i = x/32;
        int j = y/32;
        if((x == i*32 && y == j*32) || (x + 31 == i*32 + 31 && y == j*32) || (x == i*32 && y + 31 == j*32 + 31) ||(x + 31 == i*32 + 31 && y + 31 == j*32 + 31)){
            if(tiles[i][j].isEat()==true){
                tiles[i][j].setEat(false);
                pacman.setScore(10);
                countWin++;
            }
            if(tiles[i][j].isSuperP() == true){
                tiles[i][j].setSuperP(false);
                pacman.setScore(100);
                pacman.setPower(true);
                time = System.currentTimeMillis();
                setGhostsSpeed(speedHigh);
                eatSuperPill.play();
                countWin++;
            }
            if(tiles[i][j].isFruit() == true){
                tiles[i][j].setFruit(false);
                pacman.setScore(500);
            }
        } 
        checkTime(time);
    }
    

    /**
     * This method checks if the is a collision between pacman and selected ghost
     * @param ghost selected ghost
     * @return true if there is collision
     */
    private boolean checkGhostCollision(Ghost ghost) {
        return (pacman.getxPos() + 15)/mazeWidth*tileWidth == (ghost.getxPos() + 15)/mazeWidth*tileWidth && 
               (pacman.getyPos() + 15)/mazeHeight*tileHeight == (ghost.getyPos() + 15)/mazeHeight*tileHeight;
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
            Logger.getLogger(MazeModality.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private void YouWin() {
        if(countWin == 156){
            delay(true, 3000);
            Display.destroy();
        }
    }

    protected abstract void checkModeGame(Input input);

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
        if (checkGhostCollision(clyde)) {
            pacman.setScore(200);
            clyde.setDeath(true);
            eatGhost.play();
        }
        if (checkGhostCollision(blinky)) {
            pacman.setScore(200);
            blinky.setDeath(true);
            eatGhost.play();
        }
        if (checkGhostCollision(pinky)) {
            pacman.setScore(200);
            pinky.setDeath(true);
            eatGhost.play();
        }
        if (checkGhostCollision(inky)) {
            pacman.setScore(200);
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
     * This method reset the position after player's (or ghost's) death 
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

    protected void checkPowerTime() {
            POWER_TIME = 10000;
    }
}
