
package view.Animations;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import view.Animations.AnimationsAdapter;


public class GhostAnimation extends Animation implements AnimationsAdapter{

    private final String[] path_image = {"data/ghost_images/","data/ghost_images/"};
    private String name_ghost;
    private final int DURATION = 200;

    public GhostAnimation(String type) throws SlickException {
        switch(type){
            case "red":
                super.addFrame(new Image(path_image[0].concat("blinky_up1.png")), DURATION);
                super.addFrame(new Image(path_image[1].concat("blinky_up2.png")), DURATION);
                name_ghost = type;
                break;
            case "blu":
                super.addFrame(new Image(path_image[0].concat("inky_up1.png")), DURATION);
                super.addFrame(new Image(path_image[1].concat("inky_up2.png")), DURATION);
                name_ghost = "inky";
                break;
            case "green":
                super.addFrame(new Image(path_image[0].concat("clyde_up1.png")), DURATION);
                super.addFrame(new Image(path_image[1].concat("clyde_up2.png")), DURATION);
                name_ghost = "clyde";
                break;
            case "pinky":
                super.addFrame(new Image(path_image[0].concat("pinky_up1.png")), DURATION);
                super.addFrame(new Image(path_image[1].concat("pinky_up2.png")), DURATION);
                name_ghost = "pinky";
                break;
        }
    }
    
    
    
    @Override
    public Animation rotate(int value) throws SlickException{
        super.getImage(0).destroy();
            super.getImage(1).destroy();
            
            switch(value){
                case 0:
                    this.addFrame(new Image(path_image[0].concat(name_ghost + "right_1.png")), DURATION);
                    this.addFrame(new Image(path_image[1].concat(name_ghost + "right_2.png")), DURATION);
                    break;
                case 90:
                    this.addFrame(new Image(path_image[0].concat(name_ghost + "down_1.png")), DURATION);
                    this.addFrame(new Image(path_image[1].concat(name_ghost + "down_2.png")), DURATION);
                    break;
                case 180:
                    this.addFrame(new Image(path_image[0].concat(name_ghost + "left_1.png")), DURATION);
                    this.addFrame(new Image(path_image[1].concat(name_ghost + "left_2.png")), DURATION);
                    break;
                case 270:
                    this.addFrame(new Image(path_image[0].concat(name_ghost + "up_1.png")), DURATION);
                    this.addFrame(new Image(path_image[1].concat(name_ghost + "up_2.png")), DURATION);
                    break;
        }
    return null;
    }
}