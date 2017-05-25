package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.BoardModel;
import view.Sokoban;

public class UndoController implements ActionListener{
	
	private BoardModel _board;
	private Sokoban _game;
	
	public UndoController(Sokoban game, BoardModel board){
		_game = game;
		_board = board;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(_board.undo()){
			int currentScore = _game.getScore();
			_game.setScore(currentScore-1);
		}
	}
	
}
