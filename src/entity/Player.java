package entity;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GamePanel  gp;
    KeyHandler kh;

    public final int screenX;
    public final int screenY;

    private boolean isNeutral = false;
    public Player(GamePanel gp, KeyHandler kh) {
        this.gp = gp;
        this.kh = kh;

        // Half of the screen width and height
        // Keeps the Player at the center of the screen
        screenX =  (gp.screenWidth / 2)  - (gp.tileSize/2); // 360
        screenY = (gp.screenHeight / 2)  - (gp.tileSize/2); // 264

        // Collision shape for Player
        solidArea =     new Rectangle();
        solidArea.x =                 8;
        solidArea.y =                16;
        // We want to record the default values cause we're gonna change the values later
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width =            32;
        solidArea.height =           32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        // Starting position of the Player on the map
        worldX = (gp.tileSize * 23);
        worldY = (gp.tileSize * 21);
        speed =            10;
        direction =    "down";
        charState = direction;
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png")));
            //create a field for the normal state of the character, normal1, normal2
            nFront1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/normal1.png")));
            nFront2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/normal2.png")));
            nBack1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/back1.png")));
            nBack2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/back2.png")));
            nSideL2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/left2.png")));
            nSideR2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/right2.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (kh.upPressed || kh.downPressed || kh.leftPressed || kh.rightPressed) {
            isNeutral = false;
            if (kh.upPressed) {
                direction = "up";
            } else if (kh.downPressed) {
                direction = "down";
            } else if (kh.leftPressed) {
                direction = "left";
            } else {
                direction = "right";
            }
            // check tile collision
            collisionOn = false;
            gp.oChecker.checkTile(this);

            // IF COLLISION IF FALSE PLAYER CANT MOVE
            if (!collisionOn) {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            spriteCounter++;
            isNeutral = true;
            if (spriteCounter > 15) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;
        if(!isNeutral)
            switch (direction) {
                case "up" -> {
                    charState = direction;
                    image = (spriteNum == 1) ? up1 : up2;
                }
                case "down" -> {
                    charState = direction;
                    image = (spriteNum == 1) ? down1 : down2;
                }
                case "left" -> {
                    charState = direction;
                    image = (spriteNum == 1) ? left1 : left2;
                }
                case "right" -> {
                    charState = direction;
                    image = (spriteNum == 1) ? right1 : right2;
                }
            }
        else
            switch (charState) {
                case "up" ->     image = (spriteNum == 1) ? nBack1 : nBack2;
                case "down" -> image = (spriteNum == 1) ? nFront1 : nFront2;
                case "left" ->   image = (spriteNum == 1) ? left1 : nSideL2;
                case "right" -> image = (spriteNum == 1) ? right1 : nSideR2;
            }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

}
