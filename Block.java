import java.awt.Color;
import java.util.Random;



public class Block {
	char type;
	int size;
	Color color;
	Color border;
	int x=5;
	int y;
	boolean filled[][]=new boolean[4][4];
	static Color purple=new Color(128,0,128);
	
	Block() {
		this.change();
		// randomize
	}
	
	
	void setShape() {
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				this.filled[i][j]=false;
			}
		}
		
		this.size=3;
		this.border=Grid.defaultBorder;
		
		if(this.type=='L') {
			this.color=Color.orange;
			this.filled[0][1]=true;
			this.filled[1][1]=true;
			this.filled[2][1]=true;
			this.filled[2][2]=true;
		}
		else if(this.type=='Z') {
			this.color=Color.red;
			this.filled[1][0]=true;
			this.filled[1][1]=true;
			this.filled[2][1]=true;
			this.filled[2][2]=true;
		}
		else if(this.type=='O') {
			this.color=Color.yellow;
			this.size=2;
			this.filled[0][0]=true;
			this.filled[0][1]=true;
			this.filled[1][0]=true;
			this.filled[1][1]=true;
		}
		else if(this.type=='I') {
			this.color=Color.cyan;
			this.size=4;
			this.filled[0][1]=true;
			this.filled[1][1]=true;
			this.filled[2][1]=true;
			this.filled[3][1]=true;
		}
		else if(this.type=='J') {
			this.color=Color.blue;
			this.filled[0][1]=true;
			this.filled[1][1]=true;
			this.filled[2][0]=true;
			this.filled[2][1]=true;
		}
		else if(this.type=='S') {
			this.color=Color.green;
			this.filled[1][1]=true;
			this.filled[1][2]=true;
			this.filled[2][0]=true;
			this.filled[2][1]=true;
		}
		else if(this.type=='T') {
			this.color=purple;
			this.filled[0][1]=true;
			this.filled[1][0]=true;
			this.filled[1][1]=true;
			this.filled[1][2]=true;
		}
		
	}
	
	
	
	void change() {
		Random random=new Random();
		
		int n=random.nextInt(7);
		
		switch (n) {
		case 0:
			this.type='L';
			break;
		case 1:
			this.type='Z';
			break;
		case 2:
			this.type='O';
			break;
		case 3:
			this.type='I';
			break;
		case 4:
			this.type='J';
			break;
		case 5:
			this.type='S';
			break;
		case 6:
			this.type='T';
			break;
		}
		
		this.setShape();
		this.x=5;
		if(this.size==2) {
			this.y=-1;
		}
		else if(this.size==3) {
			this.y=-2;
		}
		else {
			this.y=-3;
		}
	}
	
	
	
	void changeTo(Block nextBlock) {
		this.type=nextBlock.type;
		
		this.setShape();
		this.x=5;
		if(this.size==2) {
			this.y=-1;
		}
		else if(this.size==3) {
			this.y=-2;
		}
		else {
			this.y=-3;
		}
	}
	
	void draw(Grid[][] grid) {
		for(int i=0;i<this.size;i++) {
			for(int j=0;j<this.size;j++) {
				if(Brick.outOfBound(this.x-1+j,this.y-1+i)==0&&this.filled[i][j])
				{
					grid[this.y-1+i][this.x-1+j].changeColor(this.color);
					grid[this.y-1+i][this.x-1+j].changeBorder(this.border);
				}
			}
		}
	}
	
	void drawShadow(Block shadow,Grid[][] grid) {
		for(int i=0;i<shadow.size;i++) {
			for(int j=0;j<shadow.size;j++) {
				if(Brick.outOfBound(shadow.x-1+j,shadow.y-1+i)==0&&shadow.filled[i][j])
				{
					grid[shadow.y-1+i][shadow.x-1+j].changeColor(shadow.color);
				}
			}
		}
	}
	
	void changeBorder(Grid[][] grid,Block shadow) {
		for(int i=0;i<shadow.size;i++) {
			for(int j=0;j<shadow.size;j++) {
				if(Brick.outOfBound(shadow.x-1+j,shadow.y-1+i)==0&&shadow.filled[i][j])
				{
					grid[shadow.y-1+i][shadow.x-1+j].changeBorder(shadow.border);
				}
			}
		}
	}
	
	void erase(Grid[][] grid) {
		for(int i=0;i<this.size;i++) {
			for(int j=0;j<this.size;j++) {
				if(Brick.outOfBound(this.x-1+j,this.y-1+i)==0&&this.filled[i][j])
				{
					grid[this.y-1+i][this.x-1+j].changeColor(Grid.defaultColor);
					grid[this.y-1+i][this.x-1+j].changeBorder(Grid.defaultBorder);
				}
			}
		}
	}
	
	void eraseShadow(Block shadow,Grid[][] grid) {
		for(int i=0;i<shadow.size;i++) {
			for(int j=0;j<shadow.size;j++) {
				if(Brick.outOfBound(shadow.x-1+j,shadow.y-1+i)==0&&shadow.filled[i][j])
				{
					grid[shadow.y-1+i][shadow.x-1+j].changeColor(Grid.defaultColor);
				}
			}
		}
	}
	
	boolean outOfBound() {
		for(int i=0;i<this.size;i++) {
			for(int j=0;j<this.size;j++) {
				if(this.filled[i][j]&&Brick.outOfBound(this.x-1+j,this.y-1+i)>0)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	boolean checkCollision(Grid[][] grid) {
		for(int i=0;i<this.size;i++) {
			for(int j=0;j<this.size;j++) {
				if(this.filled[i][j]&&Brick.outOfBound(this.x-1+j,this.y-1+i)==0)
				{
					if(grid[this.y-1+i][this.x-1+j].filled) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	void copy(Block original) {
		this.type=original.type;
		this.size=original.size;
		this.color=original.color;
		this.border=original.border;
		this.x=original.x;
		this.y=original.y;
		
		for(int i=0;i<this.size;i++) {
			for(int j=0;j<this.size;j++) {
				this.filled[i][j]=original.filled[i][j];
			}
		}
	}
	
	
	
	
	void rotateClockwise() {
		Block before=new Block();
		before.copy(this);
		
		if(this.size==2) {
			for(int i=0;i<2;i++) {
				for(int j=0;j<2;j++) {
					this.filled[i][j]=before.filled[i][j];
				}
			}
		}
		else if(this.size==3) {
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					this.filled[i][j]=before.filled[2-j][i];
				}
			}
		}
		else if(this.size==4) {
			for(int i=0;i<4;i++) {
				for(int j=0;j<4;j++) {
					this.filled[i][j]=before.filled[3-j][i];
				}
			}
		}
	}
	
	
	
	
	void rotateCounterClockwise() {
		Block before=new Block();
		before.copy(this);
		
		if(this.size==2) {
			for(int i=0;i<2;i++) {
				for(int j=0;j<2;j++) {
					this.filled[i][j]=before.filled[i][j];
				}
			}
		}
		else if(this.size==3) {
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					this.filled[i][j]=before.filled[j][2-i];
				}
			}
		}
		else if(this.size==4) {
			for(int i=0;i<4;i++) {
				for(int j=0;j<4;j++) {
					this.filled[i][j]=before.filled[j][3-i];
				}
			}
		}
	}
	
	
	
	static void fall(Grid[][] grid,Block curBlock,Block nextBlock,ReferenceVars refVars,
			Window.InGameWindow inGameWindow) {
		
		if(!refVars.keyPressed) {
			// lower curBlock
			curBlock.erase(grid);
			curBlock.eraseShadow(refVars.shadow, grid);
			curBlock.y++;
			
			// if curBlock is out of bound or in collision,
			// restore curBlock
			if(curBlock.outOfBound()||curBlock.checkCollision(grid)) {
				curBlock.y--;
				curBlock.draw(grid);
				
				refVars.score+=refVars.score_block(curBlock.type);
				
				Grid.deleteLine(grid,refVars);
				
				Sound.blockDown(refVars);
				
				curBlock.changeTo(nextBlock);
				nextBlock.change();
				if(refVars.speed<refVars.maxSpeed) {
					refVars.speed=refVars.speed*1.01;
				}
				
				// if the first line is filled
				for(int i=0;i<Window.InGameWindow.GRID_COLUMNS;i++) {
					if(grid[0][i].filled) {
						refVars.gameover=true;
						if(refVars.score>refVars.highscore) {
							refVars.highscore=refVars.score;
							TeTris.saveHighscore(refVars);
						}
						TeTris.gameover(refVars,inGameWindow);
						break;
					}
				}
			}
			else {
				curBlock.draw(grid);
			}
			
			curBlock.refreshShadow(grid, refVars);
		}
	}
	
	
	void refreshShadow(Grid[][] grid,ReferenceVars refVars) {
		int saveLocation=this.y;
		Block shadow=refVars.shadow;
		
		this.erase(grid);
		// erase curBlock
		
		while(!this.outOfBound()&&!this.checkCollision(grid)) {
			this.y++;
		}
		this.y--;
		// curBlock is at bottom
		
		shadow.copy(this);
		// copy curBlock to shadow
		
		shadow.color=Grid.shadowColor;
		
		this.drawShadow(shadow, grid);
		
		// restore curBlock
		this.y=saveLocation;
		this.draw(grid);
	}
}

















