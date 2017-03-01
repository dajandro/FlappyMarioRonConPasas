/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author DanielAlejandro
 */
public class FlappyMario implements ActionListener, MouseListener, KeyListener {
    
    public static FlappyMario flappyMario;
    public final int WIDTH = 800, HEIGHT = 900, PROGRESSBAR_HEIGHT = 100, MAX_SCORE = 50, PLAY_TIME_M = 1, PLAY_TIME_S = 0;
    public Render renderer;
    public Rectangle mario;
    public ArrayList<Rectangle> columns;
    public int ticks, yMotion, score;
    public boolean loose, started, gameover;
    public Random rand;
    private Timer timer;
    private Chronometer chrono;
    
    public FlappyMario()
    {
        JFrame jframe = new JFrame();
        timer = new Timer(20, this);
        chrono = new Chronometer();
        chrono.setMinutes(PLAY_TIME_M);
        chrono.setSeconds(PLAY_TIME_S);

        renderer = new Render();
        rand = new Random();

        jframe.add(renderer);
        jframe.setTitle("Flappy Mario");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(WIDTH, HEIGHT);
        jframe.addMouseListener(this);
        jframe.addKeyListener(this);
        jframe.setResizable(false);
        jframe.setVisible(true);

        mario = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10 - 100, 20, 20);
        columns = new ArrayList<Rectangle>();

        addColumn(true);
        addColumn(true);
        addColumn(true);
        addColumn(true);

        timer.start();        
    }   
    
    public void addColumn(boolean start)
    {
        int space = 250 + PROGRESSBAR_HEIGHT;
        int width = 100;
        int height = 50 + rand.nextInt(300);

        if (start)
        {
            columns.add(new Rectangle(WIDTH + width + columns.size() * 300, HEIGHT - height - 120 - PROGRESSBAR_HEIGHT, width, height));
            columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 300, 0, width, HEIGHT - height - space));
        }
        else
        {
            columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, HEIGHT - height - 120 - PROGRESSBAR_HEIGHT, width, height));
            columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, HEIGHT - height - space));
        }
    }
    
    public void paintColumn(Graphics g, Rectangle column)
    {
        g.setColor(Color.green.darker());
        g.fillRect(column.x, column.y, column.width, column.height);
    }
    
    public void jump()
    {
        if (loose)
        {
            mario = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10 - 100, 20, 20);
            columns.clear();
            yMotion = 0;
            score = 0;           

            addColumn(true);
            addColumn(true);
            addColumn(true);
            addColumn(true);

            loose = false;
        }

        if (gameover)
            chrono.stop();
        else if (!started)
        {
            started = true;
            chrono.start();
        }
        else if (!loose)
        {
            if (yMotion > 0)
                yMotion = 0;
            yMotion -= 10;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        int speed = 6;

        ticks++;

        if (started)
        {
            // Mueve pipes
            for (int i = 0; i < columns.size(); i++)
            {
                Rectangle column = columns.get(i);  
                column.x -= speed;
            }

            // Hacer que caiga
            if (ticks % 2 == 0 && yMotion < 15)
                yMotion += 2;

            // Quitar pipes que ya no esten dentro del rango de pantalla
            for (int i = 0; i < columns.size(); i++)
            {
                Rectangle column = columns.get(i);
                if (column.x + column.width < 0)
                {
                    columns.remove(column);
                    if (column.y == 0)
                        addColumn(false);
                }
            }

            // Aumentar posicion y de mario
            mario.y += yMotion;

            // Score
            for (Rectangle column : columns)
            {
                if (column.y == 0 && mario.x + mario.width / 2 > column.x + column.width / 2 - 10 && mario.x + mario.width / 2 < column.x + column.width / 2 + 10)
                    score++;
                if (column.intersects(mario))
                {
                    loose = true;
                    if (mario.x <= column.x)
                        mario.x = column.x - mario.width;
                    else
                    {
                        if (column.y != 0)
                            mario.y = column.y - mario.height;
                        else if (mario.y < column.height)
                            mario.y = column.height;
                    }
                }
            }
            
            // Validaciones
            if (mario.y > HEIGHT - 120 - PROGRESSBAR_HEIGHT || mario.y < 0)
                loose = true;
            if (mario.y + yMotion >= HEIGHT - 120 - PROGRESSBAR_HEIGHT)
            {
                mario.y = HEIGHT - 120 - PROGRESSBAR_HEIGHT - mario.height;
                loose = true;
            }
            if ( (score / 3) == MAX_SCORE)
                gameover = true;
            if (chrono.isFinish())
                gameover = true;
        }

        renderer.repaint();
    }
    
    public void repaint(Graphics g)
    {   
        // Sky area de juego
        g.setColor(Color.cyan);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        // Progress Bar background
        g.setColor(Color.gray);
        g.fillRect(0, HEIGHT - PROGRESSBAR_HEIGHT, WIDTH, PROGRESSBAR_HEIGHT);

        // Ground area de juego
        g.setColor(Color.orange);
        g.fillRect(0, HEIGHT - 120 - PROGRESSBAR_HEIGHT, WIDTH, 120);

        // Grass area de juego
        g.setColor(Color.green);
        g.fillRect(0, HEIGHT - 120 - PROGRESSBAR_HEIGHT, WIDTH, 20);

        // Mario
        g.setColor(Color.red);
        g.fillRect(mario.x, mario.y, mario.width, mario.height);
        
        // Progress bar
        g.setColor(Color.black);
        g.fillRect(0, HEIGHT - PROGRESSBAR_HEIGHT + 25, WIDTH, 15);

        for (Rectangle column : columns)
            paintColumn(g, column);

        g.setColor(Color.white);
        g.setFont(new Font("Arial", 1, 40));
        
        // Chronometer
        g.drawString(chrono.toString(), 30, 50);
        
        g.setFont(new Font("Arial", 1, 100));

        if (!started)
            g.drawString("Click to start!", 75, HEIGHT / 2 - 50 - PROGRESSBAR_HEIGHT);
        
        if (gameover)
        {
            g.drawString("Game over!", 75, HEIGHT / 2 - 50 - PROGRESSBAR_HEIGHT);
            timer.stop();
        }

        if (loose && !gameover)
        {
            g.drawString("Ouch!", 250, HEIGHT / 2 - 50 - PROGRESSBAR_HEIGHT);
            g.drawString("Click to restart", 30, HEIGHT / 2 + 50 - PROGRESSBAR_HEIGHT);            
        }

        if (!gameover && !loose && started)
        {
            // Sccore
            g.drawString(String.valueOf(score/3), WIDTH / 2 - 25, 100);
            // Progress bar
            g.setColor(Color.red);
            int x_pb = ((WIDTH - 10) / MAX_SCORE);
            g.fillRect((score/3) * x_pb, HEIGHT - PROGRESSBAR_HEIGHT + 22, mario.width, mario.height);
        }
    }    
    
    @Override
    public void mouseClicked(MouseEvent e)
    {
        jump();
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            jump();
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {}

    @Override
    public void mouseReleased(MouseEvent e)
    {}

    @Override
    public void mouseEntered(MouseEvent e)
    {}

    @Override
    public void mouseExited(MouseEvent e)
    {}

    @Override
    public void keyTyped(KeyEvent e)
    {}

    @Override
    public void keyPressed(KeyEvent e)
    {}
    
}
