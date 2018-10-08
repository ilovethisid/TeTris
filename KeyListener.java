import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class KeyListener extends KeyAdapter {
	// key event
	
	JFrame gameWindow;
	int score;
	Grid[][] grid;
	Block curBlock;
	Block nextBlock;
	ReferenceVars refVars;
	
	KeyListener(Grid[][] grid,Block curBlock,Block nextBlock,ReferenceVars refVars) {
		this.grid=grid;
		this.curBlock=curBlock;
		this.nextBlock=nextBlock;
		this.refVars=refVars;
	}
	
	public void keyPressed(KeyEvent e) {
		refVars.keyPressed=true;
		
		int keyCode=e.getKeyCode();
		Block shadow=refVars.shadow;
		
		if(!refVars.gamePause) {
			switch(keyCode) {
			case KeyEvent.VK_LEFT:
				// left arrow key
				
				curBlock.erase(grid);
				curBlock.eraseShadow(shadow,grid);
				curBlock.x--;
				// move curBlock to left
				
				
				// if below restore curBlock
				if(curBlock.outOfBound()||curBlock.checkCollision(grid)) {
					curBlock.x++;
				}
				
				curBlock.draw(grid);
				curBlock.refreshShadow(grid, refVars);
				
				break;
				
			case KeyEvent.VK_RIGHT:
				// right arrow key
				
				curBlock.erase(grid);
				curBlock.eraseShadow(shadow,grid);
				curBlock.x++;
				
				
				if(curBlock.outOfBound()||curBlock.checkCollision(grid)) {
					curBlock.x--;
				}
				
				curBlock.draw(grid);
				curBlock.refreshShadow(grid, refVars);
				
				break;
			
			case KeyEvent.VK_DOWN:
				// down arrow key
				
				curBlock.erase(grid);
				curBlock.eraseShadow(shadow,grid);
				curBlock.y++;
				
				if(curBlock.outOfBound()||curBlock.checkCollision(grid)) {
					curBlock.y--;
				}
				
				curBlock.draw(grid);
				
				break;
				
			case KeyEvent.VK_Z:
				// key 'Z'
				
				curBlock.erase(grid);
				curBlock.eraseShadow(shadow,grid);
				curBlock.rotateCounterClockwise();
				// rotate curBlock clockwise
				
				
				// if below restore curBlock
				if(curBlock.outOfBound()||curBlock.checkCollision(grid)) {
					curBlock.rotateClockwise();
				}
				
				curBlock.draw(grid);
				curBlock.refreshShadow(grid, refVars);
				curBlock.drawShadow(shadow, grid);
				
				break;
				
			case KeyEvent.VK_X:
				// key 'X'
				
				curBlock.erase(grid);
				curBlock.eraseShadow(shadow,grid);
				curBlock.rotateClockwise();
				
				if(curBlock.outOfBound()||curBlock.checkCollision(grid)) {
					curBlock.rotateCounterClockwise();
				}
				
				curBlock.draw(grid);
				curBlock.refreshShadow(grid, refVars);
				curBlock.drawShadow(shadow, grid);
				
				break;
			}
		}	
		
		if(keyCode==KeyEvent.VK_ESCAPE) {
			// key 'ESC'
			
			if(refVars.gamePause) {
				refVars.gamePause=false;
			}
			else {
				refVars.gamePause=true;
			}
		}
		
		Sound.playBeep(refVars);
		
		refVars.keyPressed=false;
	
	}
}
