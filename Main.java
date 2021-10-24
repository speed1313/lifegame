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
		
		//create view 
		BoardView view = new BoardView(model);
		base.add(view, BorderLayout.CENTER);
		
		JButton nextButton = new JButton("Next");
		nextButton.setVerticalTextPosition(AbstractButton.CENTER);
		nextButtonListener nextListener = new nextButtonListener(model,view);
		nextButton.addActionListener(nextListener);
		
		JButton undoButton = new JButton("Undo");
		undoButton.setEnabled(false);
		undoButton.setVerticalTextPosition(AbstractButton.CENTER);
		undoButtonListener uListener = new undoButtonListener(model,view);
		undoButton.addActionListener(uListener);
		
		JButton newgameButton = new JButton("New Game");
		newgameButton.setVerticalTextPosition(AbstractButton.CENTER);
		newgameButtonListener newgameListener = new newgameButtonListener();
		newgameButton.addActionListener(newgameListener);
		
		
		JPanel buttonPanel = new JPanel();
		
		base.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(undoButton, BorderLayout.CENTER);
		buttonPanel.add(nextButton, BorderLayout.EAST);
		buttonPanel.add(newgameButton, BorderLayout.WEST);
		
		view.setUndoButton(undoButton);
		
		
	
		
		frame.pack();   // ウィンドウに乗せたものの配置を確定する
		frame.setVisible(true);
		
	}

}
