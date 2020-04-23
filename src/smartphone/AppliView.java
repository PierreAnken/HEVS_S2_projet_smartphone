package smartphone;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class AppliView.
 * @author Pierre Anken
 */
public class AppliView extends JPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8303268463264850683L;
	/** The background image. */
	private Image backgroundImage;
	
	/**
	 * Instantiates a new appli view.
	 */
	public AppliView(){
		setLayout(new GridLayout(0,3, 25, 25));
		
		Core.getSp();
		int nbrLignes = (int) Math.ceil((double)Smartphone.getAppList().size()/3);
		int marginBottom = 800-nbrLignes*180;
		
		setBorder(BorderFactory.createEmptyBorder(15, 15, marginBottom, 15));
		setOpaque(false);
		
		try {
			backgroundImage = ImageIO.read(new File("images/design_software/background_"+Core.getSp().getActiveTheme().getName()+".jpg"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	 	Core.getSp();
		for(App anApp : Smartphone.getAppList())
			add(new ButtonApp(anApp));
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	//background pic
	protected void paintComponent(Graphics g) {
	      super.paintComponents(g);
	      if (backgroundImage != null) {
	        g.drawImage(backgroundImage, 0, 0, null);
	      }
	  }
}