
import java.awt.*;
import java.awt.Dialog.ModalityType;

import javax.swing.*;
import javax.swing.border.LineBorder;


public class Window {
	final Color skyBlue=new Color(153,153,255);
	final Color brown=new Color(102,51,0);
	
	public class InGameWindow {

		// constants
		final static int WINDOW_WIDTH=470;
		final static int WINDOW_HEIGHT=660;
		final static int LEFTPANEL_WIDTH=300;
		final static int RIGHTPANEL_WIDTH=150;
		final static int GRID_COLUMNS=10;
		final static int GRID_ROWS=18;
		final static int GROUND_DEPTH=2;
		
		// abstract objects
		Grid[][] grid=new Grid[GRID_ROWS][GRID_COLUMNS];
		Grid[][] nextBlockGrid=new Grid[4][4];
		ReferenceVars refVars;
		Event objEvent=new Event();
		
		
		// gui objects
		
		JFrame gameWindow=new JFrame("TeTris");
		
		JPanel leftPanel=new JPanel();
		JPanel rightPanel=new JPanel();
		
		// leftPanel
		JPanel scorePanel=new JPanel();
		JPanel blockPanel = new GradientPanel(skyBlue,Color.white).gradientPanel;
		JLabel scoreText=new JLabel();
		
		// rightPanel
		JPanel nextBlockPanel=new JPanel();
		JPanel speedPanel=new JPanel();
		JLabel speedLabel=new JLabel();
		JPanel highscorePanel=new JPanel();
		JLabel highscoreLabel=new JLabel();
		JPanel buttonPanel=new JPanel();
		JButton pauseButton=new JButton();
		JButton settingButton=new JButton();
		
		
		InGameWindow(ReferenceVars refVars) {
			this.refVars=refVars;
			
			
			// creating ingame window
			TeTris.displayScore(scoreText,refVars);
			Grid.setGrid(blockPanel,grid);
			setGameWindow();
			setLeftPanel();
			setRightPanel();
		}
		
		
		void setGameWindow() {
			// layout
			
			
			
			// set components
			gameWindow.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
			gameWindow.setLayout(new BorderLayout());
			gameWindow.getContentPane().setBackground(Color.white);
			gameWindow.setLocationRelativeTo(null);
			gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			gameWindow.requestFocus();
			
			
			// add components
			gameWindow.add(leftPanel,BorderLayout.WEST);
			gameWindow.add(rightPanel,BorderLayout.EAST);
			
			
			gameWindow.setVisible(true);
		}
		
		
		
		void setLeftPanel() {
			leftPanel.setPreferredSize(new Dimension(LEFTPANEL_WIDTH,WINDOW_HEIGHT));
			leftPanel.setLayout(new BorderLayout(0,0));
			
			blockPanel.setLayout(new GridLayout(GRID_ROWS+GROUND_DEPTH,GRID_COLUMNS));

			for(int i=0;i<GROUND_DEPTH;i++) {
				for(int j=0;j<grid[0].length;j++) {
					JPanel p=new JPanel();
					p.setBackground(brown);
					p.setBorder(new LineBorder(Color.black));
					blockPanel.add(p);
				}
			}
			
			scorePanel.setBackground(refVars.scoreColor);
			scorePanel.setBorder(new LineBorder(Color.black));
			
			leftPanel.add(scorePanel,BorderLayout.NORTH);
			leftPanel.add(blockPanel,BorderLayout.CENTER);
			
			scorePanel.add(scoreText);
		}
		
		
		
