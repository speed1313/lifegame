package lifegame;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main implements Runnable{
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Main());
	}
	
	public void run() {
		BoardModel model = new BoardModel(12,15);
		model.addListener(new ModelPrinter());
		model.changeCellState(1, 1);
		model.changeCellState(2, 2);
		model.changeCellState(0, 3);
		model.changeCellState(1, 3);
		model.changeCellState(2, 3);
		model.changeCellState(4, 4);
		model.changeCellState(4, 4);
		model.changeCellState(11, 11);
		model.changeCellState(11, 10);
		model.changeCellState(10, 11);
		model.changeCellState(10, 10);
		/*for(int i=0;i<15;i++) {
			System.out.println("board: "+i);
			model.next();
		}
		while(model.isUndoable()) {
			System.out.println("undo ");
			model.undo();
		}*/
		
		
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
	
		
		frame.pack();   // ウィンドウに乗せたものの配置を確定する
		frame.setVisible(true);
		
	}

}
