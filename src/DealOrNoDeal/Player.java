/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DealOrNoDeal;

/**
 *
 * @author Tim
 */
public class Player {
    //protected int ID;
    protected Case chosenCase;
    protected Double highScore;
    protected Double gamesPlayed;
    protected String name;
    
    public Player(String name){
        this.name = name;
    }
}
