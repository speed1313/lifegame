package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;
public class newgameButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		SwingUtilities.invokeLater(new Main());
		
	}

}
