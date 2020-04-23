package AppGallery;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import AppContacts.ListContact;
import Objects.Picture;
import smartphone.App;
import smartphone.Core;

// TODO: Auto-generated Javadoc
/**
 * The Class AppGallery.
 * @author Pierre Anken et Mike Wigger
 */


@SuppressWarnings("serial")
public class AppGallery extends App implements ActionListener{
	
	/** The scroll picture list. */
	private static JScrollPane scrollPictureList;
	
	/**
	 * Instantiates a new app gallery.
	 */
	public AppGallery(){
		super("Gallery",false);
		setLayout(new BorderLayout());
		scrollPictureList = new JScrollPane(new ListPicture());
		scrollPictureList.setPreferredSize(Core.getSp().getCenterAppWithBotBarSize());
		scrollPictureList.setBorder(null);
		scrollPictureList.getVerticalScrollBar().setUnitIncrement(16);
		add(scrollPictureList,BorderLayout.CENTER);
		
		//toolbar
		JPanel toolbar = new JPanel();
		toolbar.setBackground(Core.getSp().getActiveTheme().getThemeColor());
		
		JButton impPicture = new JButton("Import new picture");
		impPicture.setActionCommand("impPicture");
		impPicture.addActionListener(this);
		toolbar.add(impPicture);
		
		add(toolbar, BorderLayout.SOUTH);
	}
	
	
	/* (non-Javadoc)
	 * @see smartphone.App#onLoad()
	 */
	public void onLoad(){
	}
	
	/**
	 * Sets the pic card.
	 *
	 * @param picName the new pic card
	 */
	public void setPicCard(String picName){
		((CardLayout)this.getLayout()).show(this,picName);
	}


	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(((JButton)e.getSource()).getActionCommand().equals("impPicture")){
			new Picture();
			refreshGallery();
		}
	}
	
	/**
	 * Refresh gallery.
	 */
	public static void refreshGallery(){
		scrollPictureList.getViewport().removeAll();
		scrollPictureList.getViewport().add(new ListPicture());
		scrollPictureList.getVerticalScrollBar().setValue(scrollPictureList.getVerticalScrollBar().getMaximum());
	}

	/**
	 * Gets the scroll picture list.
	 *
	 * @return the scroll picture list
	 */
	public JScrollPane getScrollPictureList(){
		return scrollPictureList;
	}
}
