package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class undoButtonListener implements ActionListener {
	private BoardModel model;
	private BoardView view;
	undoButtonListener(BoardModel model, BoardView view){
		this.model = model;
		this.view = view;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		this.model.undo();
		view.repaint();
		
	}

}
