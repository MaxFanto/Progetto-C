package test;

import controller.Controller;
import model.Labirinto;
import model.Partita;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import view.VistaLabirinto;

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
    public static void main(String[] args) throws SlickException, InterruptedException {
        
        Controller controller = new Controller();
        controller.startGame();
    }
}
