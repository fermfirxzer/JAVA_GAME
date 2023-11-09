package main;

import monster.GreenSlime;
import object.OBJ_Gold;
import object.OBJ_Iron;
import object.OBJ_Pickaxe;
import object.OBJ_Potion;
import object.OBJ_Stone;
import object.OBJ_TileDoor;
import object.OBJ_Trap;
import object.OBJ_Trapdoor;
import object.OBJ_coal;
import object.OBJ_copper;

public class AssetSetter {
	GamePanel gp;
	public AssetSetter(GamePanel gp) {
		this.gp=gp;
		
	}
	public void setObject(int n,int x,int y,int col,int row) {
		
		switch(n) {
		case 3:gp.obj[row][col]=new OBJ_Trap();break;
		case 4:gp.obj[row][col]=new OBJ_Trapdoor();break;
		case 5:gp.obj[row][col]=new OBJ_Stone();break;
		case 6:gp.obj[row][col]=new OBJ_coal();break;
		case 7:gp.obj[row][col]=new OBJ_copper();break;
		case 8:gp.obj[row][col]=new OBJ_Iron();break;
		case 9:gp.obj[row][col]=new OBJ_Gold();break;		
		case 30:gp.obj[row][col]=new OBJ_TileDoor();break;
		case 31:gp.obj[row][col]=new OBJ_Pickaxe();break;
		case 32:gp.obj[row][col]=new OBJ_Potion();break;
		}
		gp.obj[row][col].worldX=x;
		gp.obj[row][col].worldY=y;
		gp.obj[row][col].collision=true;
		
		if(n==3) {
			gp.obj[row][col].collision=false;
		}
		
	}

	public void setMonster(int n,int x,int y,int row,int col) {
		if(gp.monster[row][col]==null) {
			
		switch(n) {
		case 20:gp.monster[row][col]=new GreenSlime(gp);break;
		}
		gp.monster[row][col].x=x;
		gp.monster[row][col].y=y;
		gp.monster[row][col].collisionOn=false;
		}
	}
}
	