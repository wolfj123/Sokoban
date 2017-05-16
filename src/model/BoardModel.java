package model;
import java.io.IOException;

import levelLoader.*;
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
	
	private boolean checkLegality(Direction direction){
		//TODO: Jonathan
		return false;
	}
	
	
	//get X,Y coordinates of the player
	private int[] getPlayerCoords(){
		int[] output = new int[2];
		
		
		
		return null;
	}
	
	
	
	
	private void loadNewLevel(int levelNumber){
		//TODO: ariel
		//I am pretty sure you can just write here the loading code in the builder and have the 
		//builder use this method
	}
	
}
