/*
 * Student: Paul Stales
 * Professor: Dr Yates / TA: Liang Lan
 * Date: November 20th, 2011
 * Homework 13 - Connect N - Make interacting classes that will play a game of connect N
 */

/*
 * The Player class represents the players of the game.
 */

package Stales_ConnectN;
import java.util.Scanner;

public class Player 
{
    // INSTANCE VARIABLES
    private static int playersMade = 0;
    private int playerNumber;
    private String name;
    private char piece;
    private Board board;
    
    // CONSTRUCTOR
    public Player(String name, Board board)
    {
        playersMade++;
        this.name = name;
        this.board = board;
        playerNumber = playersMade;
        
        if (playerNumber == 1)
        {
            piece = '1';
        }
        else 
        {
            piece = '2';
        }
    }
    
    // ACCESSOR METHODS - Getters
    public String getName()
    {
        return name;
    }
    
    public int getNumber()
    {
        return playerNumber;
    }
    
    public char getPiece()
    {
        return piece;
    }
    
    // MUTATOR METHODS - Setters
    
    // GAME METHODS - Methods for Playing the Game

    /* The takeTurn method checks if the columns entered are valid.
     * If the they are valid options, then a call to "setBoardTurn" is made.
     * setBoardTurn will update the board accordingly.
     */
    public void takeTurn(int col)
    {
        boolean turnFinished = false;
        Scanner input = new Scanner(System.in);
        
        while (turnFinished == false)
        {
            if ((col < 0) || (col > board.getCols()-1))
            {
                System.out.println("Not a valid column! Please enter a valid column.");
                col = input.nextInt();
            }
            else if (board.checkRows(col) == -1)
            {
                System.out.println("Column is full! Please enter a non-empty column.");
                col = input.nextInt();
            }
            else 
            {
                System.out.println("Placing your piece...");
                board.setBoardTurn(board.checkRows(col), col, piece);
                turnFinished = true;
            }
        }
    }

} // class