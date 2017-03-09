/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import static Graphics.FlappyMario.players;
import Models.Player;
import Types.Request;
import java.util.ArrayList;

/**
 *
 * @author Diego Jacobs
 */
public class SendRequest implements Runnable{
    private Communicator communicator;
    private Request requestType;
    private Player player;
    private final String separator;
    private String response;
    
    public SendRequest(Request requestType, Player player){
        this.communicator = new Communicator();
        this.requestType = requestType;
        this.player = player;
        this.separator = "~";
        this.response = new String();
    }
    
    public void send() {
        this.communicator.initiateCommunication();
        
        if(this.requestType == Request.ACTION){
            String message = "010";
            message += separator + player.getMatchId();
            message += separator + player.getPlayerId();
            message += separator + player.getX();
            message += separator + player.getY();
            
            communicator.sendMessage(message);
            response = communicator.readResponse();
        }
        
        if(this.requestType == Request.END){
            String message = "100";
            message += separator + player.getMatchId();
            message += separator + player.getPlayerId();
            message += separator + player.getScore();
            
            communicator.sendMessage(message);
            response = communicator.readResponse();
        }
        
        this.communicator.closeCommunication();
    }
    
    @Override
    public void run() {
        this.communicator.initiateCommunication();
        
        if(this.requestType == Request.ACTION){
            String message = "010";
            message += separator + player.getMatchId();
            message += separator + player.getPlayerId();
            message += separator + player.getX();
            message += separator + player.getY();
            message += separator + player.getScore();
            
            communicator.sendMessage(message);
            response = communicator.readResponse();
            
            response = response.trim();
            String[] package_parts = response.split("~");
            
            for(int i=1; i < package_parts.length;){
                int id = Integer.parseInt(package_parts[i]);
                int x = Integer.parseInt(package_parts[i + 1]);
                int y = Integer.parseInt(package_parts[i + 2]);
                int score = Integer.parseInt(package_parts[i + 3]);
                for(Player player : players){
                    if(player.getPlayerId() == id){
                        player.setX(x);
                        player.setY(y);
                        player.setScore(score);
                        break;
                    }
                }
                i += 4;
            }
        }
        
        if(this.requestType == Request.END){
            String message = "100";
            message += separator + player.getMatchId();
            message += separator + player.getPlayerId();
            message += separator + player.getScore();
            
            communicator.sendMessage(message);
            response = communicator.readResponse();
        }
        
        this.communicator.closeCommunication();
    }

    public String getResponse() {
        return response;
    }
}
