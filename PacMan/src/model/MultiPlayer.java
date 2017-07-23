/**
 * This class is form multiplayer modality
 */
package model;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import view.MazeView;


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
