package lifegame;
import java.util.ArrayList;
import java.util.LinkedList;
//cellは行列で指定 ex) cells[2][3]なら2行３列
/* 
 * 			cols
 * 		  ------>
 *  	 |
 * rows  |
 * 		 V
 * 
 * historyはnext()とchangeCellStateを行なった時更新
 */
 class BoardModel {
	private int rows;//vertical
	private int cols;//horizontal
	
	private boolean[][] cells;
	private ArrayList<BoardListener> listeners;
	private LinkedList<boolean[][]> boardHistoryList;
	
	public BoardModel(int r, int c) {
		rows = r;
		cols = c;
		
		cells = new boolean[rows][cols];
		listeners = new ArrayList<BoardListener>();
		boardHistoryList = new LinkedList<boolean[][]>();
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
		if((0<=r&&r<rows)&&(0<=c&&c<cols)) {
			registerBoard();
			cells[r][c]=!cells[r][c];
			this.fireUpdate();
		}
		else {
			System.out.print("can't change out-of-board cell");
		}
	}
	
	public void addListener(BoardListener listener) {
		listeners.add(listener);
	}
	private void fireUpdate() {
		for(BoardListener listener: listeners) {
			listener.updated(this);
		}
	}
	
	/*
	 * セルの状態を更新する.
	 */
	public void next() {
		
		registerBoard();
		boolean[][] nextCells = new boolean[rows][cols];//tmporary next cells
		for(int r=0; r<rows; r++) {
			for(int c=0; c<cols; c++) {
				nextCells[r][c]=isNextAlive(r,c);
			}
		}
		for(int r=0; r<rows; r++) {
			for(int c=0; c<cols; c++) {
				cells[r][c]=nextCells[r][c];
			}
		}
		this.fireUpdate();
		
		
	}
	/*
	 * 状態の巻き戻し. 記憶する盤面数は32面まで(現在の盤面を含めず)
	 */
	public void undo() {
		if(boardHistoryList.isEmpty()) {
			System.out.println("can't undo. I have memorized only 32 board states. or it is up to date.");
			this.fireUpdate();
			return;
		}
		
		cells=boardHistoryList.removeLast();
		this.fireUpdate();
		return;
	}
	public boolean isUndoable() {
		if(!boardHistoryList.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isAlive(int row,int col) {
		if(cells[row][col]) {
			return true;
		}
		else return false;
	}
	
	/*
	 * 現在のboard状態を記録
	 */
	private void registerBoard() {
		boolean[][] cellsForSave= new boolean[rows][cols];
		for(int r=0; r<rows; r++) {
			for(int c=0; c<cols; c++) {
				cellsForSave[r][c]=cells[r][c];
			}
		}
		if(boardHistoryList.isEmpty()) {
			boardHistoryList.add(cellsForSave);
		}
		else{
			if(boardHistoryList.size()==32) {
				boardHistoryList.removeFirst();
			}
			boardHistoryList.add(cellsForSave);//更新前の状態を記録
		}
		
	}
	
	/*
	 * 次の状態の生死を返す. 
	 */
	private boolean isNextAlive(int r,int c) {
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
	
	/*
	 * 周りの8コマに対して, 生存セル数を数える.
	 */
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
