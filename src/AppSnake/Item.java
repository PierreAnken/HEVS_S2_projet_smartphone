package AppSnake;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

// TODO: Auto-generated Javadoc
/**
 * The Class Item.
 * @author Pierre Anken
 */
public class Item{
	
	/** The image. */
	private Image image;
	
	/** The size. */
	private int size;
	
	/** The pos x. */
	private int posX;
	
	/** The pos y. */
	private int posY;
	
	/** The score point. */
	private int scorePoint;
	
	/**
	 * Instantiates a new item.
	 *
	 * @param bodyParts the body parts
	 * @param screenX the screen x
	 * @param screenY the screen y
	 * @param items the items
	 */
	public Item(ArrayList<BodyPart> bodyParts, int screenX, int screenY,ArrayList<Item> items){
		
		//select random bug
		int bugNum = 1+(int)(Math.random()*3);
		scorePoint = 10;
		size = 20;
		try {
			image = ImageIO.read(new File("images/design_software/bug"+bugNum+".png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//generate random spot position
		boolean spotFound = false;
		int watchDog = 0;
		do{
			watchDog++;
			double testX = size+(Math.random()*(screenX-size*2));
			double testY = size+(Math.random()*(screenY-size*2));
			boolean posOk = true;
			
			for(BodyPart aPart : bodyParts){
				Double distance = Math.hypot(testX-aPart.getX(), testY-aPart.getY());
				if(distance<aPart.getSize()/2+size/2+5){
					posOk = false;
					break;
				}
			}
			
			if(posOk)
				for(Item item : items){
					Double distance = Math.hypot(testX-item.getPosX(), testY-item.getPosY());
					if(distance<item.getSize()/2+size/2+5){
						posOk = false;
						break;
					}
				}
			
			if(posOk){
				spotFound = true;
				posX = (int) testX;
				posY = (int) testY;
			}
			
		}while(!spotFound && watchDog<200);
	}

	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Gets the pos x.
	 *
	 * @return the pos x
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * Gets the pos y.
	 *
	 * @return the pos y
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * Gets the score point.
	 *
	 * @return the score point
	 */
	public int getScorePoint() {
		return scorePoint;
	}
	
}
