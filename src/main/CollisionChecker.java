package main;

import entity.Entity;

public class CollisionChecker {
	GamePanel gp;
	public CollisionChecker(GamePanel gp) {
		this.gp=gp;
	}
	public void checkTile(Entity entity) {
		 int entityLeftWorldX= entity.x+entity.solidArea.x;
		 int entityRightWorldX =entity.x+entity.solidArea.width;
		 int entityTopWorldY =entity.y+entity.solidArea.y;
		 int entityBottomWorldY=entity.y+entity.solidArea.height;
		 
		 int entityLeftCol=entityLeftWorldX/gp.tileSize;
		 int entityRightCol=entityRightWorldX/gp.tileSize;
		 int entityTopRow=entityTopWorldY/gp.tileSize;
		 int entityBottomRow=entityBottomWorldY/gp.tileSize;
		 
		 int tileNum1,tileNum2;
		 switch(entity.direction) {
		 case "up":
			 entityTopRow=(entityTopWorldY -entity.speed)/gp.tileSize;
			 tileNum1=gp.tileM.maptileNum[entityLeftCol][entityTopRow];
			 tileNum2=gp.tileM.maptileNum[entityRightCol][entityTopRow];
			 if(gp.tileM.tile[tileNum1].collision==true||gp.tileM.tile[tileNum2].collision==true) {
				 entity.collisionOn=true;
			 }
			 break;
		 case "down":
			 entityBottomRow=(entityBottomWorldY +entity.speed)/gp.tileSize;
			 tileNum1=gp.tileM.maptileNum[entityLeftCol][entityBottomRow];
			 tileNum2=gp.tileM.maptileNum[entityRightCol][entityBottomRow];
			 if(gp.tileM.tile[tileNum1].collision==true||gp.tileM.tile[tileNum2].collision==true) {
				 entity.collisionOn=true;
			 }
			 break;
		 case "left":
			 entityLeftCol=(entityLeftWorldX -entity.speed)/gp.tileSize;
			 tileNum1=gp.tileM.maptileNum[entityLeftCol][entityTopRow];
			 tileNum2=gp.tileM.maptileNum[entityLeftCol][entityBottomRow];
			 if(gp.tileM.tile[tileNum1].collision==true||gp.tileM.tile[tileNum2].collision==true) {
				 entity.collisionOn=true;
			 }
			 break;
		 case "right":
			 entityRightCol=(entityRightWorldX+entity.speed)/gp.tileSize;
			 tileNum1=gp.tileM.maptileNum[entityRightCol][entityTopRow];
			 tileNum2=gp.tileM.maptileNum[entityRightCol][entityBottomRow];
			 if(gp.tileM.tile[tileNum1].collision==true||gp.tileM.tile[tileNum2].collision==true) {
				 entity.collisionOn=true;
			 }
			 break;
		 }
	}
	public int checkObject(Entity entity,boolean player) {
		int index=999;
		for(int i=0;i<gp.obj.length;i++) {
			if(gp.obj[i]!=null) {
				// get entity solid area position
				entity.solidArea.x=entity.x+entity.solidArea.x;
				entity.solidArea.y=entity.y+entity.solidArea.y;
				// get the object's solid area position
				gp.obj[i].solidArea.x=gp.obj[i].worldX+gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y=gp.obj[i].worldY+gp.obj[i].solidAreaDefaultY;
				
				switch(entity.direction) {
				case "up":
					entity.solidArea.y-=entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision==true) {
							entity.collisionOn=true;
						}
						if(player==true) {
							index=i;
						}
					}
					break;
				case "down":
					entity.solidArea.y+=entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision==true) {
							entity.collisionOn=true;
						}
						if(player==true) {
							index=i;
						}
					}
					break;
				case "left":
					entity.solidArea.x-=entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision==true) {
							entity.collisionOn=true;
						}
						if(player==true) {
							index=i;
						}
					}
					break;
				case "right":
					entity.solidArea.x+=entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision==true) {
							entity.collisionOn=true;
						}
						if(player==true) {
							index=i;
						}
			
					}
					break;
				}
			entity.solidArea.x=entity.solidAreaDefaultX;
			entity.solidArea.y=entity.solidAreaDefaultY;
			gp.obj[i].solidArea.x=gp.obj[i].solidAreaDefaultX;
			gp.obj[i].solidArea.y=gp.obj[i].solidAreaDefaultY;
		}
	}
		return index;
	
	}
}
