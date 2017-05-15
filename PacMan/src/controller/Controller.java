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

    public int[] setInput(Input input) {
        this.input = input;
        return sendInput();
    }

    public void initLabirinto(TiledMap mazeMap) throws SlickException{
        labirinto = new Labirinto(mazeMap);
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
    
    private int[] sendInput(){
        int[] a = new int[2];
        a = labirinto.movimento(input);
        return a;
    }
}
