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

public class VistaLabirinto extends BasicGame implements Observer {
    
    private int TILE_WIDTH, TILE_HEIGHT;
    int mem_button;
    
    boolean[][] blocked, tunnel, eat;
    
    public TiledMap mazeMap;
    
    private Animation pacman, up, down, left, right, pill, pill_debug;
    
    private int x = 288, y = 512;
    private int XpmanUPsx, XpmanUPdx, XpmanDOWNsx, XpmanDOWNdx;     
    private int YpmanUPsx, YpmanUPdx, YpmanDOWNsx, YpmanDOWNdx;
    
    
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
    
    
    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        int spostamento = 2;
        Input input = container.getInput();
        
        System.out.println("coordinata x:   " + x + "    coordinata y:   " + y);
        
        XpmanUPsx = x; YpmanUPsx = y;
        XpmanUPdx = x + TILE_WIDTH - 1; YpmanUPdx = y;
        XpmanDOWNsx = x; YpmanDOWNsx = y + TILE_HEIGHT - 1;
        XpmanDOWNdx = x + TILE_WIDTH - 1; YpmanDOWNdx = y + TILE_HEIGHT - 1;

        if ((input.isKeyDown(Input.KEY_UP) || mem_button == 1) && ((!hasProperty(XpmanUPsx, YpmanUPsx - spostamento, blocked)) &&
           (!hasProperty(XpmanUPdx, YpmanUPdx - spostamento, blocked)))){
                
                pacman = up;
                mem_button = 1;
                input = container.getInput();
                
                if (AltroTastoPremuto(input, Input.KEY_UP)){
                   mem_button = 0;
                }else{
                    pacman.update(spostamento * 10);
                    y -= spostamento;
                }
        }
        
        if ((input.isKeyDown(Input.KEY_DOWN) || mem_button == 2) && !hasProperty(XpmanDOWNsx, YpmanDOWNsx + spostamento, blocked) && 
           (!hasProperty(XpmanDOWNdx, YpmanDOWNdx + spostamento, blocked))){
                pacman = down;
                mem_button = 2;
                
                if (AltroTastoPremuto(input, Input.KEY_DOWN)){
                   mem_button = 0;
                }else{
                    pacman.update(spostamento * 10);
                    y += spostamento;
                }                               
        }
        
        if ((input.isKeyDown(Input.KEY_LEFT) || mem_button == 3) && !hasProperty(XpmanUPsx - spostamento, YpmanUPsx, blocked) && 
           (!hasProperty(XpmanDOWNsx - spostamento, YpmanDOWNsx , blocked))){
                pacman = left;
                mem_button = 3;
            
                if (AltroTastoPremuto(input, Input.KEY_LEFT)){
                   mem_button = 0;
                }else{
                    pacman.update(spostamento * 10);
                    x -= spostamento;
                }
                
                if (hasProperty(x - delta + (TILE_WIDTH - 1), y, tunnel) && (hasProperty(x - delta, y + (TILE_HEIGHT - 1), tunnel))) 
                    x = TILE_WIDTH * (mazeMap.getWidth() - 1);            
        }
        
        if ((input.isKeyDown(Input.KEY_RIGHT) || mem_button == 4) && !hasProperty(XpmanUPdx + spostamento, YpmanUPdx, blocked) &&
           (!hasProperty(XpmanDOWNdx + spostamento, YpmanDOWNdx, blocked))){
                pacman = right;
                mem_button = 4;
                
                if (AltroTastoPremuto(input, Input.KEY_RIGHT)){
                   mem_button = 0;
                }else{
                    pacman.update(spostamento * 10);
                    x += spostamento;
                }
                
                if (hasProperty(x + delta, y, tunnel) && (hasProperty(x + delta + (TILE_WIDTH - 1), y + (TILE_HEIGHT - 1), tunnel))) 
                    x = 0;
        }
    }
 
    public void render(GameContainer container, Graphics g) throws SlickException
    {
        mazeMap.render(0, 0);
        pacman.draw(x, y);
        
        g.drawLine(x, y, x + 31, y);
        g.drawLine(x + 31, y, x + 31, y + 31);
        g.drawLine(x, y + 31, x + 31, y + 31);
        g.drawLine(x, y, x, y + 31);
        
        for (int i = 0; i < mazeMap.getWidth(); i++) { 
            for (int j = 0; j < mazeMap.getHeight(); j++) {
                if (eat[i][j] == true)
                    pill.draw(i*32, j*32);
                if ((x  == i*32 && y == j*32) || (x +31 == i*32 +31 && y == j*32) || (x  == i*32 && y +31 == j*32+31) ||
                   (x + 31 == i*32+31 && y + 31 == j*32+31))
                        eat[i][j] = false;
            }
        }
    }

    
    private boolean hasProperty(int x, int y, boolean[][] b) {  //ritorna vero o falso, a seconda che la propriotà ci sia o meno
        int xP = x / TILE_WIDTH; //normalizzazione
        int yP = y / TILE_HEIGHT;
        return b[xP][yP];
    }
    
    public boolean AltroTastoPremuto(Input input, int n) {
        int[] UsedKeys = {Input.KEY_DOWN, Input.KEY_UP, Input.KEY_LEFT, Input.KEY_RIGHT}; 

        if ((input.isKeyDown(Input.KEY_DOWN)) && (!hasProperty(XpmanDOWNsx, YpmanDOWNsx + 1, blocked) &&
            (!hasProperty(XpmanDOWNdx, YpmanDOWNdx + 1, blocked))) && Input.KEY_DOWN != n)
                return true;
                
        else if ((input.isKeyDown(Input.KEY_UP)) && (!hasProperty(XpmanUPsx, YpmanUPsx - 1, blocked)) && 
            (!hasProperty(XpmanUPdx, YpmanUPdx - 1, blocked)) && Input.KEY_UP != n)
                return true;
        
        else if ((input.isKeyDown(Input.KEY_LEFT)) && !hasProperty(XpmanUPsx - 1, YpmanUPsx, blocked) &&
           (!hasProperty(XpmanDOWNsx - 1, YpmanDOWNsx, blocked)) && Input.KEY_LEFT != n)
                return true;
        
        else if((input.isKeyDown(Input.KEY_RIGHT)) && !hasProperty(XpmanUPdx + 1, YpmanUPdx, blocked) && 
          (!hasProperty(XpmanDOWNdx + 1, YpmanDOWNdx, blocked)) && Input.KEY_RIGHT != n)
                return true;
        else
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
        
    }
}
