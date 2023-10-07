package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	public int score=0;
	public Player(GamePanel gp,KeyHandler keyH) {
		this.gp=gp;
		this.keyH=keyH;
		solidArea=new Rectangle(8,16,32,32);
		solidAreaDefaultX=solidArea.x;
		solidAreaDefaultY=solidArea.y;
		setDefaultValues();
		direction="down";
		getPlayerImage();
	}
	public void setDefaultValues() {
		x=100;
		y=100;speed=4;
		//Player Status
		maxLife=4;
		life=maxLife;
	}
	public void getPlayerImage() {
			
		up1=setup("Lady_up_1");
		up2=setup("Lady_up_2");
		up3=setup("Lady_up_3");
		down1=setup("Lady_down_1");
		down2=setup("Lady_down_2");
		down3=setup("Lady_down_3");
		left1=setup("Lady_left_1");
		left2=setup("Lady_left_2");
		left3=setup("Lady_left_3");
		right1=setup("Lady_right_1");
		right2=setup("Lady_right_2");
		right3=setup("Lady_right_3");
		
		
	}
	public BufferedImage setup(String imageName) {
		UtilityTool uTool=new UtilityTool();
		BufferedImage Image=null;
		try{
			Image=ImageIO.read(getClass().getResource("/player/"+imageName+".png"));
			Image =uTool.scaleImage(Image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return Image;
	}
	public void update() {
		if(keyH.upPressed==true|| keyH.downPressed==true||
				keyH.leftPressed==true||keyH.rightPressed==true) {
		if(keyH.upPressed==true) {
			direction="up";
			
		}
		else if(keyH.downPressed==true) {
			direction="down";
		}
		else if(keyH.leftPressed==true) {
			direction="left";
			
		}
		else if(keyH.rightPressed==true) {
			direction="right";
			
		}
		//check tile collision
		collisionOn=false;
		gp.Checker.checkTile(this);
		//check Object collision
		int objIndex=gp.Checker.checkObject(this, true);
		pickUpObject(objIndex);
		//if collision is false player can't move
		if(collisionOn ==false) {
			switch(direction) {
			case"up":
				y-=speed;
				break;
			
			case "down":
				y+=speed;
				break;
			case "left":
				x-=speed;
				break;
			case "right":
				x+=speed;
				break;
			}
		}
		spriteCounter++;
		if(spriteCounter>10) {
			if(spriteNum==1) {
				spriteNum=2;
			}
			else if(spriteNum==2) {
				spriteNum=3;
			}
			else if(spriteNum==3) {
				spriteNum=1;
			}
			spriteCounter=0;
		}
		}
	}
	public void pickUpObject(int index) {
		if(index!=999) {
			String objectName=gp.obj[index].name;
			switch(objectName) {
			case "Stone":
				score+=1;
				gp.obj[index]=null;
				break;
			case "Trapdoor":
				gp.gameState=gp.dialogueState;
				break;
			case "Trap":
				life--;
				break;
			}
	}
	}
	public void draw(Graphics2D g2) {
	    BufferedImage image = null;
	    switch (direction) {
	        case "up":
	            if (spriteNum == 1) {
	                image = up1;
	            } else if (spriteNum == 2) {
	                image = up2;
	            } else if (spriteNum == 3) {
	                image = up3;
	            }
	            break;
	        case "down":
	            if (spriteNum == 1) {
	                image = down1;
	            } else if (spriteNum == 2) {
	                image = down2;
	            } else if (spriteNum == 3) {
	                image = down3;
	            }
	            break;
	        case "left":
	            if (spriteNum == 1) {
	                image = left1;
	            } else if (spriteNum == 2) {
	                image = left2;
	            } else if (spriteNum == 3) {
	                image = left3;
	            }
	            break;
	        case "right":
	            if (spriteNum == 1) {
	                image = right1;
	            } else if (spriteNum == 2) {
	                image = right2;
	            } else if (spriteNum == 3) {
	                image = right3;
	            }
	            break;
	    }
	    g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}

}
