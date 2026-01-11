package main;

import entity.Entity;

public class CollisionChecker {

	GamePanel gp;

	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}

	public void checkTile(Entity entity) {

		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

		int entityLeftCol = entityLeftWorldX / gp.finalTile;
		int entityRightCol = entityRightWorldX / gp.finalTile;
		int entityTopRow = entityTopWorldY / gp.finalTile;
		int entityBottomRow = entityBottomWorldY / gp.finalTile;

		int tileNum1, tileNum2;

		switch (entity.direction) {
		case "up":
			// Hacia arriba
			entityTopRow = (entityTopWorldY - entity.speed) / gp.finalTile;
			tileNum1 = gp.tileM.mapTileNumber[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNumber[entityRightCol][entityTopRow];
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			// Hacia abajo
			entityBottomRow = (entityBottomWorldY + entity.speed) / gp.finalTile;
			tileNum1 = gp.tileM.mapTileNumber[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNumber[entityRightCol][entityBottomRow];
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			// Hacia izquierda
			entityLeftCol = (entityLeftWorldX - entity.speed) / gp.finalTile;
			tileNum1 = gp.tileM.mapTileNumber[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNumber[entityLeftCol][entityBottomRow];
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			// Hacia derecha
			entityRightCol = (entityRightWorldX + entity.speed) / gp.finalTile;
			tileNum1 = gp.tileM.mapTileNumber[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNumber[entityRightCol][entityBottomRow];
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;

		}
	}

	// Method for checkObject
	public int checkObject(Entity entity, boolean player) {
		int index = 999;

		for (int i = 0; i < gp.obj.length; i++) {

			if (gp.obj[i] != null) {

				// Tomo la posicion del area solida de la entidad
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;

				// Tomo el objeto solido

				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

				switch (entity.direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if (gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if (player == true) {
							index = i;
						}
					}
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if (gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if (player == true) {
							index = i;
						}

					}
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if (gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if (player == true) {
							index = i;
						}

					}
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if (gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if (player == true) {
							index = i;
						}

					}
					break;
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
			}
		}

		return index;
	}
}
