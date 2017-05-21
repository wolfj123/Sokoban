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
	private ListListner _listListner; // contains list listner
	private JScrollPane _scrolList;
	private Vector<String> _levelNames; //list of level names

	private JButton _resetButton;
	private JLabel _scoreLabel;
	private int _score;
	
	private String _scoreText;
	
	private BoardDraw _gameBoardDrawer;
	
	//constructor creates the window and layouts 
	public Sokoban (int numberOfLevels){
		super ("Sokoban");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//this.setResizable(false); 
		
		_levelNames = new Vector<String>();
		//create level names
		for (int i=1;i<=numberOfLevels;i++){
			_levelNames.add("Level " + i);
		}
		
		_resetButton = new JButton("Reset Game");
		_scoreText = "The Score is: ";
		_score =0;
		_scoreLabel = new JLabel(text + _score);
		

		// creates the split view of the upper window
		JSplitPane upperWindow = new JSplitPane (JSplitPane.HORIZONTAL_SPLIT,CreateLevelPanel(),CreateRightPanel());
		
		// set the initial level value to 0
		_levelList.setSelectedIndex(0); 
		
		// gets the new board after changing level
		BoardModel currentBoard = _listListner.GetNewBoard();
		
		//TODO send board into key listner???
		
		_gameBoardDrawer = new BoardDraw();
		
		//creates a panel containg the visual presentaion of the board
		JPanel lowerPanel = _gameBoardDrawer.DrawGameBoard(currentBoard.GetCellArray());
		
		//splits the frame into 3
		JSplitPane fullWindow = new JSplitPane(JSplitPane.VERTICAL_SPLIT,upperWindow,lowerPanel);
		
		add (fullWindow);

		
		
		pack();
		setVisible(true);
	}
	
	// level selector panel
	private JPanel CreateLevelPanel(){	
		JPanel levelPanel = new JPanel();
		
		//create scroll list and scroll pane
		_levelList = new JList<String>(_levelNames);
		_scrolList = new JScrollPane(_levelList);
		_levelList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); /// enable selecting only one level
		//create list listner
		_listListner = new ListListner(_levelList);
		_levelList.addListSelectionListener(_listListner); // create list listner
		
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Sokoban frame = new Sokoban(4/*change to number of levels in text file*/);
	}

}
