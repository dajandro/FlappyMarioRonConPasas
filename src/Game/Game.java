/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Graphics.FlappyMario;
import Models.Player;
import Network.Communicator;
import Network.RequestParser;
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
        String separator = "~";
        String username = "";
        while(username == null || username.isEmpty())
            username = JOptionPane.showInputDialog(null, "Please enter your ussername: ","Flappy Mario Ron con Pasas", JOptionPane.INFORMATION_MESSAGE);
        
        Communicator comms = new Communicator();
        comms.initiateCommunication();
        
        // Mensaje de inicio
        String join = "000"+separator+username;
        comms.sendMessage(join);
        System.out.println("C: "+join);
        String response = comms.readResponse();
        System.out.println("S: "+response);
        
        RequestParser requestParser = new RequestParser(separator);
        
        //Player with MatchId and PlayerId
        Player player = new Player(requestParser.getMatchId(response), requestParser.getPlayerId(response), requestParser.getColor(response));
        
        FlappyMario flappyMario = null;
        flappyMario.flappyMario = new FlappyMario(player);
    }
    
}
