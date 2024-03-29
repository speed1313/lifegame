package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class undoButtonListener implements ActionListener {
	private BoardModel model;
	private BoardView view;

	undoButtonListener(BoardModel model, BoardView view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.model.undo();
		view.repaint();

	}

}
