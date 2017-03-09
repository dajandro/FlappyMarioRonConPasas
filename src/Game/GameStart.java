/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Graphics.FlappyMario;
import Network.Communicator;
import Network.RequestParser;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego Jacobs
 */
public class GameStart {
    
    public void Start(){
        String separator = "~";
        String username = "";
        while(username == null || username.isEmpty())
            username = JOptionPane.showInputDialog(null, "Please enter your username: ","Flappy Mario Ron con Pasas", JOptionPane.INFORMATION_MESSAGE);
        
        Communicator comms = new Communicator();
        comms.initiateCommunication();
        
        // Mensaje de inicio
        String join = "000"+separator+username;
        comms.sendMessage(join);
        System.out.println("C: "+join);
        String response = comms.readResponse();
        System.out.println("S: "+response);
        comms.closeCommunication();
        
        RequestParser requestParser = new RequestParser(separator);
        requestParser.parse(response);
        
        FlappyMario flappyMario = null;
        flappyMario.flappyMario = new FlappyMario();
    }
}
