import junit.framework.*;
import com.mockobjects.servlet.*;

public class TestTestingLabConverterServlet extends TestCase {


  
  
/*  ----------------------------------- SPECIFICATION REQUIRED TO TEST -----------------------  
  -T1-Temperature results should be 2 places of precision for temperatures from 0 to 212 degrees Farenheit, inclusive, and 1 place of precision otherwise.
  -T2-Temperature inputs are floating point numbers in decimal notation (i.e., 97 or -3.14, but not 9.73E2)
  -T2-Temperature inputs that are not valid should return Got a NumberFormatException on (input string)
  -T3-The temperature parameter should be passed in as farenheitTemperature=-40 in the URL; the parameter “farenheitTemperature” should be case insensitive.
*/
  
  public void test_M_T1_Precision() throws Exception {
	  /* SPEC: Temperature results should be: 
	   * 								2 places of precision for temperatures from 0 to 212 degrees Farenheit, inclusive, and 
	   * 								1 place of precision otherwise.
	   */
	String result = " PRECISION: \n";
	String [] contents;
	String [] value;
	String [] digits;
	boolean flag = false;
	int crt_test=0;
	
	TestingLabConverterServlet s = new TestingLabConverterServlet();
    MockHttpServletRequest request = 
      new MockHttpServletRequest();
    MockHttpServletResponse response = 
      new MockHttpServletResponse();
    
    //--------------------------------------------------------------
    // Expects to have a precision of not more than 2 
    // use consecutive values to "catch" a variety of situation, including no "\." in the value   
    // considering
    
    Double [] a = {	0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 
    				99.12, 100.32, 101.55, 102.74, 
    				204.0, 205.0, 206.0, 207.0, 208.0, 209.0, 210.0, 211.0, 212.0 };
	int len=0;
	
	for (int i = 0; i < a.length; i++) {
	   request = new MockHttpServletRequest();
	   response = new MockHttpServletResponse();
	    request.setupAddParameter("farenheitTemperature", Double.toString(a[i]));
	    response.setExpectedContentType("text/html");
	    s.doGet(request,response);
	    response.verify();
	    if (response.getOutputStreamContents().indexOf("=") != -1) { // make sure it contains the '=' in the response 
	    	contents = response.getOutputStreamContents().split("=");
	    	value	= contents[1].trim().split("\\s+");
	    	digits = value[0].split("\\.");
	    	if (digits.length>2) { 
	    		len=(digits[1]).length();
	    		if (len>1)
	    		{
	    			result += "\ttest number: " + crt_test + " For temperature "+ Double.toString(a[i]) + "F shows precision of "+ Integer.toString(len)+" in C\n";
	    			flag=true;
	    		}
	    	}
	    }
		crt_test++;
	}
	
	   //--------------------------------------------------------------
    // Expects to have a precision of not more than 1
    // use 9 consecutive values to "catch" a variety of situation, including no "\." in the value   
    // considering
    
	Double [] b = {213.0, 214.0, 215.0, 216.0, 217.0, 218.0, 219.0, 220.0, 221.0, 
				300.23, 500.75, 
				-1.72, -2.91, -3.53, -4.89, -111.55, -212.63 };
	len=0;
	
	for (int i = 0; i < b.length; i++) {
	   request = new MockHttpServletRequest();
	   response = new MockHttpServletResponse();
	    request.setupAddParameter("farenheitTemperature", Double.toString(b[i]));
	    response.setExpectedContentType("text/html");
	    s.doGet(request,response);
	    response.verify();
	    if (response.getOutputStreamContents().indexOf("=") != -1) { // make sure it contains the '=' in the response 
	    	contents = response.getOutputStreamContents().split("=");
	    	value	= contents[1].trim().split("\\s+");
	    	digits = value[0].split("\\.");
	    	if (digits.length>1) { 
	    		len=(digits[1]).length();
	    		if (len>1)
	    		{
	    			result += "\ttest number: " + crt_test + " For temperature "+ Double.toString(b[i]) + "F shows precision of "+ Integer.toString(len)+" instead of 1 in C\n";
	    			flag=true;
	    		}
	    	}
	    }
		crt_test++;
	}
	
	
	if (flag) 
		System.out.println(result);
	assertFalse(flag);

  }
  
