package smartphone;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class AppContainer.
 * @author Mike Wigger
 */
@SuppressWarnings("serial")
public class AppContainer extends JPanel{
	
	/**
	 * Instantiates a new app container.
	 *
	 * @param anApp the an app
	 */
	public AppContainer(App anApp){
		setLayout(new BorderLayout());
		if(!anApp.isFullScreen()){
			
			JPanel appTitle = new JPanel();
			appTitle.setLayout(new BoxLayout(appTitle,BoxLayout.X_AXIS));
			appTitle.setBackground(Core.getSp().getActiveTheme().getThemeColor());
			appTitle.setBorder(null);
			
			//Creating the Label for the name of the view
			JLabel nameView = new JLabel(anApp.getName());
			nameView.setFont(new Font("Arial", Font.BOLD,25));
			nameView.setForeground(Core.getSp().getActiveTheme().getTextColor());
			
			appTitle.add(Box.createRigidArea(new Dimension(10,40)));
			appTitle.add(nameView);
			
			add(appTitle,BorderLayout.NORTH);
		}
		add(anApp,BorderLayout.CENTER);
	}
}
