package Tools;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

// TODO: Auto-generated Javadoc
/**
 * The Class JLabelFormated.
 * @author Pierre Anken
 */
public class JLabelFormated extends JLabel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7644389352154225638L;

	/**
	 * Instantiates a new j label formated.
	 *
	 * @param aFont the a font
	 * @param aText the a text
	 */
	public JLabelFormated(Font aFont, String aText){
		setFont(aFont);
		if(aText.equals(""))
			setText(" ");
		else{
			setText(aText);
		}
	}
	
	/**
	 * Instantiates a new j label formated.
	 *
	 * @param aFont the a font
	 */
	public JLabelFormated(Font aFont){
		setFont(aFont);
	}
	
	/**
	 * Instantiates a new j label formated.
	 *
	 * @param aFont the a font
	 * @param aText the a text
	 * @param aColor the a color
	 */
	public JLabelFormated(Font aFont, String aText, Color aColor){
		setFont(aFont);
		setText(aText);
		setForeground(aColor);
	}
	
}
