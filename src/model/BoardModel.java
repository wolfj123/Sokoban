package model;
import java.io.IOException;
import java.util.Vector;

import levelLoader.*;
import model.EnumDirection.Direction;

public class BoardModel {
	
	private LevelLoader _levelLoader = new LevelLoader();
	private Cell[][] _levelGrid;
	private Vector<Cell> _storageVector;
	
	public BoardModel(){
		this(0);
		
	}
	
	public BoardModel(int levelNumber){
		this(levelNumber, "levels");
		
	}
	
	public BoardModel(int levelNumber, String levelsFile){
		try {
			_levelLoader.load(levelsFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(levelNumber<0 | levelNumber>_levelLoader.getLevelsCount()-1)
			throw new IllegalArgumentException("Level does not exist");
		
		//TODO: maybe throw an exception if the width is 0 ? it might mess up the getNextCell
		_levelGrid = _levelLoader.get(levelNumber);
		_storageVector = new Vector<>();
		
		
		//initialize the storage list
		for(Cell[] cellColumn: _levelGrid){
			for (Cell cell : cellColumn){
				if(cell.isStorage())
					_storageVector.add(cell);
			}
		}
		
		
	}
	
	private void loadNewLevel(int levelNumber){
		//TODO: ariel
		//I am pretty sure you can just write here the loading code already in the builder and have the 
		//builder use this method
	}
	
	
    /**
     * Loads all the levels to the internal levels buffer
     * @param direction - the direction the player wishes to go
     * @return Cell[][] represeting the new 
     * @throws IOException 
     */
	public Cell[][] makeMove(Direction direction){
		//TODO: NOT FINISHED!!
		
		Cell player = getPlayerCell();
		Cell nextCell = getNextCell(player, direction);
		Cell nextNextCell = getNextCell(nextCell, direction);
		
		if(nextCell==null){
			return _levelGrid;
		}

		//move to next empty cell
		if(nextCell.isEmptyFloor()){
			player.set_hasPlayer(false);
			nextCell.set_hasPlayer(true);
		}

		//move player and box to next cells
		if(nextNextCell!=null && nextCell.hasBox() & nextNextCell.isEmptyFloor()){
			player.set_hasPlayer(false);
			nextCell.set_hasPlayer(true); nextCell.set_hasBox(false);
			nextNextCell.set_hasBox(true);
		}
		
		return _levelGrid;
	}
	
	
	
	/*
	private boolean checkLegality(Direction direction){
		Cell player = getPlayerCell();
		Cell nextCell = getNextCell(player, direction);
		Cell nextNextCell = getNextCell(nextCell, direction);
		
		if(nextCell==null)
			return false;
		
		if(nextCell.isEmptyFloor())
			return true;
		
		if(nextNextCell!=null && nextCell.hasBox() & nextNextCell.isEmptyFloor())
			return true;
		
		
		return false;
		
		//might need to use duplicate code in the makeMove method which implies that 
		//this method is unnecessary

	}
	
	*/
	
	private boolean checkVictory(){
		for(Cell storage : _storageVector){
			if(!storage.hasBox())
				return false;
		}
		return true;
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
		
	
	//return next X,Y coordinates based on direction
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
	
	

	
	
}
