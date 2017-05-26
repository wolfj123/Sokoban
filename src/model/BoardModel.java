package model;

import java.io.IOException;
import java.util.Stack;
import java.util.Vector;
import levelLoader.*;
import model.EnumDirection.Direction;

public class BoardModel {
	
	private LevelLoader _levelLoader = new LevelLoader();
	private Cell[][] _levelGrid;
	private Vector<Cell> _storageVector;
	private Stack<Cell[][]> _undoStack;
	
	public BoardModel(){
		this(0);
	}
	
	public BoardModel(int levelNumber){
		this(levelNumber, "levels.txt");
	}
	
	public BoardModel(int levelNumber, String levelsFile){
		try {
			_levelLoader.load(levelsFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		loadNewLevel(levelNumber);	
	}
	
	//get Cell array
	public Cell[][] getCellArray (){
		return _levelGrid;
	}
	
	
	//returns the number of levels in the file
	public int getNumberOfLevels (){
		return _levelLoader.getLevelsCount();
	}
	
    /**
     * Loads all the levels to the internal levels buffer
     * @param direction - the direction the player wishes to go
     * @return boolean representing whether a move was made
     */
	public boolean makeMove(Direction direction){
		
		if(checkVictory()) return false;
		
		Cell player = getPlayerCell();
		Cell nextCell = getNextCell(player, direction);
		Cell nextNextCell = getNextCell(nextCell, direction);
			
		boolean isLegal = false;

		//move to next empty cell
		if(nextCell.isEmptyFloor()){
			isLegal = true;
			//Cell[][] previousBoard = _levelGrid.clone();
			Cell[][] previousBoard = cloneCellArray(_levelGrid);
			_undoStack.push(previousBoard);
			
			player.set_hasPlayer(false);
			nextCell.set_hasPlayer(true);
		}

		//move player and box to next cells
		if(nextNextCell!=null && nextCell.hasBox() & nextNextCell.isEmptyFloor()){
			isLegal = true;
			//Cell[][] previousBoard = _levelGrid.clone();
			Cell[][] previousBoard = cloneCellArray(_levelGrid);
			_undoStack.push(previousBoard);
			
			player.set_hasPlayer(false);
			nextCell.set_hasPlayer(true); nextCell.set_hasBox(false);
			nextNextCell.set_hasBox(true);
		}
		return isLegal;
	}
	
	public boolean checkVictory(){
		for(Cell storage : _storageVector){
			if(!storage.hasBox())
				return false;
		}
		return true;
	}
	
	public boolean undo(){
		if(!_undoStack.isEmpty()){
			_levelGrid = _undoStack.pop();
			storageVectorInit();
			return true;
		}
		else{
			return false;
		}
	}
	
	public static Cell[][] cloneCellArray(Cell[][] arr){
		if(arr == null)
			return null;

		int n = arr.length;
		int m = arr[0].length;
		Cell[][] output = new Cell[n][m];
		
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				output[i][j] = arr[i][j].clone();
			}
		}
		return output;
	}
	
	private void loadNewLevel(int levelNumber){
		if(levelNumber<0 | levelNumber>_levelLoader.getLevelsCount()-1)
			throw new IllegalArgumentException("Level does not exist");
		
		_levelGrid = _levelLoader.get(levelNumber);
		storageVectorInit();
		_undoStack = new Stack<Cell[][]>();
	}
	
	//initialize the storage list
		private void storageVectorInit(){
			_storageVector = new Vector<>();
			for(Cell[] cellColumn: _levelGrid){
				for (Cell cell : cellColumn){
					if(cell.isStorage())
						_storageVector.add(cell);
				}
			}
		}
	
	//return next Cell based on direction
		private Cell getNextCell(Cell cell, Direction direction){
			int x = cell._x;
			int y = cell._y;
			
			int nextX = x;
			int nextY = y;
			
			switch(direction){
			
			case UP:
				nextY -= 1;
				break;
			
			case DOWN:
				nextY += 1;
				break;
			
			case LEFT:
				nextX -= 1;
				break;
			
			case RIGHT:
				nextX += 1;
				break;
			}	

			if(nextX<0 | nextX>=_levelGrid.length |
					nextY<0 | nextY>=_levelGrid[0].length)
				return null;
			
			return _levelGrid[nextX][nextY];
		}
	
	private Cell getPlayerCell(){
		for(Cell[] cellColumn: _levelGrid){
			for (Cell cell : cellColumn){
				if(cell.hasPlayer())
					return cell;
			}
		}
		return null;
	}
	
}
