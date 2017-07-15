package view.Animations;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class GhostAnimation extends Animation implements AnimationsAdapter {

    private final String PATH_IMAGE_0 = "data/ghost/";
    private final String PATH_IMAGE_1 = "data/ghost/";
    private String nameGhost;
    
    final int DURATION = 200;
    
    private int xPos = 288, yPos = 320;

    public GhostAnimation(String type) throws SlickException {
        super.addFrame(new Image(PATH_IMAGE_0.concat(type).concat("_up1.png")), DURATION);
        super.addFrame(new Image(PATH_IMAGE_1.concat(type).concat("_up2.png")), DURATION);
        nameGhost = type;
    }

    public GhostAnimation() throws SlickException {
        super.addFrame(new Image(PATH_IMAGE_0.concat("blue_ghost1.png")), DURATION);
        super.addFrame(new Image(PATH_IMAGE_1.concat("blue_ghost2.png")), DURATION);
    }

    public int getxPos() {return xPos;}
    public void setxPos(int xPos) {this.xPos = xPos;}
    public int getyPos() {return yPos;}
    public void setyPos(int yPos) {this.yPos = yPos;}
    
    
    @Override
    public void rotate(int value) throws SlickException {
        super.getImage(0).destroy();
        super.getImage(1).destroy();
            
        switch(value) {
            case 0:
                this.addFrame(new Image(PATH_IMAGE_0.concat(nameGhost + "_right1.png")), DURATION);
                this.addFrame(new Image(PATH_IMAGE_1.concat(nameGhost + "_right2.png")), DURATION);
                break;
            case 90:
                this.addFrame(new Image(PATH_IMAGE_0.concat(nameGhost + "_down1.png")), DURATION);
                this.addFrame(new Image(PATH_IMAGE_1.concat(nameGhost + "_down2.png")), DURATION);
                break;
            case 180:
                this.addFrame(new Image(PATH_IMAGE_0.concat(nameGhost + "_left1.png")), DURATION);
                this.addFrame(new Image(PATH_IMAGE_1.concat(nameGhost + "_left2.png")), DURATION);
                break;
            case 270:
                this.addFrame(new Image(PATH_IMAGE_0.concat(nameGhost + "_up1.png")), DURATION);
                this.addFrame(new Image(PATH_IMAGE_1.concat(nameGhost + "_up2.png")), DURATION);
                break;
        }
    }
}