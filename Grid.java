import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Grid {
	static Color defaultColor=Color.white;
	static Color shadowColor=Color.darkGray;
	static Color defaultBorder=Color.black;
	
	Color color;
	Color border;
	int x;
	int y;
	JPanel grid=new JPanel();
	boolean filled;
	
	
	Grid() {
		this.color=defaultColor;
		this.border=defaultBorder;
		this.grid.setBackground(this.color);
		this.grid.setBorder(new LineBorder(this.border));
		this.filled=false;
		this.grid.setOpaque(false);
	}
	
	
	void changeColor(Color color) {
		this.color=color;
		this.grid.setBackground(color);
		
		if(color.equals(defaultColor)) {
			this.filled=false;
			this.grid.setOpaque(false);
		}
		else if(color.equals(shadowColor)) {
			this.filled=false;
			this.grid.setOpaque(true);
		}
		else {
			this.filled=true;
			this.grid.setOpaque(true);
		}
	}
	
	void changeBorder(Color color) {
		this.border=color;
		this.grid.setBorder(new LineBorder(color));
	}
	
	void copy(Grid original) {
		this.color=original.color;
		this.border=original.border;
		this.x=original.x;
		this.y=original.y;
		this.filled=original.filled;
		this.grid.setOpaque(original.grid.isOpaque());
	}
	
	void draw() {
		this.grid.setBackground(this.color);
	}
	
	
	static void setGrid(JPanel blockPanel,Grid[][] grid) {
		for(int i=0;i<grid.length;i++) {
			for(int j=0;j<grid[0].length;j++) {
				grid[i][j]=new Grid();
				grid[i][j].x=j;
				grid[i][j].y=i;
				blockPanel.add(grid[i][j].grid);
			}
		}
		
		// reverse
		/*for(int i=0;i<grid.length;i++) {
			for(int j=0;j<grid[0].length;j++) {
				grid[grid.length-i-1][j]=new Grid();
				grid[grid.length-i-1][j].x=i;
				grid[grid.length-i-1][j].y=j;
				blockPanel.add(grid[grid.length-i-1][j].grid);
			}
		}*/
	}
	
	
	
	static void cleanGrid(Grid[][] grid) {
		for(int i=0;i<grid.length;i++) {
			for(int j=0;j<grid[0].length;j++) {
				grid[i][j].changeColor(defaultColor);
				grid[i][j].changeBorder(defaultBorder);
			}
		}
	}
	
	
	
	static void drawGrid(Grid[][] grid) {
		for(int i=0;i<grid.length;i++) {
			for(int j=0;j<grid[0].length;j++) {
				grid[i][j].draw();
			}
		}
	}
	

	
	
	
	
	static void deleteLine(Grid[][] grid,ReferenceVars refVars) {
		final int numRow=Window.InGameWindow.GRID_ROWS;
		final int numCol=Window.InGameWindow.GRID_COLUMNS;
		boolean[] line=new boolean[numRow];
		// line is full
		int i,j;
		short num_deleteLine=0;
		Clock clock=new Clock();
		
		
		for(i=0;i<numRow;i++) {
			for(j=0;j<numCol;j++) {
				if(!grid[i][j].filled) {
					line[i]=false;
					break;
				}
			}
			
			if(j==numCol) {
				line[i]=true;
				num_deleteLine++;
			}
		}
		// set values for line[i]
		
		
		for(i=numRow-1;i>0;i--) {
			if(line[i]) {
				for(j=0;j<numCol;j++) {
					grid[i][j].changeBorder(Color.yellow);
					grid[i][j].changeColor(Color.black);
				}
			}
		}
		
		clock.wait(0.1);
		// line delete effect
		
		
		for(i=numRow-1;i>0;i--) {
			if(line[i]) {
				for(j=0;j<numCol;j++) {
					grid[i][j].changeBorder(defaultBorder);
					grid[i][j].changeColor(defaultColor);
				}
			}
		}
		// if line is full, delete line
		
		if(num_deleteLine>0) {
			if(num_deleteLine<4) {
				Sound.lineClear(refVars);
			}
			else {
				Sound.tetris(refVars);
			}
		}
		
		
		for(i=0;i<numRow;i++) {
			if(line[i]) {
				for(int k=i;k>0;k--) {
					for(j=0;j<numCol;j++) {
						grid[k][j].copy(grid[k-1][j]);
					}
				}
				
				for(j=0;j<numCol;j++) {
					grid[0][j].changeColor(defaultColor);
				}
				
				line[i]=false;
			}
		}
		// apply gravity
		
		
		Grid.drawGrid(grid);
		
		
		refVars.score+=refVars.score_line(num_deleteLine);
	}
}












