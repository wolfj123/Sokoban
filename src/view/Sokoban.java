package view;
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
	private JScrollPane _scrolList;
	private Vector<String> _levelNames;

	private JButton _resetButton;
	private JLabel _scoreLabel;
	private int _score;
	
	private String text;
	
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
		text = "The Score is: ";
		_score =0;
		_scoreLabel = new JLabel(text + _score);
		
		JSplitPane upperWindow = new JSplitPane (JSplitPane.HORIZONTAL_SPLIT,CreateLevelPanel(),CreateRightPanel());
		add (upperWindow);

		
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
		Sokoban frame = new Sokoban(4);
	}

}
