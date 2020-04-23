package AppContacts;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class JLabelContact extends JLabel{
	String type;
	public JLabelContact(ContactLine aLine, ImageIcon icon, String type){
		super(icon);
		this.type = type;
		addMouseListener(new MouseLiestenerContactLine(aLine));
	}
	public String getType(){
		return type;
	}
}
