package lifegame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main implements Runnable{
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Main());
	}
	
	public void run() {

		int rows;
		int cols;
		
		int cellsSize[] = setRowsAndCols();
		rows = cellsSize[0];
		cols = cellsSize[1];
		//create a window
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		BoardModel model = new BoardModel(rows,cols);
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
	
	private int[] setRowsAndCols() {
		int cellsSize[] = {20,20};
		cellsSize[0]=getRowsOrCols("Rows");
		cellsSize[1]=getRowsOrCols("Cols");
		return  cellsSize;
	}
	private int getRowsOrCols(String target) {
		int defaultnum = 20;
		boolean isInputValid=false;
		int nInput=0;
		//取り消しが押された場合default, それ以外はvalidな数字が得られるまで繰り返す
		while(isInputValid==false) {
			String inputTarget = JOptionPane.showInputDialog("Please input "+target+" of cells (values must be integer 1 to 100, default is 20)");
			if(inputTarget == null) {
				return defaultnum;
			}
			try 
			{ 
				nInput = Integer.parseInt(inputTarget); 
				System.out.println(inputTarget + " is a valid integer"); 
				if(!(1<=nInput && nInput <=50)) {
					System.out.println("values must be integer 1 to 50.");
					isInputValid = false;
				}
				else{
					isInputValid = true;
				}
				
			}  
			catch (NumberFormatException e)  
			{ 
				System.out.println(inputTarget + " is not a valid integer"); 
				isInputValid = false;
			} 
		}
		return nInput;
	}

}
