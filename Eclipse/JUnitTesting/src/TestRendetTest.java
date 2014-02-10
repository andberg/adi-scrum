import static org.junit.Assert.*;

import org.junit.Test;


public class TestRendetTest
{

	@Test
	public void concat()
	{
		TextRender textRender = new TextRender(); 
		String result = textRender.concat("Helllllooo ", "World"); 
		assertEquals("Helllllooo World", result); 
	}

}
