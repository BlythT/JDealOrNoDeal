/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DealOrNoDeal;

import java.util.ArrayList;
import java.util.Collections;
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
    private int round;
    private ArrayList<Double> remainingPrizes;

    //Default game settings
    //Holds the amount of cases revealed for each stage of the game
    //Must add to amount of prizes minus 2
    private static final Integer[] REVEALS = {5, 3, 3, 3, 3, 3};
    private static final double[] PRIZELIST = {0.5, 1, 2, 5, 10, 20, 50, 100, 200, 300, 500, 1000, 2000, 3000, 5000, 10000, 20000, 30000, 50000, 75000, 100000, 200000};

    private Scanner sc;

    public Game(Integer[] reveals, double[] prizelist) {
        sc = new Scanner(System.in);
        turn = 0;
        round = 0;
        //Initialise empty treemap ready for cases to be added
        remainingPrizes = new ArrayList<>();
        remainingCases = new TreeMap<>();
        //Add cases with prizes in them
        initialiseCases(prizelist);
        //navigable key set is tied to treemap meaning when a case is removed
        //from the treemap, the number is also removed from remaining cases
        //further explained in navagableKeySet() doccumentation
        caseNumbers = remainingCases.navigableKeySet();
        //Game is ready to be played
    }

    public Game() {
        //Create game with default values
        this(REVEALS, PRIZELIST);
    }

    public void play() {
        turn = 0;
        round = 0;
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
            //get and validate the user input
            int caseNumber = caseInput();
            //set chosenCase of player to case (The Case object that corrisponds
            //with chosen index
            player.chosenCase = remainingCases.remove(caseNumber);
            return true;
        }
        return false;
    }

    private void initialiseCases(double[] prizes) {
        ArrayList<Double> prizeList = new ArrayList<>();
        for (double prize : prizes) {
            prizeList.add(prize);
            remainingPrizes.add(prize);
        }
        Collections.shuffle(prizeList);
        for (int i = 1; i <= prizeList.size(); i++) {
            remainingCases.put(i, new Case(i, prizeList.get(i-1)));
            
        }
    }

    private void revealCase() {
        int caseNumber = caseInput();
        Case revealedCase = remainingCases.remove(caseNumber);
        System.out.println("The case contained... $" + revealedCase.prize + "!");
        remainingPrizes.remove(revealedCase.prize);
        enterToContinue();
    }

    private void bankOffer() {
        double sum = 0;
        for (Double prize : remainingPrizes) {
            sum+=prize;
        }
        double offer = ((sum/remainingPrizes.size())*(double)round/(double)10);
        String offerString = String.format("%.0f", offer);
        System.out.println("$$ The bank offers... $" + offerString + "!!! $$");
        enterToContinue();
        dealOrNoDeal(offer);
    }

    private void doReveals(int amount) {

        for (int i = 1; i <= amount; i++) {
            System.out.println("");
            System.out.println("------ Reveal " + turn + " ------");
            revealCase();
            turn++;
            System.out.println("");
        }
        round++;
        //Player is then made an offer by the banker based on turn count
        //and remaining value in cases
        bankOffer();
    }

    private Player initialisePlayer() {
        System.out.println("Please enter your name:");
        String name = sc.nextLine();
        return new Player(name);
    }

    private int caseInput() {
        //Print cases to choose from
        printRemainaingPrizes();
        printRemainingCases();
        while (true) {
            System.out.println("Choose your case: ");
            //Get user input
            try {
                int caseNum = Integer.parseInt(sc.nextLine());
                if (remainingCases.containsKey(caseNum)) {
                    return caseNum;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Input error: not a valid number");
            }

        }
        //If case is valid number and is within the remaining cases, return number
        //else repeat
    }

    private void printRemainingCases() {
        System.out.println("Remaining cases:");
        for (Integer caseNumber : caseNumbers) {
            System.out.print(caseNumber + " ");
        }
        System.out.println("");
    }

    private void printRemainaingPrizes() {
        System.out.println("Remaining prizes:");
        for (Double prize : remainingPrizes) {
            System.out.print(prize + " ");
        }
        System.out.println("");
    }

    private void enterToContinue() {
         System.out.println("Press enter to continue to the next turn!");
        sc.nextLine();
    }

    private void dealOrNoDeal(double offer) {
        
    }
}
