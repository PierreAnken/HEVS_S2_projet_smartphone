package Objects;

import java.io.Serializable;
import java.util.ArrayList;

import smartphone.Core;

// TODO: Auto-generated Javadoc
/**
 * The Class Param.
 * @author Pierre Anken
 */
public class Param implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5893062926184261407L;
	
	/** The key. */
	private String key;
	
	/** The content. */
	private Object content;
	
	/**
	 * Instantiates a new param.
	 *
	 * @param aKey the a key
	 * @param anObject the an object
	 */
	public Param(String aKey, Object anObject){
		key = aKey;
		content = anObject;
	} 
	
	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public Object getContent(){
		return content;
	}
	
	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public String getKey(){
		return key;
	}
	
	/**
	 * Sets the value.
	 *
	 * @param anObject the new value
	 */
	public void setValue(Object anObject){
		content = anObject;
	}
	
	/**
	 * Gets the param.
	 *
	 * @param paramArray the param array
	 * @param aKey the a key
	 * @return the param
	 */
	public static Param getParam(ArrayList<Param> paramArray, String aKey){
		for(Param aParam : paramArray)
			if(aParam.getKey().equals(aKey))
				return aParam;
		
		return null;
	}
	
	/**
	 * Write update param.
	 *
	 * @param paramArray the param array
	 * @param aParam the a param
	 */
	public static void writeUpdateParam(ArrayList<Param> paramArray,Param aParam){
		if(getParam(paramArray,aParam.getKey()) == null)
			paramArray.add(aParam);
		else{
			for(Param theParam : paramArray)
				if(theParam.getKey().equals(aParam.getKey()))
					theParam.setValue(aParam.getContent());
		}
	}
}
