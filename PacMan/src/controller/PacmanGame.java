package controller;

import java.awt.FontFormatException;
import java.io.IOException;
import model.Maze;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import view.MazeView;
import view.menu.Menu;

public class PacmanGame {
    
    private Maze maze;
    private Input input;

    public void setInfo(Input input, String mode) {
        this.input = input;
        sendInput(mode);
    }

    public void initMaze(TiledMap mazeMap, MazeView mazeView) throws SlickException{
        maze = new Maze(mazeMap, mazeView);
    }
    
    public void startMenu() throws FontFormatException, IOException{
        new Menu(this).setVisible(true);
    }
    
    /**
     * This method starts the game 
     * @param mode identify the modality of the game 
     */
    public void startGame(String mode){
        try
        {
            MazeView vl = new MazeView(this, mode);
            AppGameContainer app = new AppGameContainer(vl);
            app.setDisplayMode(608, 704, false);
           
            if(mode.equals("extreme"))
                app.setTargetFrameRate(260);
            else 
                app.setTargetFrameRate(60);

            app.setShowFPS(false);
            app.start();
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
    
    private void sendInput(String mode){
        maze.notifyModify(input, mode);
    }
}
