package monster;
import main.UtilityTool;
import main.CollisionChecker.CollisionPosition;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;
import tile.TileManager;
import tile.tile;
public class GreenSlime extends Entity {
	GamePanel gp;
	UtilityTool uTool =new UtilityTool();
	public GreenSlime(GamePanel gp) {
		super(gp);
		this.gp=gp;
		name="Green slime";
		speed=1;
		solidArea=new Rectangle(50,50,5,5);
		solidAreaDefaultX=solidArea.x;
		solidAreaDefaultY=solidArea.y;
		actionLockCounter=0;
		direction="down";
		collision=false;
		getImage();
	}
	public void getImage() {
		right1=loadImageAndScale("/slime/run0",gp.tileSize,gp.tileSize);
		right2=loadImageAndScale("/slime/run1",gp.tileSize,gp.tileSize);
		right3=loadImageAndScale("/slime/run2",gp.tileSize,gp.tileSize);
		right4=loadImageAndScale("/slime/run3",gp.tileSize,gp.tileSize);
		left1=loadImageAndScale("/slime/runLeft0",gp.tileSize,gp.tileSize);
		left2=loadImageAndScale("/slime/runLeft1",gp.tileSize,gp.tileSize);
		left3=loadImageAndScale("/slime/runLeft2",gp.tileSize,gp.tileSize);
		left4=loadImageAndScale("/slime/runLeft3",gp.tileSize,gp.tileSize);
		up1=loadImageAndScale("/slime/run0",gp.tileSize,gp.tileSize);
		up2=loadImageAndScale("/slime/run1",gp.tileSize,gp.tileSize);
		up3=loadImageAndScale("/slime/run2",gp.tileSize,gp.tileSize);
		up4=loadImageAndScale("/slime/run3",gp.tileSize,gp.tileSize);
		down1=loadImageAndScale("/slime/runLeft0",gp.tileSize,gp.tileSize);
		down2=loadImageAndScale("/slime/runLeft1",gp.tileSize,gp.tileSize);
		down3=loadImageAndScale("/slime/runLeft2",gp.tileSize,gp.tileSize);
		down4=loadImageAndScale("/slime/runLeft3",gp.tileSize,gp.tileSize);
	}
	public void draw(Graphics2D g2) {
		BufferedImage image=null;
			
		    switch (direction) {
		        case "up":
		            if (spriteNum == 1) {
		                image = up1;
		            } else if (spriteNum == 2) {
		                image = up2;
		            } else if (spriteNum == 3) {
		                image = up3;
		            }
		            else if (spriteNum == 4) {
		                image = up4;
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
		            else if (spriteNum == 4) {
		                image = down4;
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
		            else if (spriteNum == 4) {
		                image = left4;
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
		            else if (spriteNum == 4) {
		                image = right4;
		            }    
		            break;
		    }
		
		    		
		    g2.drawImage(image, this.x, this.y, gp.tileSize, gp.tileSize, null);
		    
		    }
	public void setAction() {
	    actionLockCounter += 1;
	    if (actionLockCounter >= 60) {
	        Random random = new Random();
	        int i = random.nextInt(100) + 1;
	            if (i <= 25) {
	                direction = "up";
	            } else if (i <= 50) {
	                direction = "down";
	            } else if (i <= 75) {
	                direction = "left";
	            } else {
	                direction = "right";
	            }
	            
		        collisionOn = false;
		        if (collision && direction.equals("up")) {
		            direction = "right";
		        } else if (collision && direction.equals("down")) {
		            direction = "left";
		        } else if (collision && direction.equals("right")) {
		            direction = "down";
		        } else if (collision && direction.equals("left")) {
		            direction = "up";
		        }
	        actionLockCounter = 0;
	    }
	    
	    spriteCounter++;
	    
	    if (spriteCounter > 10) {
	        if (spriteNum == 1) {
	            spriteNum = 2;
	        } else if (spriteNum == 2) {
	            spriteNum = 3;
	        } else if (spriteNum == 3) {
	            spriteNum = 4;
	        } else if (spriteNum == 4) {
	            spriteNum = 1;
	        }
	        
	        spriteCounter = 0;
	    }
	}

	    // ตรวจสอบและคำนวณ spriteCounter ที่คุณต้องการที่นี่

	    

	public void update() {
	    setAction();
	    
	    if (!collisionOn) {
	        switch (direction) {
	            case "up":
	                y -= speed;
	                break;
	            case "down":
	                y += speed;
	                break;
	            case "left":
	                x -= speed;
	                break;
	            case "right":
	                x += speed;
	                break;
	        }
	    } else {
	    }
	    gp.Checker.checkTile(this);
	    if(collision==false) {
	    	gp.Checker.checkObject(this, false);
	    }
	    
	    
	}

}

