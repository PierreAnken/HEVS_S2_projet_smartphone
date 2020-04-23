package smartphone;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class SerializationManager.
 * @author Pierre Anken
 */
public class SerializationManager{

	/**
	 * Array list to file.
	 *
	 * @param anArray the an array
	 * @param aFolderPath the a folder path
	 * @param aFileName the a file name
	 */
	@SuppressWarnings("rawtypes")
	public void ArrayListToFile(ArrayList anArray, String aFolderPath, String aFileName){
	
		try {
			
			if(!new File(aFolderPath+aFileName).exists()){
				new File(aFolderPath).mkdirs();
				new File(aFolderPath+aFileName).createNewFile();
			}
			
			try(
					
				FileOutputStream fos = new FileOutputStream(aFolderPath+aFileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos)
			){
				oos.writeObject(anArray);
				oos.flush();			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Array list to file.
	 *
	 * @param anArray the an array
	 * @param aFileName the a file name
	 */
	@SuppressWarnings("rawtypes")
	public void ArrayListToFile(ArrayList anArray,String aFileName){
		ArrayListToFile(anArray, Core.getDataPath(), aFileName);
	}
	
	/**
	 * File to array list.
	 *
	 * @param aFilePath the a file path
	 * @param aFileName the a file name
	 * @return the array list
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList fileToArrayList(String aFilePath, String aFileName){
		
		try{
			if(!new File(aFilePath+aFileName).exists())
				return new ArrayList();
			try(
				
				FileInputStream fis = new FileInputStream(aFilePath+aFileName);
				ObjectInputStream ois = new ObjectInputStream(fis))
				{
				ArrayList data = (ArrayList) ois.readObject();
				return data;
			}
		}
		catch (EOFException e)
		{
		  //nothing to do, file will be closed automatically
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return new ArrayList();
	}
	
	/**
	 * File to array list.
	 *
	 * @param aFileName the a file name
	 * @return the array list
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList fileToArrayList(String aFileName){
		return fileToArrayList(Core.getDataPath(), aFileName);
	}
}
