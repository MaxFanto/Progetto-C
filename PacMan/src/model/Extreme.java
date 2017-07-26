/**
* This class is the extension of the modality single player
*/
package model;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import view.MazeView;


public class Extreme extends SinglePlayer{
    
    public Extreme(TiledMap mazeMap, MazeView mazeView) throws SlickException {
        super(mazeMap, mazeView);
    }
    
    protected void checkPowerTime() {
            POWER_TIME = 2000;
    }
    
}
