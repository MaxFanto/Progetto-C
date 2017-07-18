package model;

import other.Tile;

public class PacMan extends Player {
    private int lives;
    private boolean power;
    private int score;
    private final int X_MAIN_POS = 288;
    private final int Y_MAIN_POS = 512;

    public PacMan(int tile_width, int tile_heigth, int mapWidth, Tile[][] tiles) {
        super(tile_width, tile_heigth, mapWidth, tiles);
        x = X_MAIN_POS;
        y = Y_MAIN_POS;
        lives = 3;
        score = 0;
    }

    /**
     * 
     * @return the number of lives
     */
    public int getLives() {return lives;}
    /**
     * 
     * @return return if the pill is a power pil. 
     */
    public boolean isPower() {return power;}

    /**
     * method that sets the number of lives
     */
    public void setVite() {lives--;}
    /**
     * 
     * @param power set the pill as power 
     */
    public void setPower(boolean power) {this.power = power;}

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score += score;
    }
    
    
}
