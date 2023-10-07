package object;
import javax.imageio.ImageIO;

import main.GamePanel;

import java.io.IOException;
public class OBJ_Stone extends SuperObject {
	GamePanel gp;
	public OBJ_Stone(GamePanel gp) {
		name="Stone";
		try {
			image=ImageIO.read(getClass().getResourceAsStream("/object/Stone.png"));
			uTool.scaleImage(image,gp.tileSize,gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
