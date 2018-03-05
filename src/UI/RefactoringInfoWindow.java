package UI;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DataHandling.RefactoringOptions;
import RefactoringDetectors.RefactoringDetector;
import RefactoringDetectors.RefactoringDetector;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.awt.Cursor;

/*	This class handles the creation of the 3-slide information window for each refactoring.
 * 	Each of the 3-slides is an image displayed in the JLabel picLabel component. The user can
 * 	browse between slides using the arrow keys on the top left of the window. The first slide
 * 	contains the "Motivation" section that describes why you should perform the said refactoring.
 * 	The second slide is the "Example" section containing a simple code example on how to perform the
 * 	said refactoring. The third and last slide contains information about the available automated
 * 	tools for this refactoring. If there automated detection for the said refactoring is provided by RTA then
 *  a "Start Identification" button appears on the third slide. If RTA does not provide with automated detection
 *  then a message prompting the user to click on "here" appears containing a link to a paper on refactoring identification.
 *  
 *  The data needed for the creation window (paths for each image slide, window title, corresponding detector 
 *  along with its data) are passed on this class' constructor via a RefactoringOptions object.
 */
@SuppressWarnings("serial")
public class RefactoringInfoWindow extends JFrame {

	private JPanel contentPane;
	private int currentSlide;
	private JLabel picLabel, hereLabel;
	private JButton prevPageButton, nextPageButton, identificationButton;

	public RefactoringInfoWindow(final RefactoringOptions options) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(new Point(400, 0));
		setResizable(false);
		setSize(new Dimension(1100, 660));
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);	
		setContentPane(contentPane);
		setTitle(options.getRefactoringTitle());
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/repair.png")));		
		
		ImageIcon motivationIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(options.getMotivationPath())));
		ImageIcon exampleIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(options.getExamplePath())));
		ImageIcon identificationIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(options.getIdentificationPath())));

		ImageIcon leftArrow = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/leftArrowSmall.png")));
		ImageIcon rightArrow = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/rightArrowSmall.png")));
		
		final int MOTIVATION = 1, EXAMPLE = 2, IDENTIFICATION = 3;
		currentSlide = MOTIVATION;
		picLabel = new JLabel(motivationIcon);			
		picLabel.setBounds(0, 0, 1085, 625);
		
		hereLabel = new JLabel("here.");
		hereLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hereLabel.setForeground(Color.BLUE);
		hereLabel.setFont(new Font("Calibri", Font.PLAIN, 35));
		hereLabel.setBounds(928, 299, 95, 44);
		hereLabel.setVisible(false);
		hereLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					URL url = new URL("http://www.researchgate.net/publication/264791334_Identifying_Refactoring_Opportunities_in_Object-Oriented_Code_A_Systematic_Literature_Review");
					Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
					if((desktop != null)&&(desktop.isSupported(Desktop.Action.BROWSE)))
						desktop.browse(url.toURI());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		contentPane.add(hereLabel);
		
		prevPageButton = new JButton();
		prevPageButton.setToolTipText("Previous Page");
		prevPageButton.setFocusable(false);
		prevPageButton.setBackground(Color.WHITE);
		prevPageButton.setBounds(18, 11, 55, 55);
		prevPageButton.setIcon(leftArrow);
		prevPageButton.setEnabled(false);
		prevPageButton.addActionListener(e -> {
			if(currentSlide == EXAMPLE) {
				picLabel.setIcon(motivationIcon);
				currentSlide = MOTIVATION;
				identificationButton.setVisible(false);
				prevPageButton.setEnabled(false);
				if (options.getIdentificationPath().equals("")) 
					nextPageButton.setEnabled(true);
			} else {
				picLabel.setIcon(exampleIcon);
				currentSlide = EXAMPLE;
				identificationButton.setVisible(false);
				nextPageButton.setEnabled(true);
				hereLabel.setVisible(false);
			}
		});
		contentPane.add(prevPageButton);
		
		nextPageButton = new JButton();
		nextPageButton.setToolTipText("Next Page");
		nextPageButton.setFocusable(false);
		nextPageButton.setIcon(rightArrow);
		nextPageButton.setBackground(Color.WHITE);
		nextPageButton.setBounds(83, 11, 55, 55);
		nextPageButton.addActionListener(e -> {
			if(currentSlide == MOTIVATION) {
				picLabel.setIcon(exampleIcon);
				currentSlide = EXAMPLE;
				identificationButton.setVisible(false);
				prevPageButton.setEnabled(true);
				if (options.getIdentificationPath().equals("")) 
					nextPageButton.setEnabled(false);
			} else {
				picLabel.setIcon(identificationIcon);
				currentSlide = IDENTIFICATION;
				nextPageButton.setEnabled(false);
				if(options.getIdentificationPath().startsWith("/images/CustomIdentification"))
					identificationButton.setVisible(true);
				if(options.getIdentificationPath().equals("/images/NoIdentificationNoTool.png"))
					hereLabel.setVisible(true);	
			}
		});
		contentPane.add(nextPageButton);
		
		identificationButton = new JButton("Start Identification");
		identificationButton.setToolTipText("Identify opportunities for this refactoring.");
		identificationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(options.getDetectorData()!=null)	{
					if(options.getMap().getSelectionInfo().getSelectedProject() == null)
						JOptionPane.showMessageDialog(contentPane, "You need to select a java file or a package from you Package Explorer first.", "Warning", JOptionPane.WARNING_MESSAGE);
					else {
						RefactoringDetector detector = options.getDetector();
						//Start the identification-detection process
						JFrame identificationFrame = detector.getDetectorFrame(options.getDetectorData());
						//Check to see if any opportunities detected
						if(detector.opportunitiesFound())
							//View the identification-detection window containing the suggested results
							identificationFrame.setVisible(true);
						else
							JOptionPane.showMessageDialog(contentPane, "There are no opportunities for this refactoring choice.", "Information", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		identificationButton.setFont(new Font("Arial", Font.BOLD, 16));
		if (options.getIdentificationPath().equals("/images/CustomIdentificationPlusTool.png")) 
			identificationButton.setBounds(435, 211, 188, 48);
		else 
			identificationButton.setBounds(435, 311, 188, 48);
		identificationButton.setVisible(false);
		contentPane.add(identificationButton);
		contentPane.add(picLabel);	
	}
}