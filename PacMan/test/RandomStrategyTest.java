/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Algorithms.RandomStrategy;
import model.Fantasmi.Ghost;
import model.Fantasmi.Pinky;
import org.junit.Test;
import static org.junit.Assert.*;
import other.Tile;

/**
 *
 * @author mattia
 */
public class RandomStrategyTest {
    Tile[][] tiles = new Tile[3][6];

    public RandomStrategyTest() {
        /*wall zone*/
        tiles[0][0] = new Tile(32,32,true,false,false,false,false);
        tiles[0][1] = new Tile(32,32,true,false,false,false,false);
        tiles[0][2] = new Tile(32,32,true,false,false,false,false);
        tiles[0][3] = new Tile(32,32,true,false,false,false,false);
        tiles[0][4] = new Tile(32,32,true,false,false,false,false);
        tiles[0][5] = new Tile(32,32,true,false,false,false,false);
        tiles[1][0] = new Tile(32,32,true,false,false,false,false);
        tiles[1][5] = new Tile(32,32,true,false,false,false,false);
        tiles[2][0] = new Tile(32,32,true,false,false,false,false);
        tiles[2][1] = new Tile(32,32,true,false,false,false,false);
        tiles[2][2] = new Tile(32,32,true,false,false,false,false);
        tiles[2][3] = new Tile(32,32,true,false,false,false,false);
        tiles[2][4] = new Tile(32,32,true,false,false,false,false);
        tiles[2][5] = new Tile(32,32,true,false,false,false,false);
        /*walkable zone*/
        tiles[1][1] = new Tile(32,32,false,false,false,false,false);
        tiles[1][2] = new Tile(32,32,false,false,false,false,false);
        tiles[1][3] = new Tile(32,32,false,false,false,false,false);
        tiles[1][4] = new Tile(32,32,false,false,false,false,false);    
    }
    
    
    @Test
    public void testDirection(){
        Ghost ghost = new Pinky(32, 32, 6, tiles);
        RandomStrategy strategy = new RandomStrategy();
        ghost.setX(32);
        ghost.setY(32);        
        assertEquals(3, strategy.direction(ghost));      
    }
    
}
