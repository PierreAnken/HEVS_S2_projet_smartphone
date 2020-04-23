package AppContacts;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

import smartphone.Core;
import Objects.Contact;

public class MouseLiestenerContactLine extends MouseAdapter{

	private ContactLine aContactLine;
	private EditContact anEditContact;
	
	public MouseLiestenerContactLine(ContactLine aContactLine){
		this.aContactLine = aContactLine;
	}
	
	public MouseLiestenerContactLine(EditContact anEditContact){
		this.anEditContact = anEditContact;
	}
	
	public void mouseClicked(MouseEvent e) {
 		if(e.getSource() instanceof JLabelContact){
 			String type = ((JLabelContact)e.getSource()).getType();
 			if(type.equals("del")){
 				//delete user
 				aContactLine.getContact().removeFromContactList();
 				aContactLine.getParent().remove(aContactLine);
 				Core.getSp().revalidate();
 				Core.getSp().repaint();
 				Contact.saveContactList();
 			}
 			else if (type.equals("edit")){ 
 				//edit mode
 				JPanel parent = (JPanel) aContactLine.getParent();
 				parent.remove(aContactLine);
 				EditContact edit = new EditContact(aContactLine);
 				edit.addMouseListener(new MouseLiestenerContactLine(edit));
 				parent.add(edit,aContactLine.getIndex());
 				parent.repaint();
 				parent.revalidate();
 			}
 		}
    } 
	
	
    public void mouseEntered(MouseEvent e) {
    	if(aContactLine != null){
	 		aContactLine.getActionPanel().setVisible(true);
	 		if(ContactLine.getLastLineOver() != null && !ContactLine.getLastLineOver().equals(aContactLine))
	 			ContactLine.getLastLineOver().getActionPanel().setVisible(false);
	 		
	 		ContactLine.setLastLineOver(aContactLine);
    	}
    } 
}
