package lifegame;



public class Main {
	
	public static void main(String[] args) {
		BoardModel model = new BoardModel(12,12);
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
		for(int i=0;i<15;i++) {
			System.out.println("board: "+i);
			model.next();
		}
		while(model.isUndoable()) {
			System.out.println("undo ");
			model.undo();
		}
	}

}
