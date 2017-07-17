package test;

import controller.PacmanGame;
import java.awt.FontFormatException;
import java.io.IOException;
import org.newdawn.slick.SlickException;

public class TestMaze {
    public static void main(String[] args) throws SlickException, InterruptedException, FontFormatException, IOException {
        
        PacmanGame pacmanGame = new PacmanGame();
        pacmanGame.startMenu();
    }
}
