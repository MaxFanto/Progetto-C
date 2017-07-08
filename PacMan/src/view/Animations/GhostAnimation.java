package view.Animations;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class GhostAnimation extends Animation implements AnimationsAdapter{

    private final String[] path_image = {"data/ghost/","data/ghost/"};
    private String name_ghost;
    
    private int xPos = 288, yPos = 256;

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
    public void rotate(int value) throws SlickException{
//        System.out.println(path_image+name_ghost);
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
    }

    @Override
    public void changeAnimationSet() throws SlickException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        /*
        TODO: modifica animazione quando muore il fantasma e quando ritorna in vita
        */
    }
}