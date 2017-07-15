package view.Animations;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.Texture;

public class GhostAnimation extends Animation implements AnimationsAdapter {

    private final String PATH_IMAGE = "data/ghost/";
    private String nameGhost;
    private Texture [] textureData;
    final int DURATION = 200;
    
    private int xPos = 288, yPos = 320;

    public GhostAnimation(String type) throws SlickException {
        super.addFrame(new Image(PATH_IMAGE.concat(type).concat("_up1.png")), DURATION);
        super.addFrame(new Image(PATH_IMAGE.concat(type).concat("_up2.png")), DURATION);
        nameGhost = type;
        textureData = getTextureImage();
    }

    public GhostAnimation() throws SlickException {
        super.addFrame(new Image(PATH_IMAGE.concat("blue_ghost1.png")), DURATION);
        super.addFrame(new Image(PATH_IMAGE.concat("blue_ghost2.png")), DURATION);
    }

    public int getxPos() {return xPos;}
    public void setxPos(int xPos) {this.xPos = xPos;}
    public int getyPos() {return yPos;}
    public void setyPos(int yPos) {this.yPos = yPos;}
    
    /**
     * get the texture of all image ghost 
     * @return a vector with a texture:
     * [0] = Im1up, [1] = Im2up
     * [2] = Im1down, [3] = Im2down
     * [4] = Im1left, [5] = Im2left
     * [6] = Im1right, [7] = Im2right
     * @throws SlickException 
     */
    private Texture[] getTextureImage() throws SlickException{        
        Image Im1up = new Image(PATH_IMAGE.concat(nameGhost).concat("_up1.png"));
        Image Im2up = new Image(PATH_IMAGE.concat(nameGhost).concat("_up2.png"));
        Image Im1down = new Image(PATH_IMAGE.concat(nameGhost).concat("_down1.png"));
        Image Im2down = new Image(PATH_IMAGE.concat(nameGhost).concat("_down2.png"));
        Image Im1left = new Image(PATH_IMAGE.concat(nameGhost).concat("_left1.png"));
        Image Im2left = new Image(PATH_IMAGE.concat(nameGhost).concat("_left2.png"));
        Image Im1right = new Image(PATH_IMAGE.concat(nameGhost).concat("_right1.png"));
        Image Im2right = new Image(PATH_IMAGE.concat(nameGhost).concat("_right2.png"));
        
        Texture []t = new Texture[8];
        t[0] = Im1up.getTexture();
        t[1] = Im2up.getTexture();
        t[2] = Im1down.getTexture();
        t[3] = Im2down.getTexture();
        t[4] = Im1left.getTexture();
        t[5] = Im2left.getTexture();
        t[6] = Im1right.getTexture();
        t[7] = Im2right.getTexture();
        
        return t;
    }
    
    @Override
    public void rotate(int value) throws SlickException {
                   
        switch(value) {
            case 0:
                this.getImage(0).setTexture(textureData[6]);
                this.getImage(1).setTexture(textureData[7]);
                break;
            case 90:
                this.getImage(0).setTexture(textureData[2]);
                this.getImage(1).setTexture(textureData[3]);
                break;
            case 180:
                this.getImage(0).setTexture(textureData[4]);
                this.getImage(1).setTexture(textureData[5]);
                break;
            case 270:
                this.getImage(0).setTexture(textureData[0]);
                this.getImage(1).setTexture(textureData[1]);
                break;
        }
    }
}