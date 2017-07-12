package controller;

import java.awt.FontFormatException;
import java.io.IOException;
import model.Labirinto;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import view.VistaLabirinto;
import view.menu.Menu;

public class Controller {
    
    private Labirinto labirinto;
    private Input input;

    public void setInfo(Input input, String mode) {
        this.input = input;
        sendInput(mode);
    }

    public void initLabirinto(TiledMap mazeMap, VistaLabirinto vistaLabirinto) throws SlickException{
        labirinto = new Labirinto(mazeMap,vistaLabirinto);
    }
    
    public void startMenu() throws FontFormatException, IOException{
        new Menu(this).setVisible(true);
    }
    
    public void startGame(String mode){
        try
        {
            VistaLabirinto vl = new VistaLabirinto(this,mode);
            AppGameContainer app = new AppGameContainer(vl);
            app.setDisplayMode(608, 704, false);
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
        labirinto.movimentoGiocatori(input, mode);
    }
}
