package lifegame;
import java.util.ArrayList;
//cellは行列で指定 ex) cells[2][3]なら2行３列
/* 
 * 			cols
 * 		  ------>
 *  	 |
 * rows  |
 * 		 V
 */
 class BoardModel {
	private int cols;//horizontal
	private int rows;//vertical
	private boolean[][] cells;
	private ArrayList<BoardListener> listeners;
	
	public BoardModel(int r, int c) {
		rows = r;
		cols = c;
		
		cells = new boolean[rows][cols];
		listeners = new ArrayList<BoardListener>();
	}
	
	public int getCols() {
		return cols;
	}
	public int getRows() {
		return rows;
	}
	public int addCols() {
		cols++;
		return cols;
	}
	
	public void printForDebug() {
		for(int r=0;r<rows;r++) {
			for(int c=0;c<cols;c++) {
				if(cells[r][c]==true) {
					System.out.print("*");
				}
				else {
					System.out.print(".");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void changeCellState(int r, int c) {
		cells[r][c]=!cells[r][c];
		this.fireUpdate();
	}
	
	public void addListener(BoardListener listener) {
		listeners.add(listener);
	}
	private void fireUpdate() {
		for(BoardListener listener: listeners) {
			listener.updated(this);
		}
	}
	
	public void next() {
		boolean[][] nextCells = new boolean[rows][cols];
		for(int r=0; r<rows; r++) {
			for(int c=0; c<cols; c++) {
				nextCells[r][c]=nextCellState(r,c);
			}
		}
		for(int r=0; r<rows; r++) {
			for(int c=0; c<cols; c++) {
				cells[r][c]=nextCells[r][c];
			}
		}
		this.fireUpdate();
		
	}
	private boolean nextCellState(int r,int c) {
		int numAliveCells=0;
		numAliveCells=countAliveCells(r,c);
		if(cells[r][c]==true) {
			if(numAliveCells==2 || numAliveCells==3) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if(numAliveCells==3) {
				return true;
			}
			else {
				return false;
			}
		}
		
	}
	private int countAliveCells(int r,int c) {
		var drlist = new int[]{-1,0,1};
		var dclist = new int[]{-1,0,1};
		int numAliveCells=0;
		for(int dr : drlist) {
			for(int dc : dclist) {
				if((0<=r+dr && r+dr <rows) && (0<=c+dc && c+dc <cols) && !(dr==0&&dc==0)){
					if(cells[r+dr][c+dc]==true) {
						numAliveCells++;
						
					}
				}
			}		
		}		
		return numAliveCells;
		
	}
	
	
	

}
