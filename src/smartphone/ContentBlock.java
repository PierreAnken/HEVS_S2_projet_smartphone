package smartphone;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class ContentBlock.
 * @author Pierre Anken
 */
public class ContentBlock extends JPanel{
	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2573913439419742969L;

	/**
	 * Instantiates a new content block.
	 *
	 * @param Title the title
	 * @param comp the comp
	 */
	public ContentBlock(String Title, JComponent comp){
		
		if(!(comp instanceof JButton))
			comp.setBackground(Color.WHITE);
		add(comp);
		setBorder(BorderFactory.createTitledBorder(Title));
		setBackground(Color.WHITE);
		
	}
}
