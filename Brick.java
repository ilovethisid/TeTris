
public class Brick {
	static int outOfBound(int x,int y) {
		// check if a brick is out of bound
		// in bound: 0
		// not yet created: -1
		// out of bound: 1
		
		if(x<0||x>=Window.InGameWindow.GRID_COLUMNS) {
			return 1;
		}
		
		if(y<0) {
			return -1;
		}
		else {
			if(y<Window.InGameWindow.GRID_ROWS) {
				return 0;
			}
			else {
				return 1;
			}
		}
	}
}
