import static org.junit.Assert.*;

import org.junit.Test;


public class CalculatorTest
{
	Calculator calculator = new Calculator(); 
	
	@Test
	public void canAdd()
	{	
		double result = calculator.add(3.0, 5.8);
		assertEquals(8.8, result, 0.0001);
	}
	
	@Test
	public void canDivide(){
		double result = calculator.divide(4.0, 2.0); 
		assertEquals(2.0, result, 0.0001); 	
	}
}
