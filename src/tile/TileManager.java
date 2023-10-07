package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {
	GamePanel gp;
	public tile[] tile;
	public int maptileNum[][];
	public TileManager(GamePanel gp) {
		this.gp=gp;
		tile=new tile[50];
		maptileNum=new int[gp.maxScreenCol][gp.maxScreenRow];
		getTileImage();
		loadMap("/maps/map001.txt");
	}
	public void getTileImage() {
			
			setup(0,"Ground",false);
			setup(1,"wall",true);
//			tile[2]=new tile();
//			tile[2].image=ImageIO.read(getClass().getResource("/tiles/Stone.png"));
//			tile[2].collision=true;
		
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
//			if(tileNum==2) {
//				g2.drawImage(tile[0].image, x, y, gp.tileSize,gp.tileSize,null);
//				g2.drawImage(tile[tileNum].image, x, y, gp.tileSize,gp.tileSize,null);
//			}
//			else {
				g2.drawImage(tile[tileNum].image, x, y,null);
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
