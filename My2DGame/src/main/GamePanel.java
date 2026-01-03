package main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel{ //Clase que hereda al JPANEL 
	
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
	
	//CONSTRUCTOR
	
	public GamePanel () {
		
		this.setPreferredSize(new Dimension(screenWidth,screenHeigth)); //Set tamaño de la pantalla
		this.setBackground(Color.black); //color del panel
		this.setDoubleBuffered(true);
		
	}
	
}
