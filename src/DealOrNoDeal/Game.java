/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DealOrNoDeal;

import java.util.Scanner;
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
    private int turn;

    //Default game settings
    //Holds the amount of cases revealed for each stage of the game
    //Must add to NUMCASES minus 2
    private static final Integer[] REVEALS = {5, 3, 3, 3, 3, 3};
    private static final Integer NUMCASES = 22;

    private Integer[] reveals;
    private Integer numCases;

    private Scanner sc;

    public Game(Integer[] reveals, Integer numCases) {
        sc = new Scanner(System.in);
        this.reveals = reveals;
        this.numCases = numCases;
        turn = 0;
        //Initialise empty treemap ready for cases to be added
        remainingCases = new TreeMap<>();
        //Add cases (for now, temporary for testing, but more polished code or
        //method to come later)
        initialiseCases(NUMCASES);
        //navigable key set is tied to treemap meaning when a case is removed
        //from the treemap, the number is also removed from remaining cases
        //further explained in navagableKeySet() doccumentation
        caseNumbers = remainingCases.navigableKeySet();
        //Game is ready to be played
    }

    public Game() {
        //Create game with default values
        this(REVEALS, NUMCASES);
    }

    public void play() {
        turn = 0;
        //For testing purposes at the moment
        Player player = initialisePlayer();
        //Player chooses a case
        pickCase(player);
        //Player reveals a number of cases per turn based on reveals array field before an offer
        for (Integer amount : REVEALS) {
            doReveals(amount);
        }

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

    private void initialiseCases(Integer amount) {
        for (int i = 1; i <= amount; i++) {
            remainingCases.put(i, new Case(i, i * 10));   //Temporaray prizes of i*10        
        }
    }

    private void revealCase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void bankOffer(int turn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void doReveals(int amount) {

        for (int i = 1; i <= amount; i++) {
            revealCase();
            turn++;
        }
        //Player is then made an offer by the banker based on turn count
        //and remaining value in cases
        bankOffer(turn);
    }

    private Player initialisePlayer() {
        System.out.println("Please enter your name:");
        String name = sc.nextLine();
        return new Player(name);
    }
}
