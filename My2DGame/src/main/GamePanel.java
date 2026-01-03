package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable { //Clase que hereda al JPANEL 
	
	//Propiedad de la herencia, una subclase de JPANEL que contiene todas las funciones de la clase padre
	//GamePanel	funciona como una especie de pantalla de juego
	
	
	//CONFIGURACIÓN DE PANTALLA
	final int originalTileSize = 16; //16px * 16px tile
	final int scale = 3; //escala los tiles  **= 16x3 = 48
	final int finalTile = originalTileSize * scale; // Pixel escalado 
	final int maxScreenCol = 16; //tamaño de columna de azulejos
	final int maxScreenRow = 12; //tamaño de filas de azulejes
	//relación de 4x3
	final int screenWidth = finalTile * maxScreenCol; //Tamaño del ancho de la ventana 768px
	final int screenHeigth = finalTile * maxScreenRow; //Tamaño del alto de la ventana 576px
	
	
	//KeyPress
	KeyHandler keyH = new KeyHandler(); //Instancio la clase
	
	//Hilo
	Thread gameThread;
	
	//Seteo posicion personaje 
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	
	//CONSTRUCTOR
	
	public GamePanel () {
		
		this.setPreferredSize(new Dimension(screenWidth,screenHeigth)); //Set tamaño de la pantalla
		this.setBackground(Color.black); //color del panel
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);//Agrego al panel el controlador de teclas
		this.setFocusable(true); //Defino al panel como el foco al que puede recibir salidas de teclas
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	
	@Override
	public void run() {

		while (gameThread != null) {
			System.out.println("El juego está corriendo");
			
			//1 : ACTUALIZAR
			update();
			
			//2 : DIBUJAR LA PANTALLA CON LA INFORMACIÓN ACTUALIZADA
			repaint();
			
		}
		
	}
	
	public void update () {
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g; //Defino la class Graphics2D para Grapichs g
		
		//Provee funciones más sofisticadas sobre funciones, matematica y geometrica para dibujar
		
		g2.setColor(Color.white); //Seteo el color para dibujar objetos
		
		g2.fillRect(100, 100, finalTile, finalTile); //X,Y,WIDTH,HEIGTH parametros para dibujar, coordenadas X e Y y la altura y ancho del dibujo
		
		g2.dispose();
	}
	
}
