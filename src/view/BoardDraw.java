package view;
import levelLoader.Cell;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders.SplitPaneBorder;
import levelLoader.Cell;

public class BoardDraw {
	private ImageIcon _characterIcon;
	private ImageIcon _floorIcon;
	private ImageIcon _crateIcon;
	private ImageIcon _storageIcon;
	private ImageIcon _wallIcon;
	private ImageIcon _correctCrateLocation;

	public BoardDraw (){
		_characterIcon = new ImageIcon(this.getClass().getResource("/Character4.png"));
		_floorIcon = new ImageIcon (this.getClass().getResource("/Ground_Grass.png"));
		_crateIcon = new ImageIcon (this.getClass().getResource("/CrateDark_Black.png"));
		_storageIcon = new ImageIcon (this.getClass().getResource("/EndPoint_Black.png"));
		_wallIcon = new ImageIcon (this.getClass().getResource("/Wall_Gray.png"));
		_correctCrateLocation = new ImageIcon (this.getClass().getResource("/Crate_Yellow.png"));
	}
	
	public JPanel DrawGameBoard (Cell [][] level){
		level = transpose(level);
		JLabel [][] labelArray = CreateBoard(level);
		JPanel jp = new JPanel();
		Color backgroundColor = new Color (0,170,0);
		jp.setBackground(backgroundColor);
		jp.setLayout(new GridLayout(level.length,level[0].length));
		
		
		// insert the jlabel array into panel
		for (int i=0;i<level.length;i++){
			for (int j=0;j<level[0].length;j++){
				jp.add(labelArray[i][j]);
			}
		}

		return jp;
	}
	
	// create the Jlabel array according to board
	private JLabel [][] CreateBoard(Cell [][] level){
		
		JLabel [][] labelArray = new JLabel[level.length][level[0].length];
		
		for (int i=0;i<level.length;i++){
			for (int j=0;j<level[i].length;j++){
				//Cell c = level[i][j];
				//paint floor
				// Paint Character
				if (level[i][j].hasPlayer()){
					labelArray[i][j] = new JLabel(_characterIcon);
				}
				//paint correct box position
				else if (level[i][j].hasBox()&level[i][j].isStorage()){
					labelArray[i][j] = new JLabel(_correctCrateLocation);
				}
				// paint storage
				else if (level[i][j].isStorage()){
					labelArray[i][j] = new JLabel(_storageIcon);
				}
				else if (level[i][j].isEmptyFloor()){
					//labelArray[i][j] = new JLabel(_floorIcon);
					labelArray[i][j] = new JLabel();
				}
				//Paint box
				else if (level[i][j].hasBox()){
					labelArray[i][j] = new JLabel(_crateIcon);
				}
				else{
					labelArray[i][j] = new JLabel(_wallIcon);
				}
			}
		}
		
		return labelArray;
	}
	
	private Cell[][] transpose (Cell[][] orig){
		Cell[][] transposed = new Cell[orig[0].length][orig.length];
		for (int i=0;i<orig[0].length;i++){
			for (int j=0;j<orig.length;j++){
				transposed[i][j]=orig[j][i];
			}
		}
		return transposed;
	}
}

// TODO update score label