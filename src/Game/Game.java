/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Graphics.FlappyMario;
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
        
        String username = "";
        while(username == null || username.isEmpty())
            username = JOptionPane.showInputDialog(null, "Please enter your ussername: ","Flappy Mario Ron con Pasas", JOptionPane.INFORMATION_MESSAGE);
        
        FlappyMario flappyMario = null;
        flappyMario.flappyMario = new FlappyMario();
    }
    
}
