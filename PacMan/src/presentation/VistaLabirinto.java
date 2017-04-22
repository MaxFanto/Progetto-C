/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import java.util.Observer;
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
    
    boolean[][] blocked, tunnel, eat;
    
    public TiledMap mazeMap;
    
    private Animation pacman, up, down, left, right, pill;
    
//    private final int speedDown = 1, speedMedium = 2, speedHigh = 4;
    
    private int x = 288, y = 512;
    
    public VistaLabirinto() throws SlickException
    {
        super("Pac-man game");
    }
 
    public static void main(String[] arguments) throws SlickException
    {                                        
        try
        {
            AppGameContainer app = new AppGameContainer(new VistaLabirinto());
            app.setDisplayMode(608, 704, false);
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
        mazeMap = new TiledMap("data/Maze_nero.tmx");
        TILE_HEIGHT = mazeMap.getTileHeight();
        TILE_WIDTH = mazeMap.getTileWidth();
        
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
        eat = generaMappaProprietà("eat");
        
        Image[] p = {new Image("data/pill_nero.png"), new Image("data/pill_nero.png")};
        pill = new Animation(p, duration, false);
        
    }
    
    int memoria;
    
    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        delta = 2;
        
        Input input = container.getInput();
        
        if ((input.isKeyDown(Input.KEY_UP) || memoria == 1) && ((!hasProperty(x, y - delta, blocked)) && (!hasProperty(x + (TILE_WIDTH - 1), y - delta, blocked))))
        {
            pacman = up;
            memoria = 1;
            input = container.getInput();
            if (AltroTastoPremuto(input, Input.KEY_UP)) 
                memoria = 0;
//            if ((!hasProperty(x, y - delta, blocked)) && (!hasProperty(x + (TILE_WIDTH - 1), y - delta, blocked)))
//            {
                pacman.update(delta * 10);
                y -= delta;
//            }
            if ((hasProperty(x, y - delta + (TILE_HEIGHT - 1), tunnel)) && (hasProperty(x + (TILE_WIDTH - 1), y - delta, tunnel)))
                y = TILE_HEIGHT * (mazeMap.getHeight() - 1);
        }
        
        if ((input.isKeyDown(Input.KEY_DOWN) || memoria == 2) && !hasProperty(x, y + delta + (TILE_HEIGHT - 1), blocked) && (!hasProperty(x + (TILE_WIDTH - 1), y + delta + (TILE_HEIGHT - 1), blocked)))
        {
            pacman = down;
            memoria = 2;
            if (AltroTastoPremuto(input, Input.KEY_DOWN)) 
                memoria = 0;
//            if (!hasProperty(x, y + delta + (TILE_HEIGHT - 1), blocked) && (!hasProperty(x + (TILE_WIDTH - 1), y + delta + (TILE_HEIGHT - 1), blocked)))
//            {
                pacman.update(delta * 10);
                y += delta;
//            }
            if ((hasProperty(x, y + delta, tunnel)) && (hasProperty(x + (TILE_WIDTH - 1), y + delta + (TILE_HEIGHT - 1), tunnel)))
                y = 0;
        }
        
        if ((input.isKeyDown(Input.KEY_LEFT) || memoria == 3) && !hasProperty(x - delta, y, blocked) && (!hasProperty(x - delta, y + (TILE_HEIGHT - 1), blocked)))
        {
            pacman = left;
            memoria = 3;
            if (AltroTastoPremuto(input, Input.KEY_LEFT)) 
                memoria = 0;
//            if (!hasProperty(x - delta, y, blocked) && (!hasProperty(x - delta, y + (TILE_HEIGHT - 1), blocked))) 
//            {
                pacman.update(delta * 10);
                x -= delta;    
//            }
            if (hasProperty(x - delta + (TILE_WIDTH - 1), y, tunnel) && (hasProperty(x - delta, y + (TILE_HEIGHT - 1), tunnel))) 
                x = TILE_WIDTH * (mazeMap.getWidth() - 1);
        }
        if ((input.isKeyDown(Input.KEY_RIGHT) || memoria == 4) && !hasProperty(x + delta + (TILE_WIDTH - 1), y, blocked) && (!hasProperty(x + delta + (TILE_WIDTH - 1), y + (TILE_HEIGHT - 1), blocked)))
        {
            pacman = right;
            memoria = 4;
            if (AltroTastoPremuto(input, Input.KEY_RIGHT)) 
                memoria = 0;
//            if (!hasProperty(x + delta + (TILE_WIDTH - 1), y, blocked) && (!hasProperty(x + delta + (TILE_WIDTH - 1), y + (TILE_HEIGHT - 1), blocked)))
//            {
                pacman.update(delta * 10);
                x += delta;
//            }
            if (hasProperty(x + delta, y, tunnel) && (hasProperty(x + delta + (TILE_WIDTH - 1), y + (TILE_HEIGHT - 1), tunnel))) 
                x = 0;
        }
    }
 
    public void render(GameContainer container, Graphics g) throws SlickException
    {
        mazeMap.render(0, 0);
        pacman.draw(x, y);
        for (int i = 0; i < mazeMap.getWidth(); i++) {
            for (int j = 0; j < mazeMap.getHeight(); j++) {
                if (eat[i][j] == true)
                    pill.draw(i*32, j*32);
                if (x == i*32 && y == j*32)
                    eat[i][j] = false;
            }
        }
    }

    private boolean hasProperty(int x, int y, boolean[][] b) {
        int xP = x / TILE_WIDTH; //normalizzazione
        int yP = y / TILE_HEIGHT;
        return b[xP][yP];
    }
    
    public boolean AltroTastoPremuto(Input input, int n) {
        int[] UsedKeys = {Input.KEY_DOWN, Input.KEY_UP, Input.KEY_LEFT, Input.KEY_RIGHT}; 

//        for (int i = 0; i < UsedKeys.length; i++) {
//            if(input.isKeyDown(UsedKeys[i]) && UsedKeys[i] != n && !hasProperty(x, y, blocked) && !hasProperty(x, y, blocked))
//                return true;
//        }

        if ((input.isKeyDown(Input.KEY_DOWN)) && (!hasProperty(x, y + 2 + (TILE_HEIGHT - 1), blocked) && (!hasProperty(x + (TILE_WIDTH - 1), y + 2 + (TILE_HEIGHT - 1), blocked))) && Input.KEY_DOWN != n)
            return true;
        if ((input.isKeyDown(Input.KEY_UP)) && (!hasProperty(x, y - 2, blocked)) && (!hasProperty(x + (TILE_WIDTH - 1), y - 2, blocked)) && Input.KEY_UP != n)
            return true;
        if ((input.isKeyDown(Input.KEY_LEFT)) && !hasProperty(x - 2, y, blocked) && (!hasProperty(x - 2, y + (TILE_HEIGHT - 1), blocked)) && Input.KEY_LEFT != n)
            return true;
        if((input.isKeyDown(Input.KEY_RIGHT)) && !hasProperty(x + 2 + (TILE_WIDTH - 1), y, blocked) && (!hasProperty(x + 2 + (TILE_WIDTH - 1), y + (TILE_HEIGHT - 1), blocked)) && Input.KEY_RIGHT != n)
            return true;
        return false;
    }

    private boolean[][] generaMappaProprietà(String s) {
        boolean[][] b = new boolean[mazeMap.getWidth()][mazeMap.getHeight()];
        for (int i = 0; i < mazeMap.getWidth(); i++) {
            for (int j = 0; j < mazeMap.getHeight(); j++) {
                
                int tileID = mazeMap.getTileId(i, j, mazeMap.getLayerIndex("Livello tile 1"));
                
                String value = mazeMap.getTileProperty(tileID, s , "false");
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