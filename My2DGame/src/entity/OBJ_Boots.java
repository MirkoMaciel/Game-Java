package entity;

import java.io.IOException;

import javax.imageio.ImageIO;

import object.SuperObject;

public class OBJ_Boots extends SuperObject {

	public OBJ_Boots() {
		name = "Boots";
		try {
			image = ImageIO.read(getClass().getResource("/objects/boots.png"));
					
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
