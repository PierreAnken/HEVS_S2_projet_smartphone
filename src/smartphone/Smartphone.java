package smartphone;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;

import AppClock.AppClock;
import AppContacts.AppContacts;
import AppGallery.AppGallery;
import AppSettings.AppSettings;
import AppSnake.AppSnake;
import Objects.Contact;
import Objects.Param;
import Objects.Picture;
import Objects.Theme;
import Tools.Toolbox;


// TODO: Auto-generated Javadoc
/**
 * The Class Smartphone.
 * @author Pierre Anken
 */
@SuppressWarnings("serial")
public class Smartphone extends JFrame {

	/** Portrait mode screen. */
	private ScreenContent theScreen;
	
	/** Landscape mode screen. */
	private ScreenContent theScreenLs;
	
	/**  ArrayLists. */
	private static ArrayList<Theme> listTheme = new ArrayList<Theme>();
	
	/** The params. */
	private static ArrayList<Param> params = new ArrayList<Param>();
	
	/** The list app. */
	private static ArrayList<App> listApp = new ArrayList<App>();
	
	/** The list contact. */
	private static ArrayList<Contact>listContact = new ArrayList<Contact>();
	
	/** The list pic. */
	private static ArrayList<Picture>listPic = new ArrayList<Picture>();

	/**  Pictures used to represent the smartphone (hardware). */
	private JLabel leftImage;
	
	/** The right image. */
	private JLabel rightImage;
	
	/** The top image. */
	private JLabel topImage;
	
	/** The right image ls. */
	private JLabel rightImageLs;
	
	/** The top image ls. */
	private JLabel topImageLs;
	
	/** The bottom image ls. */
	private JLabel bottomImageLs;
	
	/** The top bar. */
	private TopBar topBar = new TopBar();	
	
