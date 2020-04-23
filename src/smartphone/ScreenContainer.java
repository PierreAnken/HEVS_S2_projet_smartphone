package smartphone;

import java.awt.BorderLayout;

import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class ScreenContainer.
 * @author Pierre Anken
 */
@SuppressWarnings("serial")
public class ScreenContainer extends JPanel{
	
	/**
	 * Instantiates a new screen container.
	 *
	 * @param theContent the the content
	 * @param topBar the top bar
	 */
	public ScreenContainer(ScreenContent theContent, TopBar topBar){
		setLayout(new BorderLayout());
		
		if(topBar!= null)
			add(topBar, BorderLayout.NORTH);
		add(theContent, BorderLayout.CENTER);
	}

}
