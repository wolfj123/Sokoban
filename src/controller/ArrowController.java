package controller;

import model.EnumDirection.Direction;
import view.Sokoban;
import model.BoardModel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ArrowController implements KeyListener{

	private BoardModel _board;
	private Sokoban _game;
	
	public ArrowController(Sokoban game){
		super();
		_game = game;
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
		
		boolean victory = _board.checkVictory();
		if(victory){
			//TODO make player win	
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

	
}
