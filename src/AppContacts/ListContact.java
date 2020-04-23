package AppContacts;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import Objects.Contact;
import smartphone.Core;

@SuppressWarnings("serial")
public class ListContact extends JPanel{

	@SuppressWarnings("unchecked")
	public ListContact(){
		
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		setBackground(Color.LIGHT_GRAY);
		
		ArrayList<Contact> contacts = Core.getSm().fileToArrayList("Contact.list");
		int i = contacts.size()-1;
		
		for(Contact aContact : contacts){
			ContactLine contactLine = new ContactLine(aContact,i);
			contactLine.addMouseListener(new MouseLiestenerContactLine(contactLine));
			add(contactLine,0);
			i--;
		}
		
		add(Box.createVerticalGlue());	
	}
}
