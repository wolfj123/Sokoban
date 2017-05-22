package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.BoardModel;
import model.EnumDirection.Direction;
import view.Sokoban;

public class ControllerClass implements ListSelectionListener, KeyListener {
	
	private BoardModel _board;
	private Sokoban _game;
	private JList<String> _levelList;
	
	public ControllerClass (Sokoban game, JList<String> levelList){
		super();
		_game = game;
		_levelList = levelList;
		_board = new BoardModel();
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			_board = new BoardModel(_levelList.getSelectedIndex());
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		Direction direction = Direction.NONE; 
		int key = e.getKeyCode();
		
		switch(key){ 
		
		case KeyEvent.VK_UP:
			direction = Direction.UP;
		
		case KeyEvent.VK_DOWN:
			direction = Direction.DOWN;
			
		case KeyEvent.VK_LEFT:
			direction = Direction.LEFT;
			
		case KeyEvent.VK_RIGHT:
			direction = Direction.RIGHT;
		}
		
		boolean madeMove = false;
		
		if(direction!=Direction.NONE){
			madeMove = _board.makeMove(direction);
		}
		
		// TODO View.drawBoard()
		
		if(madeMove){
			int currentScore = _game.getScore();
			_game.setScore(currentScore + 1);
		}
		
		if(_board.checkVictory()){
			_game.victory();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
	public int GetNumberOfLevels (){
		return _boardControl.GetNumberOfLevels();
	}


}
