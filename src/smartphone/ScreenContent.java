package smartphone;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import Objects.Param;

// TODO: Auto-generated Javadoc
/**
 * The Class Screen.
 * @author Pierre Anken
 */
@SuppressWarnings("serial")
public class ScreenContent extends JPanel {
	
	/**
	 * Instantiates a new screen.
	 *
	 * @param landscape the landscape
	 */
	public ScreenContent(Boolean landscape){
		
		setLayout(new CardLayout());
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		if(landscape){
			Core.getSp();
			//create landscape cards
			for(App anApp : Smartphone.getAppList())
				if(anApp.isFullScreen())
					add(new AppContainer(anApp),anApp.getName());
		}
		else{
			//create portrait cards
			//special screens
			if(Param.getParam(Core.getSp().getSParams(),"BootComplete") == null)
				add(new Boot(),"bootScreen");
			
			add(new AppliView(),"homeScreen");
			
			Core.getSp();
			//app add
			for(App anApp : Smartphone.getAppList())
				if(!anApp.isFullScreen())
					add(new AppContainer(anApp),anApp.getName());	
		}
		setOpaque(false);
	}
	
	/**
	 * Sets the card.
	 *
	 * @param appName the new card
	 */
	
	//app display
	public void setCard(String appName){
		if(appName != "bootScreen" && appName != "homeScreen")
		try {
			Core.getSp().getApp(appName).onLoad();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((CardLayout)this.getLayout()).show(this,appName);
	}
	
	/**
	 * Sets the home.
	 */
	//home display
	public void setHome(){
		if(Param.getParam(Core.getSp().getSParams(),"BootComplete") != null)
				((CardLayout)this.getLayout()).show(this,"homeScreen");
	}
}
