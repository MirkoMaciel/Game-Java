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
	
	//FPS
	int FPS = 60;
	
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

			
			//Draw
			double drawInterval = 1000000000 / FPS; //0.016666 seconds
			double nextDrawTime = System.nanoTime() + drawInterval;
			
			//1 : ACTUALIZAR
			update();
			
			//2 : DIBUJAR LA PANTALLA CON LA INFORMACIÓN ACTUALIZADA
			repaint();
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime / 1000000; //Conversión de nanosegundos a milisegundos
				if (remainingTime < 0 ) {
					remainingTime = 0;
				}
				Thread.sleep( (long )remainingTime);
				nextDrawTime += drawInterval;
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
	
	/*
	public void run () {
		
		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while (gameThread != null) {

		currentTime = System.nanoTime();
		
		delta += (currentTime - lastTime) / drawInterval;
		timer += (currentTime - lastTime);
		lastTime = currentTime;
		
		if(delta >= 1){
			update();
			repaint();
			delta--;
			drawCount++;
			
		}
		
		if (timer >= 1000000000){
			System.out.println("FPS:"+drawCount);
			darwCount = 0;
			timer =0;
		}
		
		}
	}*/
	
	public void update () {
		
		if (keyH.upPressed == true) {
			playerY -=  playerSpeed;
		}else if (keyH.downPressed == true) {
			playerY += playerSpeed;
		}else if (keyH.leftPressed == true) {
			playerX -=  playerSpeed;
		}else if(keyH.rightPressed == true) {
			playerX += playerSpeed;
		}
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g; //Defino la class Graphics2D para Grapichs g
		
		//Provee funciones más sofisticadas sobre funciones, matematica y geometrica para dibujar
		
		g2.setColor(Color.white); //Seteo el color para dibujar objetos
		
		g2.fillRect(playerX, playerY, finalTile, finalTile); //X,Y,WIDTH,HEIGTH parametros para dibujar, coordenadas X e Y y la altura y ancho del dibujo
		
		g2.dispose();
	}
	
}
