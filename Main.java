package lifegame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main implements Runnable{
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Main());
	}
	
	public void run() {
		BoardModel model = new BoardModel(15,20);
		
		
		//create a window
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//create base panel which occupy internal window
		JPanel base = new JPanel();
		frame.setContentPane(base);
		base.setPreferredSize(new Dimension(400,300));
		frame.setMinimumSize(new Dimension(300,200));
		frame.setTitle("Lifegame");
		
		base.setLayout(new BorderLayout());
		BoardView view = new BoardView(model);
		base.add(view, BorderLayout.CENTER);
		
		JButton nextButton = new JButton("Next");
		nextButton.setVerticalTextPosition(AbstractButton.CENTER);
		nextButtonListener nListener = new nextButtonListener(model,view);
		nextButton.addActionListener(nListener);
		
		JButton undoButton = new JButton("Undo");
		undoButton.setEnabled(false);
		undoButton.setVerticalTextPosition(AbstractButton.CENTER);
		undoButtonListener uListener = new undoButtonListener(model,view);
		undoButton.addActionListener(uListener);
		
		JPanel buttonPanel = new JPanel();
		
		base.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(undoButton, BorderLayout.WEST);
		buttonPanel.add(nextButton, BorderLayout.EAST);
		
		view.setUndoButton(undoButton);
		
		
	
		
		frame.pack();   // ウィンドウに乗せたものの配置を確定する
		frame.setVisible(true);
		
	}

}
