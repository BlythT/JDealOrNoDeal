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
class Case {
    //Number on the case (stored here as well as key for case for ease of access
    //When case is removed from map, e.g. for printing out it's number and amount
    protected Integer number;
    //Prizes are only money in this version of the game (as opposed to having
    //a CAR prize)
    protected Double prize;
    
    public Case(int number, Double prize){
        this.number = number;
        this.prize = prize;
    }
}
