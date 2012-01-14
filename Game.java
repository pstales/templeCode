/*
 * Student: Paul Stales
 * Professor: Dr Yates / TA: Liang Lan
 * Date: November 20th, 2011
 * Homework 13 - Connect N - Make interacting classes that will play a game of connect N
 */

/*
 * The Game class will perform all calculations to see if the game is over or not.
 */

package Stales_ConnectN;

import java.util.Scanner;
import java.util.Random;

public class Game 
{
    // INSTANCE VARIABLES
    private Player player1;
    private Player player2;
    private Board board;
    private boolean gameOver;
    private Scanner scan = new Scanner(System.in);
    
    // CONSTRUCTOR - Accepts arguments for two players and a board
    public Game(Player player1, Player player2, Board board)
    {
        this.player1 = player1;
        this.player2 = player2;
        this.board = board;
    }
    
    public static void getWelcome()
    {
        System.out.println("** WELCOME TO CONNECT N **");
        System.out.println(" ");
        System.out.println("** DESIGNED BY PAUL J STALES **");
        System.out.println(" ");
        System.out.println("** PRESS ANY KEY AND HIT ENTER TO CONTINUE **");
    }
    
    public static void getRules()
    {
        for (int i = 0; i < 5; i++)
        {
            System.out.println(" ");
        }
        
        System.out.println("** RULES OF CONNECT N **");
        System.out.println("- At first, the board is completely empty.");
        System.out.println("- During each round, the player chooses a column to place (or 'drop') one of their pieces.");
        System.out.println("- The piece drops down and automatically takes the bottom-most unoccupied spot in that column.");
        System.out.println("- The players take turns choosing columns to place their pieces in.");
        System.out.println("- When a column fills up, no more pieces can be added to the column.");
        System.out.println("- Players may not 'pass' or skip their turn.");
        System.out.println("- The object of the game is to get N pieces in a straight line (vertical, horizontal, or diagonal) before the opponent gets N of their pieces in a line.");
        System.out.println("- If the board fills completely before either player gets N in a row, the game is a draw (tie).");
        System.out.println("- Good luck, and enjoy playing Connect N!");
    }


    /*
     * The startGame method will run a loop that continously asks players for their turn input.
     * This loop will only end when a player wins from N in a row, or a tie (all spaces full).
     */

    public void startGame()
    {
        System.out.println("Hello Player " + player1.getNumber() + " " + player1.getName() + " and Player " + player2.getNumber() + " " + player2.getName() + "!");
        System.out.println("");
        System.out.println("Get ready to connect " + board.getNumberN() + "!");
        System.out.println("");
        System.out.println("** PRESS ANY KEY AND HIT ENTER TO CONTINUE **");
        scan.next();
        
        Random rand = new Random();
        int currentPlayer = 1;
        
        // Loop for game, ends only when a game results in a win or a tie
        while (gameOver == false)
        {
            String name;
            if (currentPlayer == 1)
            {
                name = player1.getName();
            }
            else
            {
                name = player2.getName();
            }
            
            
            System.out.println(" ");
            System.out.println("Player " + (currentPlayer) + ", " + name + ", please choose a column between 0 and " + (board.getCols()-1));
            board.getBoardDisplay();
            
            int choice;
            if ( (name.equals("Paul-CPU")) || (name.equals("Ian-CPU")) )
            {
                choice = rand.nextInt(board.getCols()-1);
            }
            else
            {
                choice = scan.nextInt();
            }
            
            
            System.out.println("Player " + (currentPlayer) + ", " + name + ", chooses column " + choice);
            if (currentPlayer == 1)
            {
                player1.takeTurn(choice);
                currentPlayer++;
                if ( getWinResult(board.getLastRow(), board.getLastCol(), player1.getPiece()) == true)
                {
                    System.out.println("*** WINNER *** WINNER *** WINNER ***");
                    System.out.println("***           PLAYER 1           ***");
                    System.out.println("*** WINNER *** WINNER *** WINNER ***");
                    break;
                }
                else if (getTieResult() == true)
                {
                    System.out.println("GAME ENDS IN A TIE.");
                }
            }
            else
            {
                player2.takeTurn(choice);
                currentPlayer--;
                if ( getWinResult(board.getLastRow(), board.getLastCol(), player2.getPiece()) == true)
                {
                    System.out.println("*** WINNER *** WINNER *** WINNER ***");
                    System.out.println("***           PLAYER 2           ***");
                    System.out.println("*** WINNER *** WINNER *** WINNER ***");
                    gameOver = true;
                    break;
                }
                else if (getTieResult() == true)
                {
                    System.out.println("GAME ENDS IN A TIE.");
                    gameOver = true;
                    break;
                }
            }
        }
    }

