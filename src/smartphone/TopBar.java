package smartphone;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Objects.Picture;

// TODO: Auto-generated Javadoc
/**
 * The Class BarreMenuSommet.
 * @author Pierre Anken
 */
@SuppressWarnings("serial")
public class TopBar extends JPanel{

	/**
	 * Instantiates a new barre menu sommet.
	 *
	 * @author Mike Wigger & Pierre Anken
	 */
	
	private ImageIcon iconTopBar;
	
	/** The icon top bar2. */
	private ImageIcon iconTopBar2;
	
	/** The network on. */
	private boolean networkOn = false;
	
	/**
	 * Instantiates a new top bar.
	 */
	public TopBar(){
	
		setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		setBackground(Color.black);
		

		//time clock
		SimpleDateFormat formatedTime = new SimpleDateFormat(" HH:mm  ");
		JLabel time = new JLabel(formatedTime.format(new Date()));
		time.setForeground(Color.LIGHT_GRAY);
		time.setFont(new Font("Arial",Font.BOLD,12));

		//network icon
		iconTopBar = Picture.getImageIconFromPath("images/design_software/topBar.png");
		iconTopBar2 = Picture.getImageIconFromPath("images/design_software/topBar2.png");

		//network name
		JLabel networkName = new JLabel("Swisscom");
		networkName.setForeground(Color.LIGHT_GRAY);
		networkName.setFont(new Font("Arial",Font.BOLD,12));
		
		JLabel labelTopBar = new JLabel(iconTopBar);
		
		add(Box.createRigidArea(new Dimension(10,30)));
		add(networkName);
		add(Box.createRigidArea(new Dimension(210,30)));
		add(labelTopBar);
		add(time);

		//update top bar clock
		Timer refreshTime = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent evt){
				time.setText(formatedTime.format(new Date()));
				
				// generate topbar activity
				if(networkOn && (int)(Math.random() * 50)>35){
					labelTopBar.setIcon(iconTopBar2);
					networkOn = false;
				}
				else if((int)(Math.random() * 50)>25){
					labelTopBar.setIcon(iconTopBar);
					networkOn = true;
				}
			}    
		});
		refreshTime.start();	
	}
}
