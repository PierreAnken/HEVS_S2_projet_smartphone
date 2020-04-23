package AppClock;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

import smartphone.Core;

// TODO: Auto-generated Javadoc
//Source : http://moonwave99.altervista.org/digitalClock.java

/**
 * The Class digitalNumber.
 * @author Mike Wigger
 */
public class digitalNumber {
	
	/** The y. */
	int x,y;
	
	/** The k. */
	int k;
	
	/** The leds. */
	led[] leds;
	
	/**
	 * Instantiates a new digital number.
	 *
	 * @param x the x
	 * @param y the y
	 * @param k the k
	 */
	public digitalNumber(int x, int y, int k){
		this.x=x;
		this.y=y;
		this.k=k;
		leds = new led[7];
		leds[0] = new led(x,y,"vert");
		leds[1] = new led(x,y+10*k,"vert");
		leds[2] = new led(x+8*k,y,"vert");
		leds[3] = new led(x+8*k,y+10*k,"vert");
		leds[4] = new led(x+2*k,y-9*k,"horiz");
		leds[5] = new led(x+2*k,y+k,"horiz");
		leds[6] = new led(x+2*k,y+11*k,"horiz");
	}
	
	/**
	 * Sets the number.
	 *
	 * @param num the new number
	 */
	public void setNumber(int num){
		if(num==0){
			leds[0].setState(true);
			leds[1].setState(true);
			leds[2].setState(true);
			leds[3].setState(true);
			leds[4].setState(true);
			leds[5].setState(false);
			leds[6].setState(true);
		}else if(num==1){
			leds[0].setState(false);
			leds[1].setState(false);
			leds[2].setState(true);
			leds[3].setState(true);
			leds[4].setState(false);
			leds[5].setState(false);
			leds[6].setState(false);			
		}else if(num==2){
			leds[0].setState(false);
			leds[1].setState(true);
			leds[2].setState(true);
			leds[3].setState(false);
			leds[4].setState(true);
			leds[5].setState(true);
			leds[6].setState(true);			
		}else if(num==3){
			leds[0].setState(false);
			leds[1].setState(false);
			leds[2].setState(true);
			leds[3].setState(true);
			leds[4].setState(true);
			leds[5].setState(true);
			leds[6].setState(true);			
		}else if(num==4){
			leds[0].setState(true);
			leds[1].setState(false);
			leds[2].setState(true);
			leds[3].setState(true);
			leds[4].setState(false);
			leds[5].setState(true);
			leds[6].setState(false);			
		}else if(num==5){
			leds[0].setState(true);
			leds[1].setState(false);			
			leds[2].setState(false);
			leds[3].setState(true);
			leds[4].setState(true);
			leds[5].setState(true);
			leds[6].setState(true);			
		}else if(num==6){
			leds[0].setState(true);
			leds[1].setState(true);			
			leds[2].setState(false);
			leds[3].setState(true);
			leds[4].setState(true);
			leds[5].setState(true);
			leds[6].setState(true);			
		}else if(num==7){
			leds[0].setState(false);
			leds[1].setState(false);
			leds[2].setState(true);
			leds[3].setState(true);
			leds[4].setState(true);
			leds[5].setState(false);
			leds[6].setState(false);		
		}else if(num==8 ){
			leds[0].setState(true);
			leds[1].setState(true);
			leds[2].setState(true);
			leds[3].setState(true);
			leds[4].setState(true);
			leds[5].setState(true);
			leds[6].setState(true);		
		}else if(num==9){
			leds[0].setState(true);
			leds[1].setState(false);
			leds[2].setState(true);
			leds[3].setState(true);
			leds[4].setState(true);
			leds[5].setState(true);
			leds[6].setState(true);		
		}
	}
	
	/**
	 * Turn off number.
	 */
	public void turnOffNumber(){
		for(int i=0;i<7;i++){
			leds[i].setState(false);
		}
	}
	
	/**
	 * Draw number.
	 *
	 * @param g2 the g2
	 */
	public void drawNumber(Graphics2D g2){
		for(int i=0; i<7; i++){
			leds[i].render(g2);
		}
	}
	
	/**
	 * The Class led.
	 */
	class led{
		
		/** The y. */
		int x, y;
		
		/** The p. */
		Polygon p;
		
		/** The type. */
		String type;
		
		/** The light on. */
		boolean lightOn=false;
		
		/**
		 * Instantiates a new led.
		 *
		 * @param x the x
		 * @param y the y
		 * @param type the type
		 */
		public led(int x, int y, String type){
			this.x=x;
			this.y=y;
			this.type=type;

			p = new Polygon();
						
			if(type=="vert"){
				p.addPoint(x,y);
				p.addPoint(x+k,y+k);
				p.addPoint(x+2*k,y);
				p.addPoint(x+2*k,y-8*k);
				p.addPoint(x+k,y-9*k);
				p.addPoint(x,y-8*k);
			}
			
			if(type=="horiz"){
				p.addPoint(x,y);
				p.addPoint(x+k,y+k);
				p.addPoint(x+5*k,y+k);
				p.addPoint(x+6*k,y);
				p.addPoint(x+5*k,y-k);
				p.addPoint(x+k,y-k);				
			}
		}
		
		/**
		 * Render.
		 *
		 * @param g2 the g2
		 */
		public void render(Graphics2D g2){
			g2.setColor(new Color(255,255,255));
			if(lightOn)g2.setColor(Core.getSp().getActiveTheme().getThemeColor());				
			g2.fillPolygon(p);
		}
		
		/**
		 * Sets the state.
		 *
		 * @param s the new state
		 */
		public void setState(boolean s){
			lightOn=s;
		}
	}
	
}
