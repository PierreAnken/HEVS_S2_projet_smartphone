package AppContacts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Objects.Contact;
import Objects.Picture;
import smartphone.Core;

@SuppressWarnings("serial")
public class EditContact extends JPanel implements ActionListener{
	
	private ContactLine contactLine;
	private JTextField firstNameF;
	private JTextField lastNameF;
	private JTextField emailF;
	private JTextField phoneF;
	private JComboBox<ImageIcon> pictureC;
	
	public EditContact(ContactLine aContactLine){
		contactLine = aContactLine;
		
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.GRAY));
		setMaximumSize(new Dimension(500,250));
		setBackground(Core.getSp().getActiveTheme().getThemeColor());
		
		//forms
		JPanel forms = new JPanel();
		forms.setLayout(new BoxLayout(forms,BoxLayout.Y_AXIS));
		forms.setOpaque(false);
		
		// firstname
		firstNameF = new JTextField(1);
		firstNameF.setBorder(null);
		firstNameF.setText(contactLine.getContact().getFirstName());
		firstNameF.setMaximumSize(new Dimension(500,22));
		
		JPanel firstNameJ = new JPanel();
		firstNameJ.setLayout(new BoxLayout(firstNameJ, BoxLayout.X_AXIS));
		firstNameJ.setOpaque(false);
		
		firstNameJ.add(new JLabel("Firstname"));
		firstNameJ.add(Box.createRigidArea(new Dimension(20,27)));
		firstNameJ.add(firstNameF);
		forms.add(firstNameJ);
		
		//lastname
		lastNameF = new JTextField(1);
		lastNameF.setBorder(null);
		lastNameF.setText(contactLine.getContact().getLastName());
		lastNameF.setMaximumSize(new Dimension(500,22));
		
		JPanel lastNameJ = new JPanel();
		lastNameJ.setLayout(new BoxLayout(lastNameJ, BoxLayout.X_AXIS));
		lastNameJ.setOpaque(false);
		
		lastNameJ.add(new JLabel("Lastname"));
		lastNameJ.add(Box.createRigidArea(new Dimension(20,27)));
		lastNameJ.add(lastNameF);
		forms.add(lastNameJ);
		
		//email
		emailF = new JTextField(1);
		emailF.setBorder(null);
		emailF.setText(contactLine.getContact().getEmail());
		emailF.setMaximumSize(new Dimension(500,22));
		
		JPanel emailJ = new JPanel();
		emailJ.setLayout(new BoxLayout(emailJ, BoxLayout.X_AXIS));
		emailJ.setOpaque(false);
		
		emailJ.add(new JLabel("Email"));
		emailJ.add(Box.createRigidArea(new Dimension(46,27)));
		emailJ.add(emailF);
		forms.add(emailJ);
		
		//phone
		phoneF = new JTextField(1);
		phoneF.setBorder(null);
		phoneF.setText(contactLine.getContact().getTelNum());
		phoneF.setMaximumSize(new Dimension(500,22));
		
		JPanel phoneJ = new JPanel();
		phoneJ.setLayout(new BoxLayout(phoneJ, BoxLayout.X_AXIS));
		phoneJ.setOpaque(false);
		
		phoneJ.add(new JLabel("Phone"));
		phoneJ.add(Box.createRigidArea(new Dimension(41,27)));
		phoneJ.add(phoneF);
		forms.add(phoneJ);
		
		//Image chooser
		ImageIcon[] arrayImageIcon = new ImageIcon[Core.getSp().getListPicture().size()];
		for(int i = 0; i< Core.getSp().getListPicture().size(); i++){
			try {
				String pathMini = Core.getSp().getListPicture().get(i).getCreatePic(Picture.CONTACTLIST);
				arrayImageIcon[i] = Picture.getImageIconFromPath(pathMini);
				arrayImageIcon[i].setDescription(Core.getSp().getListPicture().get(i).getName());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		pictureC = new JComboBox<ImageIcon>(arrayImageIcon);
		pictureC.setMaximumSize(new Dimension(85,85));
		pictureC.setMaximumRowCount(5);
		if(contactLine.getContact().getPicture() != null)
			pictureC.setSelectedItem(Picture.getImageIconFromPath(contactLine.getContact().getPicture().getPicPath()));
		
		//buttons
		JPanel actionPanel = new JPanel();
		actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.X_AXIS));
		actionPanel.setOpaque(false);
		
		actionPanel.add(Box.createRigidArea(new Dimension(150,50)));
		JButton validate = new JButton("Save");
		validate.setActionCommand("save");
		validate.addActionListener(this);
		actionPanel.add(validate);
		
		actionPanel.add(Box.createRigidArea(new Dimension(30,0)));
		JButton cancel = new JButton("Cancel");
		cancel.setActionCommand("cancel");
		cancel.addActionListener(this);
		actionPanel.add(cancel);
		
		//center
		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.X_AXIS));
		center.setOpaque(false);
		
		center.add(pictureC);
		center.add(Box.createRigidArea(new Dimension(20,0)));
		center.add(forms);
		center.add(Box.createRigidArea(new Dimension(50,0)));
		
		add(Box.createRigidArea(new Dimension(0,10)),BorderLayout.NORTH);
		add(center,BorderLayout.CENTER);
		add(actionPanel,BorderLayout.SOUTH);
	}

	public ContactLine getContactLine(){
		return contactLine;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	  if(((JButton)e.getSource()).getActionCommand().equals("save")){
		  EditContact editContact = (EditContact)((JButton)e.getSource()).getParent().getParent();
		  Contact contact = editContact.getContactLine().getContact();
		  contact.setEmail(editContact.getEmailF());
		  contact.setLastName(editContact.getLastNameF());
		  contact.setFirstName(editContact.getFirstNameF());
		  contact.setTelNum(editContact.getPhoneF());
		  contact.setPicture(editContact.getPicture());
		  contact.removeFromContactList();
		  contact.addToContactList();
		  Contact.saveContactList();
	  }
	
	  JPanel parent = (JPanel) getParent();
	  parent.remove(this);
	  parent.add(this.getContactLine(),this.getContactLine().getIndex());
	  parent.repaint();
	  parent.revalidate();
		
	}
	
	public String getFirstNameF() {
		return firstNameF.getText();
	}

	public String getLastNameF() {
		return lastNameF.getText();
	}

	public String getEmailF() {
		return emailF.getText();
	}

	public String getPhoneF() {
		return phoneF.getText();
	}

	public Picture getPicture() {
		return Picture.getPicFromName(((ImageIcon)pictureC.getSelectedItem()).getDescription());
	}
}
