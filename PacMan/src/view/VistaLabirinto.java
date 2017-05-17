package view;

import controller.Controller;
import java.util.Observable;
import java.util.Observer;
import model.Labirinto;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.tiled.TiledMap;

public class VistaLabirinto extends BasicGame implements Observer{
    
    int mem_button;
    public int [] duration;
    
    boolean[][] blocked, tunnel, eat;
    
    private TiledMap mazeMap;
    
    private Input input;//prendiamo info da qui
    
    private AnimationsFactory factory;
    private PacManAnimation pacman;
    private GhostAnimation pinky;
    private PillAnimation pill;
    
//    private final int speedLow = 1, speedMedium = 2, speedHigh = 4;
    
    private int x = 288, y = 512;
    
    //    private Music music;
    private Sound begin, eat_pill;
    
    private Controller controller;

    
    public VistaLabirinto(Controller controller) throws SlickException
    {
        super("Pac-man game");
        factory = AnimationsFactory.getInstance();
        this.controller = controller;
    }

    public Input getInput() {return input;}
    public TiledMap getMazeMap() {return mazeMap;}
    public boolean[][] getBlocked() {return blocked;}
    public boolean[][] getTunnel() {return tunnel;}
    public boolean[][] getEat() {return eat;}
    
    @Override
    public void init(GameContainer container) throws SlickException
    {
        mazeMap = new TiledMap("data/Maze_nero.tmx");
        blocked = generaMappaProprietà("blocked");
        tunnel = generaMappaProprietà("tunnel");
        eat = generaMappaProprietà("eat");
        
        pacman = (PacManAnimation) factory.getPacmanAnimation();
        pacman = (PacManAnimation) pacman.rotate(0);
        
        pinky = (GhostAnimation) factory.getGhostAnimation("pinky");
        
        pill = (PillAnimation) factory.getPillAnimation();       
        
//        begin = new Sound("data/Pacman sound/pacman_begin.wav");
//        begin.play();
//        eat_pill = new Sound("data/Pacman sound/pacman_eat.wav");
        
        controller.initLabirinto(mazeMap,this);
    }
    
    int xp = x, yp = y;
    
    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        input = container.getInput();
        aggiornaOrientamento();
        controller.setInput(input);       
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
        
        pinky.draw(32, 32);
    }  
    
    
    private boolean[][] generaMappaProprietà(String s) {
        
        int altezza = mazeMap.getHeight();
        int larghezza = mazeMap.getWidth();
        
        boolean[][] b = new boolean[larghezza][altezza];
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    private void aggiornaOrientamento() {
        if(x < xp){
            pacman = (PacManAnimation) pacman.rotate(180);
            pacman.update(20);
        }
        if(x > xp){
            pacman = (PacManAnimation) pacman.rotate(0);
            pacman.update(20);
        }
        if(y > yp){
            pacman = (PacManAnimation) pacman.rotate(90);
            pacman.update(20);
        }
        if(y < yp){
            pacman = (PacManAnimation) pacman.rotate(270);
            pacman.update(20);
        }
        xp = x;
        yp = y;
    }

    @Override
    public void update(Observable o, Object o1) {
        x = ((Labirinto)o).getX();
        y = ((Labirinto)o).getY();
    }

}
