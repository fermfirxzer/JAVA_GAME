package object;
import javax.imageio.ImageIO;

import main.GamePanel;

import java.io.IOException;
public class OBJ_Trap extends SuperObject {
	GamePanel gp;
	public OBJ_Trap(GamePanel gp) {
		name="Trap";
		try {
			image=ImageIO.read(getClass().getResourceAsStream("/object/trap.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
