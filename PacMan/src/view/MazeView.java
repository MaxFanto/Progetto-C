package view;

import view.Animations.PacManAnimation;
import view.Animations.GhostAnimation;
import view.Animations.PillAnimation;
import controller.PacmanGame;
import java.util.Observable;
import java.util.Observer;
import model.Player;
import model.MazeModality;
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

public class MazeView extends BasicGame implements Observer {
    
    boolean[][] blocked, tunnel, eat, superP, fruit;
    
    private TiledMap mazeMap;
    
    private Input input;
    
    private PacManAnimation pacman;
    private GhostAnimation pinky, clyde, blinky, inky, blue;
    private PillAnimation pill, superPill;
    private FruitAnimation fruits;
    
    private Image ready, gameOver, youWin;
    
    private int lives = 3;
    private int score = 0;
    
    private boolean pacmanDeath = false;
    private boolean pacmanPower = false;
    
    private Sound begin, eatFruit;
    
    private PacmanGame pacmanGame;
    
    private boolean readyFlag = true;
    private boolean fruitFlag = true;
    
    private String mode;
    
    public MazeView(PacmanGame pacmanGame,String mode) throws SlickException {
        super("");
        this.mode = mode;
        this.pacmanGame = pacmanGame;
    }

    public Input getInput() {return input;}
    public TiledMap getMazeMap() {return mazeMap;}
    public boolean[][] getBlocked() {return blocked;}
    public boolean[][] getTunnel() {return tunnel;}
    public boolean[][] getEat() {return eat;}
    public boolean[][] getSuperP() {return superP;}
    public boolean[][] getFruit() {return fruit;}
    
    /**
     * This method inizializes map, images, animations and sounds 
     * @param container
     * @throws SlickException
     */
    
    @Override
    public void init(GameContainer container) throws SlickException {
        ready = new Image("data/ready.jpg");
        gameOver = new Image("data/gameover.png");
        youWin = new Image("data/youwin.png");
        
        mazeMap = new TiledMap("data/maze/Maze.tmx");
        initMapProperty();
        initAnimations();
        
        begin = new Sound("data/pacmanSound/begin.wav");
        begin.play();
        
        eatFruit = new Sound("data/pacmanSound/eatFruit.wav");
        pacmanGame.initMaze(mazeMap,this);
    }
    
    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        int x = pacman.getxPos();
        int y = pacman.getyPos();
        
        mazeMap.render(0, 0);
        renderFood(x, y);
        
        startReady();
        
        renderPacman();
        
        soundEatFruit();
        
        renderGhosts();
        
        renderScore(g);
        renderLives(g, lives);
        renderTitle(g);
        
        if(lives == 0)
            gameOver.draw(200, 384);

