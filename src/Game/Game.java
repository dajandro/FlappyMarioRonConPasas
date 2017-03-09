/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Graphics.FlappyMario;
import static Graphics.FlappyMario.players;
import Models.Player;
import Network.Communicator;
import Network.RequestParser;
import Network.SendRequest;
import Types.Request;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author DanielAlejandro
 */
public class Game {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameStart start = new GameStart();
        start.Start();
    }
    
}
