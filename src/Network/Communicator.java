/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego Jacobs
 */
public class Communicator {
    private ServerConnection serverConnection;
    private DataOutputStream out;
    private DataInputStream in;
    private int size = 5000;
    BufferedReader _in;
    PrintWriter _out;

    public Communicator() {
        serverConnection = new ServerConnection("172.20.2.149", 5555);        
    }
    
    public void initiateCommunication(){
        serverConnection.setClient();
    }
    
    public void closeCommunication(){
        serverConnection.closeClient();
    }
    
    public void sendMessage(String message){
        try {
            out = new DataOutputStream((serverConnection.getOutputStream()));
            //out.writeInt(message.length());
            out.write(message.getBytes());        
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Communicator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String readResponse(){
        try {
            in = new DataInputStream(serverConnection.getInputStream());
            //size = in.readInt();
            byte[] request_bytes = new byte[size];
            in.read(request_bytes);
            String response = new String(request_bytes);
            return response;
        } catch (IOException ex) {
            Logger.getLogger(Communicator.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return "";
    }
}
