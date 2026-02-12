package tictactoe;

import java.io.*;
import java.util.*;


public class TicTacToe {
    
    int boardSize = 9;
    Selection[] board = new Selection[boardSize];
    char winnerToken = 'E';
    boolean isQuit = false;

    void initialize() {
        for (int i = 0; i < boardSize; i++) {
                board[i] = new Selection();
                board[i].setDisplay(i + 1);
        }
    }

    void Display() {
        int location;
        for (int i = 0; i < Math.sqrt(boardSize); i++) {
            for (int j = 0; j < Math.sqrt(boardSize); j++) {
                location = j + (3*i);
                if (board[location].isEmpty()) {
                    System.out.print(board[location].getDisplay());
                }
                else {
                    System.out.print(board[location].SelectValue());
                }

                if (j < Math.sqrt(boardSize) - 1) {
                    System.out.print("   ");
                }
            }
            System.out.println("");
        }
    }

    void getDisplay(int i) {
        for (int j = 0; j < Math.sqrt(boardSize); j++) {
                int location = j + (3*i);
                if (board[location].isEmpty()) {
                    System.out.print(board[location].getDisplay());
                }
                else {
                    System.out.print(board[location].SelectValue());
                }

                if (j < Math.sqrt(boardSize) - 1) {
                    System.out.print("   ");
                }
            }
    }

    String getRow(int line) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < Math.sqrt(boardSize); j++) {
                int location = j + (3*line);
                if (board[location].isEmpty()) {
                    sb.append(board[location].getDisplay() + "    ");
                }
                else {
                    sb.append(board[location].SelectValue() + "    ");
                }
        }
        return sb.toString();
    }

    String getEmptyRow(int line) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < Math.sqrt(boardSize); j++) {
            if (line == 1) {
                if (j == 1) {
                    sb.append(getWinner() + "    ");
                }
                else {
                    sb.append("     ");
                }
            }
            else {
                sb.append("     ");
            }
        }
        return sb.toString();
    }

    int MakeChoice(Player player) {
        System.out.println("Please select an empty tile " + player.getName());
        int userSelect = GetValidInt();
        if (!isQuit) {
            board[userSelect].Select(player.getToken());
            checkWinner(player);
        }
        return userSelect;
    }

    void checkWinner(Player player) {
        if (board[0].SelectValue() == player.getToken() && board[1].SelectValue() == player.getToken() && board[2].SelectValue() == player.getToken()) {
           winnerToken = player.getToken();
        }
        else if (board[3].SelectValue() == player.getToken() && board[4].SelectValue() == player.getToken() && board[5].SelectValue() == player.getToken()) {
           winnerToken = player.getToken();
        }
        else if (board[6].SelectValue() == player.getToken() && board[7].SelectValue() == player.getToken() && board[8].SelectValue() == player.getToken()) {
           winnerToken = player.getToken();
        }
        else if (board[0].SelectValue() == player.getToken() && board[3].SelectValue() == player.getToken() && board[6].SelectValue() ==  player.getToken()) {
           winnerToken = player.getToken();
        }
        else if (board[1].SelectValue() == player.getToken() && board[4].SelectValue() == player.getToken() && board[7].SelectValue() == player.getToken()) {
           winnerToken = player.getToken();
        }
        else if (board[2].SelectValue() == player.getToken() && board[5].SelectValue() == player.getToken() && board[8].SelectValue() == player.getToken()) {
           winnerToken = player.getToken();
        }
        else if (board[0].SelectValue() == player.getToken() && board[4].SelectValue() == player.getToken() && board[8].SelectValue() == player.getToken()) {
           winnerToken = player.getToken();
        }
        else if (board[2].SelectValue() == player.getToken() && board[4].SelectValue() == player.getToken() && board[6].SelectValue() == player.getToken()) {
           winnerToken = player.getToken();
        }

        if (isTie() && !isWon()) {
            winnerToken = 'T';
        }
    }

    boolean isTie() {
        for (Selection s : board) {
            if (s.SelectValue() == 'E') {
                return false;
            }
        }
        return true;
    }

    char getWinner() {
        return winnerToken;
    }
    
    boolean isWon() {
        if (getWinner() == 'E') {
            return false;
        }
        return true;
    }

    int GetValidInt() {
        Display();
        int userInput = getInteger();
        while (!isEmpty(userInput)) {
            System.out.println("Please select a valid tile");
            Display();
            userInput = getInteger();
        }
        return (userInput - 1);
    }

    int getInteger() {
        int userInput = -1;
        String exitStatus;
        boolean canExit = false;
        Scanner userIn = new Scanner(System.in);
        while (!canExit) {
            if (userIn.hasNextInt()) {
                userInput = userIn.nextInt();
                canExit = true;
            }
            else {
                exitStatus = userIn.next();
                if (exitStatus.equals("exit") || exitStatus.equals("quit")) {
                    isQuit = true;
                    return 0;
                }
                System.out.println("Please input an integer that corresponds to a valid tile");
                //userIn.nextLine();
            }
        }

        return userInput;
    }

    boolean isEmpty(int userInt) {
        if (isQuit) {
            return true;
        }
        if (userInt < 1 || userInt > boardSize) {
            return false;
        }
        if (!getTile(userInt - 1).isEmpty()) {
            return false;
        }
        return true;
    }

    Selection getTile(int location) {
        return board[location];
    }

    char GetValidChar() {
        String userInput;
        Scanner userIn = new Scanner(System.in);
        userInput = userIn.nextLine();
        while (userInput.length() != 1 || userInput != "X" || userInput != "O") {
            System.out.println("Please input a valid character ('X' or 'O')");
            userInput = userIn.nextLine();
        }
        return userInput.charAt(0);
    }

    String getBoard(int line, int location) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < Math.sqrt(boardSize); j++) {
            if (line == 1) {
                if (j == 1) {
                    sb.append(location + "    ");
                }
                else {
                    sb.append("     ");
                }
            }
            else {
                sb.append("     ");
            }
        }
        return sb.toString();
    }

    void loadTile(int tile, char token) {
        if (token != 'E') {
            board[tile].Select(token);
        }
    }
}
