package tictactoe;

import java.io.*;
import java.util.*;


public class Selection {
    
    boolean empty = true;
    char player = 'E';
    int display = -1;

    void setDisplay(int location) {
        display = location;
    }

    int getDisplay() {
        return display;
    }
    
    void Select(char c) {
        empty = false;
        player = c;
    }

    boolean isEmpty() {
        return empty;
    }

    char SelectValue() {
        return player;
    }
}
