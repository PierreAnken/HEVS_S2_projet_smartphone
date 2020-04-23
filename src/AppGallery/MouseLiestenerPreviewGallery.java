package AppGallery;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import Objects.Picture;
import Tools.ComponentWithPadding;

// TODO: Auto-generated Javadoc
/**
 * The Class MouseLiestenerPreviewGallery.
 * @author Pierre Anken
 */
public class MouseLiestenerPreviewGallery extends MouseAdapter{
	
	/** The pic container. */
	private ComponentWithPadding picContainer;
	
	/** The picture. */
	private Picture picture;
	
	/**
	 * Instantiates a new mouse liestener preview gallery.
	 *
	 * @param picContainer the pic container
	 * @param aPic the a pic
	 */
	public MouseLiestenerPreviewGallery(ComponentWithPadding picContainer,Picture aPic){
		this.picContainer = picContainer;
		picture = aPic;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
	 */
	public void mouseClicked(MouseEvent e) {
 
		picContainer.removeAll();
		ComponentWithPadding paramBox = new ComponentWithPadding(6,new paramPicture(picture,picContainer));
		paramBox.setBackground(Color.BLACK);
		picContainer.add(paramBox);
		picContainer.repaint();
		picContainer.revalidate();
	}
}
