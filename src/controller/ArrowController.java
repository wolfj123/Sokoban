package controller;

import model.EnumDirection.Direction;
import model.BoardModel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ArrowController implements KeyListener{

	private BoardModel _board;
	
	
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
		
		if(direction!=Direction.NONE){
			
			try{
				_board.makeMove(direction);
			}
			catch(Exception ex){
				//TODO: maybe indicate to user that the move is wrong with annoying sound?
			}
		}
		
		// TODO View.drawBoard()
		
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
