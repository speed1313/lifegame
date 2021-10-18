package lifegame;
import java.util.ArrayList;

 class BoardModel {
	private int cols;//horizontal
	private int rows;//vertical
	private boolean[][] cells;
	private ArrayList<BoardListener> listeners;
	
	public BoardModel(int c, int r) {
		cols = c;
		rows = r;
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
		for(int i=0;i<rows;i++) {
			for(int j=0;j<cols;j++) {
				if(cells[i][j]==true) {
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
	
	public void changeCellState(int x, int y) {
		cells[x][y]=!cells[x][y];
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
	

}
