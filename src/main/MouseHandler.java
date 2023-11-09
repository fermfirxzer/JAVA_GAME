package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
	public boolean mouseclicked;
	GamePanel gp;
	MouseHandler(GamePanel gp){
		this.gp=gp;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	
	}
	public void mouseReleased(MouseEvent e) {	
		mouseclicked=false;
	}
	@Override
	public void mousePressed(MouseEvent e) {	
		mouseclicked=true;
	}


	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
	
}
