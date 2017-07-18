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
public class MultiPlayer extends MazeModality{
    
    public MultiPlayer(TiledMap mazeMap, MazeView mazeView) throws SlickException {
        super(mazeMap, mazeView);
    }
    
    protected void checkModeGame(Input input) {
            clyde.manualMovement(input);
            blinky.manualMovement(input);
            inky.manualMovement(input);
            pinky.manualMovement(input);
    }
    
}
