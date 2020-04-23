package smartphone;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Objects.Contact;

public class CoreTest {

	@Before
	public void setUp() throws Exception {
		new Core();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testThemeColor() {
		assertEquals(Core.getSp().getActiveTheme().getThemeColor(),new Color(150,82,126));
	}
	
	@Test
	public void testContact(){
		assertEquals(Core.getSp().getListContact().get(8).getFirstName(),"Mike");
	}
	
	@Test
	public void testApp(){
		assertEquals(Smartphone.getAppList().get(2).getName(),"Gallery");
	}
	

}
