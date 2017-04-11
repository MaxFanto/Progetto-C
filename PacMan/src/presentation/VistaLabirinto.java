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
public class VistaLabirinto extends BasicGame{
    
    private boolean[][] blocked;
    
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
            app.setDisplayMode(500, 400, false);
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
        int layer = 1;
        
        grassMap = new TiledMap("data/map_1010.tmx");
        Image [] movementUp = {new Image("data/pacman0.png"), new Image("data/pacman0.png")};
        Image [] movementDown = {new Image("data/pacman0.png"), new Image("data/pacman0.png")};
        Image [] movementLeft = {new Image("data/pacman0.png"), new Image("data/pacman0.png")};
        Image [] movementRight = {new Image("data/pacman0.png"), new Image("data/pacman0.png")};
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
        
// Original orientation of the sprite. It will look right.
        pacman = right;
        
        blocked = new boolean[grassMap.getWidth()][grassMap.getHeight()];
        
        for (int i = 0; i < grassMap.getWidth(); i++) {
            for (int j = 0; j < grassMap.getHeight(); j++) {
                
                int tileID = grassMap.getTileId(i, j, grassMap.getLayerIndex("Livello tile 1"));
                
                String value = grassMap.getTileProperty(tileID, "blocked", "false");
                if(value.equals("true")) {

                    blocked[i][j] = true;
                    //commento di prova
                    
                }
            }
            
        }            

    }
    
    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        
        Input input = container.getInput();
        if (input.isKeyDown(Input.KEY_UP))
        {
            pacman = up;
            if ((!isBlocked(x, y - delta * 0.1f)) && (!isBlocked(x + 31, y - delta * 0.1f))){
                pacman.update(delta);
                y -= delta * 0.1f;
                }
            
        }
        else if (input.isKeyDown(Input.KEY_DOWN))
        {
            pacman = down;
            
            if (!isBlocked(x, y + 31 + delta * 0.1f) && (!isBlocked(x + 31, y + 31 + delta * 0.1f)))
            {
                pacman.update(delta);
                y += delta * 0.1f;
            }
        }
        else if (input.isKeyDown(Input.KEY_LEFT))
        {
            pacman = left;
            if (!isBlocked(x - delta * 0.1f, y) && (!isBlocked(x - delta * 0.1f, y + 31))) {
            pacman.update(delta);
            x -= delta * 0.1f;
            }
        }
        else if (input.isKeyDown(Input.KEY_RIGHT))
        {
            pacman = right;
            if (!isBlocked(x + 31 + delta * 0.1f, y) && (!isBlocked(x + 31 + delta * 0.1f, y + 31))) {
            pacman.update(delta);
            x += delta * 0.1f;
            }
        }
    }
 
    public void render(GameContainer container, Graphics g) throws SlickException
    {
        grassMap.render(0, 0);
        pacman.draw((int)x, (int)y);
    }

    private boolean isBlocked(float x, float y) {
        int xBlock = (int)x / 32; //normalizzazione
        int yBlock = (int)y / 32;
        return blocked[xBlock][yBlock];
    }
    
}
