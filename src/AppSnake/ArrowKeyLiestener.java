package AppSnake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class ArrowKeyLiestener.
 * @author Pierre Anken
 */
public class ArrowKeyLiestener extends KeyAdapter{
	
	/** The game screen. */
	private GameScreen gameScreen;
	
	/**
	 * Instantiates a new arrow key liestener.
	 *
	 * @param gameScreen the game screen
	 */
	public ArrowKeyLiestener(GameScreen gameScreen){
		this.gameScreen = gameScreen;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
	 */
	public void keyPressed(KeyEvent e) {
		
		boolean isArrow = true;
		int newDirection;
		
		switch(e.getKeyCode()){
	  	case KeyEvent.VK_UP: 
	  		newDirection = 0;
	  		
	  		break;
	  	case KeyEvent.VK_RIGHT: 
	  		newDirection = 1;
	  		break;
	  	case KeyEvent.VK_DOWN: 
	  		newDirection = 2;
	  		break;
	  	case KeyEvent.VK_LEFT: 
	  		newDirection = 3;
	  		break;
	  	default:
	  		isArrow = false;
	  		return;
		}
		
		int difference = Math.abs(newDirection-gameScreen.getDirection());
		
		if(difference == 3 || difference == 1)
			gameScreen.setDirection(newDirection);
		
		
		if(!gameScreen.gameStarted() && isArrow){
			gameScreen.setStarted();
			gameScreen.startTimer();
			
		}
		
	}
}
