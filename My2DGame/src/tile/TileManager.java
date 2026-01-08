package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNumber[][]; // matriz sin dimensión

	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10]; // 10 tipos de tiles
		mapTileNumber = new int[gp.maxWorldCol][gp.maxWorldRow]; // map dimensionado al tamaño de mi panel
		getTileImage();
		loadMap("/maps/world01.txt");
	}

	public void loadMap(String filePath) { // carga de mapeado

		try {
			InputStream is = getClass().getResourceAsStream(filePath); // Permite leer bit a bit los datos de
																		// un flujo de entrada
			BufferedReader br = new BufferedReader(new InputStreamReader(is)); // inputStreamReader toma los datos de
																				// los elementos que estan en binario y
																				// // los traduce a los simbolos
			// Buffered lee un bloque completo o grande de texto y lo guarda en memoria

			int col = 0;
			int row = 0;

			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

				String line = br.readLine(); // Leo y almaceno una sola line a de texto

				while (col < gp.maxWorldCol) {

					String number[] = line.split(" "); // Divido la cadena por un separador en este caso es el espacio

					int num = Integer.parseInt(number[col]); // Convierto el valor de string a entero

					mapTileNumber[col][row] = num;
					col++;
				}

				if (col == gp.maxWorldCol) { // Si el valor de col llega al tope del mapeo reseteo
					col = 0;
					row++; // Cambio a la fila siguiente

				}

			}
			br.close(); // Cierro la variable de lectura del archivo

		} catch (IOException e) {

		}

	}

	public void getTileImage() {

		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResource("/tiles/grass.png"));

			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResource("/tiles/wall.png"));
			//Collision
			tile[1].collision = true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResource("/tiles/water.png"));
			tile[2].collision = true;
			
			// tile[6] = new Tile();
			// tile[6].image = ImageIO.read(getClass().getResource("/tiles/eyes.png"));

			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResource("/tiles/earth.png"));

			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResource("/tiles/tree.png"));
			tile[4].collision = true;
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResource("/tiles/sand.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void draw(Graphics2D g2) {
		// Dibujo dentro del panel
		/*
		 * Posición de la cuadricula int col = 0; int row = 0; Posición del tile int x =
		 * 0; int y = 0;
		 */

		int worldCol = 0;
		int worldRow = 0;

		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

			int tileNum = mapTileNumber[worldCol][worldRow]; // if 0,0

			// this is possition on the map
			int worldX = worldCol * gp.finalTile; // 0
			int worldY = worldRow * gp.finalTile; // 0

			// and this is where on the screen we draw it
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;

			if (worldX + gp.finalTile > gp.player.worldX - gp.player.screenX
					&& worldX - gp.finalTile < gp.player.worldX + gp.player.screenX
					&& worldY + gp.finalTile > gp.player.worldY - gp.player.screenY
					&& worldY - gp.finalTile < gp.player.worldY + gp.player.screenY) {

				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.finalTile, gp.finalTile, null);

			}

			// Revisar
			if (gp.player.screenX == gp.maxWorldCol && gp.player.screenY == gp.maxWorldRow) {

			}

			worldCol++;

			if (worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}

	}

}
