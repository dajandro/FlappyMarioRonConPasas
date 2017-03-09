/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.awt.Color;

/**
 *
 * @author Diego Jacobs
 */
public class Player {
    private int MatchId;
    private int PlayerId;
    private int X;
    private int Y;
    private int Score;
    private Color color;
    
    public Player(){
        
    }

    public Player(int MatchId, int PlayerId) {
        this.MatchId = MatchId;
        this.PlayerId = PlayerId;
    }

    public Player(int MatchId, int PlayerId, Color color) {
        this.MatchId = MatchId;
        this.PlayerId = PlayerId;
        this.color = color;
    }
    
    public Player(int MatchId, int PlayerId, int X, int Y) {
        this.MatchId = MatchId;
        this.PlayerId = PlayerId;
        this.X = X;
        this.Y = Y;
    }

    public Player(int MatchId, int PlayerId, int Score) {
        this.MatchId = MatchId;
        this.PlayerId = PlayerId;
        this.Score = Score;
    }
    
    public Player(int MatchId, int PlayerId, int X, int Y, int Score) {
        this.MatchId = MatchId;
        this.PlayerId = PlayerId;
        this.X = X;
        this.Y = Y;
        this.Score = Score;
    }
    
    public int getMatchId() {
        return MatchId;
    }

    public void setMatchId(int MatchId) {
        this.MatchId = MatchId;
    }

    public int getPlayerId() {
        return PlayerId;
    }

    public void setPlayerId(int PlayerId) {
        this.PlayerId = PlayerId;
    }

    public int getX() {
        return X;
    }

    public void setX(int X) {
        this.X = X;
    }

    public int getY() {
        return Y;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Player{" + "PlayerId=" + PlayerId + ", X=" + X + ", Y=" + Y + ", Score=" + Score + ", color=" + color + '}';
    }
}
