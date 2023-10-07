package main;

import object.OBJ_Stone;
import object.OBJ_Trap;
import object.OBJ_Trapdoor;

public class AssetSetter {
	GamePanel gp;
	public AssetSetter(GamePanel gp) {
		this.gp=gp;
	}
	public void setObject() {
		gp.obj[0]=new OBJ_Trap(gp);
		gp.obj[0].worldX=13*gp.tileSize;
		gp.obj[0].worldY=2*gp.tileSize;
		gp.obj[1]=new OBJ_Trap(gp);
		gp.obj[1].worldX=14*gp.tileSize;
		gp.obj[1].worldY=2*gp.tileSize;
		
		gp.obj[2]=new OBJ_Trapdoor(gp);
		gp.obj[2].worldX=12*gp.tileSize;
		gp.obj[2].worldY=8*gp.tileSize;
		for(int i=3;i<10;i++) {
			for(int j=0;j<2;j++) {
				gp.obj[i]=new OBJ_Stone(gp);
				gp.obj[i].worldX=i*gp.tileSize;
				gp.obj[i].worldY=j*gp.tileSize;
			}
		}
		
	}
}
