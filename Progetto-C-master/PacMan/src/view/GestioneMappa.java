/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import org.newdawn.slick.Input;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author MassimilianoFanto
 */
public class GestioneMappa {
     
    private int TILE_WIDTH, TILE_HEIGHT;
    
    public static boolean AltroTastoPremuto(Input input, int n, int x, int y) {
        int[] UsedKeys = {Input.KEY_DOWN, Input.KEY_UP, Input.KEY_LEFT, Input.KEY_RIGHT}; 

        for (int i = 0; i < UsedKeys.length; i++) {
            if(input.isKeyDown(UsedKeys[i]) && UsedKeys[i] != n)
                return true;
        }

        return false;
    }
    
    

    private boolean[][] generaMappaProprietà(TiledMap grassMap, String s) {
        boolean[][] b = new boolean[grassMap.getWidth()][grassMap.getHeight()];
        for (int i = 0; i < grassMap.getWidth(); i++) {
            for (int j = 0; j < grassMap.getHeight(); j++) {
                
                int tileID = grassMap.getTileId(i, j, grassMap.getLayerIndex("Livello tile 1"));
                
                String value = grassMap.getTileProperty(tileID, s , "false");
                if(value.equals("true")) {

                    b[i][j] = true;
                    
                }
            }
        }
        return b;
    }
}
