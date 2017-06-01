/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Labirinto;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import view.VistaLabirinto;

/**
 *
 * @author cl427927
 */
public class Controller {
    
    private Labirinto labirinto;
    private Input input;

    public void setInput(Input input) {
        this.input = input;
        sendInput();
    }

    public void initLabirinto(TiledMap mazeMap, VistaLabirinto vistaLabirinto) throws SlickException{
        labirinto = new Labirinto(mazeMap,vistaLabirinto);
    }
    
    public void startGame(){
        try
        {
            VistaLabirinto vl = new VistaLabirinto(this);
            AppGameContainer app = new AppGameContainer(vl);
            app.setDisplayMode(608, 704, false);
            app.setTargetFrameRate(60);
            app.start();
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
    
    private void sendInput(){
        labirinto.movimentoGiocatori(input);
    }
}
