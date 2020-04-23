package AppGallery;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;

import Objects.Picture;
import Tools.ComponentWithPadding;
import smartphone.Core;

// TODO: Auto-generated Javadoc
/**
 * The Class ListPicture.
 * @author Pierre Anken
 */
@SuppressWarnings("serial")
public class ListPicture extends JPanel{
	
	/**
	 * Instantiates a new list picture.
	 */
	public ListPicture(){
		setLayout(new GridLayout(0,2));
		setBackground(Core.getSp().getActiveTheme().getThemeColor());
		for(Picture aPic : Core.getSp().getListPicture()){
			ComponentWithPadding picPad = new ComponentWithPadding(6, new PicturePreview(aPic));
			picPad.setBackground(Color.BLACK);
			picPad.addMouseListener(new MouseLiestenerPreviewGallery(picPad,aPic));
			add(picPad);
		}
		setBackground(Color.BLACK);
	}
}
