package view.Animations;

import org.newdawn.slick.SlickException;

public interface AnimationsAdapter {

    public void rotate(int value) throws SlickException;
    
    public void changeAnimationSet() throws SlickException;
}
