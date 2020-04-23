package smartphone;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Objects.Picture;

// TODO: Auto-generated Javadoc
/**
 * The Class App is used as skeleton for all the applications.
 *
 * @author Pierre Anken
 * @see ButtonApp
 */
public abstract class App extends JPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7489369254686988599L;

	/**  App name. */
	private String name;
	
	/** The icon. */
	private ImageIcon icon;
	
	/** The icon over. */
	private ImageIcon iconOver;
	
	/** The full screen. */
	private boolean fullScreen;
	
	/**
	 * Instantiates a new app.
	 *
	 * @param aName the a name
	 * @param isfullScreen the isfull screen
	 */
	public App(String aName, Boolean isfullScreen){
		name = aName;
		fullScreen = isfullScreen;
		setBackground(Color.WHITE);
		icon = Picture.getImageIconFromPath("images/app/"+aName+".png");
		iconOver = Picture.getImageIconFromPath("images/app/"+aName+"_over.png");

	}
	
	/**
	 * Checks if is full screen.
	 *
	 * @return true, if is full screen
	 */
	public boolean isFullScreen() {
		return fullScreen;
	}
	
	/**
	 * Gets the icon.
	 *
	 * @return the icon
	 */
	public ImageIcon getIcon(){
		return icon;
	}
	
	/**
	 * Gets the icon over.
	 *
	 * @return the icon over
	 */
	public ImageIcon getIconOver(){
		return iconOver;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Component#getName()
	 */
	public String getName(){
		return name;
	}

	/**
	 * On load.
	 */
	public abstract void onLoad();
	
}
