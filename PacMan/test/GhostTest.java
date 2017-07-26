/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import model.Fantasmi.Ghost;
import model.Fantasmi.Pinky;
import org.junit.Test;
import static org.junit.Assert.*;
import other.Tile;

/**
 *
 * @author mattia
 */
public class GhostTest {
    private Tile[][] tiles = new Tile[5][6];
    Ghost ghost;
    public GhostTest() {
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
        
        ghost = new Pinky(32,32,6,tiles);
    }
    
    @Test
    public void testAIMovement(){
        ghost.setX(64);
        ghost.setY(32);
        int[] expecteds = {65,32};
        int[] value = ghost.AIMovement(3);
        assertEquals(65, value[0]);
    }
    @Test
    public void testcheckBlockedUP(){
        ghost.setX(64);
        ghost.setY(32);
        
        assertEquals(false, ghost.checkBlockUp());
    }
    @Test
    public void testcheckBlockedDown(){
        ghost.setX(64);
        ghost.setY(32);
        
        assertEquals(false, ghost.checkBlockUp());
    }
    @Test
    public void testcheckBlockedLeft(){
        ghost.setX(64);
        ghost.setY(32);
        
        assertEquals(true, ghost.checkBlockUp());
    }
    @Test
    public void testcheckBlockedRight(){
        ghost.setX(64);
        ghost.setY(32);
        
        assertEquals(true, ghost.checkBlockUp());
    }   
}
