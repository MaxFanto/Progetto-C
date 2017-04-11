/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicModel;

import altro.Frutto;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author lorenzo
 */
public class Labirinto {
    private int larghezza, altezza, numTiles;
    
    ArrayList<Double[]> powerPills;
    private final Double[] POWER_PILL_1 = new Double[]{0.3,0.7};
    private final Double[] POWER_PILL_2 = new Double[]{0.3,0.7};
    private final Double[] POWER_PILL_3 = new Double[]{0.3,0.7};
    private final Double[] POWER_PILL_4 = new Double[]{0.3,0.7};

    public Labirinto(int larghezza, int altezza, int numTiles) {
        this.larghezza = larghezza;
        this.altezza = altezza;
        this.numTiles = numTiles;
        
        generaPowerPills();

    }
    private void generaPowerPills(){
        powerPills.add(POWER_PILL_1);
        powerPills.add(POWER_PILL_2);
        powerPills.add(POWER_PILL_3);
        powerPills.add(POWER_PILL_4);
    }
    
    
    
    
    public Frutto generaFrutto(){
        Random random = new Random();
        int indiceFrutto = random.nextInt(6);
        
        switch(indiceFrutto){
            case 0:
                return Frutto.CILIEGIA;               
            case 1:
                return Frutto.FRAGOLA;               
            case 2:
                return Frutto.ARANCIA;              
            case 3:
                return Frutto.MELA;            
            case 4:
                return Frutto.UVA;        
            case 5:
                return Frutto.CAMPANA;
            case 6:
                return Frutto.CHIAVE;                
        }
        return null;
    }
}
