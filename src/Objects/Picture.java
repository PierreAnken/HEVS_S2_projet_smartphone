package Objects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.sun.jmx.snmp.Timestamp;

import smartphone.Core;

// TODO: Auto-generated Javadoc
/**
 * The Class Picture.
 */
public class Picture implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 935632945021846874L;
	
	/** The file name. */
	private String fileName;
	
	/** The Constant GALLERY. */
	public transient static final int GALLERY = 0;
	
	/** The Constant GALLERYPREVIEW. */
	public transient static final int GALLERYPREVIEW = 1;
	
	/** The Constant CONTACTLIST. */
	public transient static final int CONTACTLIST = 2;
	
	/**
	 * Instantiates a new picture.
	 *
	 * @param aFileName the a file name
	 * @param aFolderPath the a folder path
	 */
	public Picture(String aFileName, String aFolderPath) {	
			
		//import picture in data
		fileName = Core.getSp().getListPicture().size()+new SimpleDateFormat("yyyyMMddHHmmss", Locale.FRANCE).format(new Date())+".png";
		BufferedImage baseImage = (BufferedImage) getImageFromPath(aFolderPath+"/"+aFileName);
		writeImage(baseImage,Core.getPicsFolder(),fileName);
		Picture.addToPictureList(this);
	}
	
	/**
	 * Instantiates a new picture.
	 */
	public Picture(){
		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "JPG & PNG Images", "jpg", "png");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(Core.getSp());
	    fileName = Core.getSp().getListPicture().size()+new SimpleDateFormat("yyyyMMddHHmmss", Locale.FRANCE).format(new Date())+".png";
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       	BufferedImage baseImage = (BufferedImage) getImageFromPath(chooser.getSelectedFile().getParent()+"/"+chooser.getSelectedFile().getName());
			writeImage(baseImage,Core.getPicsFolder(),fileName);
			Picture.addToPictureList(this);
	    } 
	}
	
	/**
	 * Write image.
	 *
	 * @param anImage the an image
	 * @param aFolderPath the a folder path
	 * @param aFileName the a file name
	 */
	public static void writeImage(Image anImage, String aFolderPath, String aFileName){
		try {
			if(!new File(aFolderPath+aFileName).exists()){
				new File(aFolderPath).mkdirs();
			}
			String fileNameWithoutExt = aFileName.split("[.]", 2)[0];
			ImageIO.write((RenderedImage) anImage, "png", new File(aFolderPath+"/"+fileNameWithoutExt+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the image from path.
	 *
	 * @param aPath the a path
	 * @return the image from path
	 */
	public static Image getImageFromPath(String aPath){
		try {
			return ImageIO.read(new File(aPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Gets the icon from path.
	 *
	 * @param aPath the a path
	 * @return the icon from path
	 */
	public static ImageIcon getIconFromPath(String aPath){
		return new ImageIcon(getImageFromPath(aPath));
	}
	
	/**
	 * Gets the jbutton with icon from path.
	 *
	 * @param aPath the a path
	 * @return the jbutton with icon from path
	 */
	public static JButton getJbuttonWithIconFromPath(String aPath){
		return new JButton(getIconFromPath(aPath));
	}
	
	/**
	 * Gets the image icon from path.
	 *
	 * @param aPath the a path
	 * @return the image icon from path
	 */
	public static ImageIcon getImageIconFromPath(String aPath){
			return new ImageIcon(getImageFromPath(aPath));
	}
	
	/**
	 * Gets the j label image from path.
	 *
	 * @param aPath the a path
	 * @return the j label image from path
	 */
	public static JLabel getJLabelImageFromPath(String aPath){
			return new JLabel(getImageIconFromPath(aPath));
	}
	
	/**
	 * Gets the creates the pic.
	 *
	 * @param type the type
	 * @return the creates the pic
	 * @throws Exception the exception
	 */
	public String getCreatePic(int type) throws Exception {
		
		if(!new File(Core.getPicsFolder()+fileName).exists())
			throw new Exception("Base image not found");
		
		String picPath;
		switch(type){
			case 0:
				//445px x 693px (max)
				picPath = Core.getPicsFolder()+"_445_"+fileName;
				if(!new File(picPath).exists()){
					BufferedImage baseImage = (BufferedImage) getImageFromPath(getPicPath());
					int width = baseImage.getWidth(null);
					int height = baseImage.getHeight(null);
					int ratio = width/height;
					int newHeight = 445*ratio;
					if(newHeight>693)
						newHeight = 693;
					Image galleryPic = resizeImage(baseImage,445,newHeight);
					ImageIO.write((RenderedImage) galleryPic, "png", new File(picPath));
				}
				break;
			case 1:
				//200px x 200px 
				picPath = Core.getPicsFolder()+"_200_"+fileName;
				if(!new File(picPath).exists()){
					Image galleryPic = resizeImage((BufferedImage) getImageFromPath(getPicPath()),200,200);
					ImageIO.write((RenderedImage) galleryPic, "png", new File(picPath));
				}
				break;	
			case 2:
				//50px x 50px
				picPath = Core.getPicsFolder()+"_80_"+fileName;
				if(!new File(picPath).exists()){
					Image galleryPic = resizeImage((BufferedImage) getImageFromPath(getPicPath()),80,80);
					ImageIO.write((RenderedImage) galleryPic, "png", new File(picPath));
				}
				break;
			default:
				throw new Exception("Unknown type");
		}
		return picPath;
	}
	
	
	/**
	 * Resize image.
	 *
	 * @param originalImage the original image
	 * @param width the width
	 * @param height the height
	 * @return the buffered image
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	//found on http://stackoverflow.com/questions/8284048/resizing-an-image-in-swing 
	public static BufferedImage resizeImage(BufferedImage originalImage, int width, int height) throws IOException {  
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);  
        Graphics2D g = resizedImage.createGraphics();  
        g.drawImage(originalImage, 0, 0, width, height, null);  
        g.dispose();  
        return resizedImage;  
    }  
	
	/**
	 * Gets the pic path.
	 *
	 * @return the pic path
	 */
	public String getPicPath(){
		return Core.getPicsFolder()+fileName;
	}
	
	/**
	 * Adds the to picture list.
	 *
	 * @param aPicture the a picture
	 */
	public static void addToPictureList(Picture aPicture){
		Core.getSp().getListPicture().add(aPicture);
		savePictureList();
	}
	
	/**
	 * Save picture list.
	 */
	public static void savePictureList(){
		Core.getSm().ArrayListToFile(Core.getSp().getListPicture(), "Picture.list");
	}
	
	/**
	 * Gets the pic from name.
	 *
	 * @param aName the a name
	 * @return the pic from name
	 */
	public static Picture getPicFromName(String aName){
		for(Picture picture : Core.getSp().getListPicture()){
			if(picture.getName().equals(aName)){
			return picture;
			}
		}
		return null;
	}
	
	/**
	 * Removes the from picture.
	 */
	public void removeFromPicture(){
		
	
		Core.getSp().getListPicture().remove(this);
		savePictureList();
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName(){
		return fileName;
	}
}
