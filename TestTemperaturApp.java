package org.openqa.selenium.example;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import junit.framework.*;

public class TestTemperatureApp extends TestCase {
    
	
	public void testCorrectLogin1() throws Exception {
		boolean login=true;
		boolean can_advance=true;
        WebDriver driver = new FirefoxDriver();
        WebElement query=null;
        WebElement resultsDiv=null;
        
        String [] [] a = { {"andy", "apple"}, {"bob","bathtub"}, {"charley", "china"}};                
        
        for (int i = 0; i < a.length; i++) {
        	// Go to the home page
        	driver = new FirefoxDriver();
        	driver.get("http://adnan.appspot.com/testing-lab-login.html");
        
        	// Logging in  with password 
        	try{query = driver.findElement(By.name("userId"));}
        	catch (Exception e) { 
        		can_advance=false; 
        		assertTrue(can_advance);
        	} 
        	query.clear();
        	query.sendKeys(a[i][0]);
        
        	try {query = driver.findElement(By.name("userPassword"));}
        	catch (Exception e) { 
        		can_advance=false; 
        		assertTrue(can_advance);
        	} 
      	
        	query.clear();
        	query.sendKeys(a[i][1]);
      
        	// LOGGING IN 
        	try {query = driver.findElement(By.xpath("//input[@type='submit']"));}
        	catch (Exception e) { 
        		can_advance=false; 
        		assertTrue(can_advance);
        	} 

        	query.click();
             
        	// Sleep until the div we want is visible or 5 seconds is over
        
        	long end = System.currentTimeMillis() + 5000;
        	login=false;
        	while (System.currentTimeMillis() < end) {
        		try {resultsDiv = driver.findElement(By.name("farenheitTemperature"));}
            	catch (Exception e) { 
            		can_advance=false; 
            		assertTrue(can_advance);
            	} 

        		// If results have been returned, the results are displayed in a drop down.
        		if (resultsDiv.isDisplayed()) { login=true;
        		break;
        		}	
        	}
        	if(!login) {System.out.println(" Login Failed and expected to pass for "+a[i][0]+" password used "+ a[i][1]+"\n");}
        }      
        assertTrue(login); 
        
    }
    
	public void testCorrectLogin2() throws Exception {
		boolean login=true;
		boolean can_advance=true;
		boolean failed=false;
        WebDriver driver = new FirefoxDriver();
        WebElement query=null;
        WebElement resultsDiv=null;
        
        String [] [] a = { {"aNDy", "apple"}, {"BOB","bathtub"}, {"cHARlEY", "china"}};                
        
        for (int i = 0; i < a.length; i++) {
        	// Go to the home page
        	driver = new FirefoxDriver();
        	driver.get("http://adnan.appspot.com/testing-lab-login.html");
        
        	// Logging in  with password 
        	try{query = driver.findElement(By.name("userId"));}
        	catch (Exception e) { 
        		can_advance=false; 
        		assertTrue(can_advance);
        	} 
        	query.clear();
        	query.sendKeys(a[i][0]);
        
        	try {query = driver.findElement(By.name("userPassword"));}
        	catch (Exception e) { 
        		can_advance=false; 
        		assertTrue(can_advance);
        	} 
      	
        	query.clear();
        	query.sendKeys(a[i][1]);
      
        	// LOGGING IN 
        	try {query = driver.findElement(By.xpath("//input[@type='submit']"));}
        	catch (Exception e) { 
        		can_advance=false; 
        		assertTrue(can_advance);
        	} 

        	query.click();
             
        	// Sleep until the div we want is visible or 5 seconds is over
        
        	long end = System.currentTimeMillis() + 5000;
        	login=false;
        	while (System.currentTimeMillis() < end) {
        		try {resultsDiv = driver.findElement(By.name("farenheitTemperature"));}
            	catch (Exception e) { 
            		can_advance=false; 
            		assertTrue(can_advance);
            	} 

        		// If results have been returned, the results are displayed in a drop down.
        		if (resultsDiv.isDisplayed()) 
        		{login=true;}
        		if(login) break;
        	}
    		try {resultsDiv = driver.findElement(By.name("farenheitTemperature"));}
        	catch (Exception e) { 
        		can_advance=false; 
        		assertTrue(can_advance);
        	} 

    		// If results have been returned, the results are displayed in a drop down.
    		if (resultsDiv.isDisplayed()) {login=true;}

        	if(!login) {
        		failed=true;
        		System.out.println(" Login Failed and expected to pass for "+a[i][0]+" password used "+ a[i][1]+"\n");}
        }      
        assertFalse(failed); 
        
    }
    
