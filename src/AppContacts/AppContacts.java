package AppContacts;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Objects.Contact;
import smartphone.App;
import smartphone.Core;


/**
 * The Class AppContacts.
 */

@SuppressWarnings("serial")
public class AppContacts extends App implements ActionListener{
	
	private JScrollPane scrollContactList;
	private ListContact contactList;
	
	public AppContacts(){
		
		super("Contacts",false);
		setLayout(new BorderLayout());
		
		//scrollbar
		contactList = new ListContact();
		scrollContactList = new JScrollPane(new ListContact());
		scrollContactList.setPreferredSize(Core.getSp().getCenterAppWithBotBarSize());
		scrollContactList.setBorder(null);
		scrollContactList.getVerticalScrollBar().setUnitIncrement(16);
		add(scrollContactList,BorderLayout.CENTER);
		
		//toolbar
		JPanel toolbar = new JPanel();
		toolbar.setBackground(Core.getSp().getActiveTheme().getThemeColor());
		
		JButton addContact = new JButton("Add contact");
		addContact.setActionCommand("addContact");
		addContact.addActionListener(this);
		toolbar.add(addContact);
		
		add(toolbar, BorderLayout.SOUTH);
	}


	@Override
	public void onLoad() {	
		
		
		ContactLine firstLine = ((ContactLine) ((ListContact)scrollContactList.getViewport().getComponent(0)).getComponent(0));
		if(firstLine.getContact().getFirstName().equals("New contact")){
			firstLine.getParent().remove(firstLine);
			Core.getSp().revalidate();
			Core.getSp().repaint();
		}
	}


	public void actionPerformed(ActionEvent e) {
		if(((JButton)e.getSource()).getActionCommand().equals("addContact")){
			ContactLine newLine = new ContactLine(new Contact("New contact", "", "", ""),0);
			newLine.addMouseListener(new MouseLiestenerContactLine(newLine));
			if(!(((ContactLine) ((ListContact)scrollContactList.getViewport().getComponent(0)).getComponent(0)).getContact().getFirstName().equals("New contact"))){
				contactList.add(newLine,0);
				contactList.revalidate();
				scrollContactList.getViewport().removeAll();
				scrollContactList.getViewport().add(contactList);
			}
		}
	}

}
