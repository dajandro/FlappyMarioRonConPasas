/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

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
    
    public Color getColor(String request){
        if (request.startsWith("001")){
            String[] package_parts = request.split(this.separator);
            return this.colors.get(Integer.valueOf(package_parts[package_parts.length-1]));
        }
        return null;
    }
    
    public String parse(String request){
        return "";
    }
    
}
