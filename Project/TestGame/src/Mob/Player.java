package Mob;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Player {
	
	public int playerX, playerY, playerSpeed;
	public final int PLAYER_SIZE = 50;
	
	public void Init(int WINDOW_WIDTH, int WINDOW_HEIGHT) {
		playerX = WINDOW_WIDTH / 2;
        playerY = WINDOW_HEIGHT / 2;
        playerSpeed = 2;
	}
	
	 // Check if player collides with the obstacle
    public boolean isColliding(int obstacleX, int obstacleY, int OBSTACLE_WIDTH, int OBSTACLE_HEIGHT) {
        // Player rectangle
        Rectangle playerRect = new Rectangle(playerX, playerY, PLAYER_SIZE, PLAYER_SIZE);

        // Obstacle rectangle
        Rectangle obstacleRect = new Rectangle(obstacleX, obstacleY, OBSTACLE_WIDTH, OBSTACLE_HEIGHT);

        // Check for intersection between player and obstacle
        return playerRect.intersects(obstacleRect);
    }
    
    public void checkCollision(int obstacleX, int obstacleY, int OBSTACLE_WIDTH, int OBSTACLE_HEIGHT) {
    	 // Collision detection with the obstacle
        if (isColliding(obstacleX, obstacleY, OBSTACLE_WIDTH, OBSTACLE_HEIGHT)) {
            System.out.println("Hello World");
        }
    }
 

	public void Move(KeyEvent e) {
		
		 if (e.getKeyCode() == KeyEvent.VK_LEFT) {
         	playerX -= playerSpeed;
         }
         
         if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        	 playerX += playerSpeed;
         }
         
         if (e.getKeyCode() == KeyEvent.VK_UP) {
        	 playerY -= playerSpeed;
         }
         
         if (e.getKeyCode() == KeyEvent.VK_DOWN) {
        	 playerY += playerSpeed;
         }
		
	}
	
	public void draw(Graphics g, Image Sprite, int x, int y, int width, int height) {
        g.drawImage(Sprite, x, y, width, height, null);
    }
	
	public void Draw(Graphics g) {
		// Draw the player
        g.setColor(Color.RED);
        g.fillRect(playerX, playerY, PLAYER_SIZE, PLAYER_SIZE);
	}
}
