package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int speed;
    // Walking state
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    // Normal State
    public BufferedImage nFront1, nFront2, nBack1, nBack2, nSideL2, nSideR2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public String charState;
}
