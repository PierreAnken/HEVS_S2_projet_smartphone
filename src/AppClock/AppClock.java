package AppClock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.Rectangle2D;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JCheckBox;

import smartphone.App;
import smartphone.Core;

// TODO: Auto-generated Javadoc

/**
 * The Class AppClock.
 * @author Mike Wigger
 * //Source : http://moonwave99.altervista.org/digitalClock.java
 */
public class AppClock extends App implements Runnable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2071655895386636236L;
	
	/** The mode. */
	String mode="am";	
	
	/** The size. */
	int size=7;
	
	/** The tot secs. */
	int totSecs;
	
	/** The s2. */
	digitalNumber h1,h2,m1,m2,s1,s2;
	
	/** The pulse. */
	boolean pulse=false;
	
	/** The af noon. */
	boolean afNoon;
	
	/** The cal. */
	GregorianCalendar cal;
	
	/** The th. */
	Thread th;
	
	/**
	 * Instantiates a new app clock.
	 */
	public AppClock(){
		
		
		
		super("Clock",false);
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		
		h1=new digitalNumber(20,100,size);
		h2=new digitalNumber(100,100,size);
		m1=new digitalNumber(200,100,size);
		m2=new digitalNumber(280,100,size);
		s1=new digitalNumber(360,60,size/2);
		s2=new digitalNumber(400,60,size/2);
		
		JCheckBox modeBox = new JCheckBox("Toggle AM/PM mode");
		modeBox.setBackground(Core.getSp().getActiveTheme().getThemeColor());
		modeBox.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				if(e.getStateChange() == ItemEvent.DESELECTED)mode="am";
				if(e.getStateChange() == ItemEvent.SELECTED)mode="pm";
				repaint();
			}
		});
		add(modeBox,BorderLayout.SOUTH);
		start();
		
	}
	
	/**
	 * Start.
	 */
	public void start(){
		if(th==null){
			th=new Thread(this);
			th.start();
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run(){
		while(th!=null){
			try{
				totSecs=setSecs();
				showTime();
				if(pulse)pulse=false;
				else pulse=true;
				repaint();
				Thread.sleep(1000);
			}catch(Exception e){}
		}
	}
	
	/**
	 * Stop.
	 */
	public void stop(){
		if(th!=null)th=null;
	}
	
	/**
	 * Sets the secs.
	 *
	 * @return the int
	 */
	public int setSecs(){
		cal=new GregorianCalendar();
		int h,m,s;		
		h=cal.get(Calendar.HOUR)*3600;
		if(cal.get(Calendar.AM_PM)==Calendar.PM)h+=3600*12;
		m=cal.get(Calendar.MINUTE)*60;
		s=cal.get(Calendar.SECOND);
		return h+m+s;
	}
	
	/**
	 * Divide.
	 *
	 * @param a the a
	 * @param b the b
	 * @return the int
	 */
	public int divide(int a, int b){
		int z = 0;
        int i = a;

        while (i>= b)
          {
            i = i - b;
            z++;
          }
        return z;
    }
	
	/**
	 * Show time.
	 */
	public void showTime(){
		if(totSecs>86399)totSecs=0;		
	
		int hours=divide(totSecs,3600);
		int minutes=divide(totSecs,60)-hours*60;
		int seconds=totSecs-hours*3600-60*divide((totSecs-hours*3600),60);

		if(hours<13&&afNoon==true)afNoon=false;
		if(mode=="pm" && hours> 12){
			hours=hours-12;
			afNoon=true;
		}
		//set Hours
		if(hours<10){
			h1.turnOffNumber();
			h2.setNumber(hours);
		}else if(hours>=10 && hours<20){
			h1.setNumber(1);
			h2.setNumber(hours-10);
		}else{
			h1.setNumber(2);
			h2.setNumber(hours-20);
		}
		//set Minutes		
		int dM=divide(minutes,10);
		if(dM<6)m1.setNumber(dM);
		else m1.setNumber(0);
		m2.setNumber(minutes-dM*10);
		//set Seconds		
		int dS=divide(seconds,10);
		if(dS<6)s1.setNumber(dS);
		else s1.setNumber(0);
		s2.setNumber(seconds-dS*10);
						
		//System.out.println(""+hours+" : "+minutes+" . "+seconds);
	}
	
	/**
	 * Show dots.
	 *
	 * @param g2 the g2
	 */
	public void showDots(Graphics2D g2){
		if(pulse)g2.setColor(Color.GRAY);
		else g2.setColor(new Color(255,255,255));
		g2.fill(new Rectangle2D.Double(178,65,14,14));
		g2.fill(new Rectangle2D.Double(178,135,14,14));
	}
	
	/**
	 * Show mode.
	 *
	 * @param g2 the g2
	 */
	public void showMode(Graphics2D g2){
		if(afNoon && mode=="pm"){g2.setColor(Color.BLUE);
			g2.drawString("PM", 360, 140);
			g2.setColor(new Color(255,255,255));
			g2.drawString("AM", 360, 120);
		}else if(afNoon==false && mode=="pm"){
			g2.setColor(new Color(255,255,255));
			g2.drawString("PM", 360, 140);			
			g2.setColor(Color.BLUE);
			g2.drawString("AM", 360, 120);
		}else if(mode=="am"){
			g2.setColor(new Color(255,255,255));
			g2.drawString("PM", 360, 140);			
			g2.drawString("AM", 360, 120);
		}
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		h1.drawNumber(g2);
		h2.drawNumber(g2);
		m1.drawNumber(g2);
		m2.drawNumber(g2);
		s1.drawNumber(g2);
		s2.drawNumber(g2);
		showDots(g2);
		showMode(g2);
	}
	
	/* (non-Javadoc)
	 * @see smartphone.App#onLoad()
	 */
	@Override
	public void onLoad() {
		// TODO Auto-generated method stub
		
	}

}
