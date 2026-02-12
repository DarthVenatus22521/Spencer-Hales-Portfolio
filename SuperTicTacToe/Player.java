package tictactoe;

import java.io.*;
import java.util.*;


public class Player {
    
    String m_name;
    char playerToken = 'E';

    void setName(String name) {
        m_name = name;
    }

    String getName() {
        return m_name;
    }

    void setToken(char c) {
        playerToken = c;
    }
    char getToken() {
        return playerToken;
    }
}
