package AppSnake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

// TODO: Auto-generated Javadoc
/**
 * The Class GameScreen.
 * @author Pierre Anken
 */
@SuppressWarnings("serial")
public class GameScreen extends JPanel{
	

	  /** The game started. */
  	private boolean gameStarted = false;
	  
  	/** The refresh screen. */
  	private Timer refreshScreen;
	  
  	/** The speed. */
  	private int speed = 5;
	  
  	/** The forest floor. */
  	private Image forestFloor;
	  
  	/** The keyboard arrows. */
  	private Image keyboardArrows;
	  
  	/** The border size. */
  	private final int borderSize = 5;
	  
  	/** The body parts. */
  	private ArrayList<BodyPart> bodyParts;
	  
  	/** The items. */
  	private ArrayList<Item> items;
	  
  	/** The dead. */
  	private boolean dead = false;
	  
  	/** The items eat. */
  	private int itemsEat = 0;
	  
  	/** The score. */
  	private int score = 0;
	  
  	/** The direction. */
  	private int direction = 3;
	  // 0 = top, 1 = right, 2 = bottom, 3 = left 

	  /**
  	 * Instantiates a new game screen.
  	 *
  	 * @param appSnake the app snake
  	 */
  	public GameScreen(AppSnake appSnake){
		  
		  addKeyListener(new ArrowKeyLiestener(this));
		  
		  try {
			keyboardArrows = ImageIO.read(new File("images/design_software/keyboardArrows.png"));
			forestFloor = ImageIO.read(new File("images/design_software/forestFloor.jpg"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		  
		  items = new ArrayList<Item>();
		  
		  bodyParts = new ArrayList<BodyPart>(
			  Arrays.asList(
			  new BodyPart(225,150,20),
			  new BodyPart(230,150,16),
			  new BodyPart(235,150,16),
			  new BodyPart(240,150,16),
			  new BodyPart(245,150,16),
			  new BodyPart(250,150,16),
			  new BodyPart(255,150,16),
			  new BodyPart(260,150,14),
			  new BodyPart(265,150,12),
			  new BodyPart(270,150,10),
			  new BodyPart(275,150,10)
			  )
		  );
		  
		  refreshScreen = new Timer(50, new ActionListener() {
				public void actionPerformed(ActionEvent evt){
					updateScreen();
					if(dead){
						refreshScreen.stop();
						
						new ScoreSnake(score);
						
						appSnake.addUpdateCard(new HomeScreen(appSnake), "home");
						appSnake.resetGame();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						appSnake.setCard("home");
					}
						
				}    
		  });
	  }
	  
	  /**
  	 * Update screen.
  	 */
  	public void updateScreen(){
		  	  
		 //we add bugs on screen if < 3
		 while(items.size()<3)
			  items.add(new Item(bodyParts,this.getWidth(), this.getHeight(),items));

		  //we swap body parts position
		  for(int part = bodyParts.size()-1; part > 0; part--){
			  BodyPart partToMove = bodyParts.get(part);
			  BodyPart partDestination = bodyParts.get(part-1);
			  partToMove.setX(partDestination.getX());
			  partToMove.setY(partDestination.getY());
		  }
		  
		  //We move the head
		  BodyPart head = bodyParts.get(0);
		  switch(direction){
		  	case 0: 
		  		head.setY(head.getY()-speed);
		  		break;
		  	case 1: 
		  		head.setX(head.getX()+speed);
		  		break;
		  	case 2: 
		  		head.setY(head.getY()+speed);
		  		break;
		  	default:
		  		head.setX(head.getX()-speed);
		  }
		  
		  //if snake eat an item
		  for(Item item : items){
			  if(Math.hypot(head.getX()-item.getPosX(), head.getY()-item.getPosY()) < head.getSize()/2+item.getSize()/2){
				score+=item.getScorePoint();
				items.remove(item);
				bodyParts.add(5, new BodyPart(bodyParts.get(5).getX(),bodyParts.get(5).getY(),16));
				bodyParts.add(5, new BodyPart(bodyParts.get(5).getX(),bodyParts.get(5).getY(),16));
				itemsEat++;
				if(itemsEat%5 == 0)
					refreshScreen.setDelay(refreshScreen.getDelay()-1);
				break;
			  }
		   }
		  
		  
		  //if snake touch borderSize
		  if(head.getX()-head.getSize()/2 < borderSize || head.getY()<borderSize+head.getSize()/2|| head.getX()+head.getSize()/2>this.getWidth()-borderSize || head.getY()+head.getSize()/2>this.getHeight()-borderSize){
			  dead = true;
		  }
		  
		  //if snake self-eating
		  for(int part = 8; part < bodyParts.size(); part++){
			  Double distancePartHead = BodyPart.distanceParts(head,bodyParts.get(part));
			  if(distancePartHead<head.getSize()/2+bodyParts.get(part).getSize()/2)
				  dead = true;
		  }

		  
		  this.repaint();
		  
	  }
	  
	 /* (non-Javadoc)
 	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
 	 */
 	public void paintComponent(Graphics g){
		 
		   g.setColor(new Color(142,99,46));
		   g.fillRect(0, 0, getWidth(), getHeight());
		   g.drawImage(forestFloor,borderSize,borderSize,null);
		   g.setColor(new Color(109,100,100));
		   
		   for(BodyPart aPart : bodyParts){
			   g.setColor(new Color(60,85,48));
			   g.fillOval(aPart.getX()-aPart.getSize()/2, aPart.getY()-aPart.getSize()/2, aPart.getSize(), aPart.getSize());
			 
		   } 
		   for(BodyPart aPart : bodyParts){
			   g.setColor(new Color(77,123,56));
			   g.fillOval(aPart.getX()-(aPart.getSize()-4)/2, aPart.getY()-(aPart.getSize()-4)/2, aPart.getSize()-4, aPart.getSize()-4);
		   } 
		   
		   for(Item anItem : items)
			   g.drawImage(anItem.getImage(), anItem.getPosX()-anItem.getSize()/2, anItem.getPosY()-anItem.getSize()/2, null);
		   
		   if(!gameStarted)
				g.drawImage(keyboardArrows, getWidth()/2-100, 220, null);

		   
		   Graphics2D g1 = (Graphics2D) g;
		   g1.setPaint(Color.BLACK);
		   Font font = new Font("Arial", Font.BOLD, 20);
		   
		   TextLayout scoreText = new TextLayout("Score: "+score , font, g1.getFontRenderContext());
		   scoreText.draw(g1, getWidth()-120-borderSize, 30);
		   
		   TextLayout speedText = new TextLayout("Speed: "+(51-refreshScreen.getDelay()), font, g1.getFontRenderContext());
		   speedText.draw(g1, getWidth()-240-borderSize, 30);
	 }

	 /**
 	 * Game started.
 	 *
 	 * @return true, if successful
 	 */
 	public boolean gameStarted(){
		 return gameStarted;
	 }
	 
	 /**
 	 * Sets the started.
 	 */
 	public void setStarted(){
		 gameStarted = true;
	 }
	 
	 /**
 	 * Start timer.
 	 */
 	public void startTimer(){
		 refreshScreen.start();
	 }
	 
	 /**
 	 * Sets the direction.
 	 *
 	 * @param newDirection the new direction
 	 */
 	public void setDirection(int newDirection){
		 direction = newDirection;
	 }
	 
	 /**
 	 * Gets the direction.
 	 *
 	 * @return the direction
 	 */
 	public int getDirection(){
		 return direction;
	 }

}
