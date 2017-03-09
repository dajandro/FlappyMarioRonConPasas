/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import static Graphics.FlappyMario.players;
import Models.Player;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author DanielAlejandro
 */
public class RequestParser {
    
    private ArrayList<Color> colors;
    private String separator;
    
    public RequestParser(String sep){
        this.separator = sep;
        this.colors = new ArrayList<>();
        this.colors.add(Color.red);
        this.colors.add(Color.yellow);
        this.colors.add(Color.pink);
        this.colors.add(Color.white);
    }
    public void parse(String request){
        request = request.trim();
        String[] package_parts = request.split(this.separator);
        players = new ArrayList<>();
        int MatchId = Integer.parseInt(package_parts[1]);
        for(int i = 2; i < package_parts.length; i++){
            Color color = colors.get(Integer.parseInt(package_parts[i]));
            int id = Integer.parseInt(package_parts[i]);
            players.add(new Player(MatchId, id, color));
        }
    }
    
    public Color getColor(String request){
        System.out.println(this.colors.toString());
        if (request.startsWith("001")){
            String[] package_parts = request.split(this.separator);
            switch(package_parts[package_parts.length - 1]){
                case "0": return this.colors.get(0);
                case "1": return this.colors.get(1);
                case "2": return this.colors.get(2);
            }
        }
        return null;
    }
    
    public int getPlayerId(String request){
        if (request.startsWith("001")){
            String[] package_parts = request.split(this.separator);
            return Integer.parseInt(package_parts[2]);
        }
        
        return 0;
    }
    
    public int getMatchId(String request){
        if (request.startsWith("001")){
            String[] package_parts = request.split(this.separator);
            return Integer.parseInt(package_parts[1]);
        }
        
        return 0;
    }
}
