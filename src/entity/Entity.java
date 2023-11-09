package entity;
import main.GamePanel;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;
import tile.TileManager;
import main.UtilityTool;
public abstract class Entity {
	public String name;
	public int x,y;
	public int speed=1;
	public BufferedImage up1,up2,up3,up4,down1,down2,down3,down4,right1,right2,
	right3,right4,left1,left2,left3,left4;
	public String direction;
	public int spriteCounter=0;
	public boolean collision=false;
	public boolean invincible=false;
	public int invincibleCounter=0;
	public int spriteNum =1;
	public Rectangle player;
	public int actionLockCounter=0;
	
	public int solidAreaDefaultX,solidAreaDefaultY;
	public boolean collisionOn=false;
	public Rectangle solidArea;
	public int life;
	UtilityTool uTool=new UtilityTool();GamePanel gp;
	public Entity(GamePanel gp) {
		this.gp=gp;
	}
	public abstract void update();
	public BufferedImage loadImageAndScale(String imageName, int width, int height) {
	    BufferedImage image = null;
	    try {
	        image = ImageIO.read(getClass().getResource(imageName + ".png"));
	        image = scaleImage(image, width, height);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return image;
	}
	public BufferedImage scaleImage(BufferedImage original, int width, int height) {
	    BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
	    Graphics2D g2 = scaledImage.createGraphics();
	    g2.drawImage(original, 0, 0, width, height, null);
	    g2.dispose();
	    return scaledImage;
	}
	
}