	/**
	 * Instantiates a new smartphone.
	 */
	public Smartphone(){
		
		MouseLiestenerSmartphone SMMove = new MouseLiestenerSmartphone();
		
		addMouseListener(SMMove);
		addMouseMotionListener(SMMove);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);
		setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		setVisible(true);
		setResizable(false);
		
	}

	
	/**
	 * Inits the params.
	 */
	//inits
	@SuppressWarnings("unchecked")
	public void initParams(){
		params = Core.getSm().fileToArrayList("Config.cfg");
		if(params.isEmpty()){
			Param.writeUpdateParam(params, new Param("theme", getListTheme().get(0).getName()));
			Param.writeUpdateParam(params, new Param("firstStart", true));
			this.saveParams();
		}
	}
	
	public void saveParams(){
		Core.getSm().ArrayListToFile(params, "Config.cfg");
	}
	
	/**
	 * Inits the gallery.
	 */
	@SuppressWarnings("unchecked")
	public void initGallery(){
		listPic = Core.getSm().fileToArrayList("Picture.list");
		if(listPic.isEmpty()){
			for(int i = 0; i<10; i++)
				new Picture(i+".jpg","images/defaultGalleryPics");
		}
	}
	
	/**
	 * Inits the themes.
	 */
	public void initThemes(){
		listTheme.add(new Theme("sea",new Color(80,120,132),Color.WHITE));
		listTheme.add(new Theme("mountain",new Color(150,82,126),Color.WHITE));
	}
	
	/**
	 * Inits the contacts.
	 */
	@SuppressWarnings("unchecked")
	public void initContacts(){
		
		listContact = Core.getSm().fileToArrayList("Contact.list");
		
		// if contact list empty and not the first start
		if(listContact.isEmpty() && (boolean)Param.getParam(Core.getSp().getSParams(),"firstStart").getContent()){
		
			Contact.addToContactList(new Contact("Avant","Urié","075465645",""));
			Contact.addToContactList(new Contact("Bulby","","073455554",""));
			Contact.addToContactList(new Contact("Sainto","Noré","","st@tarte.fr"));
			Contact.addToContactList(new Contact("Pierre","Anken","",""));
			Contact.addToContactList(new Contact("Mike","Wigger","073463564","mw@hotmail.fr"));
			Contact.addToContactList(new Contact("Tae","Reax","074566874","jurassic@park.edu"));
			Contact.addToContactList(new Contact("Narcant","Cyel","076920757","wheater@sky.eu"));
			Contact.addToContactList(new Contact("Otau","Psie","076783374","kill@ncis.com"));
			Contact.addToContactList(new Contact("Bryca","Brack","073556556","bb@gmail.com"));
			
			Core.getSm().ArrayListToFile(listContact,"Contact.list");
			Param.writeUpdateParam(Core.getSp().getSParams(),new Param("firstStart", false));
			Core.getSp().saveParams();
		}
	}
	
	/**
	 * Inits the hardware design.
	 */
	public void initHardwareDesign(){
		leftImage = Picture.getJLabelImageFromPath("images/design_hardware/left.png");
		rightImage = Picture.getJLabelImageFromPath("images/design_hardware/right.png");
		topImage = Picture.getJLabelImageFromPath("images/design_hardware/top.png");
		rightImageLs = Picture.getJLabelImageFromPath("images/design_hardware/right_ls.png");
		topImageLs = Picture.getJLabelImageFromPath("images/design_hardware/top_ls.png");
		bottomImageLs = Picture.getJLabelImageFromPath("images/design_hardware/bottom_ls.png");
	}
	
	/**
	 * Inits the app.
	 */
	public void initApp(){
		listApp.add(new AppClock());
		listApp.add(new AppContacts());
		listApp.add(new AppGallery());
		listApp.add(new AppSnake());
		listApp.add(new AppSettings());
		listApp.add(new AppExit());
	}
	
	/**
	 * Inits the screens.
	 */
	public void initScreens(){
		theScreen = new ScreenContent(false);
		theScreenLs = new ScreenContent(true);
		setPortrait(Param.getParam(getSParams(),"BootComplete") != null);
	}
	
	/**
	 * Sets the landscape.
	 */
	public void setLandscape(){
		getContentPane().removeAll();
		add(rightImageLs,BorderLayout.LINE_END);
		add(topImageLs,BorderLayout.PAGE_START);
		add(bottomImageLs,BorderLayout.PAGE_END);
		add(new HomeButtons(true),BorderLayout.LINE_START);
		add(new ScreenContainer(theScreenLs,null),BorderLayout.CENTER);
		pack();
	}
	
	/**
	 * Sets to portrait mode.
	 *
	 * @param withTop the new portrait
	 */
	public void setPortrait(boolean withTop){
		getContentPane().removeAll();
		add(leftImage,BorderLayout.LINE_START);
		add(rightImage,BorderLayout.LINE_END);
		add(topImage,BorderLayout.PAGE_START);
		add(new HomeButtons(false),BorderLayout.PAGE_END);
		if(withTop)
			add(new ScreenContainer(theScreen,topBar),BorderLayout.CENTER);
		else
			add(new ScreenContainer(theScreen,null),BorderLayout.CENTER);
		pack();
		
	}

	/**
	 * Gets the screen.
	 *
	 * @param landscape the landscape
	 * @return the screen
	 */
	public ScreenContent getScreen(Boolean landscape){
		if(landscape)
			return theScreenLs;
		else
			return theScreen;
	}
	
	/**
	 * Gets the app list.
	 *
	 * @return the app list
	 */
	public static ArrayList<App> getAppList(){
		return listApp;
	}
	
	/**
	 * Gets the list theme.
	 *
	 * @return the list theme
	 */
	public ArrayList<Theme> getListTheme(){
		return listTheme;
	}
	
	/**
	 * Gets the list contact.
	 *
	 * @return the list contact
	 */
	public ArrayList<Contact> getListContact(){
		return listContact;
	}
	
	/**
	 * Gets the list picture.
	 *
	 * @return the list picture
	 */
	public ArrayList<Picture> getListPicture(){
		return listPic;
	}

	/**
	 * Gets the s params.
	 *
	 * @return the s params
	 */
	public ArrayList<Param> getSParams(){
		return params;
	}
	
	/**
	 * Gets the theme.
	 *
	 * @param themeName the theme name
	 * @return the theme
	 */
	public Theme getTheme(String themeName){
		if(themeName != null){
			for(Theme currentTheme : getListTheme()){
				if(currentTheme.getName().equals(themeName))
					return currentTheme;
			}
			
		}
		return getListTheme().get(0);
	}
	
	/**
	 * Gets the active theme.
	 *
	 * @return the active theme
	 */
	public Theme getActiveTheme(){
		return getTheme((String)Param.getParam(getSParams(), "theme").getContent());
	}
	
	/**
	 * Gets the app.
	 *
	 * @param appName the app name
	 * @return the app
	 * @throws Exception if app not found
	 */
	public App getApp(String appName) throws Exception{
		for(App currentApp : getAppList()){
			if(currentApp.getName().equals(appName))
				return currentApp;
		}
		throw new Exception("App : "+appName+" not found");
	}
	
	
	/**
	 * Factory reset.
	 */
	public void factoryReset(){
		Toolbox.deleteDirectory(new File(Core.getDataPath()));
		System.exit(1);
	}
	
	/**
	 * Center.
	 */
	public void center(){
		setLocationRelativeTo(null);
	}
	
	/**
	 * Gets the center app with bot bar size.
	 *
	 * @return the center app with bot bar size
	 */
	public Dimension getCenterAppWithBotBarSize(){
		return new Dimension(443,594);
	}
	
}
