package tictactoe;

import java.io.*;
import java.util.*;


public class SuperTicTacToe {
    int superSize = 9;
    TicTacToe[] superBoard = new TicTacToe[superSize];
    boolean isWinner = false;
    Player playerOne = new Player();
    Player playerTwo = new Player();
    int whoseTurn = 1;
    int curTile = -1;

    void Display() {
        System.out.println("");
        StringBuilder row0;
        StringBuilder row1;
        StringBuilder row2;
        int location;
        for (int i = 0; i < Math.sqrt(superSize); i++) {
            row0 = new StringBuilder();
            row1 = new StringBuilder();
            row2 = new StringBuilder();
            for (int j = 0; j < Math.sqrt(superSize); j++) {
                location = j + (3*i);
                if (superBoard[location].isWon()) {
                    row0.append(superBoard[location].getEmptyRow(0));
                    row1.append(superBoard[location].getEmptyRow(1));
                    row2.append(superBoard[location].getEmptyRow(2));
                }
                else {       
                    row0.append(superBoard[location].getRow(0));
                    row1.append(superBoard[location].getRow(1));
                    row2.append(superBoard[location].getRow(2));
                }
                if (j != Math.sqrt(superSize) - 1) {
                    row0.append("    ");
                    row1.append("    ");
                    row2.append("    ");
                }
            }
            System.out.println(row0.toString());
            System.out.println(row1.toString());
            System.out.println(row2.toString());
            System.out.println("");
            System.out.println("");
        }
    }

    void playGame(Player player1, Player player2) {
        playerOne = player1;
        playerTwo = player2;
        int turn = whoseTurn;
        int tile = curTile;
        if (tile == -1) {
            tile = GetValidInt(player1);
        }
        int playerTile = -1;
        while (!isWinner) {
            Display();
            System.out.println("Current board: " + (tile + 1));
            if (turn == 1) {
                tile = doTurn(tile, player1, turn);
                turn++;
            }
            else {
                tile = doTurn(tile, player2, turn);
                turn--;
            }
        }
    }

    int doTurn(int tile, Player player, int turn) {
        int playerTile = -1;
        playerTile = superBoard[tile].MakeChoice(player);
        checkWinner(player);
        if (playerTile == -1) {
            whoseTurn = turn;
            curTile = tile;
            quitProgram();
        }
        if (!isWinner) {
            if (!superBoard[playerTile].isWon()) {
                return playerTile;
            }
            else if (superBoard[playerTile].isWon()) {
                if (superBoard[tile].isWon() && !isTie()) {
                    if (player.equals(playerOne)) {                    
                        return GetValidInt(playerTwo);
                    }
                    return GetValidInt(playerOne);
                }
                else {
                    return tile;
                }
            }
        }
        return 0;
    }

    void checkWinner(Player player) {

        if (superBoard[0].getWinner() == player.getToken() && superBoard[1].getWinner() == player.getToken() && superBoard[2].getWinner() == player.getToken()) {
           isWinner = true;
        }
        else if (superBoard[3].getWinner() == player.getToken() && superBoard[4].getWinner() == player.getToken() && superBoard[5].getWinner() == player.getToken()) {
           isWinner = true;
        }
        else if (superBoard[6].getWinner() == player.getToken() && superBoard[7].getWinner() == player.getToken() && superBoard[8].getWinner() == player.getToken()) {
           isWinner = true;
        }
        else if (superBoard[0].getWinner() == player.getToken() && superBoard[3].getWinner() == player.getToken() && superBoard[6].getWinner() ==  player.getToken()) {
           isWinner = true;
        }
        else if (superBoard[1].getWinner() == player.getToken() && superBoard[4].getWinner() == player.getToken() && superBoard[7].getWinner() == player.getToken()) {
           isWinner = true;
        }
        else if (superBoard[2].getWinner() == player.getToken() && superBoard[5].getWinner() == player.getToken() && superBoard[8].getWinner() == player.getToken()) {
           isWinner = true;
        }
        else if (superBoard[0].getWinner() == player.getToken() && superBoard[4].getWinner() == player.getToken() && superBoard[8].getWinner() == player.getToken()) {
           isWinner = true;
        }
        else if (superBoard[2].getWinner() == player.getToken() && superBoard[4].getWinner() == player.getToken() && superBoard[6].getWinner() == player.getToken()) {
           isWinner = true;
        }

        if (isWinner) {
            Display();
            System.out.println(player.getName() + " is the winner!");
        }
        else if (isTie()) {
            Display();
            System.out.println("Nobody is the winner!");
            isWinner = true;
        }
    }

