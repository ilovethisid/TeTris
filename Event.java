

import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class Event {
	public class replay implements ActionListener {
		Window.GameoverWindow gameoverWindow;
		Window.InGameWindow inGameWindow;
		
		replay(Window.GameoverWindow gameoverWindow,Window.InGameWindow inGameWindow) {
			this.gameoverWindow=gameoverWindow;
			this.inGameWindow=inGameWindow;
		}
		
		public void actionPerformed(ActionEvent e) {
			gameoverWindow.gameoverWindow.dispose();
			inGameWindow.refVars.gameover=false;
		}
	}
	
	public class pause implements ActionListener {
		ReferenceVars refVars;
		Window.InGameWindow inGameWindow;
		JButton pauseButton;
		JFrame gameWindow;
		
		pause(ReferenceVars refVars,Window.InGameWindow inGameWindow) {
			this.refVars=refVars;
			this.inGameWindow=inGameWindow;
			pauseButton=inGameWindow.pauseButton;
			gameWindow=inGameWindow.gameWindow;
		}
		
		public void actionPerformed(ActionEvent e) {
			if(refVars.gamePause) {
				refVars.gamePause=false;
				pauseButton.setIcon(refVars.pauseBtnIcon);
			}
			else if(!refVars.gamePause) {
				refVars.gamePause=true;
				pauseButton.setIcon(refVars.playBtnIcon);
			}
			
			Sound.playBeep(refVars);
			gameWindow.requestFocus();
		}
	}
	
	public class settings implements ActionListener {
		ReferenceVars refVars;
		Window.InGameWindow inGameWindow;
		Window.SettingsWindow settingsWindow;
		JButton pauseButton;
		
		settings(ReferenceVars refVars,Window.InGameWindow inGameWindow) {
			this.refVars=refVars;
			this.inGameWindow=inGameWindow;
		}
		
		public void actionPerformed(ActionEvent e) {
			if(!refVars.gamePause) {
				implementAction();
			}
			else if(refVars.gamePause) {
				if(!refVars.settings) {
					implementAction();
				}
			}
			
			inGameWindow.gameWindow.requestFocus();
		}
		
		void implementAction() {
			inGameWindow.pauseButton.setIcon(refVars.playBtnIcon);
			Sound.playBeep(refVars);
			refVars.gamePause=true;
			refVars.settings=true;
			Window objWindow=new Window();
			settingsWindow=objWindow.new SettingsWindow(refVars,inGameWindow);
		}
		
		public class resume implements ActionListener {
			JDialog window=new JDialog();
			
			resume(JDialog window) {
				this.window=window;
			}
			
			public void actionPerformed(ActionEvent e) {
				refVars.gamePause=false;
				refVars.settings=false;
				window.dispose();
				inGameWindow.pauseButton.setIcon(refVars.pauseBtnIcon);
				
				Sound.playBeep(refVars);
				inGameWindow.gameWindow.requestFocus();
			}
		}
		
		
		public class exit implements WindowListener {
			
			exit() {
				
			}
			
			public void windowClosed(WindowEvent e) {
				
			}

			public void windowActivated(WindowEvent arg0) {
				
			}

			public void windowClosing(WindowEvent arg0) {
				refVars.settings=false;
				Sound.playBeep(refVars);
			}

			public void windowDeactivated(WindowEvent arg0) {
				
			}

			public void windowDeiconified(WindowEvent arg0) {
				
			}

			public void windowIconified(WindowEvent arg0) {
				
			}

			public void windowOpened(WindowEvent arg0) {
				
			}
		}
		
		public class regame implements ActionListener {
			Window.InGameWindow inGameWindow;
			Window.SettingsWindow settingsWindow;
			
			regame(Window.InGameWindow inGameWindow,Window.SettingsWindow settingsWindow) {
				this.inGameWindow=inGameWindow;
				this.settingsWindow=settingsWindow;
			}
			
			public void actionPerformed(ActionEvent e) {
				inGameWindow.pauseButton.setIcon(refVars.pauseBtnIcon);
				settingsWindow.window.dispose();
				refVars.gamePause=false;
				refVars.settings=false;
				refVars.regame=true;
			}
		}
		
		public class changeVolume implements ChangeListener {
			changeVolume() {
				
			}
			
			public void stateChanged(ChangeEvent e) {
				JSlider volume=new JSlider();
				volume=(JSlider)e.getSource();
				refVars.volume=volume.getValue();
				Sound.setVolume(refVars.mainbgm, 6, refVars.volume);
			}
		}
		
		public class exitGame implements ActionListener {
			exitGame() {
				
			}
			
			public void actionPerformed(ActionEvent e) {
				refVars.gameExit=true;
				System.exit(0);
			}
		}
	}
}











