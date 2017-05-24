package view;

import controller.*;
import levelLoader.*;
import model.*;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders.SplitPaneBorder;


public class Sokoban extends JFrame{

	private JList<String> _levelList; 
	private ControllerClass _controller;
	//private JScrollPane _scrolList;
	//private Vector<String> _levelNames; //list of level names
	//private JButton _resetButton;
	private JLabel _scoreLabel;
	private int _score;
	private String _scoreText;
	//private BoardDraw _gameBoardDrawer;
	//private JPanel _lowerPanel;
	private JSplitPane _fullWindow;
	//private JSplitPane _upperWindow;

	
	//constructor creates the window and layouts 
	public Sokoban (){
		super ("Sokoban");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.setResizable(false); 
		_controller = new ControllerClass(this);

		
	
		
		//initialize JList and level list names
		
		Vector<String >levelNames = new Vector<String>();
		
		int numberOfLevels = _controller.getNumberOfLevels();
		//create level names
		for (int i=1;i<=numberOfLevels;i++){
			levelNames.add("Level " + i);
		}
		
		_levelList = new JList<String>(levelNames);
		_levelList.setSelectedIndex(0); // set the initial level value to 0
		_controller.UpdateJList(_levelList);
		
		//disable the arrows on the list
		_levelList.getInputMap().put(KeyStroke.getKeyStroke("UP"), "none");
		_levelList.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "none");
		
		_levelList.addKeyListener(_controller);
		
		//create list listener

		_levelList.addListSelectionListener(_controller); // create list listner
		
		//reset button initialize
		JButton resetButton = new JButton("Reset Game");
		resetButton.addActionListener(_controller);
		resetButton.setFocusable(false);
	
		
		//score label initialization 
		_scoreText = "The Score is: ";
		_score =0;
		_scoreLabel = new JLabel(_scoreText + _score);
		
		// creates the split view of the upper window
		 JSplitPane upperWindow = new JSplitPane (JSplitPane.HORIZONTAL_SPLIT,createLevelPanel(),createRightPanel(resetButton));

		


		
		
		// create board visual represenation 
		BoardDraw gameBoardDrawer = new BoardDraw();
		
		//creates a panel containg the visual presentaion of the board
		 JPanel lowerPanel = gameBoardDrawer.DrawGameBoard(_controller.getBoard());
		
		//splits the frame into 3
		 _fullWindow = new JSplitPane(JSplitPane.VERTICAL_SPLIT,upperWindow,lowerPanel);
		
		add (_fullWindow);
		pack();
		setVisible(true);
	}
	
	// level selector panel
	private JPanel createLevelPanel(){	
		JPanel levelPanel = new JPanel();

		JScrollPane scrolList = new JScrollPane(_levelList);
		_levelList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); /// enable selecting only one level

		
		levelPanel.add(scrolList);	
		
		return levelPanel;
	}
	
	private JPanel createRightPanel (JButton resetButton){
		
		JPanel rightPanel = new JPanel(new FlowLayout());
		rightPanel.add(resetButton);
		rightPanel.add(_scoreLabel);
		return rightPanel;
	}
	
	
	public void setScore(int score){
		_score=score;
		_scoreLabel.setText(_scoreText+_score);
	}
	
	public int getScore(){
		return _score;
	}
	
	//TODO: this needs to be actually tested eventually...
	public void victory(){
		JOptionPane.showMessageDialog(null, 
				"You have won!"+"\n"+"Your score is :" + getScore());
	}
	
	// paint the new board 
	public void paintNewBoard (){
		BoardDraw gameBoardDrawer = new BoardDraw();
		JPanel lowerPanel = gameBoardDrawer.DrawGameBoard(_controller.getBoard());
		_fullWindow.setBottomComponent(lowerPanel);
		this.pack();
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sokoban frame = new Sokoban();
	}

}