		void setRightPanel() {
			Color lightBlue=new Color(204,204,255);
			final int width=RIGHTPANEL_WIDTH-10;
			
			JLabel highscore=new JLabel();
			
			rightPanel.setLayout(new FlowLayout());
			rightPanel.setPreferredSize(new Dimension(width+5,WINDOW_HEIGHT));
			rightPanel.setBackground(lightBlue);
			rightPanel.setBorder(new LineBorder(Color.black));
			
			nextBlockPanel.setLayout(new GridLayout(4,4));
			nextBlockPanel.setPreferredSize(new Dimension(width,width));
			nextBlockPanel.setBackground(skyBlue);
			
			speedPanel.setPreferredSize(new Dimension(width,50));
			speedPanel.setBackground(skyBlue);
			speedPanel.setBorder(new LineBorder(Color.black));
			
			speedLabel.setForeground(Color.red);
			
			highscore.setFont(new Font("Tetroserbogia",Font.PLAIN,24));
			highscore.setText("Highscore");
			highscore.setBackground(skyBlue);
			highscore.setForeground(Color.yellow);
			
			highscorePanel.setPreferredSize(new Dimension(width,100));
			highscorePanel.setBackground(skyBlue);
			highscorePanel.setBorder(new LineBorder(Color.black));
			
			highscoreLabel.setForeground(Color.yellow);
			
			Grid.setGrid(nextBlockPanel,nextBlockGrid);
			
			buttonPanel.setPreferredSize(new Dimension(width,50));
			buttonPanel.setBorder(new LineBorder(Color.black));
			buttonPanel.setBackground(skyBlue);
			buttonPanel.setLayout(new GridLayout(1,3));
			
			pauseButton.addActionListener(objEvent.new pause(refVars,this));
			pauseButton.setIcon(refVars.pauseBtnIcon);
			pauseButton.setBorderPainted(false);
			pauseButton.setContentAreaFilled(false);
			pauseButton.setFocusPainted(false);
			
			settingButton.addActionListener(objEvent.new settings(refVars,this));
			settingButton.setIcon(refVars.settingBtnIcon);
			settingButton.setBorderPainted(false);
			settingButton.setContentAreaFilled(false);
			settingButton.setFocusPainted(false);
			
			rightPanel.add(nextBlockPanel);
			rightPanel.add(speedPanel);
			rightPanel.add(highscorePanel);
			rightPanel.add(buttonPanel);
			
			speedPanel.add(speedLabel);
			
			highscorePanel.add(highscore);
			highscorePanel.add(highscoreLabel);
			
			buttonPanel.add(pauseButton);
			buttonPanel.add(settingButton);
		}
	}
	
	
	public class GameoverWindow {
		// gui objects
		JFrame gameoverWindow;
		JLabel gameoverLabel;
		JLabel scoreLabel;
		JPanel highscoreText;
		JPanel replayPanel;
		DefaultButton replayButton;
		
		
		ReferenceVars refVars;
		Window.InGameWindow inGameWindow;
		
		
		// constants
		final int windowWidth=400;
		final int windowHeight=600;
		
		
		GameoverWindow(ReferenceVars refVars,Window.InGameWindow inGameWindow) {
			this.refVars=refVars;
			this.inGameWindow=inGameWindow;
			
			setGameoverWindow();
		}
		
		
		void setGameoverWindow() {
			gameoverWindow=new JFrame("Gameover :(");
			
			setGameoverLabel();
			setScoreLabel();
			setHighscoreText();
			setReplay();
			
			gameoverWindow.add(gameoverLabel);
			gameoverWindow.add(scoreLabel);
			gameoverWindow.add(highscoreText);
			gameoverWindow.add(replayButton);
			
			gameoverWindow.setSize(windowWidth,windowHeight);
			gameoverWindow.setLocationRelativeTo(null);
			gameoverWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			gameoverWindow.setLayout(new FlowLayout());
			gameoverWindow.setVisible(true);
		}
		
		void setGameoverLabel() {
			gameoverLabel=new JLabel();
			gameoverLabel.setPreferredSize(new Dimension(windowWidth,50));
			gameoverLabel.setText("Gameover");
			gameoverLabel.setFont(new Font("digital display tfb",Font.PLAIN,32));
			gameoverLabel.setForeground(Color.red);
			gameoverLabel.setHorizontalAlignment(JLabel.CENTER);
		}
		
		void setScoreLabel() {
			String test;
			scoreLabel=new JLabel();
			scoreLabel.setPreferredSize(new Dimension(windowWidth,50));
			scoreLabel.setText("Your score is...."+refVars.score);
			scoreLabel.setFont(new Font("digital display tfb",Font.PLAIN,32));
			scoreLabel.setHorizontalAlignment(JLabel.CENTER);
		}
		
		void setHighscoreText() {
			highscoreText=new JPanel();
			highscoreText.setPreferredSize(new Dimension(windowWidth,100));
			
			JLabel txt=new JLabel();
			txt.setPreferredSize(new Dimension(windowWidth,50));
			txt.setText("Highscore:");
			txt.setFont(new Font("digital display tfb",Font.PLAIN,32));
			txt.setHorizontalAlignment(JLabel.CENTER);
			txt.setForeground(Color.red);
			
			JLabel highscoreLabel=new JLabel();
			highscoreLabel.setPreferredSize(new Dimension(windowWidth,50));
			highscoreLabel.setText(Integer.toString(refVars.highscore));
			highscoreLabel.setFont(new Font("digital display tfb",Font.PLAIN,32));
			highscoreLabel.setHorizontalAlignment(JLabel.CENTER);
			
			highscoreText.add(txt);
			highscoreText.add(highscoreLabel);
		}
		
		void setReplay() {
			replayButton=new DefaultButton(refVars);
			
			replayButton.setText("Replay");
			
			Event objEvent=new Event();
			replayButton.addActionListener(objEvent.new replay(this,inGameWindow));
		}
	}
	
