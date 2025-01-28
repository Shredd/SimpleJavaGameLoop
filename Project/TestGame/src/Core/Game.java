package Core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import Mob.*;

public class Game extends JPanel implements Runnable {

	private static final long serialVersionUID = -1230132242641428858L;
	private boolean running;
    private static String TITLE = "Game";
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;
    
    private final int TARGET_FPS = 60;
    private final int TARGET_TIME = 1000 / TARGET_FPS;
    private long lastTime, timer;
    public int frames;

    Player Player = new Player();
    
    public final int OBSTACLE_WIDTH = 25;
    public final int OBSTACLE_HEIGHT = 25;
    public int obstacleX, obstacleY;
    

    public Game() {
        Init();
        setObjects();
    }
    
    private void Init() {
    	this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        setKeyBase();
        Player.Init(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.running = false;
        this.frames = 0;
        this.timer = 0;
    }
    
    public void setObjects() {
    	// Define obstacle position
        this.obstacleX = 300;
        this.obstacleY = 200;
    }
    
    public void setKeyBase() {
    	this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                Player.Move(e);
            }
        });
    }

    public void start() {
        running = true;
        new Thread(this).start();
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        lastTime = System.nanoTime();
        while (running) {
            long now = System.nanoTime();
            long elapsedTime = now - lastTime;
            lastTime = now;

            timer += elapsedTime;
            frames++;

            // Update and render the game
            update();
            render();

            // Sleep to maintain the target FPS
            if (elapsedTime < TARGET_TIME) {
                try {
                    Thread.sleep((TARGET_TIME - elapsedTime) / 1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Update FPS in window title every second
            if (timer >= 1000000000) {
                System.out.println("FPS: " + frames);
                frames = 0;
                timer = 0;
            }
        }
    }

    private void update() {
        // Additional update logic if needed
    	Player.checkCollision(obstacleX, obstacleY, OBSTACLE_WIDTH, OBSTACLE_HEIGHT);
    }

    private void render() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Player.Draw(g);
        
        // Draw the obstacle
        g.setColor(Color.GREEN);
        g.fillRect(obstacleX, obstacleY, OBSTACLE_WIDTH, OBSTACLE_HEIGHT);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame(TITLE);
        Game gamePanel = new Game();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePanel);
        frame.pack();
        frame.setVisible(true);
        gamePanel.start();
    }
}

