package Tools;

import java.io.File;

// TODO: Auto-generated Javadoc
/**
 * The Class Toolbox.
 * @author Pierre Anken
 */
public class Toolbox {
	
	/**
	 * Delete directory.
	 *
	 * @param directory the directory
	 * @return true, if successful
	 */
	//found on http://stackoverflow.com/questions/3775694/deleting-folder-from-java
	public static boolean deleteDirectory(File directory) {
	    if(directory.exists()){
	        File[] files = directory.listFiles();
	        if(null!=files){
	            for(int i=0; i<files.length; i++) {
	                if(files[i].isDirectory()) {
	                    deleteDirectory(files[i]);
	                }
	                else {
	                    files[i].delete();
	                }
	            }
	        }
	    }
	    return(directory.delete());
	}
	
	
}
