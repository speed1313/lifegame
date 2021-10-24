package lifegame;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
public class BoardView extends JPanel
 	implements BoardListener, MouseListener, MouseMotionListener{
	private BoardModel model;
	private int rows;
	private int cols;
	private int interval;
	private int leftMargin;
	private int upperMargin;
	private int mouserow;
	private int mousecol;
	private int[] pastMousePoint= new int[]{-1,-1};

	
	public BoardView(BoardModel m) {
		this.model=m;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);// JPanelの描画処理(背景塗りつぶし)
		
		calcBoardCoordinate();
		//原点調整と, interval
		for(int r=0; r<=model.getRows();r++) {
			for(int c=0;c<=model.getCols();c++) {
				g.drawLine(leftMargin+0,upperMargin+interval*r,leftMargin+interval*cols,upperMargin+interval*r); //horizon line
				g.drawLine(leftMargin+interval*c,upperMargin+0,leftMargin+interval*c,upperMargin+interval*rows);//vertical line
			}
		}
		//原点調整と, interval
		for(int r=0; r<model.getRows();r++) {
			for(int c=0;c<model.getCols();c++) {
				if(model.isAlive(r,c)) {
					int leftx=c*interval+leftMargin;
					int lefty=r*interval+upperMargin;
					g.fillRect(leftx,lefty,interval,interval);
				}
			}
		}
		
	}
	private void calcBoardCoordinate() {
		this.rows=model.getRows();
		this.cols=model.getCols();
		int width=this.getWidth();
		int height=this.getHeight();
		int heightInterval = height/model.getRows();
		int widthInterval = width/model.getCols();
	    this.interval = Math.min(widthInterval,heightInterval);//intervalは小さい方に合わせる(大きい方に合わせると, 小さい方がはみ出る)
		
		//隙間を上下左右分配させる
		this.leftMargin=(width-this.interval*this.cols)/2; 
		this.upperMargin=(height-this.interval*this.rows)/2;
	}
	
	private void calcMousePoint(int x,int y) {
		int fixedX = x - this.leftMargin;
		int fixedY = y - this.upperMargin;
		if((0<fixedX && fixedX<cols*interval) && (0<fixedY && fixedY<rows*interval)){//ふちは範囲外とする
			this.mouserow= fixedY/this.interval;
			this.mousecol= fixedX/this.interval;
		}
		else {
			this.mouserow=-1;
			this.mousecol=-1;
		}	
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		calcBoardCoordinate();
		calcMousePoint(e.getX(),e.getY());
    	System.err.println("Pressed: " + e.getX()+ ", " + e.getY());
    	System.err.println("Pressed: " + this.mouserow + ", " + this.mousecol);
    	if(this.pastMousePoint[0]==this.mouserow && this.pastMousePoint[1]==this.mousecol){
    		return;
    	}
    	this.updatePastMousePoint(this.mouserow,this.mousecol);
    	this.model.changeCellState(this.mouserow, this.mousecol);
    	this.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		calcBoardCoordinate();
		calcMousePoint(e.getX(),e.getY());
    	System.err.println("Pressed: " + e.getX()+ ", " + e.getY());
    	System.err.println("Pressed: " + this.mouserow + ", " + this.mousecol);
    	this.model.changeCellState(this.mouserow, this.mousecol);
    	updatePastMousePoint(this.mouserow,this.mousecol);
    	this.repaint();
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void updated(BoardModel m) {
		// TODO 自動生成されたメソッド・スタブ
	}
	private void updatePastMousePoint(int row, int col) {
    	this.pastMousePoint[0]=row;
    	this.pastMousePoint[1]=col;
    	return;
	}


}
