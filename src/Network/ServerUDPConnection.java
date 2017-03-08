/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import Types.Action;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego Jacobs
 */
public class ServerUDPConnection {
    private DatagramSocket ClientSocket;
    private InetAddress InetAddress;
    private int Port;
    private DatagramPacket Packet;
    
    public ServerUDPConnection(String inetAddress, int port){
        this.Port = port;
        try {
            this.InetAddress = InetAddress.getByName(inetAddress);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ServerUDPConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DatagramSocket getClientSocket() {
        return ClientSocket;
    }

    public void setClientSocket(DatagramSocket clientSocket) {
        this.ClientSocket = clientSocket;
    }

    public InetAddress getInetAddress() {
        return InetAddress;
    }

    public void setInetAddress(InetAddress InetAddress) {
        this.InetAddress = InetAddress;
    }

    public int getPort() {
        return Port;
    }

    public void setPort(int Port) {
        this.Port = Port;
    }
    
    public void Send(DatagramPacket sendPacket){
        try {
            this.ClientSocket.send(sendPacket);
        } catch (IOException ex) {
            Logger.getLogger(ServerUDPConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String Receive(DatagramPacket receivePacket){
        String response = new String();
        try {
            this.ClientSocket.receive(receivePacket);
            response = new String(receivePacket.getData());
        } catch (IOException ex) {
            Logger.getLogger(ServerUDPConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return response;
    }
    
    public DatagramPacket NewPacket(byte[] data, Action actionType){
        if(actionType == Action.SEND)
            this.Packet = new DatagramPacket(data, data.length, this.InetAddress, this.Port);
        else
            this.Packet = new DatagramPacket(data, data.length);
        
        return this.Packet;
    }
    
    public void closeClient(){
        this.ClientSocket.close();
    }
}
