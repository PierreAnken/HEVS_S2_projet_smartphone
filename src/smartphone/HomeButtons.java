package smartphone;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Objects.Param;

// TODO: Auto-generated Javadoc
/**
 * The Class HomeButtons.
 * @author Pierre Anken
 */
public class HomeButtons extends JPanel implements ActionListener{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -245161449691390793L;

	/** The background image home. */
	private Image backgroundImageHome;
	
	/** The icon home. */
	private ImageIcon iconHome;
	
	/** The icon home over. */
	private ImageIcon iconHomeOver;
	
	/** The icon home pressed. */
	private ImageIcon iconHomePressed;
	
	/** The home. */
	private JButton home;
	
	/**
	 * Instantiates a new home buttons.
	 *
	 * @param landscape the landscape
	 */
	public HomeButtons(Boolean landscape){


		try {
			if(landscape){
				add(Box.createRigidArea(new Dimension(55,0)));
				setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
				setPreferredSize(new Dimension(133,445));
				backgroundImageHome = ImageIO.read(new File("images/design_hardware/left_ls.png"));
				iconHome = new ImageIcon(ImageIO.read(new File("images/design_hardware/home_ls.jpg")));
				iconHomeOver = new ImageIcon(ImageIO.read(new File("images/design_hardware/home_over_ls.jpg")));
				iconHomePressed = new ImageIcon(ImageIO.read(new File("images/design_hardware/home_pressed_ls.jpg")));
			}
			else{
				add(Box.createRigidArea(new Dimension(0,25)));
				setPreferredSize(new Dimension(525,134));
				setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
				backgroundImageHome = ImageIO.read(new File("images/design_hardware/bottom.png"));
				iconHome = new ImageIcon(ImageIO.read(new File("images/design_hardware/home.jpg")));
				iconHomeOver = new ImageIcon(ImageIO.read(new File("images/design_hardware/home_over.jpg")));
				iconHomePressed = new ImageIcon(ImageIO.read(new File("images/design_hardware/home_pressed.jpg")));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		home = new JButton(iconHome);
		home.setAlignmentX(Component.CENTER_ALIGNMENT);
		home.setBorderPainted(false);
		home.setBorder(null);
		home.setRolloverIcon(iconHomeOver);
		home.setPressedIcon(iconHomePressed);
		home.addActionListener(this);
		
		add(home);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	//bottom pic
	protected void paintComponent(Graphics g){
	      super.paintComponents(g);
	      if (backgroundImageHome != null) {
	         g.drawImage(backgroundImageHome, 0, 0, null);
	      }
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
    {
		//affichage des cartes (accueil)
		if(Param.getParam(Core.getSp().getSParams(),"BootComplete") != null){
			Core.getSp().setPortrait(true);
			Core.getSp().getScreen(false).setHome();
		}
    }
}
