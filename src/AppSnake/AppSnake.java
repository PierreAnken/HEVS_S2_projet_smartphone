package AppSnake;

import java.awt.CardLayout;
import java.awt.Component;

import smartphone.App;

// TODO: Auto-generated Javadoc
/**
 * The Class AppSnake.
 * @author Pierre Anken
 */
@SuppressWarnings("serial")
public class AppSnake extends App{
	
	/** The home. */
	HomeScreen home;
	
	/** The game. */
	GameScreen game;
	
	/**
	 * Instantiates a new app snake.
	 */
	public AppSnake(){
		
		super("Snake",true);
		setLayout(new CardLayout());

		home = new HomeScreen(this);
		add(home,"home");
		
		game = new GameScreen(this);
		add(game,"game");
	}
	
	/**
	 * Adds the update card.
	 *
	 * @param content the content
	 * @param name the name
	 */
	public void addUpdateCard(Component content, String name){
		add(content,name);
	}
	
	/**
	 * Reset game.
	 */
	public void resetGame(){
		game = new GameScreen(this);
		add(game,"game");
	}
	
	/**
	 * Sets the card.
	 *
	 * @param cardname the new card
	 */
	public void setCard(String cardname){
		((CardLayout)this.getLayout()).show(this,cardname);
		game.requestFocus();
	}

	/* (non-Javadoc)
	 * @see smartphone.App#onLoad()
	 */
	@Override
	public void onLoad() {
		// TODO Auto-generated method stub
		
	}
}
