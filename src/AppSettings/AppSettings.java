package AppSettings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import Objects.Param;
import Objects.Theme;
import smartphone.App;
import smartphone.ContentBlock;
import smartphone.Core;

// TODO: Auto-generated Javadoc
/**
 * The Class AppSettings.
 * @author Pierre Anken
 */
public class AppSettings extends App implements ActionListener{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7457542983855476230L;
	/** The theme list. */
	private JComboBox<String> themeList;
	
	/**
	 * Instantiates a new app settings.
	 */
	public AppSettings(){
		
		super("Settings",false);
		setLayout(new BorderLayout());
	
		
		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center,BoxLayout.Y_AXIS));
		center.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 50));
		center.setBackground(Color.WHITE);
		
		center.add(Box.createRigidArea(new Dimension(0,20)));
		
		//theme selection
		themeList = new JComboBox<String>();
		Iterator<Theme> it = Core.getSp().getListTheme().iterator();
		while(it.hasNext()){
			themeList.addItem(it.next().getName());
		}
		themeList.setActionCommand("themeList");
		themeList.addActionListener(this);
		center.add(new ContentBlock("Themes",themeList));
		
		
		//factory reset (erase all files)
		center.add(Box.createRigidArea(new Dimension(0,20)));
		JButton factoryReset = new JButton("Reset phone");
		factoryReset.setActionCommand("factoryReset");
		factoryReset.addActionListener(this);
		center.add(new ContentBlock("Factory reset",factoryReset));
		center.add(Box.createRigidArea(new Dimension(0,400)));
		
		add(center);
	}

	/* (non-Javadoc)
	 * @see smartphone.App#onLoad()
	 */
	public void onLoad(){
		themeList.setSelectedItem(Core.getSp().getActiveTheme().getName());
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "themeList" ){
			@SuppressWarnings("unchecked")
			JComboBox<String> cb = (JComboBox<String>)e.getSource();
			String selectedTheme = (String)cb.getSelectedItem();
			
			Param.writeUpdateParam(Core.getSp().getSParams(),new Param("theme",selectedTheme));
			Core.getSm().ArrayListToFile(Core.getSp().getSParams(), "Config.cfg");
			
		}
		else if(e.getActionCommand() == "factoryReset" ){
			Core.getSp().factoryReset();	
		}
	}

}
