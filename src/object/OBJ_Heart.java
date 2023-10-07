package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Heart extends SuperObject{
	GamePanel gp;
	public OBJ_Heart(GamePanel gp) {
		this.gp=gp;
		name="Heart";
		try {
			image=ImageIO.read(getClass().getResourceAsStream("/object/helth_bar_0.png"));
			image1=ImageIO.read(getClass().getResourceAsStream("/object/helth_bar_1.png"));
			image2=ImageIO.read(getClass().getResourceAsStream("/object/helth_bar_2.png"));
			image3=ImageIO.read(getClass().getResourceAsStream("/object/helth_bar_3.png"));
			image4=ImageIO.read(getClass().getResourceAsStream("/object/helth_bar_full.png"));
			image=uTool.scaleImage(image,gp.tileSize*2,gp.tileSize);
			image1=uTool.scaleImage(image1,gp.tileSize*2,gp.tileSize);
			image2=uTool.scaleImage(image2,gp.tileSize*2,gp.tileSize);
			image3=uTool.scaleImage(image3,gp.tileSize*2,gp.tileSize);
			image4=uTool.scaleImage(image4,gp.tileSize*2,gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
