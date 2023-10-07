package main;
import javax.swing.JPanel;

import entity.Player;
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
    public final int screeheight=tileSize*maxScreenRow;//576
    //World setting
    public final int maxWorldCol=50;
    public final int maxWorldRow=50;
    public final int worldWidth=tileSize*maxWorldCol;
    public final int worldHeight=tileSize*maxWorldRow;
    //fps
    int fps=60;
    //Game State
    public int gameState;
    public final int titleState=0;
    public final int playState=1;
    public final int pauseState=2;
    public final int dialogueState=3;
    KeyHandler keyH=new KeyHandler(this);
    Player player=new Player(this,keyH);
    Thread gameThread;
    public CollisionChecker Checker=new CollisionChecker(this);
    public SuperObject obj[]=new SuperObject[100];
    public UI ui=new UI(this);
    public EventHandler eHandler=new EventHandler(this);
    public AssetSetter aSetter=new AssetSetter(this);
 
    TileManager tileM=new TileManager(this);
    public GamePanel() {
    	this.setPreferredSize(new Dimension(screenWidth,screeheight));
    	this.setBackground(Color.gray);
    	this.setDoubleBuffered(true);
    	this.addKeyListener(keyH);
    	this.setFocusable(true);
    }
    public void setupGame(){
    	gameState=titleState;
    	aSetter.setObject();
    }
    public  void startgameThread() {
    	gameThread=new Thread(this);
    	gameThread.start();
    }
    
    public void run() {
    	double drawInterval=1000000000/fps;
    	double delta=0;long currentTime;int timer=0;int drawCount=0;
    	long lastTime=System.nanoTime();
    	while(gameThread!=null) {
    		currentTime=System.nanoTime();
    		delta+=(currentTime-lastTime)/drawInterval;
    		timer+=(currentTime-lastTime);
    		lastTime=currentTime;
    		if(delta>=1) {
    			update();
    			repaint();
    			delta--;
    			drawCount++;
    		}
    		if(timer>=1000000000) {
    			System.out.println("Fps :"+drawCount);
    			drawCount=0;
    			timer=0;
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
		//OBJECT
		for(int i=0;i<obj.length;i++) {
			if(obj[i]!=null) {
				obj[i].draw(g2, this);
			}
		}
		//Player
		player.draw(g2);
		ui.draw(g2);
		g2.dispose();
		}
	}
}
