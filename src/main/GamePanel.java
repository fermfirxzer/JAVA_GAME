package main;
import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import monster.GreenSlime;
import object.SuperObject;
import tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize=16;//16*16 title
    final int scale=5;
    public final int tileSize=originalTileSize*scale;
    public final int maxScreenCol=16;
    public final int maxScreenRow=12;
    public final int screenWidth=tileSize*maxScreenCol;//768
    public final int screenheight=tileSize*maxScreenRow;//576
    //World setting
    public final int maxWorldCol=50;
    public final int maxWorldRow=50;
    public double playTime;
    //Game State
    public int gameState;
    public final int titleState=0;
    public int floor =1;
    public final int playState=1;
    public final int EndGameState=2;
    Thread gameThread;
    KeyHandler keyH=new KeyHandler(this);
    MouseHandler mouseH=new MouseHandler(this);

    public CollisionChecker Checker=new CollisionChecker(this);
    public SuperObject obj[][];
    public UI ui=new UI(this);
    public AssetSetter aSetter=new AssetSetter(this);
    public UtilityTool ut=new UtilityTool();
    TileManager tileM=new TileManager(this);
    public GreenSlime[][] monster;
    Player player;
    sound sound=new sound();

    public GamePanel() {
    	this.setPreferredSize(new Dimension(screenWidth,screenheight));
    	this.setBackground(Color.gray);
    	this.addKeyListener(keyH);
    	this.addMouseListener(mouseH);
    	this.setFocusable(true);
    	gameThread=new Thread(this);
    	playTime=60;
    	player=new Player(this,keyH,mouseH,tileM);
    	monster=new GreenSlime[20][15];
    	obj=new SuperObject[20][15];
    }
    public void setupGame(){
    	gameState=titleState;

    }
    public  void startgameThread() {	
    	gameThread.start();
    }
    public void run() {
    	while(true&&gameThread!=null) {
    		update();
    		repaint();
    	try {
			Thread.sleep(10);		
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
    	}	
    	}
	public void update() {
		player.update();
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		
		//Title Screen
		if(gameState==titleState) {
			ui.draw(g2);
		}
		else {
		
		//Tile
		tileM.draw(g2);
		}
		//player
		player.draw(g2);
		
		for(int i=0;i<monster.length;i++) {
			for(int j=0;j<monster[i].length;j++) {
				if(monster[i][j]!=null) {
					monster[i][j].draw(g2);
					monster[i][j].update();
				}
			}
		}
		ui.draw(g2);
		g2.dispose();
	}
	public void playMusic(int i) {
		sound.setFile(i);
		sound.play();
	}
	public void stopMusic(int i) {
		sound.stop();
	}
}
