package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
	
	public int hasKey=0;

	public Player(GamePanel gp, KeyHandler keyH) {

		this.gp = gp;
		this.keyH = keyH;
		//Values of possition the player
		screenX = gp.screenWidth/2 - (gp.finalTile/2);
		screenY = gp.screenHeight/2 - (gp.finalTile/2);
		solidArea = new Rectangle(8,16,32,32); //x ,y, width, heigth
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
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
			

		} else if (keyH.downPressed == true) {
			direction = "down";
			
		} else if (keyH.leftPressed == true) {
			direction = "left";
		
		} else if (keyH.rightPressed == true) {
			direction = "right";
			
		}
		
		//Check collision
		collisionOn = false;
		gp.cChecker.checkTile(this);
		
		//Check objetc collision
		int objIndex = gp.cChecker.checkObject(this, true);
		pickUpObject(objIndex);
		
		//if collision is false player can move
		if (collisionOn == false) {
			switch(direction) {
			case "up":
				worldY -= speed;
				break;
			case "down":
				worldY += speed;
				break;
			case "left":
				worldX -= speed;
				break;
			case "right":
				worldX += speed;
				break;	
			}
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
	
	
	public void pickUpObject (int i) {
		if (i != 999) {
			String objectName = gp.obj[i].name;
			switch(objectName) {
			case "Key":
				gp.playSE(1);
				hasKey++;
				gp.obj[i] = null;
				gp.ui.showMessage("You got a key!");
				//System.out.println("Key:"+hasKey);
				break;
			case "Door":
				if(hasKey > 0) {
					gp.obj[i] = null;
					gp.playSE(3);
					hasKey--;
					gp.ui.showMessage("You opened the door!");
					System.out.println("Key:"+hasKey);
				}
				else {
					gp.ui.showMessage("You need a key!");
				}
				break;
			case "Boots":
				int booteoSpeed = 5;
				speed += booteoSpeed;
				gp.playSE(2);
				gp.obj[i] = null;	
				gp.ui.showMessage("Speed UP!");
				// Creamos un hilo que "duerme" 5 segundos y luego quita el bonus
			    new Thread(() -> {
			        try {
			            Thread.sleep(5000); // 5000 milisegundos = 5 segundos
			            speed -= booteoSpeed;     // Volvemos a la velocidad normal
			            System.out.println("El efecto de las botas ha terminado");
			        } catch (InterruptedException e) {
			            e.printStackTrace();
			        }
			    }).start();
				break;
			case "Chest":
				gp.ui.gameFinished = true;
				gp.stopMusic();
				gp.playSE(4);
				break;
			}
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
			
		case "right":
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
