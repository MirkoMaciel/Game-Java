package main;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args){
		// 2DGAME
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Inicia la ventana con una X para cerrarla
		window.setResizable(false); //No se puede cambiar el tamaño de la ventana
		window.setTitle("2D GAME");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		window.pack(); //Hace que la ventana se ajuste al tamaño y diseño preferido del componente en este caso del GamePanel
		
		window.setLocationRelativeTo(null); //Se utiliza para posicionar el Frame relativamente sobre otro componente sin coordenadas especificas (Dinamico)
		window.setVisible(true); //Visibilidad de la ventana
		
		
		gamePanel.setupGame();
		gamePanel.startGameThread();
		
	}

}
