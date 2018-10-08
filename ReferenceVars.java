import java.awt.*;
import java.io.*;
import java.net.URLDecoder;

import javax.sound.sampled.*;
import javax.swing.*;

public class ReferenceVars {
	// reference variables
	
	int highscore;
	final String path=getJarFolder();
	final String highscoreFilePath=path.concat("\\Game File\\highscore.txt");
	File highscoreFile=new File(highscoreFilePath);
	FileWriter highscoreFW;
	FileReader highscoreFR;
	
	ImageIcon pauseBtnIcon=createIcon(path.concat("\\Game File\\images\\pauseButton.png"),30,30);
	ImageIcon playBtnIcon=createIcon(path.concat("\\Game File\\images\\playButton.png"),30,30);
	ImageIcon settingBtnIcon=createIcon(path.concat("\\Game File\\images\\settingButton.png"),30,30);
	
	static Font defaultFont=new Font("digital display tfb",Font.PLAIN,32);
	
	long startTime;
	int totalSeconds=0;
	
	boolean gamePause=false;
	boolean settings=false;
	boolean gameover=false;
	boolean gameExit=false;
	boolean regame=false;
	
	int score;
	double speed;
	double maxSpeed=5;
	boolean keyPressed;
	
	final Color lightGreen=new Color(051,255,051);
	final Color scoreColor=Color.white;
	
	Clip mainbgm;
	double volume=70;
	
	Block shadow=new Block();
	
	ReferenceVars() {
		
	}
	
	int score_block(char type) {
		int score_easyBlock=1000;
		int score_hardBlock=2000;
		
		if(type=='Z'||type=='S') {
			return score_hardBlock;
		}
		else {
			return score_easyBlock;
		}
	}
	
	int score_line(short numLines) {
		switch(numLines) {
		case 0:
			return 0;
		case 1:
			return 10000;
		case 2:
			return 25000;
		case 3:
			return 45000;
		default:
			return 70000;
		}
	}
	
	ImageIcon createIcon(String link,int width,int height) {
		ImageIcon icon=new ImageIcon(link);
		
		Image originImg=icon.getImage();
		Image changedImg=originImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		icon=new ImageIcon(changedImg);
		
		return icon;
	}
	
	private String getJarFolder() {
	    // get name and path
	    String name = getClass().getName().replace('.', '/');
	    name = getClass().getResource("/" + name + ".class").toString();
	    // remove junk
	    name = name.substring(0, name.indexOf(".jar"));
	    name = name.substring(name.lastIndexOf(':')-1, name.lastIndexOf('/')+1).replace('%', ' ');
	    // remove escape characters
	    String s = "";
	    for (int k=0; k<name.length(); k++) {
	      s += name.charAt(k);
	      if (name.charAt(k) == ' ') k += 2;
	    }
	    // replace '/' with system separator char
	    return s.replace('/', File.separatorChar);
	  }
}









