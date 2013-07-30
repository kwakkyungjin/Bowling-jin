package sds.bowling.jin;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BowlingTest {
	Bowling bowling = null;
	ArrayList<BowFrame> arr = new ArrayList<BowFrame>();
	BowFrame frame = null;
	

	@Before
	public void setup() {
		bowling = new Bowling();
		bowling.setNewFrame();
		bowling.startFlag = true;
	}

	@Test
	public void GameStart() {
		
		bowling.roll(arr);
	}

	@After
	public void scoreCal() {
		
	}	
	
	
	
	
	
	
	
	
	
	
	
}