    boolean isTie() {
        for (TicTacToe t : superBoard) {
            if (t.getWinner() == 'E') {
                return false;
            }
        }
        return true;
    }

    void initialSuper() {
        for (int i = 0; i < superSize; i++) {
                superBoard[i] = new TicTacToe();
                superBoard[i].initialize();
        }
    }

    int GetValidInt(Player player) {
        System.out.println(player.getName() + " please select a board");
        SimpleDisplay();
        int userInput = getInteger();
        while (!isEmpty(userInput)) {
            System.out.println("Please select a valid tile");
            userInput = getInteger();
        }
        return userInput - 1;
    }

    int getInteger() {
        int userInput = -1;
        String exitStatus = "";
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
                    quitProgram();
                }
                System.out.println("Please input an integer that corresponds to a valid tile");
            }
        }

        return userInput;
    }

    boolean isEmpty(int userInt) {
        if (userInt < 1 || userInt > superSize) {
            return false;
        }
        if (superBoard[userInt - 1].isWon()) {
            return false;
        }
        return true;
    }

    void SimpleDisplay() {
        System.out.println("");
        StringBuilder row0;
        StringBuilder row1;
        StringBuilder row2;
        int location;
        for (int i = 0; i < Math.sqrt(superSize); i++) {
            row0 = new StringBuilder();
            row1 = new StringBuilder();
            row2 = new StringBuilder();
            for (int j = 0; j < Math.sqrt(superSize); j++) {
                location = j + (3*i); 
                if (!superBoard[location].isWon()) {
                    row0.append(superBoard[location].getBoard(0, location + 1));
                    row1.append(superBoard[location].getBoard(1, location + 1));
                    row2.append(superBoard[location].getBoard(2, location + 1));
                }
                else {       
                    row0.append(superBoard[location].getEmptyRow(0));
                    row1.append(superBoard[location].getEmptyRow(1));
                    row2.append(superBoard[location].getEmptyRow(2));
                }

                if (j != Math.sqrt(superSize) - 1) {
                    row0.append("     ");
                    row1.append("     ");
                    row2.append("     ");
                }
            }
            System.out.println(row0.toString());
            System.out.println(row1.toString());
            System.out.println(row2.toString());
            System.out.println("");
            System.out.println("");
        }
    }

    void quitProgram() {
        System.out.println("Now exiting game\nWould you like to save? (y/n)");
        boolean isSave = willSave();
        if (isSave) {
           saveGame(); 
        }
        System.exit(0);
    }

    boolean willSave() {
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

    void saveGame() {
        try {
            PrintWriter writer = new PrintWriter("saveFile.txt");
            writer.println(playerOne.getName() + "\n" + playerOne.getToken());
            writer.println(playerTwo.getName() + "\n" + playerTwo.getToken());
            writer.println(whoseTurn);
            writer.println(curTile);
            for (TicTacToe t : superBoard) {
                for (Selection s : t.board) {
                    writer.println(s.getDisplay() + " " + s.SelectValue());
                }
            }
            writer.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    void loadGame() {
        try {
            initialSuper();
            int tile = -1;
            int location = -1;
            String s;
            Scanner in = new Scanner(new File("saveFile.txt"));
            playerOne.setName(in.next());
            playerOne.setToken(in.next().charAt(0));
            playerTwo.setName(in.next());
            playerTwo.setToken(in.next().charAt(0));
            whoseTurn = in.nextInt();
            curTile = in.nextInt();
            while (in.hasNextInt()) {
                location = in.nextInt();
                if (location == 1) {
                    tile++;
                }
                s = in.next();
                superBoard[tile].loadTile((location - 1), s.charAt(0));
            }
            for (TicTacToe t : superBoard) {
                t.checkWinner(playerOne);
                t.checkWinner(playerTwo);
            }
            System.out.println(playerOne.getName() + " you are the X's");
            System.out.println(playerTwo.getName() + " you are the O's");
            playGame(playerOne, playerTwo);
        }
        catch(FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
