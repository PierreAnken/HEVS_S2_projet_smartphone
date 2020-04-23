package Tools;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class ComponentWithPadding.
 * @author Pierre Anken
 */
public class ComponentWithPadding extends JPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8349737177611592191L;
	/** The a comp. */
	private Component aComp;
	private int paddingSize;
	
	/**
	 * Instantiates a new component with padding.
	 *
	 * @param paddingSize the padding size
	 * @param aComp the a comp
	 */
	public ComponentWithPadding(int paddingSize, Component aComp) {
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		this.aComp = aComp;
		
		add(Box.createRigidArea(new Dimension(0,paddingSize)),BorderLayout.NORTH);
		add(Box.createRigidArea(new Dimension(paddingSize,0)),BorderLayout.WEST);
		add(Box.createRigidArea(new Dimension(paddingSize,0)),BorderLayout.EAST);
		add(Box.createRigidArea(new Dimension(0,paddingSize)),BorderLayout.SOUTH);
		add(aComp,BorderLayout.CENTER);
	}
	
	/**
	 * Gets the center.
	 *
	 * @return the center
	 */
	public Component getCenter(){
		return aComp;
	}
	
	public void setCenter(Component aNewComp){
		removeAll();
		add(Box.createRigidArea(new Dimension(0,paddingSize)),BorderLayout.NORTH);
		add(Box.createRigidArea(new Dimension(paddingSize,0)),BorderLayout.WEST);
		add(Box.createRigidArea(new Dimension(paddingSize,0)),BorderLayout.EAST);
		add(Box.createRigidArea(new Dimension(0,paddingSize)),BorderLayout.SOUTH);
		add(aComp,BorderLayout.CENTER);
		revalidate();
		repaint();
		
	}
}

