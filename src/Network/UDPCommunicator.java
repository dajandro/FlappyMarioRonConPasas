/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import Types.Action;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego Jacobs
 */
public class UDPCommunicator {
    private ServerUDPConnection serverUDPConnection;
    private byte[] sendData;
    private byte[] receiveData;
    private DatagramPacket sendPacket;
    private DatagramPacket receivePacket;
    private int size;
            
    public UDPCommunicator(){
        this.serverUDPConnection = new ServerUDPConnection("127.0.0.1", 9876);
        this.size = 1024;
    }
    
    public void initiateCommunication(){
        
    }
    
    public void closeCommunication(){
        this.serverUDPConnection.closeClient();
    }
    
    public void sendMessage(String message){
        this.sendData = message.getBytes();
        this.sendPacket = this.serverUDPConnection.NewPacket(sendData, Action.SEND);
        this.serverUDPConnection.Send(sendPacket);
    }
    
    public String readResponse(){
        this.receivePacket = this.serverUDPConnection.NewPacket(receiveData, Action.RECEIVE);
        String response = this.serverUDPConnection.Receive(this.receivePacket);
        return response;
    }
}
