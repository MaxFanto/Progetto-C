package view.Animations;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class PillAnimation extends Animation {
    
    private final String IMAGE_PATH_0 = "data/pill/superPill0.png";
    private final String IMAGE_PATH_1 = "data/pill/superPill1.png";
    private final String IMAGE_PATH = "data/pill/";
    
    final int DURATION = 200;
    
    public PillAnimation() throws SlickException {
        super.addFrame(new Image(IMAGE_PATH_0), DURATION);
        super.addFrame(new Image(IMAGE_PATH_1), DURATION);
    }
    public PillAnimation(String type) throws SlickException {
        super.addFrame(new Image(IMAGE_PATH.concat(type)), DURATION);
    }
}
