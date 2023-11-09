package main;

import entity.Entity;

public class CollisionChecker {
	GamePanel gp;CollisionPosition cp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp=gp;
		this.cp=new CollisionPosition(0, 0);
	}
	public class CollisionPosition {
	    public int row;
	    public int col;
	    public CollisionPosition(int row, int col) {
	        this.row = row;
	        this.col = col;
	    }
	}

	public void checkTile(Entity entity) {
	    int entityLeftWorldX = entity.x + entity.solidArea.x;
	    int entityRightWorldX = entity.x + entity.solidArea.width;
	    int entityTopWorldY = entity.y + entity.solidArea.y;
	    int entityBottomWorldY = entity.y + entity.solidArea.height;

	    int entityLeftCol = entityLeftWorldX / gp.tileSize;
	    int entityRightCol = entityRightWorldX / gp.tileSize;
	    int entityTopRow = entityTopWorldY / gp.tileSize;
	    int entityBottomRow = entityBottomWorldY / gp.tileSize;

	    int tileNum1 = 0, tileNum2 = 0;

	    if (entity.direction.equals("up")) {
	        int newTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
	        tileNum1 = gp.tileM.maptileNum[entityLeftCol][newTopRow];
	        tileNum2 = gp.tileM.maptileNum[entityRightCol][newTopRow];
	    } else if (entity.direction.equals("down")) {
	        int newBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
	        tileNum1 = gp.tileM.maptileNum[entityLeftCol][newBottomRow];
	        tileNum2 = gp.tileM.maptileNum[entityRightCol][newBottomRow];
	    } else if (entity.direction.equals("left")) {
	        int newLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
	        tileNum1 = gp.tileM.maptileNum[newLeftCol][entityTopRow];
	        tileNum2 = gp.tileM.maptileNum[newLeftCol][entityBottomRow];
	    } else if (entity.direction.equals("right")) {
	        int newRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
	        tileNum1 = gp.tileM.maptileNum[newRightCol][entityTopRow];
	        tileNum2 = gp.tileM.maptileNum[newRightCol][entityBottomRow];
	    }

	    if (gp.tileM.tile[tileNum1].collision && gp.tileM.tile[tileNum2].collision) {
	        entity.collisionOn = true;
	    } 
	}

	public CollisionPosition checkObject(Entity entity,boolean player) {
		
		for(int i=0;i<gp.obj.length;i++) {
			for(int j=0;j<gp.obj[0].length;j++) {
			
			if(gp.obj[i][j]!=null) {
				// get entity solid area position
				entity.solidArea.x=entity.x+entity.solidArea.x;
				entity.solidArea.y=entity.y+entity.solidArea.y;
				// get the object's solid area position
				gp.obj[i][j].solidArea.x=gp.obj[i][j].worldX+gp.obj[i][j].solidAreaDefaultX;
				gp.obj[i][j].solidArea.y=gp.obj[i][j].worldY+gp.obj[i][j].solidAreaDefaultY;
	
				switch(entity.direction) {
				case "up":entity.solidArea.y-=entity.speed;break;
				case "down":entity.solidArea.y+=entity.speed;break;
				case "left":entity.solidArea.x-=entity.speed;break;
				case "right":entity.solidArea.x+=entity.speed;break;
				}
				if(entity.solidArea.intersects(gp.obj[i][j].solidArea)) {
					if(gp.obj[i][j].collision==true) {
						entity.collisionOn=true;		
					}

					if(player==true) {
						cp.row=i;
						cp.col=j;
					}
				}
				
			entity.solidArea.x=entity.solidAreaDefaultX;
			entity.solidArea.y=entity.solidAreaDefaultY;
			gp.obj[i][j].solidArea.x=gp.obj[i][j].solidAreaDefaultX;
			gp.obj[i][j].solidArea.y=gp.obj[i][j].solidAreaDefaultY;
		}
	}
		}
		return cp;
	
	}
	//NPC or Monster
	public boolean checkEntity(Entity entity) {
		boolean hit=false;
		for(int i=0;i<gp.monster.length;i++) {
			for(int j=0;j<gp.monster[0].length;j++) {
				
			if(gp.monster[i][j]!=null) {
				// get entity solid area position
				entity.solidArea.x=entity.x+entity.solidArea.x;
				entity.solidArea.y=entity.y+entity.solidArea.y;
				// get the object's solid area position
				gp.monster[i][j].solidArea.x=gp.monster[i][j].x+gp.monster[i][j].solidArea.x;
				gp.monster[i][j].solidArea.y=gp.monster[i][j].y+gp.monster[i][j].solidArea.y;
				
				switch(entity.direction) {
				case "up":entity.solidArea.y-=entity.speed;break;
				case "down":entity.solidArea.y+=entity.speed;break;
				case "left":entity.solidArea.x-=entity.speed;break;
				case "right":entity.solidArea.x+=entity.speed;break;
				}
				if(entity.solidArea.intersects(gp.monster[i][j].solidArea)) {
					if(gp.monster[i][j]!=entity) {
						
						hit=true;
					}	
			}
			entity.solidArea.x=entity.solidAreaDefaultX;
			entity.solidArea.y=entity.solidAreaDefaultY;
			gp.monster[i][j].solidArea.x=gp.monster[i][j].solidAreaDefaultX;
			gp.monster[i][j].solidArea.y=gp.monster[i][j].solidAreaDefaultY;
			
		}
			}
		}
		
		return hit;
	}
	
	public boolean checkPlayer(Entity entity) {
		boolean contactPlayer=false;
		// get entity solid area position
		entity.solidArea.x=entity.x+entity.solidArea.x;
		entity.solidArea.y=entity.y+entity.solidArea.y;
		// get the object's solid area position
		gp.player.solidArea.x=gp.player.x+gp.player.solidAreaDefaultX;
		gp.player.solidArea.y=gp.player.y+gp.player.solidAreaDefaultY;
		
		switch(entity.direction) {
		case "up":
			entity.solidArea.y-=entity.speed;
			break;
		case "down":
			entity.solidArea.y+=entity.speed;
			break;
		case "left":
			entity.solidArea.x-=entity.speed;
			break;
		case "right":
			entity.solidArea.x+=entity.speed;		
			break;
		}
		if(entity.solidArea.intersects(gp.player.solidArea)) {
			
			contactPlayer=true;
			}
	entity.solidArea.x=entity.solidAreaDefaultX;
	entity.solidArea.y=entity.solidAreaDefaultY;
	gp.player.solidArea.x=gp.player.solidAreaDefaultX;
	gp.player.solidArea.y=gp.player.solidAreaDefaultY;
	return contactPlayer;
	}
}
