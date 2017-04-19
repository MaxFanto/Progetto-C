/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

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
public class VistaLabirinto extends BasicGame {
    
    private int TILE_WIDTH, TILE_HEIGHT;
    
    boolean[][] blocked, tunnel;
    
    public TiledMap grassMap;
    
    private Animation pacman, up, down, left, right;
    
    private float x = 32f, y = 32f;
    
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
        
        int [] duration = {300, 300};
        
        
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
        delta = 20;
        Input input = container.getInput();
              
        
        if (input.isKeyDown(Input.KEY_UP) || memoria == 1)
        {
            pacman = up;
            memoria = 1;
            input = container.getInput();
            if(AltroTastoPremuto(input, Input.KEY_UP)) 
                memoria = 0;
            if ((!hasProperty(x, y - delta * 0.1f, blocked)) && (!hasProperty(x + (TILE_WIDTH - 1), y - delta * 0.1f, blocked))){
                pacman.update(delta);
                y -= delta * 0.1f;
                }
            if((hasProperty(x, y - (delta * 0.1f) + (TILE_HEIGHT - 1), tunnel)) && (hasProperty(x + (TILE_WIDTH - 1), y - delta * 0.1f, tunnel))) {
                y = TILE_HEIGHT * (grassMap.getHeight() - 1);
            }
        }
        
        if (input.isKeyDown(Input.KEY_DOWN) || memoria == 2)
        {
            pacman = down;
            memoria = 2;
            if(AltroTastoPremuto(input, Input.KEY_DOWN)) 
                memoria = 0;
            if (!hasProperty(x, y + (TILE_HEIGHT - 1) + delta * 0.1f, blocked) && (!hasProperty(x + (TILE_WIDTH - 1), y + (TILE_HEIGHT - 1) + delta * 0.1f, blocked)))
            {
                pacman.update(delta);
                y += delta * 0.1f;
            }
            if((hasProperty(x, y + delta * 0.1f, tunnel)) && (hasProperty(x + (TILE_WIDTH - 1), y + (TILE_HEIGHT - 1) + delta * 0.1f, tunnel))) {
                y = 0;
            }
        }
        
        if (input.isKeyDown(Input.KEY_LEFT) || memoria == 3)
        {
            pacman = left;
            memoria = 3;
            if(AltroTastoPremuto(input, Input.KEY_LEFT)) 
                memoria = 0;
            if (!hasProperty(x - delta * 0.1f, y, blocked) && (!hasProperty(x - delta * 0.1f, y + (TILE_HEIGHT - 1), blocked))) {
                pacman.update(delta);
                x -= delta * 0.1f;
            }

        }
        if (input.isKeyDown(Input.KEY_RIGHT) || memoria == 4)
        {
            pacman = right;
            memoria = 4;
            if(AltroTastoPremuto(input, Input.KEY_RIGHT)) 
                memoria = 0;
            if (!hasProperty(x + (TILE_WIDTH - 1) + delta * 0.1f, y, blocked) && (!hasProperty(x + (TILE_WIDTH - 1) + delta * 0.1f, y + (TILE_HEIGHT - 1), blocked))) {
            pacman.update(10);
            x += delta * 0.1f;
            }
        }
    }
 
    public void render(GameContainer container, Graphics g) throws SlickException
    {
        grassMap.render(0, 0);
        pacman.draw((int)x, (int)y);
    }

    private boolean hasProperty(float x, float y, boolean[][] b) {
        int xP = (int)x / TILE_WIDTH; //normalizzazione
        int yP = (int)y / TILE_HEIGHT;
        return b[xP][yP];
    }

    private boolean AltroTastoPremuto(Input input, int n) {
        int[] UsedKeys = {Input.KEY_DOWN, Input.KEY_UP, Input.KEY_LEFT, Input.KEY_RIGHT}; 
        
        for (int i = 0; i < UsedKeys.length; i++) {
            if(input.isKeyDown(UsedKeys[i]) && UsedKeys[i] != n)
                return true;
        }
       
        return false;
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
}