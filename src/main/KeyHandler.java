package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	public boolean upPressed,downPressed,leftPressed,rightPressed,EnterPressed,SHIFTPressed;
	GamePanel gp;
	
	public KeyHandler(GamePanel gp) {
		this.gp=gp;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int code=e.getKeyCode();
		//Title State
		if(gp.gameState==gp.titleState) {
			if(code==KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum<0) {
					gp.ui.commandNum=1;
				}
			}
			if(code==KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum>1) {
					gp.ui.commandNum=0;
				}
			}
			if(code==KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum==0) {
					gp.gameState=gp.playState;
				}
				if(gp.ui.commandNum==1) {
					System.exit(0);
				}
			}
		}
		//play State
		if(code==KeyEvent.VK_W) {
			upPressed=true;
		}
		if(code==KeyEvent.VK_S) {
			downPressed=true;
		}
		if(code==KeyEvent.VK_A) {
			leftPressed=true;
		}
		if(code==KeyEvent.VK_D) {
			rightPressed=true;
		}
		if(code==KeyEvent.VK_ENTER) {
			EnterPressed=true;
		}
		if(code==16) {
			SHIFTPressed=true;
			System.out.println("kuy");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code=e.getKeyCode();
		if(code==KeyEvent.VK_W) {
			upPressed=false;
		}
		if(code==KeyEvent.VK_S) {
			downPressed=false;
		}
		if(code==KeyEvent.VK_A) {
			leftPressed=false;
		}
		if(code==KeyEvent.VK_D) {
			rightPressed=false;
		}
		if(code==KeyEvent.VK_ENTER) {
			EnterPressed=false;
		}
		if(code==KeyEvent.VK_SHIFT) {
			EnterPressed=false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {	
	}

}
