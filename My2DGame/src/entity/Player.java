package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;
	
	//where we draw player in the screen
	public final int screenX;
	public final int screenY;

	public Player(GamePanel gp, KeyHandler keyH) {

		this.gp = gp;
		this.keyH = keyH;
		//Values of possition the player
		screenX = gp.screenWidth/2 - (gp.finalTile/2);
		screenY = gp.screenHeight/2 - (gp.finalTile/2);
		//screenX =0; screenY=0;
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setSizeImage() {
		
	}

	public void getPlayerImage() { //imagenes del jugador
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/5.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/6.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/9.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/10.png"));
			rigth1 = ImageIO.read(getClass().getResourceAsStream("/player/13.png"));
			rigth2 = ImageIO.read(getClass().getResourceAsStream("/player/14.png"));
			down3= ImageIO.read(getClass().getResourceAsStream("/player/4.png"));
			up3 = ImageIO.read(getClass().getResourceAsStream("/player/8.png"));
			left3= ImageIO.read(getClass().getResourceAsStream("/player/12.png"));
			rigth3= ImageIO.read(getClass().getResourceAsStream("/player/16.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setDefaultValues() {
		worldX = gp.finalTile * 23;
		worldY = gp.finalTile * 21;
		speed = 4;
		direction = "down";
	}

	public void update() {
		
		if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			
		
		if (keyH.upPressed == true) {
			direction = "up";
			worldY -= speed;

		} else if (keyH.downPressed == true) {
			direction = "down";
			worldY += speed;
		} else if (keyH.leftPressed == true) {
			direction = "left";
			worldX -= speed;
		} else if (keyH.rightPressed == true) {
			direction = "rigth";
			worldX += speed;
		}
		
		spriteCounter++;
		
		if (spriteCounter > 8) {
			if (spriteNum == 1) {
				spriteNum = 2;
			}else if (spriteNum == 2) {
				spriteNum = 3;
			}else if (spriteNum == 3){
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		
		}else { //Si no se presiona ninguna tecla, el pj queda quieto
			
			spriteNum = 1;
		}
	}

	public void draw(Graphics2D g2) {
		// g2.setColor(Color.white); // Seteo el color para dibujar objetos

		// g2.fillRect(x, y, gp.finalTile, gp.finalTile); // X,Y,WIDTH,HEIGTH parametros
		// para dibujar, coordenadas X e Y y
		// la altura y ancho del dibujo

		BufferedImage image = null; //Inicializo image con null

		//DEFINICIÓN DE LA ANIMACIÓN
		switch (direction) { //Depende el valor de dirección 
		// Arriba
		case "up":
			if (spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}
			if(spriteNum == 3) {
				image = up3;
			}
			break;

		case "down":
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2;
			}
			if (spriteNum == 3) {
				image = down3;
			}
			break;

		case "left":
			if (spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}
			if (spriteNum == 3) {
				image = left3;
			}
			break;
			
		case "rigth":
			if (spriteNum == 1) {
				image = rigth1;
			}
			if (spriteNum == 2) {
				image = rigth2;
			}
			if(spriteNum ==3) {
				image = rigth3;
			}
			break;
		}
		
		g2.drawImage(image, screenX, screenY, gp.finalTile, gp.finalTile, null);

	}

}
