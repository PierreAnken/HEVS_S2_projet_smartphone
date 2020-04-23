package AppSnake;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import smartphone.Core;


// TODO: Auto-generated Javadoc
/**
 * The Class ScoreSnake.
 * @author Pierre Anken
 */
@SuppressWarnings("serial")
public class ScoreSnake implements Serializable{
	
	/** The score list. */
	transient private ArrayList<ScoreSnake> scoreList = new ArrayList<ScoreSnake>();
	
	/** The formated time. */
	transient private SimpleDateFormat formatedTime;

	/** The score. */
	private int score;
	
	/** The date. */
	private String date;
	
	/**
	 * Instantiates a new score snake.
	 *
	 * @param aScore the a score
	 */
	@SuppressWarnings("unchecked")
	public ScoreSnake(int aScore){	
		score = aScore;	
		formatedTime = new SimpleDateFormat("dd.MM.yy - HH:mm");
		date = formatedTime.format(new Date());
		
		scoreList = Core.getSm().fileToArrayList("SnakeScore.list");
		boolean added = false;
		for(int i = 0; i<scoreList.size();i++){
			if(scoreList.get(i).getScore()<this.getScore()){
				scoreList.add(i,this);
				added = true;
				break;
			}
		}
		if(!added)
			scoreList.add(this);
		if(scoreList.size()>7)
			scoreList.remove(7);
		Core.getSm().ArrayListToFile(scoreList,"SnakeScore.list");
	}
	
	
	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore(){
		return score;
	}
	
	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate(){
		return date;
	}
}
