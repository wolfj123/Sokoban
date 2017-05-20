package controller;
import model.*;

public class LevelController {
	
	private BoardModel _boardControl;
	
	public LevelController() {
		_boardControl = new BoardModel();
	}
	
	public int GetNumberOfLevels (){
		return _boardControl.GetNumberOfLevels();
	}

}
