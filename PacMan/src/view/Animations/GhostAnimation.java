
package view.Animations;

import model.Direzioni;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import view.Animations.AnimationsAdapter;


public class GhostAnimation extends Animation implements AnimationsAdapter{

    private final String[] path_image = {"data/ghost_images/","data/ghost_images/"};
    private String name_ghost;
    
    private int xPos, yPos;

    public GhostAnimation(String type) throws SlickException {
        super.addFrame(new Image(path_image[0].concat(type).concat("_up1.png")), DURATION);
        super.addFrame(new Image(path_image[1].concat(type).concat("_up2.png")), DURATION);
        name_ghost = type;
    }

    public int getxPos() {return xPos;}
    public void setxPos(int xPos) {this.xPos = xPos;}
    public int getyPos() {return yPos;}
    public void setyPos(int yPos) {this.yPos = yPos;}
    
    
   
    
    
    
    @Override
    public Animation rotate(int value) throws SlickException{
        super.getImage(0).destroy();
            super.getImage(1).destroy();
            
            switch(value){
                case 0:
                    this.addFrame(new Image(path_image[0].concat(name_ghost + "_right1.png")), DURATION);
                    this.addFrame(new Image(path_image[1].concat(name_ghost + "_right2.png")), DURATION);
                    break;
                case 90:
                    this.addFrame(new Image(path_image[0].concat(name_ghost + "_down1.png")), DURATION);
                    this.addFrame(new Image(path_image[1].concat(name_ghost + "_down2.png")), DURATION);
                    break;
                case 180:
                    this.addFrame(new Image(path_image[0].concat(name_ghost + "_left1.png")), DURATION);
                    this.addFrame(new Image(path_image[1].concat(name_ghost + "_left2.png")), DURATION);
                    break;
                case 270:
                    this.addFrame(new Image(path_image[0].concat(name_ghost + "_up1.png")), DURATION);
                    this.addFrame(new Image(path_image[1].concat(name_ghost + "_up2.png")), DURATION);
                    break;
        }
    return null;
    }
}