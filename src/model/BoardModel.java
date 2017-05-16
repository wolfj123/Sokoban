package model;
import java.io.IOException;

import levelLoader.*;
import model.EnumCell.CellType;
import model.EnumDirection.Direction;

public class BoardModel {
	
	private LevelLoader _levelLoader = new LevelLoader();
	private Cell[][] _levelGrid;
	
	public BoardModel(){
		//TODO: get level integer and try to load level
		this(0);
		
	}
	
	public BoardModel(int levelNumber){
		//TODO: get level integer and try to load level
		this(levelNumber, "levels");
		
	}
	
	public BoardModel(int levelNumber, String levelsFile){
		try {
			_levelLoader.load(levelsFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(levelNumber<0 | levelNumber>_levelLoader.getLevelsCount()-1)
			throw new IllegalArgumentException("Level does not exist");
		
		_levelGrid = _levelLoader.get(levelNumber);
		
	}
	
	private void loadNewLevel(int levelNumber){
		//TODO: ariel
		//I am pretty sure you can just write here the loading code in the builder and have the 
		//builder use this method
	}
	
	
	private boolean checkLegality(Direction direction){
		//TODO: Jonathan
		return false;
	}
	
	
	//get X,Y coordinates of the player
	private int[] getPlayerCoords(){
		int[] output = new int[2];
		
		boolean foundPlayer = false;

		return null;
	}
	
	private Cell getPlayerCell(){
		int[] output = new int[2];

		for(Cell[] cellColumn: _levelGrid){
			for (Cell cell : cellColumn){
				if(cell.hasPlayer())
					return cell;
			}
		}
		return null;
	}
		
	
	private boolean checkVictory(){
		//TODO
		return false;
	}

	
	//return next X,Y coordinates based on direction
	private int[] nextCoords(int[] coords, Direction direction){
		int[] output = new int[2];
		
		switch(direction){
		
		case UP:
			output[0] = coords[0];
			output[1] = coords[1]-1;
			break;
		
		case DOWN:
			output[0] = coords[0];
			output[1] = coords[1]+1;
			break;
		
		case LEFT:
			output[0] = coords[0]-1;
			output[1] = coords[1];
			break;
		
		case RIGHT:
			output[0] = coords[0]+1;
			output[1] = coords[1];
			break;
		}	
		return output;	
	}
	
	
	/*
	private CellType getCellTypeByCoords(int[] coords){
		if(coords[0]<0 | coords[1]<0 | coords[0]>=_levelGrid.length 
				| coords[1]>=_levelGrid[0].length)
			return CellType.WALL;
		
		Cell cell = _levelGrid[coords[0]][coords[1]];
		
		if(cell.hasPlayer())
			return CellType.PLAYER;
		
	}
	*/
	
	
	
}
