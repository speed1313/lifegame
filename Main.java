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
		int rows = 20;//default
		int cols = 20;//defaul
		int cellsSize[] = {20,20};
		boolean isInputValid=false;
		
		while(isInputValid==false) {
			String input_rows = JOptionPane.showInputDialog("Please input rows of cells (values must be integer 1 to 100)");
			if(input_rows == null) {
				break;
			}
			String input_cols = JOptionPane.showInputDialog("Please input cols of cells (values must be integer 1 to 100)");
			if(input_cols == null) {
				break;
			}
			try 
			{ 
				rows = Integer.parseInt(input_rows); 
				System.out.println(input_rows + " is a valid integer"); 
				cols = Integer.parseInt(input_cols); 
				System.out.println(input_cols + " is a valid integer"); 
				if(!((1<=rows && rows <=100)&&(1<=cols && cols <=100))) {
					System.out.println("values must be integer 1 to 100.");
					isInputValid = false;
				}
				else{
					isInputValid = true;
				}
				
			}  
			catch (NumberFormatException e)  
			{ 
				System.out.println(input_rows + " or " +  input_cols  + " is not a valid integer"); 
				isInputValid = false;
			} 
		}
		cellsSize[0]=rows;
		cellsSize[1]=cols;
		return  cellsSize;
	}

}
