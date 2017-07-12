package view.Animations;

import java.util.Random;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class FruitAnimation extends Animation {

    private final String IMAGE_PATH = "data/frutta/";
    
    final int DURATION = 200;

    public FruitAnimation() throws SlickException {
        String type = generateFruit();
        this.addFrame(new Image(IMAGE_PATH.concat(type)), DURATION);
    }
    
    public String generateFruit(){
        Random random = new Random();
        int indiceFrutto = random.nextInt(6);
        
        switch(indiceFrutto){
            case 0:
                return "ciliegia.png";            
            case 1:
                return "fragola.png";               
            case 2:
                return "arancia.png";              
            case 3:
                return "mela.png";            
            case 4:
                return "pera.png";        
            case 5:
                return "banana.png";
            case 6:
                return "pretzel.png";                
        }
        return null;
    }
}
