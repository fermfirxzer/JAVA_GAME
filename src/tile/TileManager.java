package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;
import main.UtilityTool;

public class TileManager {
	GamePanel gp;Player player;
	public tile[] tile;
	public int maptileNum[][];
	public TileManager(GamePanel gp) {
		this.gp=gp;
		tile=new tile[100];
		maptileNum=new int[50][50];
		getTileImage();
		loadMap("/maps/map001.txt");
	}
	public void changeFloor(int floor) {
		for(int i=0;i<gp.obj.length;i++) {
			for(int j=0;j<gp.obj[i].length;j++) {
				gp.obj[i][j]=null;
			}
		}
		for(int i=0;i<gp.monster.length;i++) {
			for(int j=0;j<gp.monster[i].length;j++) {
				gp.monster[i][j]=null;
			}
		}
	    switch (floor) {
	        case 1:
	            loadMap("/maps/map001.txt");
	            break;
	        case 2:
	            loadMap("/maps/map002.txt");
	            break;
	        case 3:
	            loadMap("/maps/map003.txt");
	            break;
	        case 4:
	            loadMap("/maps/map004.txt");
	            break;
	        case 5:
	            loadMap("/maps/map005.txt");
	            break;
	    }
	}

	public void getTileImage() {
			setup(0,"wall",true);
			setup(1,"wall",true);
			setup(2,"Ground",false);
			setup(3,"Trap",false);
			setup(4,"Trapdoor",true);
			setup(5,"Stone_big",true);
			setup(6,"coal_big",true);
			setup(7,"copper_big",true);
			setup(8,"Iron_big",true);
			setup(9,"gold_big",true);
			setup(20,"Trap",false);
			setup(30,"Tile_Door",true);
			setup(31,"pickaxe",true);
			setup(32,"potion",true);
	}
	public void setup(int index,String imagePath,boolean collision) {
		UtilityTool uTool=new UtilityTool();
		try {
			
			tile[index]=new tile();
			tile[index].image=ImageIO.read(getClass().getResourceAsStream("/tiles/"+imagePath+".png"));
			tile[index].image=uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision=collision;
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void loadMap(String filePath) {
		try {
			
			InputStream is=getClass().getResourceAsStream(filePath);
			BufferedReader br= new BufferedReader(new InputStreamReader(is));
			int col=0;
			int row=0;
			for(int i=0;i<maptileNum.length;i++) {
				for(int j=0;j<maptileNum[0].length;j++) {
					maptileNum[i][j]=0;
				}
			}
			while(col<gp.maxScreenCol&&row<gp.maxScreenRow) {
				String line=br.readLine();
				while(col<gp.maxScreenCol) {
				
					String numbers[]=line.split(" ");
					int num =Integer.parseInt(numbers[col]);
					maptileNum[col][row]=num;
					col++;
				}
				if(col==gp.maxScreenCol) {
					col=0;
					row++;
				}
			}
			
			br.close();
			
			
		}catch(Exception e) {
		}
	}
	public void draw(Graphics2D g2) {
		
		int col =0;int row=0;int x=0;int y=0;
		
		while(col<gp.maxScreenCol&&row<gp.maxScreenRow) {
			int tileNum=maptileNum[col][row];
			if(tileNum==0) {
				g2.setColor(new Color(0x0121e));
				 g2.fillRect(x, y, gp.tileSize, gp.tileSize);
				 
			}
			else if(tileNum==2) {
				g2.drawImage(tile[tileNum].image, x, y,null);
				
			}
			else if(tileNum==5||tileNum==6||tileNum==7||tileNum==8||tileNum==9||tileNum==3||tileNum==4||tileNum==30||tileNum==31||tileNum==32) {
				if(gp.obj[row][col]==null){
					gp.aSetter.setObject(tileNum, x, y, col, row);
				}
					g2.drawImage(tile[2].image, x, y,null);
					g2.drawImage(tile[tileNum].image, x, y,null);
			}
			else if(tileNum==20) {
				if(gp.monster[row][col]==null) {
					gp.aSetter.setMonster(tileNum, x, y, col, row);
					
				}
				g2.drawImage(tile[2].image, x, y,null);
				
			}
			else {
				g2.drawImage(tile[2].image, x, y,null);
				g2.drawImage(tile[tileNum].image, x, y,null);
				
			}			
			col++;
			x+=gp.tileSize;
			if(col==gp.maxScreenCol) {
				col=0;
				x=0;
				row++;
				y+=gp.tileSize;
			}
		}
	}
}


