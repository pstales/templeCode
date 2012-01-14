/*
 * Student: Paul Stales
 * Professor: Dr Yates / TA: Liang Lan
 * Date: November 20th, 2011
 * Homework 13 - Connect N - Make interacting classes that will play a game of connect N
 */

/*
 * The GameRunnerBeta class is the MAIN class, that will create instances of the objects and run a game.
 */
package Stales_ConnectN;

import java.util.Scanner;

public class GameRunner 
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        
        // STEP 1 - PRINT THE WELCOME TO THE SCREEN, GET PERSON TO INPUT A KEY AND PRESS ENTER
        Game.getWelcome();
        input.next();
        
        // STEP 2 - PRINT THE RULES TO THE SCREEN, ASK FOR AN N
        Game.getRules();
        System.out.println(" ");
        System.out.println("Please choose an N to play. you may choose between 3 and 9. (Standard is Connect 4)");
        int N = input.nextInt();
        
        while ((N < 3) || (N > 9))
        {
            System.out.println("Please choose a VALID N between 3 and 9. Such as 3, 4, 5, 6, 7, 8, or 9. I recommend 4.");
            N = input.nextInt();
        }
        
        // STEP 3 - GET INFORMATION ABOUT PLAYER 1
        System.out.println("Is the first player human (enter 1) or computer (enter anything else)?");
        int choice = input.nextInt();
        String name1 = "Paul-CPU";
        
        if (choice == 1)
        {
            System.out.println("Greetings human, what is your name?");
            name1 = input.next();
            System.out.println("Thank you " + name1 + ", you are player 1. Good luck!");
        }
        
        // STEP 4 - GET INFORMATION ABOUT PLAYER 2
        System.out.println("Is the second player human (enter 1) or computer (enter anything else)?");
        choice = input.nextInt();
        String name2 = "Ian-CPU";
        
        if (choice == 1)
        {
            System.out.println("Greetings human, what is your name?");
            name2 = input.next();
            System.out.println("Thank you " + name2 + ", you are player 2. Good luck!");
        }
        
        // STEP 5 - CREATE AN INSTANCE OF EACH CLASS
        Board board1 = new Board(N);
        Player player1 = new Player(name1, board1);
        Player player2 = new Player(name2, board1);
        Game game1 = new Game(player1, player2, board1);
        
        // STEP 6 - START THE GAME
        game1.startGame();

    }
    
} // class