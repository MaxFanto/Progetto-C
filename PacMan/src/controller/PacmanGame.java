package controller;

import java.awt.FontFormatException;
import java.io.IOException;
import model.Extreme;
import model.MazeModality;
import model.MultiPlayer;
import model.SinglePlayer;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import view.MazeView;
import view.menu.Menu;

public class PacmanGame {
    
    private MazeModality maze;
    private Input input;
    private String mode;

    public void setInfo(Input input) {
        this.input = input;
        sendInput();
    }

    public void initMaze(TiledMap mazeMap, MazeView mazeView) throws SlickException{
        if(mode.equals("single"))
        maze = new SinglePlayer(mazeMap, mazeView);
        if(mode.equals("multi"))
        maze = new MultiPlayer(mazeMap, mazeView);
        if(mode.equals("extreme"))
        maze = new Extreme(mazeMap, mazeView);
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
            this.mode = mode;
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
    
    private void sendInput(){
        maze.notifyModify(input);
    }
}
