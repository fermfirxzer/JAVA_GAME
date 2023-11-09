//package Notuse;
//
//import java.awt.Rectangle;
//
//import main.GamePanel;
//
//public class EventHandler {
//	GamePanel gp;
//	Rectangle eventRect;
//	int eventRectDefaultX,eventRectDefaultY;
//	public EventHandler(GamePanel gp) {
//		this.gp=gp;
//		eventRect =new Rectangle();
//		eventRect.x=23;
//		eventRect.y=23;
//		eventRect.width=2;
//		eventRect.height=2;
//		eventRectDefaultX=eventRect.x;
//		eventRectDefaultY=eventRect.y;
//	}
//	public void checkEvent() {
//		if(hit(14,2,"right")==true) {damageTrap();}
//		if(hit(12,8,"right")==true) {nextfloor();}
//	}
//	public boolean hit(int eventCol,int eventRow,String reqDirection) {
//		boolean hit=false;
//		gp.player.solidArea.x=gp.player.x+gp.player.solidArea.x;
//		gp.player.solidArea.y=gp.player.y+gp.player.solidArea.y;
//		eventRect.x=eventCol*gp.tileSize+eventRect.x;
//		eventRect.y=eventRow*gp.tileSize+eventRect.y;
//		
//		if(gp.player.solidArea.intersects(eventRect)) {
//			if(gp.player.direction.contentEquals(reqDirection)||reqDirection.contentEquals("any")) {
//				hit=true;
//			}
//		}
//		gp.player.solidArea.x=gp.player.solidAreaDefaultX;
//		gp.player.solidArea.y=gp.player.solidAreaDefaultY;
//		eventRect.x=eventRectDefaultX;
//		eventRect.y=eventRectDefaultY;
//		return hit;
//	}
//	public void damageTrap() {
//		gp.player.life-=1;
//	}
//	public void nextfloor() {
//		if(gp.keyH.EnterPressed==true) {
//			System.out.println("kuy");
//		}
//	}
//}
