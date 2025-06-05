/**
 * What in the world is going on hereeeeeeee
 */
package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel  implements Runnable{
    // SCREEN SETTINGS
    // Size of Pixel for Sprites
    // Default size of Chars, Maps, Npcs
    final int originalTileSize = 16; // 16x16 tile
    // Scale to make the Sprites bigger on screen
    final int scale = 3;
    // The sprites are 16x16 but on the screen you can see it in 48x48
    public final int tileSize = originalTileSize * scale; // 48px
    // Configuration for the size of the screen
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //768px
    public final int screenHeight = tileSize * maxScreenRow; //576px

    // WORLD SETTINGS
    public final int maxWorldCol = 50; // The size of the column of the map
    public final int maxWorldRow = 50; // The size of the row of the map
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // FPS
    int FPS = 30;
    // For tile related things
    TileManager tileM = new TileManager(this);
    // For KeyMappings
    KeyHandler keyH = new KeyHandler();
    // Multi-Threading
    Thread gameThread;
    // Checks if collision is made e.g. bumped into a tree, object
    public CollisionChecker oChecker = new CollisionChecker(this);
    // To Display the objects such as Key object on the map
    public AssetSetter assetSetter = new AssetSetter(this);
    // Player related things such as sprites, player position on map
    public Player player = new Player(this, keyH);
    public SuperObject[] obj = new SuperObject[10]; // prepare 10 slots of objects

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // sets the height and width of the window
        this.setBackground(Color.black); // sets the default bg color; black
        this.setDoubleBuffered(true); //Sets whether this component should use a buffer to paint. If set to true, all the drawing from this component will be done in an offscreen painting buffer.
        this.addKeyListener(keyH); // sets which key to listen to
        this.setFocusable(true);
    }
    // Gets called from the main class before the game starts
    public void setupGame() {
        assetSetter.setObject();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start(); //Auto calls the run() method
    }

    @Override
    public void run() {
        double drawInterval =  1000000000.0 / FPS; // 0,01666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }
    // Updates Player position on map
    public void update() {
        player.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g; // Type casted Graphics  into Graphics2D
        // TILE
        tileM.draw(g2);
        // OBJECT Keys
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }
        //PLAYER
        player.draw(g2);
        g2.dispose();
    }
}
