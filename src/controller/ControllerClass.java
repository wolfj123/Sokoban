package controller;
import levelLoader.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.BoardModel;
import model.EnumDirection.Direction;
import view.Sokoban;

public class ControllerClass implements ListSelectionListener, KeyListener, ActionListener {
	
	private BoardModel _board;
	private Sokoban _game;
	private JList<String> _levelList;
	
	public ControllerClass (Sokoban game){
		super();
		_game = game;
		_levelList = null;
		_board = new BoardModel();
	}
	
	// updates the level list
	public void UpdateJList (JList<String> levelList){
		_levelList = levelList;
	}
	
	
	//get the game board
	public Cell[][] getBoard (){
		return _board.getCellArray();
	}
	
	//List Listner
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			_board = new BoardModel(_levelList.getSelectedIndex());
			boardChanged(); 
		}
	}
	
	//updates gui board
	private void boardChanged (){
		//TODO paint new score and update score
		_game.paintNewBoard();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Direction direction = Direction.NONE; 
		int key = e.getKeyCode();
		
		switch(key){ 
		
		case KeyEvent.VK_UP:
			direction = Direction.UP;
			break;
			
		case KeyEvent.VK_DOWN:
			direction = Direction.DOWN;
			break;
			
		case KeyEvent.VK_LEFT:
			direction = Direction.LEFT;
			break;
			
		case KeyEvent.VK_RIGHT:
			direction = Direction.RIGHT;
			break;
		}
		
		boolean madeMove = false;
		
		if(direction!=Direction.NONE){
			madeMove = _board.makeMove(direction);
		}
		
		if(madeMove){
			int currentScore = _game.getScore();
			_game.setScore(currentScore + 1);
			boardChanged();
		}
		
		if(_board.checkVictory()){
			_game.victory();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
	public int getNumberOfLevels (){
		return _board.getNumberOfLevels();
	}
	
	//reset button actionListner
	@Override
	public void actionPerformed(ActionEvent e) {
		_board = new BoardModel(_levelList.getSelectedIndex());
		boardChanged();
	}


}
