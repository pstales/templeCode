/*
 * Student: Paul Stales
 * Professor: Dr Yates / TA: Liang Lan
 * Date: November 20th, 2011
 * Homework 13 - Connect N - Make interacting classes that will play a game of connect N
 */

/*
 * The Board class represents the board.
 */

package Stales_ConnectN;
import java.util.Random;

public class Board 
{
    // INSTANCE VARIABLES
    private int numberN;
    private int rows;
    private int cols;
    private char[][] gameBoard;
    private int lastRow;
    private int lastCol;
    
    // CONSTRUCTOR - Sets all instance variables, and creates an empty board. 
    public Board(int N)
    {
        numberN = N;
        rows = (2*N-2);
        cols = (2*N-1);
        gameBoard = new char[rows][cols];
        
        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < cols; c++)
            {
                gameBoard[r][c] = '-';
            }
        }
    }
    
    // ACCESSOR METHODS - Getters
    
    public int getNumberN()
    {
        return numberN;
    }
    
    public int getRows()
    {
        return rows;
    }
    
    public int getCols()
    {
        return cols;
    }
    
    public char checkBoard(int row, int col)
    {
        return gameBoard[row][col];
    }
    
    public int checkRows(int col)
    {
        for (int r = rows-1; r >= 0; r--)
        {
            if (gameBoard[r][col] == '-')
            {
                //System.out.println("Returning value of row " + r);
                return r;
            }
        }
        return -1;
    }
    
    public void getBoardDisplay()
    {
        for (int r = 0; r < rows; r++)
        {
            
            for (int c = 0; c < cols; c++)
            {
                System.out.print(gameBoard[r][c]);
            }
            System.out.println("");
        }
    }
    
    public int getLastRow()
    {
        return lastRow;
    }
    
    public int getLastCol()
    {
        return lastCol;
    }
    
    // MUTATOR METHODS - Setters
    
    public void setBoardTurn(int row, int col, char piece)
    {
            gameBoard[row][col] = piece;
            getBoardDisplay();
            lastRow = row;
            lastCol = col;
    }
    
    } // class