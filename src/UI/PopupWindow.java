package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PopupWindow extends JFrame {
	
	private JPanel contentPane;
	private JLabel picLabel;
	
	/**
	 * @wbp.parser.constructor
	 */
	public PopupWindow(Dimension dim, String imageFile, String title) {
		init(dim, imageFile, title);
	}
	
	public PopupWindow(Dimension dim, ArrayList<JButton> buttons, String imageFile, String title) {
		init(dim, imageFile, title);
		layButtons(buttons);
	}
	
	private void layButtons(ArrayList<JButton> buttonList){
		int interval = 0, startpoint;
		if(buttonList.size() == 1)
			startpoint = 480;
		else if(buttonList.size() == 2)
			startpoint = 360;
		else if(buttonList.size() == 3)
			startpoint = 240;
		else 
			startpoint = 120;
		
		for (JButton button : buttonList){
			button.setBounds(startpoint + interval, 665, 180, 50);
			interval += 220;
			contentPane.add(button);
		}
	}
	
	private void init(Dimension dim, String imageFile, String title){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(new Point(700, 50));
		setResizable(false);
		setSize(dim);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);	
		setContentPane(contentPane);
		setTitle(title);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/repair.png")));
		
		ImageIcon popupIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(imageFile)));
		picLabel = new JLabel(popupIcon);
		picLabel.setBounds(0, 0, dim.width - 4, dim.height - 4);

		contentPane.add(picLabel);
	}
}
