/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.awt.Rectangle;
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
 * @author panos
 */
public class WizardGame extends BasicGame
{
    
    private boolean[][] blocked;
    
    public TiledMap grassMap;
    
    private Animation sprite, up, down, left, right;
    
    private float x = 52f, y = 32f;
    
    public WizardGame() throws SlickException
    {
        super("Wizard game");
    }
 
    public static void main(String[] arguments) throws SlickException
    {
                                                
        try
        {
            AppGameContainer app = new AppGameContainer(new WizardGame());
            app.setDisplayMode(500, 400, false);
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
        
        grassMap = new TiledMap("data/mappazzone1.tmx");
        Image [] movementUp = {new Image("data/rocketU.png"), new Image("data/rocketU.png")};
        Image [] movementDown = {new Image("data/rocketB.png"), new Image("data/rocketB.png")};
        Image [] movementLeft = {new Image("data/rocketLX.png"), new Image("data/rocketLX.png")};
        Image [] movementRight = {new Image("data/rocketRX.png"), new Image("data/rocketRX.png")};
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
        sprite = right;
        
        blocked = new boolean[grassMap.getWidth()][grassMap.getHeight()];
        
//        for (int i = 0; i < grassMap.getWidth(); i++) {
//            for (int j = 0; j < grassMap.getHeight(); j++) {
//                
//                int tileID = grassMap.getTileId(i, j, layer);
//                
//                String value = grassMap.getTileProperty(tileID, "blocked", "false");
//                
//                if(value.equals("true")) {
//
//                    // We set that index of the TileMap as blocked
//                    blocked[i][j] = true;
//
//                    
//                }
//            }
//            
//            
//        }
    }
 
    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        Input input = container.getInput();
        if (input.isKeyDown(Input.KEY_UP))
        {
            sprite = up;
            
            if (!isBlocked(x, y - delta * 0.1f))
                {
                sprite.update(delta);
                // The lower the delta the slowest the sprite will animate.
                y -= delta * 0.1f;
                }
        }
        else if (input.isKeyDown(Input.KEY_DOWN))
        {
            sprite = down;
            sprite.update(delta);
            y += delta * 0.1f;
        }
        else if (input.isKeyDown(Input.KEY_LEFT))
        {
            sprite = left;
            sprite.update(delta);
            x -= delta * 0.1f;
        }
        else if (input.isKeyDown(Input.KEY_RIGHT))
        {
            sprite = right;
            sprite.update(delta);
            x += delta * 0.1f;
        }
    }
 
    public void render(GameContainer container, Graphics g) throws SlickException
    {
        grassMap.render(0, 0);
        sprite.draw((int)x, (int)y);
    }

    private boolean isBlocked(float x, float y) {
        int xBlock = (int)x / 52;
        int yBlock = (int)y / 32;
        return blocked[xBlock][yBlock];
    }
    
    
    
    
    
    
}