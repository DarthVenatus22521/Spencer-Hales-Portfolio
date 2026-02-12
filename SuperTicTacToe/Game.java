package tictactoe;

import java.io.*;
import java.util.*;

public class Game {
    
    int superTicTacToe = 1;
    int ticTacToe = 2;
    
    void playGame(Player player1, Player player2) {
        boolean areDone = false;
        while (!areDone) {
            System.out.println(player1.getName() + " and " + player2.getName() + " what game would you like to play?");
            System.out.println("1. Super Tic Tac Toe");
            System.out.println("2. Tic Tac Toe");
            
            int game = getGame();
            
        // Super Tic Tac Toe
            if (game == superTicTacToe) {
                playSuperTicTacToe(player1, player2);
            }
        // Tic Tac Toe
            else if (game == ticTacToe) {
                playTicTacToe(player1, player2);
            }
            else {
                System.out.println("Error in Game");
            }
            System.out.println("Would you like to play another game? (y/n)");
            if (!PlayAnother()) {
                System.out.println("Thanks for playing!");
                areDone = true;
            }
        }
	}

    void playSuperTicTacToe(Player player1, Player player2) {
        SuperTicTacToe superBoard = new SuperTicTacToe();
        System.out.println("Please select an option: \n1. Play a new game\n2. Load a saved game");
        int option = getGame();
        if (option == 1) {
            superBoard.initialSuper();
            superBoard.playGame(player1, player2);
        }
        else if (option == 2) {
            superBoard.loadGame();
        }
    }

    void playTicTacToe(Player player1, Player player2) {
        TicTacToe board = new TicTacToe();
        int turn = 1;
        board.initialize();
        while (!board.isWon()) {
            if (turn == 1) {
                board.MakeChoice(player1);
                turn++;
            }
            else {
                board.MakeChoice(player2);
                turn--;
            }
        }
        System.out.println("");
        board.Display();
        System.out.println("");
        if (board.getWinner() == 'X') {
            System.out.println("Well played " + player1.getName());
        }
        else if (board.getWinner() == 'O') {
            System.out.println("Well played " + player2.getName());
        }
        else {
            System.out.println("You have tied");
        }
    }

    int getGame() {
        int game = -1;
        Scanner sc = new Scanner(System.in);

        while (game != superTicTacToe && game != ticTacToe) {
            if (sc.hasNextInt()) {
                game = sc.nextInt();
                if (game != superTicTacToe && game != ticTacToe) {
                    System.out.println("Please select 1 or 2");
                    sc.nextLine();
                }
            }
            else {
                System.out.println("Please select 1 or 2");
                sc.nextLine();
            }
        }
        return game;
    }

    boolean PlayAnother() {
        Scanner sc = new Scanner(System.in);
        String answer;
        boolean yn;
        while (true) {
          answer = sc.nextLine().trim().toLowerCase();
          if (answer.equals("y")) {
            yn = true;
            break;
          }
          else if (answer.equals("n")) {
            yn = false;
            break;
          }
          else {
             System.out.println("Sorry, I didn't catch that. Please answer (y/n)");
          }
        }
        return yn;
    }

    int getInteger() {
        int userInput = -1;
        boolean canExit = false;
        Scanner userIn = new Scanner(System.in);
        while (!canExit) {
            if (userIn.hasNextInt()) {
                userInput = userIn.nextInt();
                canExit = true;
            }
            else {
                System.out.println("Please input an integer that corresponds to a valid tile");
                userIn.nextLine();
            }
        }

        return userInput;
    }
}
