package test;

import controller.Controller;
import model.Labirinto;
import model.Partita;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import view.VistaLabirinto;

public class TestLabirinto {
    public static void main(String[] args) throws SlickException, InterruptedException {
        
        Controller controller = new Controller();
        controller.startGame();
    }
}
