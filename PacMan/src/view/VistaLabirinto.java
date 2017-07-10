package view;

import view.Animations.PacManAnimation;
import view.Animations.GhostAnimation;
import view.Animations.PillAnimation;
import controller.Controller;
import java.util.Observable;
import java.util.Observer;
import model.Player;
import model.Labirinto;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.tiled.TiledMap;
import view.Animations.AnimationsAdapter;
import view.Animations.FruitAnimation;

public class VistaLabirinto extends BasicGame implements Observer{
    
    boolean[][] blocked, tunnel, eat, superP, fruit;
    
    private TiledMap mazeMap;
    
    private Input input;
    
    private PacManAnimation pacman;
    private GhostAnimation pinky, clyde, blinky, inky;
    private PillAnimation pill, superPill;
    private FruitAnimation fruits;
    
    private Image ready;
    private Image gameOver;
    
    private int lives = 3;
    private int score = 0;
    
    private boolean pacmanDeath = false;
    private boolean pacmanPower = false;
    
//    private Music ;
    private Sound begin, eatPill, eatFruit, death, background;
    
    private Controller controller;
    
    private boolean readyFlag = true;
    private boolean fruitFlag = true;
    
    
    public VistaLabirinto(Controller controller) throws SlickException
    {
        super("Pac-man game");
        this.controller = controller;
    }

    public Input getInput() {return input;}
    public TiledMap getMazeMap() {return mazeMap;}
    public boolean[][] getBlocked() {return blocked;}
    public boolean[][] getTunnel() {return tunnel;}
    public boolean[][] getEat() {return eat;}
    public boolean[][] getPills() {return superP;}
    
    @Override
    public void init(GameContainer container) throws SlickException
    {
        ready = new Image("data/ready.jpg");
        gameOver = new Image("data/gameover.png");
        
        mazeMap = new TiledMap("data/maze/Maze.tmx");
        initMapProperty();
        initAnimations();
        
        begin = new Sound("data/pacmanSound/begin.wav");
        begin.play();
        eatPill = new Sound("data/pacmanSound/eatPill.wav");
//        eatFruit = new Sound("data/pacmanSound/eatFruit.wav");
        death = new Sound("data/pacmanSound/death.wav");
        background = new Sound("data/pacmanSound/background.wav");
        
        controller.initLabirinto(mazeMap,this);
    }
    
