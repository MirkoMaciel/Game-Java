package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObject {
	
	public BufferedImage image;
	public String name ;
	public boolean collision = false;
	public int worldX, worldY;
	public Rectangle solidArea = new Rectangle(0,0,48,48); //Tamaño del objeto donde es solido
	public int solidAreaDefaultX =0;
	public int solidAreaDefaultY =0;

	
	//Dibujo
	public void draw(Graphics2D g2, GamePanel gp) {
		//Se utiliza el mismo metodo que con los mosaicos del background
		
		// and this is where on the screen we draw it
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;

		if (worldX + gp.finalTile > gp.player.worldX - gp.player.screenX
				&& worldX - gp.finalTile < gp.player.worldX + gp.player.screenX
				&& worldY + gp.finalTile > gp.player.worldY - gp.player.screenY
				&& worldY - gp.finalTile < gp.player.worldY + gp.player.screenY) {


		}
		g2.drawImage(image, screenX, screenY, gp.finalTile, gp.finalTile, null);
	}
}