        if (youWin() == 0)
            youWin.draw(212, 384);
    }
    
    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        input = container.getInput();
        pacmanGame.setInfo(input);
    }
    
    @Override
    public void update(Observable o, Object o1) {
        
        for (int i = 0; i < mazeMap.getWidth(); i++) { 
            for (int j = 0; j < mazeMap.getHeight(); j++) {
                eat[i][j] = ((MazeModality)o).getTiles()[i][j].isEat();
                superP[i][j] = ((MazeModality)o).getTiles()[i][j].isSuperP();
                fruit[i][j] = ((MazeModality)o).getTiles()[i][j].isFruit();
            }
        }
        score = ((MazeModality)o).getPacman().getScore();
        try {
            updateOrientation(((MazeModality)o).getPacman(), pacman);
            updateOrientation(((MazeModality)o).getClyde(), clyde);
            updateOrientation(((MazeModality)o).getBlinky(), blinky);
            updateOrientation(((MazeModality)o).getInky(), inky);
            updateOrientation(((MazeModality)o).getPinky(), pinky);
        } catch (SlickException ex) {
            ex.printStackTrace();
        }
        
        if(!pacmanDeath) {
            pacman.setxPos(((MazeModality)o).getPacman().getxPos());
            pacman.setyPos(((MazeModality)o).getPacman().getyPos());
        }
        
        clyde.setxPos(((MazeModality)o).getClyde().getxPos());
        clyde.setyPos(((MazeModality)o).getClyde().getyPos());
        
        blinky.setxPos(((MazeModality)o).getBlinky().getxPos());
        blinky.setyPos(((MazeModality)o).getBlinky().getyPos());
        
        inky.setxPos(((MazeModality)o).getInky().getxPos());
        inky.setyPos(((MazeModality)o).getInky().getyPos());
        
        pinky.setxPos(((MazeModality)o).getPinky().getxPos());
        pinky.setyPos(((MazeModality)o).getPinky().getyPos());
        
        pacmanDeath = ((MazeModality)o).getPacman().isDeath();
        pacmanPower = ((MazeModality)o).getPacman().isPower();
        
        lives = ((MazeModality)o).getPacman().getLives();
    }
    
    /**
     * This method initializes the animations of the map
     * @throws SlickException 
     */
    private void initMapProperty() {
        blocked = checkMazeProperty("blocked");
        tunnel = checkMazeProperty("tunnel");
        eat = checkMazeProperty("eat");
        superP = checkMazeProperty("superP");
        fruit = checkMazeProperty("fruit");
    }
    
    /**
     * This method initializes the animations of the character
     * @throws SlickException 
     */
    private void initAnimations() throws SlickException {
        pacman = new PacManAnimation();
        pacman.rotate(0);
        
        clyde = new GhostAnimation("clyde");
        inky = new GhostAnimation("inky");       
        pinky = new GhostAnimation("pinky"); 
        blinky = new GhostAnimation("blinky");
        blue = new GhostAnimation();
        pill = new PillAnimation("pill.png");
        superPill = new PillAnimation();
        fruits = new FruitAnimation();
    }
    
    /**
     * This methodtake maze property directly from map file
     * @param s denote type of property
     */
    
    private boolean[][] checkMazeProperty(String s) {
        int height = mazeMap.getHeight();
        int weidth = mazeMap.getWidth();
        
        boolean[][] b = new boolean[weidth][height];
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
     * This method change the orientation of animation based on the direction in the movement
     * @param player main player of the animation from whom take the position
     * @param animation animation that has to be rotate
     * @throws SlickException 
     */
    private void updateOrientation(Player player, AnimationsAdapter animation) throws SlickException {
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

    /**
     * This method draws the score
     * @param g
     * @param score identify the points scored by the player
     */
    private void renderScore(Graphics g) {
        g.setColor(Color.yellow);
        g.drawString("SCORE: " + score, 24, 1);
    }
    
    /**
     * This method draws and updates the lives of pacman
     * @param g
     * @param lives
     * @throws SlickException 
     */
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
                    if(mode.equals("extreme"))
                        if(pacmanPower)
                            pill.draw(i*32, j*32, Color.red);
                        else
                            pill.draw(i*32, j*32, Color.blue);                            
                    else 
                        pill.draw(i*32, j*32);

                if (superP[i][j] == true)
                    superPill.draw(i*32, j*32);
                if (fruit[i][j] == true && score > 500)
                    fruits.draw(i*32, j*32);           
            }
        }
    }

    private void renderPacman() throws SlickException {
        if(!pacmanDeath)
                pacman.draw(pacman.getxPos(), pacman.getyPos());
        else
            ready.draw(226, 384);
    }
    
    private void startReady() {
        if(readyFlag){
            ready.draw(226, 384);
            readyFlag = false;
        }
    }

    private void renderTitle(Graphics g) {
        g.setColor(Color.yellow);
        g.drawString("PAC-MAN", 272, 1);
    }
    
    private int youWin() {
        int counter = 0;
        for (int i = 0; i < mazeMap.getWidth(); i++) { 
            for (int j = 0; j < mazeMap.getHeight(); j++) {
                if (eat[i][j] == true)
                    counter++;
                if(superP[i][j] == true)
                    counter++;
            }
        }
        return counter;
    }

    private void soundEatFruit() {
        if (fruit[9][12] == false && fruitFlag == true) {
            eatFruit.play();
            fruitFlag = false;
        }
    }

    private void renderGhosts() {
        if(pacmanPower) {
            blue.draw(clyde.getxPos(),clyde.getyPos());
            blue.draw(pinky.getxPos(), pinky.getyPos());
            blue.draw(inky.getxPos(), inky.getyPos());
            blue.draw(blinky.getxPos(), blinky.getyPos());
        }
        else {
            clyde.draw(clyde.getxPos(),clyde.getyPos());
            pinky.draw(pinky.getxPos(), pinky.getyPos());
            inky.draw(inky.getxPos(), inky.getyPos());
            blinky.draw(blinky.getxPos(), blinky.getyPos());
        }
    }
}
