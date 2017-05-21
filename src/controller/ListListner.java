package controller;
import model.*;
import javax.swing.JList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

 

public class ListListner implements ListSelectionListener  {
	
	private JList<String> _levelList;
	private BoardModel _board;
	
	public ListListner(JList<String> levelList) {
		_levelList = levelList;

	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			_board = new BoardModel(_levelList.getSelectedIndex());
		}
	}
	
	public BoardModel GetNewBoard (){
		return _board;
	}




}

