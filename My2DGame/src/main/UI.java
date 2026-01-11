package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_Key;

public class UI {

	GamePanel gp;
	Font arial_40, arial_80B;
	BufferedImage keyImage;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 70);
		OBJ_Key key = new OBJ_Key();
		keyImage = key.image;
	}
	
	public void showMessage(String text) {
		
		message = text;
		messageOn = true;
	}
	
	
	public void draw(Graphics2D g2) {
		
		if (gameFinished == true) {
			
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			
			//Mensaje final
			String text;
			int textLength;
			int x;
			int y;
			
			text = "YOU FOUND THE TREASURE!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); //Retorna el tamaño del texto
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 - (gp.finalTile*3);
			g2.drawString(text,x,y);
			
			text = "Your time is: " + dFormat.format(playTime)+ "!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); //Retorna el tamaño del texto
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 + (gp.finalTile*4);
			g2.drawString(text,x,y);
			
			//FINISHED
			g2.setFont(arial_80B);
			g2.setColor(Color.yellow);
			text = "CONGRATULATIONS!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); //Retorna el tamaño del texto
			
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 + (gp.finalTile);
			g2.drawString(text,x,y);
			
			gp.gameThread = null;
			
		}else {
			
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			g2.drawImage(keyImage, gp.finalTile/2, gp.finalTile/2, gp.finalTile, gp.finalTile, null);
			g2.drawString(" x "+gp.player.hasKey, 74, 65);
			
			//TIME
			playTime +=(double)1/60;
			g2.drawString("Time:"+dFormat.format(playTime), gp.finalTile*11, 65 );
			
			//Message
			if(messageOn == true) {
				g2.setFont(g2.getFont().deriveFont(30F));
				g2.drawString(message, gp.finalTile/2, gp.finalTile*5);
				messageCounter++;
				
				if(messageCounter > 120) { //Time for thesss disaper message in screen
					messageCounter = 0;
					messageOn = false;
				}
				
			}
		}
		
	}
}
