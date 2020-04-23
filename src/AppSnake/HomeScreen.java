package AppSnake;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import smartphone.Core;

// TODO: Auto-generated Javadoc
/**
 * The Class HomeScreen.
 * @author Pierre Anken
 */
@SuppressWarnings("serial")
public class HomeScreen extends JPanel implements ActionListener{

	/** The background. */
	private Image background;
	
	/** The app snake. */
	private AppSnake appSnake;
	
	/**
	 * Instantiates a new home screen.
	 *
	 * @param appSnake the app snake
	 */
	public HomeScreen(AppSnake appSnake){
		this.appSnake = appSnake;
		setLayout(new BorderLayout());
		
		JPanel bottom = new JPanel();
		bottom.setLayout(new BoxLayout(bottom,BoxLayout.Y_AXIS));
		bottom.setOpaque(false);
		
		
		JButton start = new JButton("Start new game");
		start.setAlignmentX(CENTER_ALIGNMENT);
		start.addActionListener(this);
		
		bottom.add(start);
		bottom.add(Box.createRigidArea(new Dimension(20,70)));
		
		add(bottom,BorderLayout.SOUTH);
		
		
		try {
			background  = ImageIO.read(new File("images/design_software/snake.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		appSnake.setCard("game");		
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	protected void paintComponent(Graphics g) {
		paintComponents(g);
       	g.drawImage(background, 0, 0, this);
       	
       	Graphics2D g1 = (Graphics2D) g;
       	g1.setPaint(Color.BLACK);
       	Font titleFont = new Font("Arial", Font.BOLD, 30);
       	Font scoreFont = new Font("Arial", Font.BOLD, 20);
       	
       	TextLayout textLayout1 = new TextLayout("Highscores", titleFont, g1.getFontRenderContext());
       	textLayout1.draw(g1, 270, 100);
       	int ligne = 1;
       	
       	@SuppressWarnings("unchecked")
		ArrayList<ScoreSnake> scoreList = Core.getSm().fileToArrayList("SnakeScore.list");
		for(ScoreSnake aScore : scoreList){
			TextLayout textLayout2 = new TextLayout(ligne+"   score: "+aScore.getScore()+"    -   "+aScore.getDate() , scoreFont, g1.getFontRenderContext());
			textLayout2.draw(g1, 200, 150+ligne*20);
			ligne++;
		}
	 }
}