    @Override
    public void render(GameContainer container, Graphics g) throws SlickException
    {
        int x = pacman.getxPos();
        int y = pacman.getyPos();
        
        mazeMap.render(0, 0);
        renderFood(x, y);
        
        startReady();
        
        renderPacman();
        
        if (fruit[9][12] == false && fruitFlag == true) {
//            eatFruit.play();
            fruitFlag = false;
        }
            
        
//        for (int i = 0; i < 720; i+=32) {
//
//            g.drawLine(0, i, 672, i);
//            g.drawLine(i, 0, i, 672);
//        }

        clyde.draw(clyde.getxPos(),clyde.getyPos());
        pinky.draw(pinky.getxPos(), pinky.getyPos());
        inky.draw(inky.getxPos(), inky.getyPos());
        blinky.draw(blinky.getxPos(), blinky.getyPos());
        
        score = countScore();
        renderScore(g, score);
        renderLives(g, lives);
        
        if(lives == 0)
            gameOver.draw(192, 384);
    }
    
    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        input = container.getInput();
        controller.setInfo(input, "multi");
    }
    
    @Override
    public void update(Observable o, Object o1) {
        try {
            aggiornaOrientamento(((Labirinto)o).getPacman(), pacman);
            aggiornaOrientamento(((Labirinto)o).getClyde(), clyde);
            aggiornaOrientamento(((Labirinto)o).getClyde(), blinky);
            aggiornaOrientamento(((Labirinto)o).getClyde(), inky);
            aggiornaOrientamento(((Labirinto)o).getClyde(), pinky);
        } catch (SlickException ex) {
            ex.printStackTrace();
        }
        if(!pacmanDeath) {
            pacman.setxPos(((Labirinto)o).getPacman().getxPos());
            pacman.setyPos(((Labirinto)o).getPacman().getyPos());
        }
        
        clyde.setxPos(((Labirinto)o).getClyde().getxPos());
        clyde.setyPos(((Labirinto)o).getClyde().getyPos());
        
        blinky.setxPos(((Labirinto)o).getBlinky().getxPos());
        blinky.setyPos(((Labirinto)o).getBlinky().getyPos());
        
        inky.setxPos(((Labirinto)o).getInky().getxPos());
        inky.setyPos(((Labirinto)o).getInky().getyPos());
        
        pinky.setxPos(((Labirinto)o).getPinky().getxPos());
        pinky.setyPos(((Labirinto)o).getPinky().getyPos());
        
        pacmanDeath = ((Labirinto)o).getPacman().isDeath();
        
        lives = ((Labirinto)o).getPacman().getVite();
    }
    
    /**
     * inizializzazione delle animazioni
     * @throws SlickException 
     */
    private void initMapProperty() {
        blocked = generaMappaProprietà("blocked");
        tunnel = generaMappaProprietà("tunnel");
        eat = generaMappaProprietà("eat");
        superP = generaMappaProprietà("superP");
        fruit = generaMappaProprietà("fruit");
    }
    
    private void initAnimations() throws SlickException{
        pacman = new PacManAnimation();
        pacman.rotate(0);
        
        clyde = new GhostAnimation("clyde");
        inky = new GhostAnimation("inky");       
        pinky = new GhostAnimation("pinky"); 
        blinky = new GhostAnimation("blinky");
        pill = new PillAnimation("pill.png");
        superPill = new PillAnimation();
        fruits = new FruitAnimation();
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

    /**
     * modifica l'orientamento dell'animazione in base alla sua direzione nel movimento
     * @param player giocatore di riferimento dell'animazione, da cui prende la posizione
     * @param animation animazione da ruotare
     * @throws SlickException 
     */
    private void aggiornaOrientamento(Player player, AnimationsAdapter animation) throws SlickException {
        
        switch(player.getDirection()){
            case UP:
                animation.rotate(270);
                break;
            case DOWN:
                animation.rotate(90);
                break;
            case LEFT:
                animation.rotate(180);
                break;
            case RIGHT:
                animation.rotate(0);
                break;
            default:
                break;
        }
    }

    private void renderScore(Graphics g, int score) {
        g.setColor(Color.yellow);
        g.drawString("SCORE: " + score, 24, 1);
    }
    
    private void renderLives(Graphics g, int lives) throws SlickException {
        g.setColor(Color.yellow);
        Image pacmanLives = new Image("data/pacman/pacman0.png");
        
        if (lives != 0) 
            pacmanLives.draw(510, 1, 18, 18);
        
        if (lives == 2 || lives == 3)
            pacmanLives.draw(533, 1, 18, 18);
        
        if (lives == 3)
            pacmanLives.draw(556, 1, 18, 18);
        
        g.drawString("LIVES: ", 450, 1);
    }

    private void renderFood(int x, int y) {
        for (int i = 0; i < mazeMap.getWidth(); i++) { 
            for (int j = 0; j < mazeMap.getHeight(); j++) {
                if (eat[i][j] == true)
                    pill.draw(i*32, j*32);
                if (superP[i][j] == true)
                    superPill.draw(i*32, j*32);
                if (fruit[i][j] == true && score > 500)
                    fruits.draw(i*32, j*32);
                
                if ((x == i*32 && y == j*32) || (x + 31 == i*32 +31 && y == j*32) || (x == i*32 && y +31 == j*32+31) ||
                   (x + 31 == i*32+31 && y + 31 == j*32+31)) {
                        eat[i][j] = false;
                        superP[i][j] = false;
                        fruit[i][j] = false;
                }        
            }
        }
    }

    private int countScore() {
        int score = -252560;
        for (int i = 0; i < mazeMap.getWidth(); i++) { 
            for (int j = 0; j < mazeMap.getHeight(); j++) {
                if (eat[i][j] == false)
                    score += 10;
                if (superP[i][j] == false)
                    score += 100;
                if (fruit[i][j] == false)
                    score += 500;
            }
        }
        return score;
    }

    private void renderPacman() throws SlickException {
        if(!pacmanDeath)
            pacman.draw(pacman.getxPos(), pacman.getyPos());
        else {
            death.play();
            ready.draw(226, 384);
        }
    }
    
    private void startReady() {
        if(readyFlag){
            ready.draw(226, 384);
            readyFlag = false;
        }
    }
}
