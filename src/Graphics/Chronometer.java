/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author DanielAlejandro
 */
public class Chronometer implements ActionListener {
    private int mins, segs;
    private Timer timer;
    
    public Chronometer(){
        this.mins = 0;
        this. segs = 0;
        this.timer = new Timer(1000, this);
    }   
    
    public void setMinutes(int m){
        this.mins = m;
    }
    
    public void setSeconds(int s){
        this.segs = s;
    }
    
    public void start(){
        this.timer.start();
    }
    
    public void stop(){
        this.timer.stop();
    }
    
    public void restart(){
        this.timer.restart();
    }
    
    public boolean isFinish(){
        return (segs==0 && mins==0);
    }
    
    public String toString(){
        return String.valueOf(this.mins) + ":" + String.valueOf(this.segs);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if (segs == 0 && mins == 0) {
            timer.stop();
        } else if (segs > 0) {
            segs--;
        } else if (mins > 0) {
            mins--;
            segs = 59;
        }
    }
}
