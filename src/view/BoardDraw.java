package view;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
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
		_characterIcon = new ImageIcon("Character4.png");
		_floorIcon = new ImageIcon ("Ground_Grass.png");
		_crateIcon = new ImageIcon ("CrateDark_Black.png");
		_storageIcon = new ImageIcon ("EndPoint_Black.png");
		_wallIcon = new ImageIcon ("Wall_Gray.png");
		_correctCrateLocation = new ImageIcon ("Crate_Yellow.png");
	}
	
	public JPanel DrawGameBoard (){
		
		return null;
	}
}
