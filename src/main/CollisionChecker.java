package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        System.out.println(entity.worldX);
        System.out.println(entity.worldY);
        // for the characters collision shape
        int entityLWX = entity.worldX + entity.solidArea.x; // 500 + 8 = 508
        int entityRWX = entity.worldX + entity.solidArea.x + entity.solidArea.width; // 500 + 8 + 32 = 540
        int entityTWY = entity.worldY + entity.solidArea.y; // 500 + 16 = 516
        int entityBWY = entity.worldY + entity.solidArea.y + entity.solidArea.height; // 500 + 16 + 32 = 548
        // for the world obj
        int entityLCol = entityLWX/gp.tileSize; // 10.48
        int entityRCol = entityRWX/gp.tileSize; // 11.25
        int entityTRow = entityTWY/gp.tileSize; // 10.75
        int entityBRow = entityBWY/gp.tileSize; // 11.42

        int tileNum1, tileNum2;

        // access the direction field from the Player class
        switch (entity.direction) {
            case "up" -> {
                entityTRow = (entityTWY - entity.speed) / gp.tileSize; //10.6
                tileNum1 = gp.tileM.mapTileNum[entityLCol][entityTRow];
                tileNum2 = gp.tileM.mapTileNum[entityRCol][entityTRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)
                    entity.collisionOn = true;
            }
            case "down" -> {
                entityBRow = (entityBWY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLCol][entityBRow];
                tileNum2 = gp.tileM.mapTileNum[entityRCol][entityBRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)
                    entity.collisionOn = true;
            }
            case "left" -> {
                entityLCol = (entityLWX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLCol][entityTRow];
                tileNum2 = gp.tileM.mapTileNum[entityLCol][entityBRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)
                    entity.collisionOn = true;
            }
            case "right" -> {
                entityRCol = (entityRWX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRCol][entityTRow];
                tileNum2 = gp.tileM.mapTileNum[entityRCol][entityBRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)
                    entity.collisionOn = true;
            }
        }
    }
}
