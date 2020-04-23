package AppGallery;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import Objects.Picture;
import Tools.ComponentWithPadding;

// TODO: Auto-generated Javadoc
/**
 * The Class paramPicture.
 * @author Pierre Anken
 */
@SuppressWarnings("serial")
public class paramPicture extends JPanel implements ActionListener{
	
	/** The a pic. */
	private Picture aPic;
	
	/** The pic container. */
	private ComponentWithPadding picContainer;
	
	/**
	 * Instantiates a new param picture.
	 *
	 * @param a picture
	 * @param picContainer the pic container
	 */
	public paramPicture(Picture aPic, ComponentWithPadding picContainer){
		
		this.aPic = aPic;
		this.picContainer = picContainer;
		
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(200,200));
		setBackground(Color.WHITE);
		
		JButton delPhoto = new JButton("Delete");
		delPhoto.setAlignmentX(CENTER_ALIGNMENT);
		delPhoto.setActionCommand("delete");
		delPhoto.addActionListener(this);
		
		JButton cancel = new JButton("Cancel");
		cancel.setAlignmentX(CENTER_ALIGNMENT);
		cancel.setActionCommand("cancel");
		cancel.addActionListener(this);

		add(Box.createRigidArea(new Dimension(0,60)));
		add(delPhoto);
		add(Box.createRigidArea(new Dimension(0,25)));
		add(cancel);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(((JButton)e.getSource()).getActionCommand().equals("cancel")){
			picContainer.removeAll();
 			ComponentWithPadding paramBox = new ComponentWithPadding(6, new PicturePreview(aPic));
 			paramBox.setBackground(Color.BLACK);
 			picContainer.add(paramBox);
 			picContainer.repaint();
 			picContainer.revalidate();
		}
		else{
			JPanel parent = (JPanel) picContainer.getParent();
			parent.remove(picContainer);
			parent.revalidate();
			aPic.removeFromPicture();
		}
	}
}
