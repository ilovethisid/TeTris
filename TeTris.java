
import java.io.*;

import java.awt.*;

import javax.swing.*;


public class TeTris {
	static int NUM_FRAME=100;
	
	// main
	public static void main(String[] args) {
		
		// variables
		ReferenceVars refVars=new ReferenceVars();
		
		
		// create window
		Window objWindow=new Window();
		Window.InGameWindow inGameWindow=objWindow.new InGameWindow(refVars);
		
		
		// objects
		Clock clock=new Clock();
		
		
		// initialize block
		Block curBlock=new Block();
		Block nextBlock=new Block();
		
		
		// key listener
		KeyListener ingameKeyListener=new KeyListener(inGameWindow.grid,curBlock,nextBlock,refVars);
		inGameWindow.gameWindow.addKeyListener(ingameKeyListener);
		inGameWindow.gameWindow.requestFocus();
		
		
		// loop until game exit
		while(!refVars.gameExit) {
			if(!refVars.gameover) {
				resetGame(inGameWindow,curBlock,nextBlock);
				gamestart(refVars,curBlock,nextBlock,clock,inGameWindow);
			}
			clock.wait(0.1);
		}
		
		System.exit(0);
	}
	
	
	static void gamestart(ReferenceVars refVars,Block curBlock,Block nextBlock,Clock clock,
			Window.InGameWindow inGameWindow) {
		double waitTime;
		waitTime=1/refVars.speed;
		
		Sound sound=new Sound(refVars);
		
		// bgm
		sound.playBackgroundMusic();
		
		
		
		refVars.startTime=System.currentTimeMillis();
		
		while(!refVars.gameover&&!refVars.regame) {
			// game pause
			if(refVars.gamePause) {
				gamePause(refVars);
			}
			
			
			Block.fall(inGameWindow.grid,curBlock,nextBlock,refVars,inGameWindow);
			displayScore(inGameWindow.scoreText,refVars);
			displayNextBlock(nextBlock,inGameWindow.nextBlockGrid,inGameWindow.nextBlockPanel);
			displaySpeed(inGameWindow.speedLabel,refVars);
			clock.wait(waitTime);
		}
		
		if(refVars.regame) {
			refVars.regame=false;
			refVars.mainbgm.close();
		}
	}
	
	
	static void gamePause(ReferenceVars refVars) {
		Clock clock=new Clock();
		
		while(refVars.gamePause) {
			clock.wait(0.01);
			// wait until resume
		}
	}
	
	
	static void getHighscore(ReferenceVars refVars) {
		String strHighscore;
		
		try {
			if(refVars.highscoreFile.exists()) {
				refVars.highscoreFR = new FileReader(refVars.highscoreFile);
				BufferedReader highscoreBR=new BufferedReader(refVars.highscoreFR);
				
				strHighscore=highscoreBR.readLine();
				
				if(strHighscore.isEmpty()) {
					refVars.highscore=0;
				}
				else {
					refVars.highscore=Integer.parseInt(strHighscore);
				}
				
				refVars.highscoreFR.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	static void displayHighscore(Window.InGameWindow inGameWindow,ReferenceVars refVars) {
		inGameWindow.highscoreLabel.setText(String.format("%08d",refVars.highscore));
		inGameWindow.highscoreLabel.setFont(new Font("digital display tfb",Font.PLAIN,32));
	}
	
	
	static void displayScore(JLabel scoreText,ReferenceVars refVars) {
		scoreText.setOpaque(true);
		scoreText.setBackground(refVars.scoreColor);
		scoreText.setForeground(Color.darkGray);
		scoreText.setText(String.format("%08d",refVars.score));
		scoreText.setFont(new Font("digital display tfb",Font.PLAIN,40));
	}
	
	static void displayNextBlock(Block nextBlock,Grid[][] nextBlockGrid,JPanel nextBlockPanel) {
		if(nextBlock.size==4) {
			nextBlock.x=1;
			nextBlock.y=1;
		}
		else {
			nextBlock.x=2;
			nextBlock.y=2;
		}
		
		Grid.cleanGrid(nextBlockGrid);
		nextBlock.draw(nextBlockGrid);
	}
	
	static void displaySpeed(JLabel speedLabel,ReferenceVars refVars) {
		speedLabel.setText(String.format("%.1f blocks/s",refVars.speed));
		speedLabel.setSize(new Dimension(Window.InGameWindow.LEFTPANEL_WIDTH,50));
		speedLabel.setBackground(Color.white);
		speedLabel.setFont(new Font("digital display tfb",Font.PLAIN,32));
	}
	
	
	
	static void gameover(ReferenceVars refVars,Window.InGameWindow inGameWindow) {
		refVars.mainbgm.close();
		
		Window objWindow=new Window();
		Window.GameoverWindow gameoverWindow=objWindow.new GameoverWindow(refVars, inGameWindow);
	}
	
	
	static void saveHighscore(ReferenceVars refVars) {
		String highscore=Integer.toString(refVars.highscore);
		
		try {
			if(refVars.highscoreFile.exists()) {
				refVars.highscoreFile.delete();
				refVars.highscoreFile=new File(refVars.highscoreFilePath);
				refVars.highscoreFW = new FileWriter(refVars.highscoreFile,false);
				refVars.highscoreFW.write(highscore);
				refVars.highscoreFW.flush();
				refVars.highscoreFW.close();
				refVars.highscoreFile.setReadOnly();
			}
			else {
				refVars.highscoreFW = new FileWriter(refVars.highscoreFile,false);
				refVars.highscoreFW.write(highscore);
				refVars.highscoreFW.flush();
				refVars.highscoreFW.close();
				refVars.highscoreFile.setReadOnly();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	static void resetGame(Window.InGameWindow inGameWindow,Block curBlock,Block nextBlock) {
		ReferenceVars refVars=inGameWindow.refVars;
		
		Grid.cleanGrid(inGameWindow.grid);
		Grid.drawGrid(inGameWindow.grid);
		
		refVars.score=0;
		refVars.speed=2;
		
		
		// settings before game
		getHighscore(refVars);
		displayHighscore(inGameWindow,inGameWindow.refVars);
		
		// initialize block
		curBlock.change();
		nextBlock.change();
	}
	
	
	
	// check if the code is working
	// delete when finished
	static void check() {
		System.out.println("check");
	}
	
	// print variables to check
	static void print(int x) {
		System.out.println(x);
	}
	
	static void print(double x) {
		System.out.println(x);
	}
	
	static void print(boolean x) {
		System.out.println(x);
	}
	
	static void print(String str) {
		System.out.println(str);
	}
}















