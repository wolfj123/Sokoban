package view;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;


public class Sokoban extends JFrame{

	//constructor creates the window and layouts 
	public Sokoban (){
		super ("Sokoban");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


		setVisible(true);
		
		
	}
	// 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sokoban frame = new Sokoban();

	}

}
