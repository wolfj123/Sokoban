package view;
import levelLoader.Cell;
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
		JLabel [][] labelArray = CreateBoard(level);
		JPanel jp = new JPanel(new GridLayout(level.length,level[0].length));
		
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
			for (int j=0;j<level[0].length;j++){
				//paint floor
				if (level[i][j].isEmptyFloor()){
					labelArray[i][j] = new JLabel(_floorIcon);
				}
				//paint correct box position
				else if (level[i][j].hasBox()&level[i][j].isStorage()){
					labelArray[i][j] = new JLabel(_correctCrateLocation);
				}
				//Paint box
				else if (level[i][j].hasBox()){
					labelArray[i][j] = new JLabel(_crateIcon);
				}
				// paint storage
				else if (level[i][j].isStorage()){
					labelArray[i][j] = new JLabel(_storageIcon);
				}
				// Paint Character
				else if (level[i][j].hasPlayer()){
					labelArray[i][j] = new JLabel(_characterIcon);
				}
				else{
					labelArray[i][j] = new JLabel(_wallIcon);
				}
			}
		}
		
		return labelArray;
	}
}
