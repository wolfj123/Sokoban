package view;

import controller.*;
import levelLoader.Cell;
import model.*;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;


public class Sokoban extends JFrame{

	private JList<String> _levelList; 
	private BoardModel _board;
	
	private ControllerClass _controller;
	private UndoController _undoController;
	
	private JLabel _scoreLabel;
	private int _score;
	private String _scoreText;
	private JSplitPane _fullWindow;

	
	//constructor creates the window and layouts 
	public Sokoban (){
		super ("Sokoban");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		
		_board= new BoardModel();
		_controller = new ControllerClass(this,_board);

		//initialize JList and level list names
		Vector<String >levelNames = new Vector<String>();
		int numberOfLevels = _controller.getNumberOfLevels();
		
		//create level names
		for (int i=1;i<=numberOfLevels;i++){
			levelNames.add("Level " + i);
		}
		// create level Jlist and scroll pane
		_levelList = new JList<String>(levelNames);
		_levelList.setSelectedIndex(0); // set the initial level value to 0
		_controller.updateJList(_levelList);
		
		//disable the arrows on the list
		_levelList.getInputMap().put(KeyStroke.getKeyStroke("UP"), "none");
		_levelList.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "none");
		
		_levelList.addKeyListener(_controller);
		
		//create list listener
		_levelList.addListSelectionListener(_controller); // create list listener
		
		
		//score label initialization 
		_scoreText = "The Score is: ";
		_score =0;
		_scoreLabel = new JLabel(_scoreText + _score);
		
		// creates the split view of the upper window
		 JSplitPane upperWindow = new JSplitPane (JSplitPane.HORIZONTAL_SPLIT,createLevelPanel(),createRightPanel());

		// create board visual representation 
		BoardDraw gameBoardDrawer = new BoardDraw();
		
		//creates a panel containing the visual presentation of the board
		 JPanel lowerPanel = gameBoardDrawer.drawGameBoard(_board.getCellArray());
		
		//splits the frame into 3
		 _fullWindow = new JSplitPane(JSplitPane.VERTICAL_SPLIT,upperWindow,lowerPanel);
		
		add (_fullWindow);
		pack();
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		Sokoban frame = new Sokoban();
	}
	
	public int getScore(){
		return _score;
	}
	
	public void setScore(int score){
		_score=score;
		_scoreLabel.setText(_scoreText+_score);
	}
	
	public BoardModel getBoard(){
		return _board;
	}
	
	public void setBoard(BoardModel board){
		_board = board;
		_undoController.setBoard(board);
		_controller.setBoard(board);
	}
	
	public void victory(){
		JOptionPane.showMessageDialog(null, 
				"You have won!"+"\n"+"Your score is :" + getScore() 
				+ "\n" + "Please select a new level from the list");
	}
	
	// paint the new board 
	public void paintNewBoard (Cell[][] cellArray){
		// create the panel of the board
		BoardDraw gameBoardDrawer = new BoardDraw(); 
		JPanel lowerPanel = gameBoardDrawer.drawGameBoard(cellArray);
		// update JFrame
		_fullWindow.setBottomComponent(lowerPanel);
		this.pack();
	}
	
	// level selector panel
		private JPanel createLevelPanel(){	
			JPanel levelPanel = new JPanel();
			JScrollPane scrolList = new JScrollPane(_levelList);
			_levelList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); /// enable selecting only one level
			levelPanel.add(scrolList);	
			
			return levelPanel;
		}
		
		private JSplitPane createRightPanel (){
			
			JPanel buttonsPanel = new JPanel(new FlowLayout());
			// create reset button
			JButton resetButton = new JButton("Reset Game");
			resetButton.addActionListener(_controller);
			resetButton.setFocusable(false);
			buttonsPanel.add(resetButton);
			// create undo button
			_undoController = new UndoController(this,_board);
			JButton undoButton = new JButton("Undo");
			undoButton.setFocusable(false);
			undoButton.addActionListener(_undoController);
			buttonsPanel.add(undoButton);
			
			//create instructions label
			JLabel instructuions = new JLabel("<html>Controls:<br>"
					+ "Use the Arrow keys to control the worker<br>"
					+ "Use Control+Up Arrow/Down Arrow to change the levels<br>"
					+ "You can reset the game using the Reset button<br>"
					+ "You can undo your moves using the Undo button</html>");

			// add to Jpanel
			JPanel labelsPanel = new JPanel (new GridLayout(2, 1));
			labelsPanel.add(_scoreLabel);
			labelsPanel.add(instructuions);
			
			// add to split pane
			JSplitPane rightPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT,buttonsPanel,labelsPanel);

			return rightPanel;
		}

}
