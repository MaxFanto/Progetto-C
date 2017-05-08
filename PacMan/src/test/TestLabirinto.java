package test;

import logicModel.Labirinto;
import logicModel.Partita;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.SlickException;
import presentation.VistaLabirinto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cl427927
 */
public class TestLabirinto {
    public static void main(String[] args) throws SlickException {
        Labirinto labirinto;

        try
        {
            VistaLabirinto vl = new VistaLabirinto();
            AppGameContainer app = new AppGameContainer(vl);
            app.setDisplayMode(608, 704, false);
            app.setTargetFrameRate(60);
            app.start();
            //VistaLabirinto vistalabirinto = new VistaLabirinto();
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
}
