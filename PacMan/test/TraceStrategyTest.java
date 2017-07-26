/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Algorithms.TraceStrategy;
import model.Fantasmi.Blinky;
import model.Fantasmi.Clyde;
import model.Fantasmi.Ghost;
import model.Fantasmi.Pinky;
import org.junit.Test;
import static org.junit.Assert.*;
import other.Tile;

/**
 *
 * @author mattia
 */
public class TraceStrategyTest {
    private Tile[][] tiles = new Tile[5][6];
    private TraceStrategy strategy;
    public TraceStrategyTest() {
        //border up
        tiles[0][0] = new Tile(true);
        tiles[0][1] = new Tile(true);
        tiles[0][2] = new Tile(true);
        tiles[0][3] = new Tile(true);
        tiles[0][4] = new Tile(true);
        tiles[0][5] = new Tile(true);
        
        //border left
        tiles[1][0] = new Tile(true);
        tiles[2][0] = new Tile(true);
        tiles[3][0] = new Tile(true);
        
        //border right
        tiles[1][5] = new Tile(true);
        tiles[2][5] = new Tile(true);
        tiles[3][5] = new Tile(true);
        
        //border down
        tiles[4][0] = new Tile(true);
        tiles[4][1] = new Tile(true);
        tiles[4][2] = new Tile(true);
        tiles[4][3] = new Tile(true);
        tiles[4][4] = new Tile(true);
        tiles[4][5] = new Tile(true);
        
        //walkable zone
        tiles[1][1] = new Tile(false);
        tiles[1][2] = new Tile(false);
        tiles[1][3] = new Tile(false);
        tiles[1][4] = new Tile(false);
        tiles[2][1] = new Tile(false);
        tiles[3][1] = new Tile(false);
        tiles[3][2] = new Tile(false);
        tiles[3][3] = new Tile(false);
        tiles[3][4] = new Tile(false);
        
        
        strategy = new TraceStrategy();
    }
    
    @Test
    public void testAxesControl(){
        Ghost ghost = new Pinky(32,32,6,tiles);
        ghost.setX(64);
        ghost.setY(32);
        ghost.setxPacMan(128);
        ghost.setyPacMan(32);
   
        assertEquals(3, strategy.axesControl(ghost));
    }
    
    @Test
    public void testDirection(){
        Ghost ghost = new Pinky(32,32,6,tiles);
        ghost.setX(64);
        ghost.setY(32);
        ghost.setxPacMan(128);
        ghost.setyPacMan(96);
        
        assertEquals(1,strategy.direction(ghost));
    }
    
}
