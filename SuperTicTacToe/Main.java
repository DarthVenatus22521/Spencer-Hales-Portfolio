package tictactoe;

import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
        Player player1 = new Player();
        Player player2 = new Player();
        
        Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Tic Tac Toe");
		System.out.println("Player 1 please enter your name: ");
        player1.setName(sc.nextLine());
		System.out.println("Player 2 please enter your name: ");
        player2.setName(sc.nextLine());
        
        System.out.println("");
        System.out.println(player1.getName() + " you will be X's");
        System.out.println(player2.getName() + " you will be O's");
        System.out.println("");
        player1.setToken('X');
        player2.setToken('O');
        Game game = new Game();
        game.playGame(player1, player2);
    }
}

