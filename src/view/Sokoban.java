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
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders.SplitPaneBorder;


public class Sokoban extends JFrame{

	private JList<String> _levelList; 
	private ControllerClass _controller;
	private JScrollPane _scrolList;
	private Vector<String> _levelNames; //list of level names
	private JButton _resetButton;
	private JLabel _scoreLabel;
	private int _score;
	private String _scoreText;
	private BoardDraw _gameBoardDrawer;
	private JPanel _lowerPanel;
	private JSplitPane _fullWindow;
	private JSplitPane _upperWindow;

	
	//constructor creates the window and layouts 
	public Sokoban (){
		super ("Sokoban");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.setResizable(false); 
		_controller = new ControllerClass(this);

		
		// score label initialization 
		_resetButton = new JButton("Reset Game");
		_scoreText = "The Score is: ";
		_score =0;
		_scoreLabel = new JLabel(_scoreText + _score);
		
		//initialize JList and level list names
		
		_levelNames = new Vector<String>();
		
		int numberOfLevels = _controller.GetNumberOfLevels();
		//create level names
		for (int i=1;i<=numberOfLevels;i++){
			_levelNames.add("Level " + i);
		}
		
		_levelList = new JList<String>(_levelNames);
		_levelList.setSelectedIndex(0); // set the initial level value to 0
		_controller.UpdateJList(_levelList);
		
		//create list listner
		_levelList.addListSelectionListener(_controller); // create list listner


		//TODO - cancel list key press
		//TODO - make keylistner listen to frame this.addKeyListener(_controller);
		// creates the split view of the upper window
		 _upperWindow = new JSplitPane (JSplitPane.HORIZONTAL_SPLIT,CreateLevelPanel(),CreateRightPanel());

		

		
		//TODO send board into key listner???
		
		// create board visual represenation 
		_gameBoardDrawer = new BoardDraw();
		//creates a panel containg the visual presentaion of the board
		
		 _lowerPanel = _gameBoardDrawer.DrawGameBoard(_controller.GetBoard());
		
		//splits the frame into 3
		 _fullWindow = new JSplitPane(JSplitPane.VERTICAL_SPLIT,_upperWindow,_lowerPanel);
		
		add (_fullWindow);
		pack();
		setVisible(true);
	}
	
	// level selector panel
	private JPanel CreateLevelPanel(){	
		JPanel levelPanel = new JPanel();

		_scrolList = new JScrollPane(_levelList);
		_levelList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); /// enable selecting only one level

		
		levelPanel.add(_scrolList);	
		
		return levelPanel;
	}
	
	private JPanel CreateRightPanel (){
		
		JPanel rightPanel = new JPanel(new FlowLayout());
		rightPanel.add(_resetButton);
		rightPanel.add(_scoreLabel);
		return rightPanel;
	}
	
	
	public void setScore(int score){
		_score=score;
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
	public void PaintNewBoard (){
		_lowerPanel = _gameBoardDrawer.DrawGameBoard(_controller.GetBoard());
		_fullWindow.setBottomComponent(_lowerPanel);
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sokoban frame = new Sokoban();
	}

}
