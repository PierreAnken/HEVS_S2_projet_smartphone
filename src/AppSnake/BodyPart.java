package AppSnake;

// TODO: Auto-generated Javadoc
/**
 * The Class BodyPart.
 * @author Pierre Anken
 */
public class BodyPart {
	
	/** The x. */
	private int x;
	
	/** The y. */
	private int y;
	
	/** The size. */
	private int size;
	
	/**
	 * Instantiates a new body part.
	 *
	 * @param x the x
	 * @param y the y
	 * @param size the size
	 */
	public BodyPart(int x, int y, int size){
		this.x = x;
		this.y = y;
		this.size = size;
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize(){
		return size;
	}
	
	/**
	 * Sets the size.
	 *
	 * @param newSize the new size
	 */
	public void setSize(int newSize){
		size = newSize;
	}
	
	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Sets the x.
	 *
	 * @param newX the new x
	 */
	public void setX(int newX){
		x = newX;
	}
	
	/**
	 * Sets the y.
	 *
	 * @param newY the new y
	 */
	public void setY(int newY){
		y = newY;
	}
	
	/**
	 * Distance parts.
	 *
	 * @param A the a
	 * @param B the b
	 * @return the double
	 */
	public static double distanceParts(BodyPart A, BodyPart B){
		return Math.hypot(A.getX()-B.getX(), A.getY()-B.getY());
	}
	
}
