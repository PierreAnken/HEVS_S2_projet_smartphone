package smartphone;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Objects.Param;
import Objects.Picture;

// TODO: Auto-generated Javadoc
/**
 * The Class Boot.
 * @author Pierre Anken
 */
@SuppressWarnings("serial")
public class Boot extends JPanel{

	/** The android boot. */
	private ImageIcon androidBoot;
	
	/** The boot statut. */
	private int bootStatut = 0;

	/**
	 * Instantiates a new boot.
	 */
	public Boot(){
		setBackground(Color.black);
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		androidBoot = Picture.getImageIconFromPath("images/design_software/boot_android.jpg");
		
		JLabel imageAndroid = new JLabel(androidBoot,SwingConstants.CENTER);
		imageAndroid.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JProgressBar loadBar = new JProgressBar();
		loadBar.setForeground(Color.WHITE);
		loadBar.setBackground(Color.BLACK);
		loadBar.setBorder(null);
		loadBar.setMaximumSize(new Dimension(350,2));
		
		add(imageAndroid);
		add(Box.createRigidArea(new Dimension(0,75)));
		add(loadBar);
	    
	    Timer refreshTime = new Timer(50, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
					
					if(bootStatut<100){
						bootStatut+=2;
						loadBar.setValue(bootStatut);
					}
					else{
						((Timer)evt.getSource()).stop();
						Param.writeUpdateParam(Core.getSp().getSParams(), new Param("BootComplete","ok"));
						Core.getSm().ArrayListToFile(Core.getSp().getSParams(), "Config.cfg");
						Core.getSp().setPortrait(true);
						Core.getSp().getScreen(false).setHome();
						
					}
			    }    
			});
		refreshTime.start();
	}

}
