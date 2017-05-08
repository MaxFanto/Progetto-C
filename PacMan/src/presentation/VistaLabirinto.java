package presentation;

import java.util.Observable;
import java.util.Observer;
import logicModel.LabObserver;
import logicModel.Labirinto;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.tiled.TiledMap;

public class VistaLabirinto extends BasicGame{
    
    private int TILE_WIDTH, TILE_HEIGHT;
    int mem_button;
    public int [] duration;
    
    boolean[][] blocked, tunnel, eat;
    
    private static TiledMap mazeMap;
    
    
    private Input input;//prendiamo info da qui
    
    
    private AnimationsFactory factory;
    private PacManAnimation pacman;
    private PillAnimation pill;
    
//    private final int speedLow = 1, speedMedium = 2, speedHigh = 4;
    
    private int x = 288, y = 512;
    private int XpmanUPsx, XpmanUPdx, XpmanDOWNsx, XpmanDOWNdx;     
    private int YpmanUPsx, YpmanUPdx, YpmanDOWNsx, YpmanDOWNdx;
    
    //    private Music music;
    private Sound begin, eat_pill;
    
    

    
    public VistaLabirinto() throws SlickException
    {
        super("Pac-man game");
        factory = AnimationsFactory.getInstance();
        
    }

    public Input getInput() {return input;}
    public TiledMap getMazeMap() {
        return mazeMap;
    }
    
    public static void main(String[] arguments) throws SlickException
    {                                        
//        try
//        {
//            AppGameContainer app = new AppGameContainer(new VistaLabirinto(mazeMap));
//            app.setDisplayMode(608, 704, false);
//            app.setTargetFrameRate(60);
//            app.start();
//        }
//        catch (SlickException e)
//        {
//            e.printStackTrace();
//        }
    }
 
    @Override
    public void init(GameContainer container) throws SlickException
    {
        mazeMap = new TiledMap("data/Maze_nero.tmx");
        pacman = (PacManAnimation) factory.getPacmanAnimation();
        pacman = (PacManAnimation) pacman.rotate(0);
        
        pill = (PillAnimation) factory.getPillAnimation();       
        
        begin = new Sound("data/Pacman sound/pacman_begin.wav");
        begin.play();
        eat_pill = new Sound("data/Pacman sound/pacman_eat.wav");
    }
    
    
    
    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        input = container.getInput();
    }
 
    @Override
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


}