	public void testInorrectLogin() throws Exception {
		boolean login=true;
		boolean can_advance=true;
		boolean failed=false;
        WebDriver driver = new FirefoxDriver();
        WebElement query=null;
        WebElement resultsDiv=null;
        
        String [] [] a = {{"andy","Apple"},{"bob","BATHTUB"},{"charley","chINA"},{"micky","apple"},{"andy","china"}};                
        
        for (int i = 0; i < a.length; i++) {
        	// Go to the home page
        	driver = new FirefoxDriver();
        	driver.get("http://adnan.appspot.com/testing-lab-login.html");
        
        	// Logging in  with password 
        	try{query = driver.findElement(By.name("userId"));}
        	catch (Exception e) { 
        		can_advance=false; 
        		assertTrue(can_advance);
        	} 
        	query.clear();
        	query.sendKeys(a[i][0]);
        
        	try {query = driver.findElement(By.name("userPassword"));}
        	catch (Exception e) { 
        		can_advance=false;
        		assertTrue(can_advance);
        	} 
      	
        	query.clear();
        	query.sendKeys(a[i][1]);
      
        	// LOGGING IN 
        	try {query = driver.findElement(By.xpath("//input[@type='submit']"));}
        	catch (Exception e) { 
        		can_advance=false; 
        		assertTrue(can_advance);
        	} 

        	query.click();
             
        	// Sleep until the div we want is visible or 5 seconds is over
        	//Doing lookup with andy
        	//performing check
        	//<html><head><title>Bad Login</title></head><body><h2>Input combination of user id and password is incorrect.</h2>
        	//<br>Here's a <a href="http://adnan.appspot.com/testing-lab-login.html">link</a> to the login page.</body></html>

        
        	long end = System.currentTimeMillis() + 5000;
        	while (System.currentTimeMillis() < end) {
        		
        		try {resultsDiv = driver.findElement(By.linkText("link"));
           		// If results have been returned, the results are displayed in a drop down.
        		if (resultsDiv.isDisplayed()) { 
        			login=false;} }
            	catch (Exception e) { ;
            	} 
        		// If results have been returned, the results are displayed in a drop down.
        		
        		if(!login)
        			 {break;}
        		}
        	
    		login=true;
    		try {resultsDiv = driver.findElement(By.name("farenheitTemperature"));}
        	catch (Exception e) { login=false;
        	} 
    		if(login) {failed=true;}
    		
        }
        	// if I succeeded to login then we have a problem
        	if(failed) {System.out.println(" Login Passed and not expected \n");}      
        	assertFalse(failed); 

        
        
    }
	
