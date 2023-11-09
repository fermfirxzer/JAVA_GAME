package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.CollisionChecker.CollisionPosition;
import main.GamePanel;
import main.KeyHandler;
import main.MouseHandler;
import main.UtilityTool;
import tile.TileManager;
import tile.tile;

public class Player extends Entity {
	
	KeyHandler keyH;MouseHandler mouseH;TileManager tileM;
	public int score=0;public int minning=1;public int hitdelay=0;
	public Player(GamePanel gp,KeyHandler keyH,MouseHandler mouseH,TileManager tileM) {
		super(gp);
		this.keyH=keyH;
		this.mouseH=mouseH;
		this.tileM=tileM;
		solidArea=new Rectangle(30,30,32,56);
		solidAreaDefaultX=solidArea.x;
		solidAreaDefaultY=solidArea.y;
		setDefaultValues();
		
		getPlayerImage();
	}
	public void setDefaultValues() {
		x=150;
		y=150;
		speed=4;
		direction="down";
		life=4;
	}
	public void getPlayerImage() {
			
		up1=setup("/player/Lady_up_1");
		up2=setup("/player/Lady_up_2");
		up3=setup("/player/Lady_up_3");
		down1=setup("/player/Lady_down_1");
		down2=setup("/player/Lady_down_2");
		down3=setup("/player/Lady_down_3");
		left1=setup("/player/Lady_left_1");
		left2=setup("/player/Lady_left_2");
		left3=setup("/player/Lady_left_3");
		right1=setup("/player/Lady_right_1");
		right2=setup("/player/Lady_right_2");
		right3=setup("/player/Lady_right_3");
	}
public BufferedImage setup(String imageName) {
		BufferedImage Image=null;
		try{
			Image=ImageIO.read(getClass().getResource(imageName+".png"));
			Image =uTool.scaleImage(Image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return Image;
	}
public void update() {
	if(invincible==true) {
		speed=5;
		invincibleCounter+=1;
		if(invincibleCounter>120) {
			invincible=false;
			invincibleCounter=0;
			
		}
	}
		if(keyH.upPressed==true|| keyH.downPressed==true||
				keyH.leftPressed==true||keyH.rightPressed==true||keyH.EnterPressed) {
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
		//check monster collision
		boolean Hitmonster=gp.Checker.checkEntity(this);
		
		
		//check Object collision
		CollisionPosition objIndex = gp.Checker.checkObject(this, true);
		
		int row = objIndex.row;
		int col = objIndex.col;
		
		if(invincible==false) {
			pickUpObject(row,col);
			interactmonster(Hitmonster);
			row = objIndex.row;
			col = objIndex.col;
		}
		System.out.println(invincible);
		//check event
		//if collision is false player can't move
		if(collisionOn ==false&&keyH.EnterPressed==false) {
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
		else {
			speed=4;
		}
		//This invincible
		if(gp.floor==6) {
			gp.gameState=gp.EndGameState;
		}
	}
	
	public void pickUpObject(int row,int col) {
		if( gp.obj[row][col] != null&&invincible==false) {
			
			String objectName=gp.obj[row][col].name;
			int sum=gp.obj[row][col].score;
			boolean trapdoorActived=false;
			boolean tiledoorActived=false;
			if(objectName=="Stone"||objectName=="Copper"||objectName=="Coal"||objectName=="Iron"||objectName=="Gold") {
				if(mouseH.mouseclicked==true) {
					mouseH.mouseclicked = false;
					gp.obj[row][col].reduceHp(minning);
					if(gp.obj[row][col].hp<=0) {
						gp.obj[row][col]=null;
						tileM.maptileNum[col][row]=2;
						gp.playMusic(1);
						score+=sum;
						
					}
					else {
						gp.playMusic(0);
					}
				}
		}
			else if(objectName=="Trap"&&invincible==false&&invincibleCounter==0) {
				invincible=true;
				life-=1;
				
			}
			
			else if(objectName=="Potion") {
				
				if(keyH.EnterPressed) {
				life=4;
				gp.obj[row][col]=null;
				tileM.maptileNum[col][row]=2;
				}
			}
			else if(objectName=="Pickaxe") {
				if(keyH.EnterPressed) {
					minning=2;
					gp.obj[row][col]=null;
					tileM.maptileNum[col][row]=2;
					}
			}
			else if(objectName=="TileDoor"&&!tiledoorActived) {
				if(keyH.EnterPressed&&gp.floor>1) {
					try{
						Thread.sleep(100);
						
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
						x=1040;
						y=700;
						gp.floor-=1;
						tileM.changeFloor(gp.floor);
					
				}
			}
			else if(objectName=="Trapdoor"&&!trapdoorActived) {
				if(keyH.EnterPressed) {
					try {
			            Thread.sleep(100);
			        } catch (InterruptedException e) {
			            e.printStackTrace();
			        }
					
					x=150;y=150;
					gp.floor+=1;
					tileM.changeFloor(gp.floor);
					gp.playTime+=30;
					
				}
				
				
			}
	}
	}
	public void interactmonster(boolean hit) {
			if(hit&&invincible==false) {
				invincible=true;
				life-=1;
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
	    if(invincible==true) {
	    	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.3f));
	    }
	    g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	   
	   
	    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
	    g2.setFont(new Font("Arial",Font.PLAIN,26));
	    g2.setColor(Color.white);
	    g2.drawString("Invincible :" +invincibleCounter, 10, 400);
	    g2.drawString("floor :" +gp.floor, 10, 500);
	    g2.drawString("Minning :" +minning,10, 600);
	    g2.drawString("life :" +life,10, 700);
	    
	}

}
