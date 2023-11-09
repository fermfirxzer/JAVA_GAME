package object;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.UtilityTool;
public abstract class SuperObject {
	public BufferedImage image,image1,image2,image3,image4;
	public String name;
	public boolean collision =false;
	public int worldX,worldY;
	public int hp;
	public Rectangle solidArea=new Rectangle(-30,-30,80,80);
	public int solidAreaDefaultX=0;
	public int solidAreaDefaultY=0;
	public int score;
	UtilityTool uTool =new UtilityTool();
	GamePanel gp;
	
	public void reduceHp(int hp) {
		this.hp-=hp;
	}
	public void draw(Graphics2D g2) {
		    g2.drawImage(image, this.worldX, this.worldY, gp.tileSize, gp.tileSize, null);
		    }
	
}
