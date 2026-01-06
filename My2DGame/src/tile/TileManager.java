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
	Tile[] tile;
	int mapTileNumber[][]; // matriz sin dimensión

	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10]; // 10 tipos de tiles
		mapTileNumber = new int[gp.maxScreenCol][gp.maxScreenRow]; // map dimensionado al tamaño de mi panel
		getTileImage();
		loadMap("/maps/map01.txt");
	}

	public void loadMap(String filePath) { // carga de mapeado

		try {
			InputStream is = getClass().getResourceAsStream(filePath); // Permite leer bit a bit los datos de
																				// un flujo de entrada
			BufferedReader br = new BufferedReader(new InputStreamReader(is)); // inputStreamReader toma los datos de
																				// los elementos que estan en binario y																// los traduce a los simbolos
			// Buffered lee un bloque completo o grande de texto y lo guarda en memoria

			int col = 0;
			int row = 0;

			while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
				
				String line = br.readLine(); //Leo y almaceno una sola line a de texto
				
				while (col < gp.maxScreenCol) {

					String number[] = line.split(" "); //Divido la cadena por un separador en este caso es el espacio
				
					int num = Integer.parseInt(number[col]); //Convierto el valor de string a entero
					
					mapTileNumber[col][row] = num;
					col++;			
				}
				
				if(col == gp.maxScreenCol) { //Si el valor de col llega al tope del mapeo reseteo
					col = 0;
					row++; //Cambio a la fila siguiente
					
				}

			}
			br.close(); //Cierro la variable de lectura del archivo

		} catch (IOException e) {

		}

	}

	public void getTileImage() {

		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResource("/tiles/grass.png"));

			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResource("/tiles/wall.png"));

			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResource("/tiles/water.png"));

			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResource("/tiles/eyes.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void draw(Graphics2D g2) {
		// Dibujo dentro del panel
		// Posición de la cuadricula
		int col = 0;
		int row = 0;
		// Posición del tile
		int x = 0;
		int y = 0;

		while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
			
			int tileNum = mapTileNumber[col][row];
			
			g2.drawImage(tile[tileNum].image, x, y, gp.finalTile, gp.finalTile, null);
			col++;
			x += gp.finalTile;

			if (col == gp.maxScreenCol) {
				col = 0;
				x = 0;
				row++;
				y += gp.finalTile;
			}
		}

	}

}
