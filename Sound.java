import java.io.File;

import javax.sound.sampled.*;

public class Sound {
	static ReferenceVars refVars;
	
	Sound(ReferenceVars refVars) {
		this.refVars=refVars;
	}
	
	static void playBackgroundMusic() {
		File source=new File(refVars.path.concat("\\Game File\\backgroundMusic.wav"));
		
		try {
			AudioInputStream ais=AudioSystem.getAudioInputStream(source);
			refVars.mainbgm=AudioSystem.getClip();
			refVars.mainbgm.open(ais);
			setVolume(refVars.mainbgm,6,refVars.volume);
			refVars.mainbgm.loop(-1);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static void playBeep(ReferenceVars refVars) {
		File source=new File(refVars.path.concat("\\Game File\\beep.wav"));
		
		try {
			AudioInputStream ais=AudioSystem.getAudioInputStream(source);
			Clip clip=AudioSystem.getClip();
			clip.open(ais);
			setVolume(clip,-9,refVars.volume);
			clip.start();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static void blockDown(ReferenceVars refVars) {
		File source=new File(refVars.path.concat("\\Game File\\blockDown.wav"));
		
		try {
			AudioInputStream ais=AudioSystem.getAudioInputStream(source);
			Clip clip=AudioSystem.getClip();
			clip.open(ais);
			setVolume(clip,0,refVars.volume);
			clip.start();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static void lineClear(ReferenceVars refVars) {
		File source=new File(refVars.path.concat("\\Game File\\lineClear.wav"));
		
		try {
			AudioInputStream ais=AudioSystem.getAudioInputStream(source);
			Clip clip=AudioSystem.getClip();
			clip.open(ais);
			setVolume(clip,0,refVars.volume);
			clip.start();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static void tetris(ReferenceVars refVars) {
		File source=new File(refVars.path.concat("\\Game File\\tetris.wav"));
		
		try {
			AudioInputStream ais=AudioSystem.getAudioInputStream(source);
			Clip clip=AudioSystem.getClip();
			clip.open(ais);
			setVolume(clip,0,refVars.volume);
			clip.start();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static void setVolume(Clip clip,double maxVolume,double volume) {
		double minVolume=-40;
		
		FloatControl controlVolume=(FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
		
		if(volume==0) {
			controlVolume.setValue(-80);
		}
		else {
			controlVolume.setValue((float)(minVolume+(maxVolume-minVolume)*(volume/100)));
		}
	}
}













