package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class nextButtonListener implements ActionListener {
	private BoardModel model;
	private BoardView view;

	nextButtonListener(BoardModel model, BoardView view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.next();
		view.repaint();

	}

}