	public class SettingsWindow {
		final int windowWidth=300;
		final int windowHeight=500;
		
		ReferenceVars refVars;
		Window.InGameWindow inGameWindow;
		
		SettingsWindow(ReferenceVars refVars,Window.InGameWindow inGameWindow) {
			this.refVars=refVars;
			this.inGameWindow=inGameWindow;
			setWindow();
		}
		
		
		JFrame frame=new JFrame();
		JDialog window=new JDialog(frame,"Settings",true);
		JLabel title=new JLabel();
		JButton resume=new DefaultButton(refVars);
		JButton regame=new DefaultButton(refVars);
		//JButton mainmenu=new DefaultButton(refVars);
		JButton exit=new DefaultButton(refVars);
		JSlider volume=new JSlider(JSlider.HORIZONTAL,100,50);
		
		
		void setWindow() {
			Event objEvent=new Event();
			JLabel volumetxt=new JLabel("Volume");
			volumetxt.setFont(refVars.defaultFont);
			
			window.setSize(new Dimension(windowWidth,windowHeight));
			window.setLocationRelativeTo(null);
			window.setLayout(new FlowLayout());
			window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			title.setText("Settings");
			title.setPreferredSize(new Dimension(windowWidth,50));
			title.setHorizontalAlignment(JLabel.CENTER);
			title.setFont(refVars.defaultFont);
			
			resume.setText("Resume");
			resume.addActionListener(objEvent.new settings(refVars, inGameWindow).new resume(window));

			regame.setText("Regame");
			regame.addActionListener(objEvent.new settings(refVars,inGameWindow).new regame(inGameWindow,this));
			
			//mainmenu.setText("Main Menu");
			
			exit.setText("Exit");
			exit.setBackground(Color.red);
			exit.addActionListener(objEvent.new settings(refVars,inGameWindow).new exitGame());
			
			volume.setPreferredSize(new Dimension(200,30));
			volume.setValue((int)refVars.volume);
			volume.addChangeListener(objEvent.new settings(refVars,inGameWindow).new changeVolume());
			
			verticalAdd(window,title,50);
			verticalAdd(window,resume,50);
			verticalAdd(window,regame,50);
			//verticalAdd(window,mainmenu,50);
			verticalAdd(window,exit,50);
			verticalAdd(window,volumetxt,30);
			verticalAdd(window,volume,30);
			
			window.addWindowListener(objEvent.new settings(refVars,inGameWindow).new exit());
			window.setVisible(true);
		}
		
		void verticalAdd(JDialog container,Component component,int height) {
			JPanel panel=new JPanel();
			panel.setPreferredSize(new Dimension(windowWidth,height+10));
			
			panel.add(component);
			container.add(panel);
		}
	}
	
	public class GradientPanel extends JPanel {
		JPanel gradientPanel;
		
		GradientPanel(Color color1,Color color2) {
			gradientPanel=new JPanel() {
		        @Override
		        protected void paintComponent(Graphics grphcs) {
		            super.paintComponent(grphcs);
		            Graphics2D g2d = (Graphics2D) grphcs;
		            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		                    RenderingHints.VALUE_ANTIALIAS_ON);
		            GradientPaint gp = new GradientPaint(0, 0,
		                    color1, 0, getHeight(),
		                    color2);
		            g2d.setPaint(gp);
		            g2d.fillRect(0, 0, getWidth(), getHeight()); 
	
		        }
	
		    };
		}
	}
	
	public class DefaultButton extends JButton {
		DefaultButton(ReferenceVars refVars) {
			super();
			this.setPreferredSize(new Dimension(100,50));
			this.setBackground(skyBlue);
			this.setBorder(new LineBorder(Color.black));
			this.setFont(ReferenceVars.defaultFont);
			this.setForeground(Color.black);
			this.setFocusPainted(false);
		}
		
		DefaultButton(ReferenceVars refVars,String title) {
			super();
			this.setText(title);
			this.setPreferredSize(new Dimension(100,50));
			this.setBackground(skyBlue);
			this.setBorder(new LineBorder(Color.black));
			this.setFont(ReferenceVars.defaultFont);
			this.setForeground(Color.black);
			this.setFocusPainted(false);
		}
		
		DefaultButton(ReferenceVars refVars,int width,int height) {
			super();
			this.setPreferredSize(new Dimension(width,height));
			this.setBackground(Color.gray);
			this.setBorder(new LineBorder(Color.black));
			this.setFont(ReferenceVars.defaultFont);
			this.setForeground(Color.white);
			this.setFocusPainted(false);
		}
	}
}