  public void test_M_T2_FPorDformat() throws Exception {
	  /* SPEC: Temperature inputs are:  
	   * 								floating point numbers , and 
	   * 								in decimal notation 
	   * 								(i.e., 97 or -3.14, but not 9.73E2)
	   *
	   * Temperature inputs that are not valid should return Got a NumberFormatException on (input string)
	   */

		String result = "F temp input correctness: \n";
		String contents;
		boolean flag = false;
		int crt_test=0;
		int len=0;
		
		TestingLabConverterServlet s = new TestingLabConverterServlet();
	    MockHttpServletRequest request = 
	      new MockHttpServletRequest();
	    MockHttpServletResponse response = 
	      new MockHttpServletResponse();
	    
	    //--------------------------------------------------------------
	    // Expects to be correct inputs if format in decimal  -3.14 
		//		<html><head><title>Temperature Converter Result</title>"
		//		+ "</head><body><h2>" + farTemp + " Farenheit = " + celTemp + " Celsius "
		//		+ "</h2>
   
	    String [] a = {	"-0.0", "-1.37", "-200.4320"," -3987.2342570", "47362345.92845", "-19471575.0024752", "7.0000", "0008.0", "9.0000 "};

		for (int i = 0; i < a.length; i++) {
		   request = new MockHttpServletRequest();
		   response = new MockHttpServletResponse();
		   request.setupAddParameter("farenheitTemperature",a[i]);
		   response.setExpectedContentType("text/html");
		   s.doGet(request,response);
		   response.verify();
		   if (response.getOutputStreamContents().indexOf("=") == -1) { 
			   // if it doesn't contain the "=" sign it means it was not accepted 
			   result += "\ttest number: " + crt_test + " Input temperature "+ a[i] + " was not accepted as legal\n";
			   flag=true;
		   }
			crt_test++;
		}
		
		//--------------------------------------------------------------
	    // Expects to be correct inputs if format integer; the response would be:
		//		<html><head><title>Temperature Converter Result</title>"
		//		+ "</head><body><h2>" + farTemp + " Farenheit = " + celTemp + " Celsius "
		//		+ "</h2>
	    
		int [] b = {213 };
		len=0;
		
		for (int i = 0; i < b.length; i++) {
		   request = new MockHttpServletRequest();
		   response = new MockHttpServletResponse();
		   request.setupAddParameter("farenheitTemperature", Integer.toString(b[i]));
		   response.setExpectedContentType("text/html");
		   s.doGet(request,response);
		   response.verify();
		   if (response.getOutputStreamContents().indexOf("=") == -1) { 
			   // if it doesn't contain the "=" sign it means it was not accepted 
			   result += "\ttest number: " + crt_test + " Input temperature "+ a[i] + " was not accepted as legal\n";
			   flag=true;
		   }
		   crt_test++;
		}
		
		//--------------------------------------------------------------
	    // Expects the following to be incorrect inputs and throw exception and the response contains:
		//		"<html><head><title>Bad Temperature</title>"
		// 		+ "</head><body><h2>Need to enter a valid temperature!"
	    //		+ "Got a NumberFormatException on "
		//								- OR - 
		//		"<html><head><title>No Temperature</title>"
		//		+ "</head><body><h2>Need to enter a temperature!"
		//		+ "</h2></body></html>"
	
		String [] c = {"213E1", "boo", "34E2", "9.73E2" };
		len=0;

		for (int i = 0; i < c.length; i++) {
		   request = new MockHttpServletRequest();
		   response = new MockHttpServletResponse();
		   request.setupAddParameter("farenheitTemperature", c[i]);
		   response.setExpectedContentType("text/html");
		   s.doGet(request,response);
		   response.verify();
		   contents=response.getOutputStreamContents();
		   if (response.getOutputStreamContents().indexOf("=") != -1) { 
			   //if it contains the '=' in the response it was accepted even if it shouldn't have been 
			   result += "\ttest number: " + crt_test + " Input temperature "+ c[i] + " was accepted as legal\n";
			   flag=true;		   
		   }    	
		crt_test++;
		}
		
		if (flag) 
			System.out.println(result);
		assertFalse(flag);
  }

  
  public void test_M_T3_CaseInsensitiveFormat() throws Exception {
	  /* SPEC: The temperature parameter should be passed in as farenheitTemperature=-40 in the URL;
	   * 					the parameter “farenheitTemperature” should be case insensitive
	   */
		String result = "farenheitTemperature CaSEe iNseENEitiVTty: \n";
		String []contents;
		int crt_test=0;
		boolean flag = false;
		
		TestingLabConverterServlet s = new TestingLabConverterServlet();
	    MockHttpServletRequest request = 
	      new MockHttpServletRequest();
	    MockHttpServletResponse response = 
	      new MockHttpServletResponse();
	    
	    //--------------------------------------------------------------
	    // Expected to accept all the formats. If not it will respond with: 
		//		"<html><head><title>No Temperature</title>"
		//		+ "</head><body><h2>Need to enter a temperature!"
		//		+ "</h2></body></html>"
 
	    String [] a = {	"farenheitTemperature", "FarenheitTemperature", "FARENHEITTemperature", 
	    			    "farenheitTEMEPERATURE", "FARENHEITTEMEPERAURE", "farenheittemperature",
	    			    "FaRaNhEiTtEmPeRaTuRe", "fArEnHeItTeMpArAtUrE"};
	    
		for (int i = 0; i < a.length; i++) {
		   request = new MockHttpServletRequest();
		   response = new MockHttpServletResponse();
		   request.setupAddParameter(a[i],"212");
		   response.setExpectedContentType("text/html");
		   s.doGet(request,response);
		   response.verify();
		   contents=response.getOutputStreamContents().split("<");
		   for (int j=0; j<contents.length; j++) {
			   if ( contents[j].equals("No")) { 
				   // if it contains the "No Temperature" text. It means it did not accept the format  
				   result += "\ttest number: " + crt_test + " Should be case insensitive, but "+ a[i] + " was not accepted as legal\n";
				   flag=true;
			   }
		   }
			crt_test++;
		}
		if (flag) 
			System.out.println(result);
		assertFalse(flag);
  }
 
}

