/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Observable;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author cl427927
 */
public abstract class Giocatore{
    private String nome;
    private int punteggio;
    
    private int velocita;
    private Image[] moveUp,moveDown,moveLeft,moveRight;
    
    
    
//    public Giocatore(Image[] moveUp) throws SlickException{
//        Image [] movementUp = moveUp;
//        Image [] movementDown = {new Image("data/pacman0.png"), new Image("data/pacman1.png")};
//        Image [] movementLeft = {new Image("data/pacman0.png"), new Image("data/pacman1.png")};
//        Image [] movementRight = {new Image("data/pacman0.png"), new Image("data/pacman1.png")};
//    }
}
