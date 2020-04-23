// Found on http://www.java2s.com/Tutorial/Java/0261__2D-Graphics/Createashadowedtext.htm
// Adapted by Pierre Anken

package Tools;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.TextLayout;

import javax.swing.JLabel;

// TODO: Auto-generated Javadoc
/**
 * The Class ShadowText.
 * @author Pierre Anken
 */
@SuppressWarnings("serial")
public class ShadowText extends JLabel {

	/** The text. */
	private String text;
	
	  /**
  	 * Instantiates a new shadow text.
  	 *
  	 * @param text the text
  	 */
  	public ShadowText(String text) {
		  super(" "+text+" ");
		  this.text = text;
	  }
	
	  /* (non-Javadoc)
  	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
  	 */
  	public void paint(Graphics g) {
	
	     
	    Graphics2D g1 = (Graphics2D) g;
	    Font font = new Font("Arial", Font.BOLD, 12);
	    TextLayout textLayout = new TextLayout(text, font, g1.getFontRenderContext());

	    g1.setPaint(Color.BLACK);
	    textLayout.draw(g1, 4, 11);
	
	    g1.setPaint(Color.WHITE);
	    textLayout.draw(g1, 3, 10);
	  }

}
