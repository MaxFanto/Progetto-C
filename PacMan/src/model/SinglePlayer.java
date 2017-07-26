/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import view.MazeView;

/**
 *
 * @author Lollodan
 */
public class SinglePlayer extends MazeModality{
    
    public SinglePlayer(TiledMap mazeMap, MazeView mazeView) throws SlickException {
        super(mazeMap, mazeView);
    }
    
    protected void checkModeGame(Input input) {
            clyde.AIMovement(clyde.chooseDirection(pacman.getxPos(),pacman.getyPos()));
            blinky.AIMovement(blinky.chooseDirection(pacman.getxPos(),pacman.getyPos()));
            inky.AIMovement(inky.chooseDirection(pacman.getxPos(),pacman.getyPos()));
            pinky.AIMovement(pinky.chooseDirection(pacman.getxPos(),pacman.getyPos()));
    }
}