	public void testIncorrectLoginDelay() throws Exception {
		boolean login=false;
		boolean can_advance=true;
        boolean failed=false;
		WebDriver driver = new FirefoxDriver();
        WebElement query=null;
        WebElement resultsDiv=null;
        
        String [] [] a = { {"andy", "Apple"}, {"andy","BATHTUB"}};                
        
        for (int i = 0; i < a.length; i++) {
        	// Go to the home page
        	driver = new FirefoxDriver();
        	driver.get("http://adnan.appspot.com/testing-lab-login.html");
        
        	// Logging in  with password 
        	try{query = driver.findElement(By.name("userId"));}
        	catch (Exception e) { 
        		can_advance=false; 
        		assertTrue(can_advance);
        	} 
        	query.clear();
        	query.sendKeys(a[i][0]);
        
        	try {query = driver.findElement(By.name("userPassword"));}
        	catch (Exception e) { 
        		can_advance=false; 
        		assertTrue(can_advance);
        	} 
      	
        	query.clear();
        	query.sendKeys(a[i][1]);
      
        	// LOGGING IN 
        	try {query = driver.findElement(By.xpath("//input[@type='submit']"));}
        	catch (Exception e) { 
        		can_advance=false; 
        		assertTrue(can_advance);
        	} 

        	query.click();
             
        	// Sleep until the div we want is visible or 5 seconds is over
        	//Doing lookup with andy
        	//performing check
        	//<html><head><title>Frequent Login</title></head><body><h2>Wait for 10 seconds before trying to login again</h2></body></html>
        	
        	
        	long end = System.currentTimeMillis() + 5000;
        	login=false;
        	while (System.currentTimeMillis() < end) {
        		try {resultsDiv = driver.findElement(By.name("farenheitTemperature"));}
            	catch (Exception e) { 
            		can_advance=true;
            	} 
        		if(can_advance) break;

        		// If results have been returned, the results are displayed in a drop down.
        		if (resultsDiv.isDisplayed()) { 
        			login=true;
        			break;
        		}	
        	}
        }
        
        
        
        long sec10 = System.currentTimeMillis() + 1000*60;
        while (System.currentTimeMillis() < sec10) { // try all the time to advance 
        	
            driver.get("http://adnan.appspot.com/testing-lab-login.html");
            can_advance=true;
            // Logging in  with password 
            try{query = driver.findElement(By.name("userId"));}
            catch (Exception e) { 
            	can_advance=false; 
            } 
            	
            if(can_advance) {
            	query.clear();
            	query.sendKeys("andy");
  
            	try {query = driver.findElement(By.name("userPassword"));}
            	catch (Exception e) { 
            		can_advance=false; 
            	} 
            		
            	query.clear();
            	query.sendKeys("apple");
          
            	// LOGGING IN 
            	try {query = driver.findElement(By.xpath("//input[@type='submit']"));}
            	catch (Exception e) { 
            		can_advance=false; 
            	} 
            	query.click();
            		
            	long end = System.currentTimeMillis() + 5000;
                login=false;
                while (System.currentTimeMillis() < end) {
                	try {resultsDiv = driver.findElement(By.name("farenheitTemperature"));
                	if (resultsDiv.isDisplayed()) { 
                		login=true; failed=true;}	}
                    catch (Exception e) {;} 
                	
                	if (failed) break;
                 }// end checking if we succeeded to login
            }// end the fact that we could attept to login
            if (failed) break;
        }// end the 10 sec
        
    if(failed) {System.out.println(" Succeeded to login before 10 sec elapsed\n");}      
    assertFalse(failed); 
   }
 
