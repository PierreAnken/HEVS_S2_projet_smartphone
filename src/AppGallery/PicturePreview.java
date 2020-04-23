package AppGallery;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

import Objects.Picture;

// TODO: Auto-generated Javadoc
/**
 * The Class PicturePreview.
 * @author Pierre Anken
 */
@SuppressWarnings("serial")
public class PicturePreview extends JPanel{
	
	/** The background. */
	private Image background;
	
	/** The pic. */
	private Picture pic;
	
	/**
	 * Instantiates a new picture preview.
	 *
	 * @param a picture
	 */
	public PicturePreview(Picture aPic){
		pic = aPic;
		setPreferredSize(new Dimension(200,200));
		try {
			background = Picture.getImageFromPath(aPic.getCreatePic(Picture.GALLERYPREVIEW));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 /* (non-Javadoc)
 	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
 	 */
 	public void paintComponent(Graphics g){
		   g.drawImage(background,0,0,null);
	 }
	 
	 /**
 	 * Gets the pic.
 	 *
 	 * @return the pic
 	 */
 	public Picture getPic(){
		 return pic;
	 }
}
