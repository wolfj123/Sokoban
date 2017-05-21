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
	
	public JPanel DrawGameBoard (/*Cell [][] level*/){
		//JLabel [][] labelArray = new JLabel[level.length][level[0].length];
		JLabel [][] labelArray = new JLabel[2][2];
		labelArray[0][0] = new JLabel(_characterIcon);
		labelArray[0][1] = new JLabel(_floorIcon);
		labelArray[1][0] = new JLabel(_floorIcon);
		labelArray[1][1] = new JLabel(_wallIcon);

		
		
		JPanel jp = new JPanel(new GridLayout(2,2));
		
		for (int i=0;i<2;i++){
			for (int j=0;j<2;j++){
				jp.add(labelArray[i][j]);
			}
		}

		
		return jp;
	}
}
