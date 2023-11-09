package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_Heart;
import object.SuperObject;

public class UI {
	 GamePanel gp;
	 Graphics2D g2;
	 Font arial_32,arial_40,arial_80B;
	 public boolean gameFinished=false;
	 public String message="";
	 public String currentDialogue="";
	 
	 BufferedImage heart_4,heart_3,heart_2,heart_1,heart_0;
	 DecimalFormat dFormat =new DecimalFormat ("#0.00");
	 public int commandNum=0;
	 public UI(GamePanel gp) {
		 this.gp=gp;
		 arial_32=new Font("Arial",Font.PLAIN,32);
		 arial_40=new Font("Arial",Font.PLAIN,40);
		 arial_80B=new Font("Arial",Font.BOLD,60);
		 
		 //CREATE HUD OBJECT
		 SuperObject heart=new OBJ_Heart(gp);
		 heart_4=heart.image4;
		 heart_3=heart.image3;
		 heart_2=heart.image2;
		 heart_1=heart.image1;
		 heart_0=heart.image;
	 }
	 public void draw(Graphics2D g2) {
		 	this.g2=g2;
		 	g2.setFont(arial_40);
		 	g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		 	g2.setColor(Color.white);
		 	if(gameFinished==true) {
		 		drawGameFinished();
		 	}
		 	else if(gp.gameState==gp.EndGameState) {
		 		drawEndGame();
		 	}
		 	else {
			 if(gp.gameState==gp.titleState) {
				 drawTitleScreen();
			 }
			 if(gp.gameState==gp.playState) {
				 drawPlayerLife();
				 drawUI();
			 }
			 
		 	}
		 
	 
	 }
	 public void drawTitleScreen() {
		 g2.setColor(new Color(0x0121e));
		 g2.fillRect(0,0,gp.screenWidth,gp.screenheight);
		 g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
		 String text="Minning";
		 int x=getXforCenteredText(text);
		 int y=gp.tileSize*2;
		 //Shadow
//		 g2.setColor(Color.blue);
//		 g2.drawString(text, x+3, y+3);
		 g2.setColor(Color.white);
		 g2.drawString(text,x,y);
		 // Character Image
		 x=gp.screenWidth/2-(gp.tileSize*2)/2;
		 y+=gp.tileSize*2;
		 g2.drawImage(gp.player.down1,x,y,gp.tileSize*2,gp.tileSize*2,null);
		 g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
		 
		 text="Start";
		 x=getXforCenteredText(text);
		 y+=gp.tileSize*4;
		 g2.drawString(text, x, y);
		 //ลูกศรเลื่อนข้างๆ
		 if(commandNum==0) {
			 g2.drawString(">", x-gp.tileSize, y);
		 }
		 text="Exit";
		 x=getXforCenteredText(text);
		 y+=gp.tileSize+10;
		 g2.drawString(text, x, y);
		 if(commandNum==1) {
			 g2.drawString(">", x-gp.tileSize, y);
		 }
	 }
	 public void drawEndGame() {
		 g2.setColor(new Color(0x0121e));
		 g2.fillRect(0,0,gp.screenWidth,gp.screenheight);
		 g2.setFont(arial_80B);
		 g2.setColor(Color.white);
		 String text;int textLength;int x;int y;
		 text="Congratulations";
		 textLength=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
		 x=gp.screenWidth/2-textLength/2;
		 y=gp.screenheight/2-(gp.tileSize*3);
		 g2.drawString(text, x, y);
		 
		 g2.setFont(arial_80B);
		 g2.setColor(Color.yellow);
		 text="Your Score : "+gp.player.score;
		 textLength=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
		 x=gp.screenWidth/2-textLength/2;
		 y=gp.screenheight/2+(gp.tileSize);
		 g2.drawString(text, x, y);
		 gp.gameThread=null;
	 }
	 public void drawGameFinished() {
		 g2.setFont(arial_80B);
		 g2.setColor(Color.white);
		 String text;int textLength;int x;int y;
		 text="End Game";
		 textLength=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
		 x=gp.screenWidth/2-textLength/2;
		 y=gp.screenheight/2-(gp.tileSize*3);
		 g2.drawString(text, x, y);
		 
		 g2.setFont(arial_80B);
		 g2.setColor(Color.yellow);
		 text="Your Score : "+gp.player.score;
		 textLength=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
		 x=gp.screenWidth/2-textLength/2;
		 y=gp.screenheight/2+(gp.tileSize);
		 g2.drawString(text, x, y);
		 gp.gameThread=null;
	 }
	 public void drawUI() {
		 g2.setFont(arial_32);
		 gp.playTime-=(double)1/60;
		 g2.drawString("Time : "+dFormat.format(gp.playTime),gp.tileSize*10,65);
		 g2.drawString("Score : "+gp.player.score,gp.tileSize*10,30);
		 if(gp.playTime<=0) {
			 gameFinished = true;
			
		 }
	 }
	 public void drawPlayerLife() {
		 int x=gp.tileSize/2;
		 int y=gp.tileSize/2;
		 switch(gp.player.life) {
		 case 0:
			 g2.drawImage(heart_0, x, y, null);
			 gameFinished=true;
			 break;
	 		case 1:
	 		g2.drawImage(heart_1, x, y, null);
	 		break;
		 case 2:
			 g2.drawImage(heart_2, x, y, null);
			 break;
	 	case 3:
	 		g2.drawImage(heart_3, x, y, null);
	 		break;
	 	case 4:
	 		g2.drawImage(heart_4, x, y, null);
	 		break;
	 }
	 
			 
		 
	 }
	public int getXforCenteredText(String text) {
		int length=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
		int x=gp.screenWidth/2-length/2;
		return x;
	}
	 
}