    /*
     * Method getWinresult will look at the most recently placed piece, and check radially around it.
     * It will check in the horizontal, vertical, and diagonals.
     * This method determines when the game is considered over via a win.
     */
    public boolean getWinResult(int row, int col, char piece)
    {
        int piecesInARow = 1;
        
        for (int i = 1; i < board.getCols(); i++)
        {
            // Outer if-else checks if the move is legal, and breaks the loop if not.
            if ((col-i) > -1)
            {
                // Inner if-else checks if there is a piece that matches, and breaks the loop if not.
                if (board.checkBoard(row, (col-i)) == piece)
                {
                    piecesInARow++;
                    // Innermost if checks for the win condition, which is four matches in a row.
                    if (piecesInARow == board.getNumberN())
                    {
                        System.out.println(board.getNumberN() + " in a row horizontally.");
                        return true;
                    }
                }
                else
                {
                    // piecesInARow does NOT get reset, because it has to check right.
                    break;
                }
            }
            else
            {
                // piecesInARow does NOT get reset, because it has to check right.
                break;
            }
            
        } // END OF CHECK 1 (HORIZONTAL, LEFT)
        
        for (int i = 1; i < board.getCols(); i++)
        {
            // Outer if-else checks if the move is legal, and breaks the loop if not.
            if ((col+i) < board.getCols())
            {
                // Inner if-else checks if there is a piece that matches, and breaks the loop if not.
                if (board.checkBoard(row, (col+i)) == piece)
                {
                    piecesInARow++;
                    // Innermost if checks for the win condition, which is four matches in a row.
                    if (piecesInARow == board.getNumberN())
                    {
                        System.out.println(board.getNumberN() + " in a row horizontally.");
                        return true;
                    }
                }
                else
                {
                    piecesInARow = 1;
                    break;
                }
            }
            else
            {
                piecesInARow = 1;
                break;
            }
            
        } // END OF CHECK 2 (HORIZONTAL, RIGHT)
        
        for (int i = 1; i < board.getCols(); i++)
        {
            // Outer if-else checks if the move is legal, and breaks the loop if not.
            if ((row-i) > -1)
            {
                // Inner if-else checks if there is a piece that matches, and breaks the loop if not.
                if (board.checkBoard((row-i), col) == piece)
                {
                    piecesInARow++;
                    // Innermost if checks for the win condition, which is four matches in a row.
                    if (piecesInARow == board.getNumberN())
                    {
                        System.out.println(board.getNumberN() + " in a row vertically.");
                        return true;
                    }
                }
                else
                {
                    // piecesInARow does NOT get reset, because it has to check right.
                    break;
                }
            }
            else
            {
                // piecesInARow does NOT get reset, because it has to check right.
                break;
            }
            
        } // END OF CHECK 3 (VERTICAL, ABOVE)
        
        for (int i = 1; i < board.getRows(); i++)
        {
            // Outer if-else checks if the move is legal, and breaks the loop if not.
            if ((row+i) < board.getRows())
            {
                // Inner if-else checks if there is a piece that matches, and breaks the loop if not.
                if (board.checkBoard((row+i), col) == piece)
                {
                    piecesInARow++;
                    // Innermost if checks for the win condition, which is four matches in a row.
                    if (piecesInARow == board.getNumberN())
                    {
                        System.out.println(board.getNumberN() + " in a row vertically.");
                        return true;
                    }
                }
                else
                {
                    piecesInARow = 1;
                    break;
                }
            }
            else
            {
                piecesInARow = 1;
                break;
            }
            
        } // END OF CHECK 4 (VERTICAL, BELOW)
        
        for (int i = 1; i < board.getCols(); i++)
        {
            // Outer if-else checks if the move is legal, and breaks the loop if not.
            if (((row-i) > -1) && ((col-i) > -1))
            {
                // Inner if-else checks if there is a piece that matches, and breaks the loop if not.
                if (board.checkBoard((row-i), (col-i)) == piece)
                {
                    piecesInARow++;
                    // Innermost if checks for the win condition, which is four matches in a row.
                    if (piecesInARow == board.getNumberN())
                    {
                        System.out.println(board.getNumberN() + " in a row diagonally \\.");
                        return true;
                    }
                }
                else
                {
                    // piecesInARow does NOT get reset, because it has to check right.
                    break;
                }
            }
            else
            {
                // piecesInARow does NOT get reset, because it has to check right.
                break;
            }
            
        } // END OF CHECK 5 (DIAGONAL, ABOVE-AND-TO-THE-LEFT)
        
        for (int i = 1; i < board.getCols(); i++)
        {
            // Outer if-else checks if the move is legal, and breaks the loop if not.
            if (((row+i) < board.getRows()) && ((col+i) < board.getCols()))
            {
                // Inner if-else checks if there is a piece that matches, and breaks the loop if not.
                if (board.checkBoard((row+i), (col+i)) == piece)
                {
                    piecesInARow++;
                    // Innermost if checks for the win condition, which is four matches in a row.
                    if (piecesInARow == board.getNumberN())
                    {
                        System.out.println(board.getNumberN() + " in a row diagonally \\.");
                        return true;
                    }
                }
                else
                {
                    piecesInARow = 1;
                    break;
                }
            }
            else
            {
                piecesInARow = 1;
                break;
            }
            
        } // END OF CHECK 6 (DIAGONALLY, BELOW-AND-TO-THE-RIGHT)
        
        for (int i = 1; i < board.getCols(); i++)
        {
            // Outer if-else checks if the move is legal, and breaks the loop if not.
            if (((row+i) < board.getRows()) && ((col-i) > -1))
            {
                // Inner if-else checks if there is a piece that matches, and breaks the loop if not.
                if (board.checkBoard((row+i), (col-i)) == piece)
                {
                    piecesInARow++;
                    // Innermost if checks for the win condition, which is four matches in a row.
                    if (piecesInARow == board.getNumberN())
                    {
                        System.out.println(board.getNumberN() + " in a row diagonally /.");
                        return true;
                    }
                }
                else
                {
                    // piecesInARow does NOT get reset, because it has to check right.
                    break;
                }
            }
            else
            {
                // piecesInARow does NOT get reset, because it has to check right.
                break;
            }
            
        } // END OF CHECK 7 (DIAGONALLY, BELOW-AND-TO-THE-LEFT)
        
        for (int i = 1; i < board.getCols(); i++)
        {
            // Outer if-else checks if the move is legal, and breaks the loop if not.
            if (((row-i) > -1) && ((col+i) < board.getCols()))
            {
                // Inner if-else checks if there is a piece that matches, and breaks the loop if not.
                if (board.checkBoard((row-i), (col+i)) == piece)
                {
                    piecesInARow++;
                    // Innermost if checks for the win condition, which is four matches in a row.
                    if (piecesInARow == board.getNumberN())
                    {
                        System.out.println(board.getNumberN() + " in a row diagonally /.");
                        return true;
                    }
                }
                else
                {
                    piecesInARow = 1;
                    break;
                }
            }
            else
            {
                piecesInARow = 1;
                break;
            }
            
        } // END OF CHECK 8 (DIAGONALLY, BELOW-AND-TO-THE-LEFT)
        
        return false;
    } // end win result
    
    public boolean getTieResult()
    {
        int numOfBlanks = 0;
        for (int i = 0; i < board.getCols(); i++)
        {
            if (board.checkBoard(0, i) == '-')
            {
                numOfBlanks++;
            }  
        }
        
        if (numOfBlanks == 0)
        {
        return true;
        }
        return false;
    }
    
} // class
                
               