    public void testCorrectInputTemp() throws Exception {
    	boolean login=true;
		boolean gotCelsius=true;
		boolean gotCity=true;
		boolean can_advance=true;
		boolean failed=false;
		int expected_precision=2;
		WebElement resultsDiv ;
		WebElement conversion_result;
		WebElement city_report;
		String Celsius_conversion;
		String city_temp_report;
        String [] contents;
        String [] value;
        String [] digits;
        String result = " TEST RESULT\n";
        int len=0;
        
		WebDriver driver = new FirefoxDriver();
            // Go to the home page
        driver.get("http://adnan.appspot.com/testing-lab-login.html");
            
       // Logging in as Andy with password apple ( not significant, just to bring it to the next page)
        WebElement query = driver.findElement(By.name("userId"));
        query.clear();
        query.sendKeys("charley");
  
        query = driver.findElement(By.name("userPassword"));
        if (!query.isDisplayed()) { System.out.println("got to wrong page\n"); can_advance=false;}
        assertTrue(can_advance);
        
        query.clear();
        query.sendKeys("china");
          
        // LOGGING IN 
        query = driver.findElement(By.xpath("//input[@type='submit']"));
        if (!query.isDisplayed()) { System.out.println("got to wrong page\n"); can_advance=false;}
        assertTrue(can_advance);
        query.click();
           
        // Sleep until the div we want is visible or 5 seconds is over
            
        long end = System.currentTimeMillis() + 5000;
        login=false;
        resultsDiv=null;
        while (System.currentTimeMillis() < end) {
             try { resultsDiv = driver.findElement(By.name("farenheitTemperature"));}
             catch (Exception e) { assertTrue(false);} 
            // If results have been returned, the results are displayed in a drop down.
            if (resultsDiv.isDisplayed()) { login=true;
              break;
            }
        }
       	if(!login) {System.out.println(" Login Failed and expected to pass ; cannot continue=> test failed.\n");}     
        assertTrue(login);
            
        //---------------------------------------------    
        // here we are moving into the application page that expects the value n Farenheit
        //<html>
        //<head> <title> Online temperature conversion calculator </title> </head>
        //<body>
        //<h3>Convert from Farenheit to Celsius</h3>
        //<form action="testing-lab-conversion" method="post">
        //Temperature in Farenheit
        //  <input type="text" name="farenheitTemperature" value="451"><br>
        //  <input type="submit" value="Convert" />
        //</form>
        //</body>
        
        int city=0; // we have only 3 cities hence the value can be only 0 (Austin),1(Berkeley),2(New York)
        String [] 	far = 	   { "0","1.123","2.432","211.78","211.1", "-111.55", "213.27", "214.875", "215.53", "-1.72"}; 
        int [] far_precision = { 2, 2, 2, 2, 2, 1, 1, 1, 1, 1};
        int f=0; // far goes over the values presented above to be tested for the Farenheit value we provide
        
        //int [][]Tests={{0,0},{1,1},{2,2},{3,1},{4,2},{5,1},{6,2},{7,0},{8,1},{9,2}};
        int [][]Tests={{8,2}};
        
        for ( int j=0; j< Tests.length ; j++) {
        	city= Tests[j][1]; // which city to choose this test
        	f = Tests[j][0]; // which value from the Farenhet values
        	if ((f<0) || (f>far.length)) break; // wrong test
        	if ((city < 0 )|| (city > 2)) break; //wrong city index
        	expected_precision = far_precision[f];
        	
        	System.out.println("\n--- Testing with test number "+Integer.toString(j)+", test using "+far[f]+"F and city "+Integer.toString(city)+"\n");
        	try{query = driver.findElement(By.name("farenheitTemperature"));}
        	catch (Exception e) {
        		System.out.println("Got to wrong page\n"); can_advance=false;
        		assertTrue(can_advance);
        	}
            query.clear();
        	query.sendKeys(far[f]);
        
        	try{query = driver.findElement(By.xpath("//input[@type='submit']"));}
        	catch (Exception e) {
        		System.out.println("Got to wrong page\n"); can_advance=false;
            	assertTrue(can_advance);
        	}
        	query.click();
        
        	end = System.currentTimeMillis() + 5000;
        	gotCelsius=false;
        	while (System.currentTimeMillis() < end) {
        		try{ resultsDiv = driver.findElement(By.name("city"));}
        		catch (Exception e) { 	
        			System.out.println("Got to wrong page\n"); 
        			can_advance=false;
        			assertTrue(can_advance);}
        		// If results have been returned, the results are displayed in a drop down.
        		if (resultsDiv.isDisplayed()) { gotCelsius=true;
        		break;
        		}
        	}  
       		if (resultsDiv.isDisplayed()) { gotCelsius=true;} 
        	if(!gotCelsius) {System.out.println(" Cannot get past the convertion to the city choice ; Asked to convert "+ far[f] +"F\n");}     
        	assertTrue(login);
        
        
        	// READ the value in Celsius equivalence text as provided by the converter
        	conversion_result=null;
        	try { conversion_result = driver.findElement(By.tagName("h2"));}
        	catch (Exception e) {
        		System.out.println("Got to wrong page\n"); can_advance=false;
            	assertTrue(can_advance);
        	}
  
            Celsius_conversion = conversion_result.getText();
            
            // checking the precision of the decimal output value. IF > 2 digits => record a failing test
            len=0;
            contents = Celsius_conversion.split("=");
            value    = contents[1].trim().split("\\s+");
            digits   = value[0].split("\\."); 
            if (digits.length>1) {
            	len=(digits[1]).length(); 
            	if (len>expected_precision){
            		result += "\ttest number: "+Integer.toString(j)+" For temperature "+ far[f] + "F shows precision of "+ Integer.toString(len)+" in C\n";                                    
            		failed=true; 
            	}
              }
            
            System.out.println(Celsius_conversion); // prints something like : "251 Farenheit = 121.67 Celsius"
            System.out.println("Read value in celsius "+value[0]+" expected precision "+Integer.toString(expected_precision)+"\n"); 
        	//--------------------------------------------------------------
        	// here we enter the page that gives us the value in Celsius 
        	//<html>
        	//<head><title>Temperature Converter Result</title></head>
        	//<body>
        	//<h2>451 Farenheit = 232.78 Celsius </h2>
        	//<form action="LookupTemperature" method="post">
        	//<input type="radio" name="city" value="Austin">Austin<br>
        	//<input type="radio" name="city" value="Berkeley">Berkeley<br>
        	//<input type="radio" name="city" value="New York">New York<br>
        	//<input type="submit" value="submit order"></form>
        	//</body>
        	//</html>

            try {
            	switch(city) {
        			case 0: query = driver.findElement(By.xpath("//input[@type='radio' and @value='Austin'] ")); break;
        			case 1:query = driver.findElement(By.xpath("//input[@type='radio' and @value='Berkeley'] "));break;
        			case 2:query = driver.findElement(By.xpath("//input[@type='radio' and @value='New York'] "));break;
        			default: query = driver.findElement(By.xpath("//input[@type='radio' and @value='Austin'] "));
        		}
            }
            catch (Exception e) {
            	System.out.println("Got to wrong page\n"); 
            	can_advance=false;
            	assertTrue(can_advance);
        	}
        	query.click();
     
        	query = driver.findElement(By.xpath("//input[@type='submit']"));
            if (!query.isDisplayed()) { System.out.println("Got to wrong page\n"); can_advance=false;}
            assertTrue(can_advance);
        	query.click();

        	end = System.currentTimeMillis() + 5000;
        	gotCity=false;
        	while (System.currentTimeMillis() < end) {
        		resultsDiv = driver.findElement(By.cssSelector("h2"));
        		// If results have been returned, the results are displayed in a drop down.
        		if (resultsDiv.isDisplayed()) { gotCity=true;
        		break;
        		}
        	}  
        
        	if(!gotCity) {System.out.println(" Chose the city and got stuck \n");}     
        	assertTrue(login);

        	//--------------------------------------------------------------
        	// here we enter the page that prints the value in C for the city (Austin) we selected 
        	
        	//<html>
        	//<head><title>Temperature in Austin</title></head>
        	//<body>
        	//    <h2>Temperature in Austin = 72 degrees Farenheit</h2>
        	//    <p><a href="http://adnan.appspot.com/testing-lab-calculator.html">Link</a> to converter page.
        	//</body>
        	//</html>
        	city_report=null;
        	try{city_report = driver.findElement(By.tagName("h2"));}
        	catch (Exception e) {
        		System.out.println("Got to wrong page\n"); 
        		can_advance=false;
        		assertTrue(can_advance);
        	}
        	
            // READ the value reported as being the temperature in a given city
            city_temp_report = city_report.getText();
            
            // checking that the value reported for this city = the value provided to the converter. If not => record a failing test
            len=0;
            contents = city_temp_report.split("=");
            value    = contents[1].trim().split("\\s+");
            if(!far[f].equals(value[0])){
            	result += "\ttest number: "+Integer.toString(j)+" Provided "+ far[f] + " and city displayed "+ value[0]+"\n";                                    
            	failed=true; 
            }
            
        	System.out.println(city_temp_report); // prints something like : "Temperature in Austin = 72 degrees Farenheit"
        	//System.out.println("Read value for city as being "+value[0]+"\n");
        			
        	try {query = driver.findElement(By.linkText("Link"));}
        	catch (Exception e) { 
        		System.out.println(" could not find link\n");
        		can_advance=false;
        		 System.out.println(" could not find link\n");can_advance=false;
        	}
        	if (query.isDisplayed()) {query.click();} 
        
        }
     if (failed)  System.out.println(result);
     assertFalse(failed);
    }
    public void testIncorrectInputTemp() throws Exception {
    	boolean login=true;
		boolean can_advance=true;
		boolean failed=false;
		
		WebElement resultsDiv ;
		WebElement conversion_result;
		
        String result = " TEST RESULT\n";
       
        
		WebDriver driver = new FirefoxDriver();
            // Go to the home page
        driver.get("http://adnan.appspot.com/testing-lab-login.html");
            
       // Logging in as Andy with password apple ( not significant, just to bring it to the next page)
        WebElement query = driver.findElement(By.name("userId"));
        query.clear();
        query.sendKeys("charley");
  
        query = driver.findElement(By.name("userPassword"));
        if (!query.isDisplayed()) { System.out.println("got to wrong page\n"); can_advance=false;}
        assertTrue(can_advance);
        
        query.clear();
        query.sendKeys("china");
          
        // LOGGING IN 
        query = driver.findElement(By.xpath("//input[@type='submit']"));
        if (!query.isDisplayed()) { System.out.println("got to wrong page\n"); can_advance=false;}
        assertTrue(can_advance);
        query.click();
           
        // Sleep until the div we want is visible or 5 seconds is over
            
        long end = System.currentTimeMillis() + 5000;
        login=false;
        resultsDiv=null;
        while (System.currentTimeMillis() < end) {
             try { resultsDiv = driver.findElement(By.name("farenheitTemperature"));}
             catch (Exception e) { assertTrue(false);} 
            // If results have been returned, the results are displayed in a drop down.
            if (resultsDiv.isDisplayed()) { login=true;
              break;
            }
        }
       	if(!login) {System.out.println(" Login Failed and expected to pass ; cannot continue=> test failed.\n");}     
        assertTrue(login);
            
        //---------------------------------------------    
        // here we are moving into the application page that expects the value n Farenheit
        
        int city=0; // we have only 3 cities hence the value can be only 0 (Austin),1(Berkeley),2(New York)
        String [] 	far = 	   { "3E45","bobo"}; 
        // far goes over the values presented above to be tested for the Farenheit value we provide
               
        for ( int f=0; f< far.length ; f++) {
        	
        	System.out.println("\n--- Testing with test number "+Integer.toString(f)+", test using "+far[f]+"F \n");
        	try{query = driver.findElement(By.name("farenheitTemperature"));}
        	catch (Exception e) {
        		System.out.println("Got to wrong page\n"); can_advance=false;
        		assertTrue(can_advance);
        	}
            query.clear();
        	query.sendKeys(far[f]);
        
        	try{query = driver.findElement(By.xpath("//input[@type='submit']"));}
        	catch (Exception e) {
        		System.out.println("Got to wrong page\n"); can_advance=false;
            	assertTrue(can_advance);
        	}
        	query.click();
        
        	
        	//---------------------expected to go to the page:
        	//<html><head><title>Bad Temperature</title></head><body><h2>Need to enter a valid temperature!Got a NumberFormatException on bobo</h2></body></html>
        	// If we see "city" it means it is wrong
        	
        	end = System.currentTimeMillis() + 5000;
        	conversion_result=null;

        	while (System.currentTimeMillis() < end) {

        		try { conversion_result = driver.findElement(By.tagName("h2"));}
            	catch (Exception e) { // we must see an h2 no matter if it accepts or doesn't accept the value
            		System.out.println("Got to wrong page\n"); 
            		can_advance=false;
                	assertTrue(can_advance);
            	}        
        		if (conversion_result.isDisplayed()) break; // we got some page that has an h2 in it
        	}
        
			try {resultsDiv = driver.findElement(By.name("city")); 
				if(resultsDiv.isDisplayed()){
						failed=true;
						result+="\ttest number: "+Integer.toString(f)+" Provided wrong input "+far[f]+"and was accepted\n";
						}
				}
			catch (Exception e){ ;
			}
   
        	driver.get("http:adnan.appspot.com/testing-lab-calculator.html");// return to a place where we provide data
        }
        if (failed)  System.out.println(result);
        assertFalse(failed);
    }
    
}
