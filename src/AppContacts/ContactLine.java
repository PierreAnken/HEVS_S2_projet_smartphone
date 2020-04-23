package AppContacts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Objects.Contact;
import Objects.Picture;
import Tools.ComponentWithPadding;
import Tools.JLabelFormated;
import smartphone.Core;


@SuppressWarnings("serial")
public class ContactLine extends JPanel {

	private Contact contact;
	private JPanel actionPanelPad;
	private static ContactLine lastLineOver;
	private int index;
	
	public ContactLine(Contact aContact, int anIndex) {
		
		contact = aContact;
		index = anIndex;

		setLayout(new BorderLayout());
		setMaximumSize(new Dimension(455,60));
		setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.GRAY));
		setBackground(Core.getSp().getActiveTheme().getTextColor());
		
		Font textFontContact = new Font("Arial", Font.PLAIN,18);
		
		//texts + labels
		JPanel contactDetails = new JPanel();
		contactDetails.setLayout(new BorderLayout());
		contactDetails.setBackground(Color.WHITE);
		
		//labels
		JPanel contactDetailsLabel = new JPanel();
		contactDetailsLabel.setOpaque(false);
		contactDetailsLabel.setLayout(new BoxLayout(contactDetailsLabel,BoxLayout.Y_AXIS));
		
		contactDetailsLabel.add(Box.createRigidArea(new Dimension(0,8)));
		contactDetailsLabel.add(new JLabelFormated(textFontContact,"Name:",Color.GRAY));
		contactDetailsLabel.add(Box.createRigidArea(new Dimension(0,6)));
		contactDetailsLabel.add(new JLabelFormated(textFontContact,"Phone:   ",Color.GRAY));
		contactDetailsLabel.add(Box.createRigidArea(new Dimension(0,8)));
		contactDetailsLabel.add(new JLabelFormated(textFontContact,"Email:",Color.GRAY));
		
		contactDetails.add(contactDetailsLabel,BorderLayout.WEST);
		
		//texts
		JPanel contactDetailsContent = new JPanel();
		contactDetailsContent.setOpaque(false);
		contactDetailsContent.setLayout(new BoxLayout(contactDetailsContent,BoxLayout.Y_AXIS));

		contactDetailsContent.add(Box.createRigidArea(new Dimension(0,8)));
		contactDetailsContent.add(new JLabelFormated(textFontContact,aContact.getLastName().equals("")?aContact.getFirstName():aContact.getLastName()+" "+aContact.getFirstName()));
		contactDetailsContent.add(Box.createRigidArea(new Dimension(0,6)));
		contactDetailsContent.add(new JLabelFormated(textFontContact,aContact.getTelNum()));
		contactDetailsContent.add(Box.createRigidArea(new Dimension(0,8)));
		contactDetailsContent.add(new JLabelFormated(textFontContact,aContact.getEmail()));
		contactDetails.add(Box.createRigidArea(new Dimension(5,5)),BorderLayout.EAST);
		
		contactDetails.add(contactDetailsContent,BorderLayout.CENTER);
		add(contactDetails,BorderLayout.CENTER);
		
		//picture with padding
		JPanel pic = new JPanel();
		pic.setLayout(new BorderLayout());
		pic.setBackground(Color.WHITE);

		JLabel iconContact = Picture.getJLabelImageFromPath("images/app/Contacts_80.png");
		if(aContact.getPicture() != null)
			try {
				iconContact = Picture.getJLabelImageFromPath(aContact.getPicture().getCreatePic(Picture.CONTACTLIST));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		ComponentWithPadding iconPadding = new ComponentWithPadding(8,iconContact);
		add(iconPadding,BorderLayout.WEST);
		
		//action panel
		JPanel actionPanel = new JPanel();
		actionPanel.setOpaque(false);
		actionPanel.setLayout(new GridLayout(1, 5));
		
		String gray = aContact.getTelNum().equals("")?"_gray":"";

		actionPanel.add(Picture.getJLabelImageFromPath("images/app/sms_32"+gray+".png"));
		actionPanel.add(Picture.getJLabelImageFromPath("images/app/phone_32"+gray+".png"));

		gray = aContact.getEmail().equals("")?"_gray":"";
		
		actionPanel.add(Picture.getJLabelImageFromPath("images/app/mail_32"+gray+".png"));
		
		actionPanel.add(new JLabelContact(this,Picture.getIconFromPath("images/app/edit_32.png"),"edit"));
		actionPanel.add(new JLabelContact(this,Picture.getIconFromPath("images/app/delUser_32.png"),"del"));
		
		actionPanelPad = new ComponentWithPadding(5,actionPanel);
		add(actionPanelPad,BorderLayout.SOUTH);
		actionPanelPad.setVisible(false);
		
	}
	
	public static ContactLine getLastLineOver(){
		return lastLineOver;
	}
	
	public  static void setLastLineOver(ContactLine aLine){
		lastLineOver = aLine;
	}
	
	public Contact getContact(){
		return contact;
	}
	
	public JPanel getActionPanel(){
		return actionPanelPad;
	}

	public int getIndex() {
		return index;
	}
}
