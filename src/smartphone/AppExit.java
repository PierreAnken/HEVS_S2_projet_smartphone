package smartphone;

// TODO: Auto-generated Javadoc
/**
 * The Class AppExit.
 * @author Pierre Anken
 */
@SuppressWarnings("serial")
public class AppExit extends App{

	
	/**
	 * Instantiates a new app exit.
	 */
	public AppExit(){
		super("Exit",true);
	}
	
	/* (non-Javadoc)
	 * @see smartphone.App#onLoad()
	 */
	public void onLoad(){
		 System.exit(1);
	}

}
