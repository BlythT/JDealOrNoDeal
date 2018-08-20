/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DealOrNoDeal;

import java.util.SortedSet;
import java.util.TreeMap;

/**
 *
 * @author Tim
 */
public class Game {

    //Cases will be held in a TreeMap to preserve order of their indexes
    //as well as one-to-one mapping thier indexes to values (case number to prize)
    private TreeMap<Integer, Case> remainingCases;
    private SortedSet<Integer> caseNumbers;

    public Game() {
        //Initialise empty treemap ready for cases to be added
        remainingCases = new TreeMap<>();
        //Add cases (for now, temporary for testing, but more polished code or
        //method to come later
        for (int i = 1; i <= 22; i++) {
            remainingCases.put(i, new Case(i, i * 10));   //Temporaray prizes of i*10        
        }
        //navigable key set is tied to treemap meaning when a case is removed
        //from the treemap, the number is also removed from remaining cases
        //further explained in navagableKeySet() doccumentation
        caseNumbers = remainingCases.navigableKeySet();

    }

    public void play() {
        //For testing purposes at the moment
        Player player = new Player();

        pickCase(player);
    }

    //For the player to pick their case when the game starts
    private boolean pickCase(Player player) {
        //If player does not have a case
        if (player.chosenCase == null) {
            //Get list of available cases from already initialised treemap
            //Contains all the case numbers in order

            //display list of available cases to player
            for (Integer caseNumber : caseNumbers) {
                System.out.println(caseNumber);
            }
           
            //testing the linking between navagable keyset and 
            System.out.println(caseNumbers.contains(5));
            remainingCases.remove(5);
            System.out.println(caseNumbers.contains(5));
            
            //get and validate the user input
            //set chosenCase of player to case (The Case object that corrisponds
            //with chosen index
        }
        return false;
    }
}
