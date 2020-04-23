package smartphone;

// TODO: Auto-generated Javadoc
/**
 * The Class Core.
 * @author Pierre Anken
 */
public class Core {

	/** The phone. */
	private static Smartphone thePhone;
	
	/** The S manager. */
	private static SerializationManager SManager;
	
	/** The Constant DATAPATH. */
	private final static String DATAPATH = "Data/";
	
	/** The Constant PICSFOLDER. */
	private final static String PICSFOLDER = "DCIM/";
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {	
		new Core();	
	}
	
	/**
	 * Instantiates a new core.
	 */
	public Core(){
		thePhone = new Smartphone();
		SManager = new SerializationManager();
		thePhone.initThemes();
		thePhone.initGallery();
		thePhone.initParams();
		thePhone.initHardwareDesign();
		thePhone.initContacts();
		thePhone.initApp();
		thePhone.initScreens();
		thePhone.center();
	}
	
	/**
	 * Gets the sp.
	 *
	 * @return the sp
	 */
	public static Smartphone getSp(){
		return thePhone;
	}
	
	/**
	 * Gets the data path.
	 *
	 * @return the data path
	 */
	public static String getDataPath(){
		return DATAPATH;
	}
	
	/**
	 * Gets the pics folder.
	 *
	 * @return the pics folder
	 */
	public static String getPicsFolder(){
		return DATAPATH+PICSFOLDER;
	}
	
	/**
	 * Gets the sm.
	 *
	 * @return the sm
	 */
	public static SerializationManager getSm(){
		return SManager;
	}
	
}
