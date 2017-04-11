/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicModel;

/**
 *
 * @author cl427927
 */
public class Partita {
    Giocatore pacMan;
    Fantasma akabei, aosuke;
    Labirinto labirinto;

    public Partita() {
        pacMan = new PacMan();
        akabei = new Akabei(0, 0);
        aosuke = new Aosuke(0, 0);
        labirinto = new Labirinto(0,0,0);
    }
    
    
}
