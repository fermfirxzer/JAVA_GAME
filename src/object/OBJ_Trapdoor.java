package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Trapdoor extends SuperObject {
	GamePanel gp;
	public OBJ_Trapdoor(GamePanel gp) {
		name="Trapdoor";
		try {
			image=ImageIO.read(getClass().getResourceAsStream("/object/tarpdoor.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision=true;
	}
}
