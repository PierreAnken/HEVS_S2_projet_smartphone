package smartphone;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import Tools.ShadowText;

// TODO: Auto-generated Javadoc
/**
 * The Class ButtonApp.
 * @author Pierre Anken
 */
public class ButtonApp extends JPanel implements ActionListener{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7386314236430761630L;
	/** The app attached. */
	App appAttached;
	
	/**
	 * Instantiates a new button app.
	 *
	 * @param anApp the an app
	 */
	public ButtonApp(App anApp){
			appAttached = anApp;
			
			setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
			JButton theButton = new JButton();
			theButton.setIcon(appAttached.getIcon());
			theButton.setRolloverIcon(appAttached.getIconOver());
			theButton.setBorderPainted(false);
			theButton.setBorder(null);
			theButton.setContentAreaFilled(false); 
			theButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			theButton.addActionListener(this);
			
			ShadowText text = new ShadowText(appAttached.getName());
			text.setAlignmentX(Component.CENTER_ALIGNMENT);
			text.setForeground(Core.getSp().getActiveTheme().getTextColor());

			add(theButton);
			add(text);
			
			setOpaque(false);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
    {
		//app launcher
		
		Core.getSp().getScreen(appAttached.isFullScreen()).setCard(appAttached.getName());
		if(appAttached.isFullScreen())
			Core.getSp().setLandscape();
    }
}
