package Objects;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import smartphone.Core;


// TODO: Auto-generated Javadoc
/**
 * The Class Contact.
 * @author Pierre Anken
 */
public class Contact implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7969267168181461051L;
	
	/** The first name. */
	private String firstName;
	
	/** The last name. */
	private String lastName;
	
	/** The tel num. */
	private String telNum;
	
	/** The email. */
	private String email;
	
	/** The picture. */
	private Picture picture;
	
	/** The id contact. */
	private String idContact;
	
	/**
	 * Instantiates a new contact.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param telNum the tel num
	 * @param email the email
	 */
	public Contact(String firstName, String lastName, String telNum, String email){
		this.firstName = firstName;
		this.lastName = lastName;
		this.telNum = telNum;
		this.email = email;
		idContact = "Contact"+Core.getSp().getListContact().size()+new SimpleDateFormat("yyyyMMddHHmmss", Locale.FRANCE).format(new Date());	
		
	}
	
	/**
	 * Instantiates a new contact.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param telNum the tel num
	 * @param email the email
	 * @param aPic the a pic
	 */
	public Contact(String firstName, String lastName, String telNum, String email, Picture aPic){
		this.firstName = firstName;
		this.lastName = lastName;
		this.telNum = telNum;
		this.email = email;
		picture = aPic;
		idContact = "Contact"+Core.getSp().getListContact().size()+new SimpleDateFormat("yyyyMMddHHmmss", Locale.FRANCE).format(new Date());	
	}
	
	
	/**
	 * Adds the to contact list.
	 *
	 * @param aContact the a contact
	 */
	public static void addToContactList(Contact aContact){
		int index = 0;
		boolean added = false;
		for(Contact listContact:Core.getSp().getListContact()){
			if((listContact.getLastName()+listContact.getFirstName()).compareTo(aContact.getLastName()+aContact.getFirstName())<0){
				Core.getSp().getListContact().add(index,aContact);
				added = true;
				break;
			}
			index++;
		}
		if(!added)
			Core.getSp().getListContact().add(aContact);
		saveContactList();
	}
	
	/**
	 * Save contact list.
	 */
	public static void saveContactList(){
		Core.getSm().ArrayListToFile(Core.getSp().getListContact(),"Contact.list");
	}
	
	/**
	 * Adds the to contact list.
	 */
	public void addToContactList(){
		addToContactList(this);
	}
	
	/**
	 * Removes the from contact list.
	 */
	public void removeFromContactList(){
		int index = 0;
		for(Contact aContact : Core.getSp().getListContact()){
			if(aContact.getId().equals(this.getId())){
				Core.getSp().getListContact().remove(index);
				break;
			}	
			index++;
		}
	}
	
	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the tel num.
	 *
	 * @return the tel num
	 */
	public String getTelNum() {
		return telNum;
	}

	/**
	 * Sets the tel num.
	 *
	 * @param telNum the new tel num
	 */
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the picture.
	 *
	 * @return the picture
	 */
	public Picture getPicture() {
		return picture;
	}

	/**
	 * Sets the picture.
	 *
	 * @param picture the new picture
	 */
	public void setPicture(Picture picture) {
		this.picture = picture;
	}


	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return idContact;
	}

}
