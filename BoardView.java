package lifegame;

import java.awt.Graphics;

import javax.swing.JPanel;
public class BoardView extends JPanel{
	BoardModel model;
	
	public BoardView(BoardModel m) {
		this.model=m;
	}
	
	@Override
	public void paint(Graphics g) {
		int rows=model.getRows();
		int cols=model.getCols();
		super.paint(g);
		int width=this.getWidth();
		int height=this.getHeight();
		int heightInterval = height/model.getRows();
		int widthInterval = width/model.getCols();
		int interval = Math.min(widthInterval,heightInterval);//intervalは小さい方に合わせる(大きい方に合わせると, 小さい方がはみ出る)
		
		//隙間を上下左右分配させる
		int leftMargin=(width-interval*cols)/2; 
		int upperMargin=(height-interval*rows)/2;
		
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

}
