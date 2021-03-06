/**
 * Class for the modality multyplayer. Also ghosts are coltrolled by keybord.
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
            clyde.AIMovement(clyde.chooseDirection(clyde.getxPacMan(),clyde.getyPacMan()));
            blinky.AIMovement(blinky.chooseDirection(blinky.getxPacMan(),blinky.getyPacMan()));
            inky.AIMovement(inky.chooseDirection(inky.getxPacMan(),inky.getyPacMan()));
            pinky.AIMovement(pinky.chooseDirection(inky.getxPacMan(),inky.getyPacMan()));
    }
}
