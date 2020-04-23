package Objects;

import java.awt.Color;

// TODO: Auto-generated Javadoc
/**
 * The Class Theme.
 * @author Pierre Anken
 */
public class Theme{
	
	/** The theme name. */
	private String themeName;
	
	/** The theme color. */
	private Color themeColor;
	
	/** The text color. */
	private Color textColor;
	
	/** The Constant picPath. */
	private final static String picPath = "images/design_software/background_";
	
	/**
	 * Instantiates a new theme.
	 *
	 * @param aName the a name
	 * @param aThemeColor the a theme color
	 * @param aTextColor the a text color
	 */
	public Theme(String aName, Color aThemeColor, Color aTextColor){
		themeName = aName;
		themeColor = aThemeColor;
		textColor = aTextColor;
	}
	
	/**
	 * Gets the theme color.
	 *
	 * @return the theme color
	 */
	public Color getThemeColor(){
		return themeColor;
	}
	
	/**
	 * Gets the text color.
	 *
	 * @return the text color
	 */
	public Color getTextColor(){
		return textColor;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName(){
		return themeName;
	}
	
	/**
	 * Path.
	 *
	 * @return the string
	 */
	public String path(){
		return picPath;
	}
	
}
