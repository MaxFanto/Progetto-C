/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import java.util.Observer;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author lorenzo
 */
public class VistaLabirinto extends BasicGame implements Observer {
    
    private int TILE_WIDTH, TILE_HEIGHT;
    
    boolean[][] blocked, tunnel;
    
    public TiledMap grassMap;
    
    private Animation pacman, up, down, left, right;
    
    private int x = 32, y = 32;
    
    public VistaLabirinto() throws SlickException
    {
        super("Pac-man game");
    }
 
    public static void main(String[] arguments) throws SlickException
    {                                        
        try
        {
            AppGameContainer app = new AppGameContainer(new VistaLabirinto());
            app.setDisplayMode(640, 640, false);
            app.setTargetFrameRate(60);
            app.start();
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
 
    @Override
    public void init(GameContainer container) throws SlickException
    {
        grassMap = new TiledMap("data/map_2020.tmx");
        TILE_HEIGHT = grassMap.getTileHeight();
        TILE_WIDTH = grassMap.getTileWidth();
        System.out.println(TILE_HEIGHT + "" + TILE_WIDTH);
        Image [] movementUp = {new Image("data/pacman0.png"), new Image("data/pacman1.png")};
        Image [] movementDown = {new Image("data/pacman0.png"), new Image("data/pacman1.png")};
        Image [] movementLeft = {new Image("data/pacman0.png"), new Image("data/pacman1.png")};
        Image [] movementRight = {new Image("data/pacman0.png"), new Image("data/pacman1.png")};
        
        for (int i = 0; i < 2; i++) {
            movementUp[i].rotate(270);
            movementDown[i].rotate(90);
            movementLeft[i].rotate(180);
        }
        
        int [] duration = {200, 200};
        
        /*
        * false variable means do not auto update the animation.
        * By setting it to false animation will update only when
        * the user presses a key.
        */
        up = new Animation(movementUp, duration, false);
        down = new Animation(movementDown, duration, false);
        left = new Animation(movementLeft, duration, false);
        right = new Animation(movementRight, duration, false);
        
        pacman = right;
        
        blocked = generaMappaProprietà("blocked");
        tunnel = generaMappaProprietà("tunnel");
    }
    
    int memoria;
    
    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        delta = 2;
        
        Input input = container.getInput();
        
        if (input.isKeyDown(Input.KEY_UP) || memoria == 1)
        {
            pacman = up;
            memoria = 1;
            input = container.getInput();
            if (GestioneMappa.AltroTastoPremuto(input, Input.KEY_UP)) 
                memoria = 0;
            if ((!hasProperty(x, y - delta, blocked)) && (!hasProperty(x + (TILE_WIDTH - 1), y - delta, blocked)))
            {
                pacman.update(delta * 10);
                y -= delta;
            }
            if ((hasProperty(x, y - delta + (TILE_HEIGHT - 1), tunnel)) && (hasProperty(x + (TILE_WIDTH - 1), y - delta, tunnel))) {
                y = TILE_HEIGHT * (grassMap.getHeight() - 1);
            }
        }
        
        if (input.isKeyDown(Input.KEY_DOWN) || memoria == 2)
        {
            pacman = down;
            memoria = 2;
            if (GestioneMappa.AltroTastoPremuto(input, Input.KEY_DOWN)) 
                memoria = 0;
            if (!hasProperty(x, y + delta + (TILE_HEIGHT - 1), blocked) && (!hasProperty(x + (TILE_WIDTH - 1), y + delta + (TILE_HEIGHT - 1), blocked)))
            {
                pacman.update(delta * 10);
                y += delta;
            }
            if ((hasProperty(x, y + delta, tunnel)) && (hasProperty(x + (TILE_WIDTH - 1), y + delta + (TILE_HEIGHT - 1), tunnel))) {
                y = 0;
            }
        }
        
        if (input.isKeyDown(Input.KEY_LEFT) || memoria == 3)
        {
            pacman = left;
            memoria = 3;
            if (GestioneMappa.AltroTastoPremuto(input, Input.KEY_LEFT)) 
                memoria = 0;
            if (!hasProperty(x - delta, y, blocked) && (!hasProperty(x - delta, y + (TILE_HEIGHT - 1), blocked))) 
            {
                pacman.update(delta * 10);
                x -= delta;    
            }
            if (hasProperty(x - delta + (TILE_WIDTH - 1), y, tunnel) && (hasProperty(x - delta, y + (TILE_HEIGHT - 1), tunnel))) 
                x = TILE_WIDTH * (grassMap.getWidth() - 1);
                
        }
        if (input.isKeyDown(Input.KEY_RIGHT) || memoria == 4)
        {
            pacman = right;
            memoria = 4;
            if (GestioneMappa.AltroTastoPremuto(input, Input.KEY_RIGHT)) 
                memoria = 0;
            if (!hasProperty(x + delta + (TILE_WIDTH - 1), y, blocked) && (!hasProperty(x + delta + (TILE_WIDTH - 1), y + (TILE_HEIGHT - 1), blocked))) {
                pacman.update(delta * 10);
                x += delta;
            }
            if (hasProperty(x + delta, y, tunnel) && (hasProperty(x + delta + (TILE_WIDTH - 1), y + (TILE_HEIGHT - 1), tunnel))) 
                x = 0;
        }
    }
 
    public void render(GameContainer container, Graphics g) throws SlickException
    {
        grassMap.render(0, 0);
        pacman.draw(x, y);
    }

    private boolean hasProperty(int x, int y, boolean[][] b) {
        int xP = x / TILE_WIDTH; //normalizzazione
        int yP = y / TILE_HEIGHT;
        return b[xP][yP];
    }

    private boolean[][] generaMappaProprietà(String s) {
        boolean[][] b = new boolean[grassMap.getWidth()][grassMap.getHeight()];
        for (int i = 0; i < grassMap.getWidth(); i++) {
            for (int j = 0; j < grassMap.getHeight(); j++) {
                
                int tileID = grassMap.getTileId(i, j, grassMap.getLayerIndex("Livello tile 1"));
                
                String value = grassMap.getTileProperty(tileID, s , "false");
                if(value.equals("true")) {

                    b[i][j] = true;
                }
            }
        }
        return b;
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        //input = partita.getInput
    }
}