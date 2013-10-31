import junit.framework.TestCase;

public class RationalTest extends TestCase {

	protected Rational HALF;

	protected void setUp() {
		HALF = new Rational(1, 2);
	}

	// Create new test
	public RationalTest(String name) {
		super(name);
	}

	public void testEquality() {
		assertEquals(new Rational(1, 3), new Rational(1, 3));
		assertEquals(new Rational(1, 3), new Rational(2, 6));
		assertEquals(new Rational(3, 3), new Rational(1, 1));
	}

	// Test for nonequality
	public void testNonEquality() {
		assertFalse(new Rational(2, 3).equals(new Rational(1, 3)));
	}

	public void testAccessors() {
		assertEquals(new Rational(2, 3).numerator(), 2);
		assertEquals(new Rational(2, 3).denominator(), 3);
	}

	public void testRoot() {
		Rational s = new Rational(1, 4);
		Rational sRoot = null;
		try {
			sRoot = s.root();
		} catch (IllegalArgumentToSquareRootException e) {
			e.printStackTrace();
		}
		assertTrue(sRoot.isLessThan(HALF.plus(Rational.getTolerance()))
				&& HALF.minus(Rational.getTolerance()).isLessThan(sRoot));
	}

	public static void main(String args[]) {
		String[] testCaseName = { RationalTest.class.getName() };
		// junit.swingui.TestRunner.main(testCaseName);
		junit.textui.TestRunner.main(testCaseName);
	}
	
	//*************************************************** RATIONAL CONSTRUCTOR **************************************************
	public void testM_RationalConstr() {
		Rational x = new Rational(1, 1);
		boolean flag = false;
		String result = "Rational constructor: \n";
		int crt_tst=0, i=0, j=0;

		// testing Rational constructor - no exception expected
		int a_tst_nb=10;
		int[][] a  = {  {0xFFFFFFFF,0xFFFFFFFF},{0xFFFFFFFF,-1},
						{0xFFFFFFFF,0xFFFFFFFF},{-1,-1},
						{1,1},{-1,-1},
						{1,-1},{-1,1},
						{0xC0000000,0xFFFFFFF0},{67108864,1} };

		int ka_nb=4;
		int [] ka = { 1, -1, 0xFFFFFFF, 0xC0000000 };

		// rational number should be constructed - no exception expected  
		for (i = 0; i < a_tst_nb; i++) {
			for (j = 0; j < ka_nb; j++) {
				try{x = new Rational (ka[j]*a[i][0], ka[j]*a[i][1]);}
				catch (Exception e) { flag=true; 
          			result += "\ttest number: " + crt_tst + " exception when none expected for new Rational (" + ka[j]+" * " +a[i][0]+") / ("+ ka[j]+" * " +a[i][1]+ ") \n";
				}
				crt_tst++;
			}
		}
		
		// testing Rational constructor (gcd implicitely) - no exception expected
		int b_tst_nb=5;
		int[][] b  = {	{0x44444444,0x44444444},{0x88888888,-4},
						{0xFFFFFFF8,0xFFFFFFF4},{-32,-4},
						{40,4},{-16,-4},
						{4,-4},{0xCC,0xC4},
						{0xC0000000,0xCFFFFF0},{67108864,4}};

		int kb_nb=3;
		int [] kb = { -1, -4 , 4};

		// rational number should be constructed - no exception expected  
		for (i = 0; i < b_tst_nb; i++) {
			for (j = 0; j < kb_nb; j++) {	
				crt_tst++;
				try{x = new Rational (b[i][0]/kb[j], b[i][1]/kb[j]);}
				catch (Exception e) { flag=true; 
          		result += "\ttest number: " + crt_tst + " exception when none expected for new Rational ("+b[i][0]+" div "+kb[j]+") / ("+b[i][1]+" div " + kb[j]+") \n";
				}
				crt_tst++;
			}
	    }	
		
		// testing Rational constructor (gcd implicitely) - exception expected
		int c_tst_nb=3;
		int[][] c  = {{1,0},{2,0}, {0xFFFFFFFF,0}};
		
		int kc_nb=3;
		int [] kc = { 0, 1, -1};

		boolean no_exception=true;
		// rational number should not be constructed - exception expected  
		for (i = 0; i < c_tst_nb; i++) {
			for (j = 0; j < kc_nb; j++) {
				no_exception=true;
				try {x = new Rational (kc[j]*c[i][0], kc[j]*c[i][1]);}
				catch (Exception e){ no_exception=false;};
			    if (no_exception) { 
			    					result += "\ttest number: " + crt_tst + " exception expected and not seen for new Rational (" + kc[j]+" * " +c[i][0]+") / ("+ kc[j]+" * " +c[i][1]+ ") \n";
			    					flag=true;
			    					}	
			    crt_tst++;
			}
	    }
		
		// testing Rational constructor - exception expected
		int d_tst_nb=3;
		int[][] d  = {{1,0},{2,0}, {0xFFFFFFFF,0}};
		
		int kd_nb=5;
		int [] kd = { 0, 1, -1, 0xFFFFFFF,0xC0000000};

		// rational number should not be constructed - exception expected  
		for (i = 0; i < d_tst_nb; i++) {
			for (j = 0; j < kd_nb; j++) {
				no_exception=true;
				try {x = new Rational (d[i][0]/kd[j], d[i][1]/kd[j]);}
				catch (Exception e){ no_exception=false;};
			    if (no_exception) { 
			    					result += "\ttest number: " + crt_tst + " exception expected and not seen for new Rational ("+d[i][0]+" div "+kd[j]+") / ("+d[i][1]+" div " + kd[j]+") \n";
			    					flag=true;
			    					}
			    crt_tst++;
			}
	    }	
	

		if (flag) 
			System.out.println(result);
		assertFalse(flag);

	}

	//*************************************************** toString **************************************************
	public void testM_ToString() {
		boolean flag = false;
		String result = "toString: \n";
		Rational x = new Rational(1,1);
		Rational y = new Rational (1,1);
		int a_tst_nb=4;
		int crt_tst=0;
		// expected to have same string representations ?
		int[][][] a  = {
						{{0, 2452346}, {0, 45867}},
						{{0xFFFFFFFF, 0xFFFFFFFF}, {1, 1}},
						{{0, 0xFFFFFFFF}, {0, -1}},
						{{1, 0xFFFFFFFF}, {1, -1}}};

		for (int i = 0; i < a_tst_nb; i++) {
        	x = new Rational (a[i][0][0], a[i][0][1]);
           	y = new Rational (a[i][1][0], a[i][1][1]);
           	if (!x.toString().equals(y.toString())) {
           		result += "\ttest number: " + i + " should be the same ; String(" + x.toString() + " different " + y.toString() + ") \n";
           		flag = true;
           	}
		}
		
		
		int b_tst_nb=4;
		// expected to have different string representations 
		int[][][] b  = {
						{{1, 1}, {2, 1}},
						{{1, 0xFFFFFFFF}, {2, 0xFFFFFFFF}},
						{{0, 0xFFFFFFFF}, {0, -2}},
						{{0xFFFFFFFF, 0xFFFFFFFF}, {1, -1}}};

		for (int i = 0; i < b_tst_nb; i++) {
        	x = new Rational (b[i][0][0], b[i][0][1]);
           	y = new Rational (b[i][1][0], b[i][1][1]);
           	if (x.toString().equals(y.toString())) {
           		result += "\ttest number: " + i + " should be different; String(" + x.toString() + " same as " + y.toString() + ") \n";
           		flag = true;
           	}
		}
		
   		if (flag) 
   			System.out.println(result);
   		
   		assertFalse(flag);
	}
	//*************************************************** EQUALS **************************************************
	public void testM_Equals() {
		boolean flag = false;
		boolean wrong_input= false;
		String result = "EQUALS: \n";
		Rational x=null;
		Rational y=null;
		int crt_tst=0;
	//--------------------------------------------------
		// check if both are null
		//Exception expected if wrong conditions to call the function
		wrong_input=true;
		try {boolean temp = x.equals(null);}
		catch (Exception e){ wrong_input=false;};
	    if (wrong_input) { result += "\ttest number: " + crt_tst + " shows ( null.equals(null) did not throw an exception) \n";}	
		crt_tst++;

		
		y = new Rational (1,1);
		wrong_input=true;
		try {boolean temp = x.equals(y);}
		catch (Exception e){ wrong_input=false;};
	    if (wrong_input) { result += "\ttest number: " + crt_tst + " shows ( null.equals(1/1) did not throw an exception) \n";}	
		crt_tst++;
		
		//check if one is null 
		y = new Rational (1,1);
		if (y.equals(null)) {
			result += "\ttest number: " + crt_tst + " shows (" + y.toString() + " == null ) \n";
			flag = true; 
		}
		crt_tst++;


		// check if they are different classes
		if (y.equals(flag)){
			result += "\ttest number: " + crt_tst + " shows (" + y.toString() + " == boolean ) \n";
			flag = true; 
		}
		crt_tst++;//4
		x = new Rational(1,1);
		
	
		//--------------------------------------------------
		int a_tst_nb=5;
		// SPEC: two rational number I expect to have the same "value" (representation) 
		int[][][] a  = {
						{{1,0},{2,0}}, 														
						{{0xFFFFFFFF,0xFFFFFFFF},{0xFFFFFFFF,-1}},
						{{0xFFFFFFFF,0xFFFFFFFF},{-1,-1}},
						{{1,1},{-1,-1}},
						{{1,-1},{-1,1}},
						{{0xC0000000,0xFFFFFFF0},{67108864,1}}
						};
		for (int i = 0; i < a_tst_nb; i++) {
        	x = new Rational (a[i][0][0], a[i][0][1]);
           	y = new Rational (a[i][1][0], a[i][1][1]);
   			if (!x.toString().equals(y.toString())) {
   				result += "\ttest number: " + crt_tst + " shows (" + x.toString() + " != " + y.toString() + ") \n";
   				flag = true;
   			}
   			crt_tst++;
		}

		//---------------------------------------------------
		// SPEC: expected to have different representation - ATTENTION at division by zero {{1,0},{2,0}} - we already know that is a problem
		int b_tst_nb=2;
		int[][][] b  = {	{{1,0xFFFFFFFE}, {1,0xFFFFFFFF}}, 
							{{0xFFFFFFFE,0xFFFFFFFF} ,{0,-2}}, 	};
		
        for (int i = 0; i < b_tst_nb; i++) {
        	x = new Rational (b[i][0][0], b[i][0][1]);
           	y = new Rational (b[i][1][0], b[i][1][1]);
   			if (x.toString().equals(y.toString())) {
   				result += "\ttest number: " + crt_tst + " shows (" + x.toString() + " == " + y.toString() + ") \n";
   				flag = true;
   			}
        	crt_tst++;
        }
        
   		if (flag) 
   			System.out.println(result);
   		assertFalse(flag);
	}
	
	
	//*************************************************** ABS **************************************************
public void testM_Abs() {
	boolean flag = false;
	boolean wrong_input=true;
	String result = "ABS: \n";
	Rational x = null;
	Rational y = new Rational (1,1);
	// not correct input
	int crt_tst=0;
	
	//------------------------------------------------------------
	//Exception expected if wrong conditions to call the function
	wrong_input=true;
	try {x.abs();}
	catch (Exception e){ wrong_input=false;// expected to throw an exception 
	                   };
    if (wrong_input) { result += "\ttest number: " + crt_tst + " got ( null.abs() did not throw an exception) \n";}
    	
	crt_tst++;
	
	 x = new Rational(1,1);
	//------------------------------------------------------------
	//SPEC: abs() return positive all the time . The expectation is that both num and den are positive. 
	// the representation when both are negative (and hence the abs value ends up being positive) is something acceptable even though not ideal
	int a_tst_nb=12; 
	int[][] a  = {
					{-1,0},	{-2,0}, {0, 0xFFFFFFFF}, {0,67108864}, 
					{1,1}, {-1,-1}, {1,-1},	{-1,1},
					{0xFFFFFFFF,0xFFFFFFFF}, {0xFFFFFFFF,0xFFFFFFFF}, {0xC0000000,0xFFFFFFF0}, {67108864,1}
					};
	
	for (int i = 0; i < a_tst_nb; i++) {
    	y = new Rational (a[i][0], a[i][1]);
    	x = y.abs();
		if ((x.numerator() * x.denominator() < 0)) {
				result += "\ttest number: " + crt_tst + " got (" + x.toString() + " != " + y.toString() + ") \n";
				flag = true;	
		}
		crt_tst++;
	}
		
	//------------------------------------------------------------
	// SPEC: abs of the value multiplied with -1 should be the same 
	
	int b_tst_nb=12; 
	int[][] b  = {
					{-1,0},	{-2,0}, {0, 0xFFFFFFFF}, {0,67108864}, 
					{1,1}, {-1,-1}, {1,-1},	{-1,1},
					{0xFFFFFFFF,0xFFFFFFFF}, {0xFFFFFFFF,0xFFFFFFFF}, {0xC0000000,0xFFFFFFF0}, {67108864,1}
					};
	
	for (int i = 0; i < b_tst_nb; i++) {
    	x = new Rational (b[i][0], b[i][1]);
        y = x.divides(new Rational (1,-1));
    
    	if ((x.abs()).equals(y.abs())) {
				result += "\ttest number: " + crt_tst + " abs (" + x.toString() + ")  != abs(" + y.toString() + ") \n";
				flag = true;	
		}
		crt_tst++;
	}
    
	//------------------------------------------------------------
	// SPEC: two values different sign - must have same abs 
	int c_tst_nb=1;
	int[][][] c  = {
				//{{0xFFFFFFFF,0} ,{1,0}}, 		           	 		
				{{1,0xFFFFFFFF}, {0xFFFFFFFF,-1}}, 
				{{0xFFFFFFFF,1} ,{1,1}}, 
				{{0,0xFFFFFFFF} ,{0,1}},
				{{0xC0000000,-736455} ,{0xC0000000,736455}},  // +/-   +/+
				{{-67108864,-736455}  ,{-67108864, 736455}},    // -/-   -/+
				{{-67108864, 736455}  ,{ 67108864,-736455}},    // -/+   +/-
				};
	
    // expected same abs values
    for (int i = 0; i < c_tst_nb; i++) {
    	x = new Rational (c[i][0][0], c[i][0][1]);
       	y = new Rational (c[i][1][0], c[i][1][1]);
		if (!(x.abs()).equals(y.abs())) {
			result += "\ttest number: " + crt_tst + " abs (" + x.toString() + ") != abs(" + y.toString() + ") \n";
			flag = true;
		}
	 	crt_tst++;	
    }
    
      
    
    
    
    if (flag) 
    	System.out.println(result);
    assertFalse(flag);
	}
//*************************************************** DIVIDE **************************************************
public void testM_Divides_Times() {
	boolean flag = false;
	boolean wrong_input= false;
	String result = "TIMES / DIVIDES: \n";
	Rational x = new Rational(1,1);
	Rational y = null;
	Rational xdivy= new Rational (1,1);
	Rational one = new Rational (1,1);
	// not correct input
	int crt_tst=0;

	//------------------------------------------------------------
	// wrong input - null 
	wrong_input=true;
	try {x.divides(y);}
	catch (Exception e){ wrong_input=false;};
    if (wrong_input) { result += "\ttest number: " + crt_tst + "; EXCEPTION DIVIDES not seen thogh expected; x.divides (null) did not throw an exception) \n";}    
	crt_tst++;
	
	wrong_input=true;
	try {x.times(y);}
	catch (Exception e){ wrong_input=false;};
    if (wrong_input) { result += "\ttest number: " + crt_tst + "; EXCEPTION TIMES not seen thogh expected; x.times (null) did not throw an exception) \n";}    
	crt_tst++;
    
	wrong_input=true;
	try {y.divides(x);}
	catch (Exception e){ wrong_input=false;};
    if (wrong_input) { result += "\ttest number: " + crt_tst + "; EXCEPTION DIVIDES not seen thogh expected; null.divides(x) did not throw an exception) \n";}  
	crt_tst++;

	wrong_input=true;
	try {y.times(x);}
	catch (Exception e){ wrong_input=false;};
    if (wrong_input) { result += "\ttest number: " + crt_tst + "; EXCEPTION TIMES not seen thogh expected; null.times(x) did not throw an exception) \n";}  
	crt_tst++;
	// wrong input - division by zero 
	
	y = new Rational (0,1);	
	wrong_input=true;
	try {x.divides(y);}
	catch (Exception e){ wrong_input=false;};
    if (wrong_input) { result += "\ttest number: " + crt_tst + "; EXCEPTION DIVIDES not seen thogh expected; x.divides(zero) did not throw an exception) \n";}  
	crt_tst++;
	
	// wrong input - division by not a rational number 
	
	y = new Rational (1,0);	
	wrong_input=true;
	try {x.divides(y);}
	catch (Exception e){ wrong_input=false;};
    if (wrong_input) { result += "\ttest number: " + crt_tst + "; EXCEPTION DIVIDES not seen thogh expected; x.divides(1/0) did not throw an exception) \n";}  
	crt_tst++;
	
	wrong_input=true;
	try {x.times(y);}
	catch (Exception e){ wrong_input=false;};
    if (wrong_input) { result += "\ttest number: " + crt_tst + "; EXCEPTION TIMES not seen though expected; x.times(1/0) did not throw an exception) \n";}  
	crt_tst++;
	
	y = new Rational (1,1);
	
	//-----------------------------------------------------------------
	
	boolean no_exception=true;
	
	int a_tst_nb=4;  // 1.divides(y))  exception expected   
	int[][] a  = { {-1,0},	{-2,0}, {0, -342324} , {0,0}  };
		
	for (int i = 0; i < a_tst_nb; i++) {
	    no_exception=true;

	    try { y = new Rational (a[i][0], a[i][1]);}  
	    catch (Exception e){ no_exception=false;};
		
	    if(no_exception)  { // will not look for this problem if we already could not generate the Rational y 
	    
	    	try { xdivy = one.divides(y);}  
	    	catch (Exception e){ no_exception=false;};
	    	
	    	if (no_exception) { 
		    				result += "\ttest number: " + crt_tst + "; EXCEPTION DIVIDES not seen though expected; 1/y where y=" + y.toString()+"\n";
		    				flag=true;
	    	}
	    	crt_tst++;
	    }
	}


//-----------------------------------------------------------------


		 a_tst_nb=5;  // 1.divides(1.divides(y)) = y - no exception expected 
	
		int[][] anoe  = { {0xFFFFFFFF,0xFFFFFFFF},	{0xFFFFFFFF,1},	{-1,-1}, {0xC0000000,0xFFFFFFF0}, {67108864,1}};
	
	for (int i = 0; i < a_tst_nb; i++) {
    	y = new Rational (anoe[i][0], anoe[i][1]);
       	
    	try { xdivy = one.divides(y);}  
       	catch (Exception e) { System.out.println("\ttest number: " + crt_tst + "; EXCEPTION DIVIDES "+ one.toString()+ "divided by " + xdivy.toString()); }    
    	crt_tst++;
    	
    	try { xdivy = one.divides(xdivy);}  
       	catch (Exception e) { System.out.println("\ttest number: " + crt_tst + "; EXCEPTION DIVIDES "+ one.toString()+ " divided by " + one.toString()+ " divided by " + y.toString());}    
    	crt_tst++;
    	
		if (! y.equals(xdivy)) {
			result += "\ttest number: " + crt_tst + " divided ( (1/1) , ( 1/1 , " + y.toString() + ")) != " + xdivy +" \n";
			flag = true;
		}	
		crt_tst++;
	}

	//-----------------------------------------------------------------

    // BOUNDARY : -MAX / (-1) > + Max 	-2147483648 / -1 = + 2147483648 ( which goes over the max positive int 2147483647= 0x7FFFFFFF) 
	
	Rational z = new Rational(1,1);
	x = new Rational ( 0x80000000,1);
	y = new Rational ( 0x7FFFFFFF,1);
	
	no_exception=true; 
	Rational minus_one = new Rational(1,-1);
	try{z = x.divides(minus_one) ;}
	catch(Exception e) {no_exception=false;}
	if (no_exception) { 
					flag=true;
					result += "\ttest number: " + crt_tst + "; EXCEPTION DIVIDES expected and not seen "+ 
							x.toString()+ " divided by  " + minus_one.toString()+ " = " + z.toString()+ "\n"; 				
	}
	crt_tst++;
	if (no_exception) {
		if (z.equals(x)) {
			flag=true; 
			result += "\ttest number: " + crt_tst + "; Division by -1 not working";
		}
		crt_tst++;
	}
	

	//-----------------------------------------------------------------
	int b_tst_nb=4;  // testing division using multiplication of pairs of numbers
	int[][][] b  = { 														
					{{0xFFFFFFFF,0xFFFFFFFF},{0xFFFFFFFF,-1}},
					{{0xFFFFFFFF,0xFFFFFFFF},{-1,-1}},
					{{1,1},{-1,-1}},
					{{1,-1},{-1,1}},
					{{0xC0000000,0xFFFFFFF0},{67108864,1}}
					};
	
	for (int i = 0; i < b_tst_nb; i++) {
		x = new Rational (b[i][0][0], b[i][0][1]);
       	y = new Rational (b[i][1][0], b[i][1][1]);
       	
       	try { xdivy = x.divides(y);}  
       	catch (Exception e) { 		
       			result += "\ttest number: " + crt_tst + " EXCEPTION DIVIDES unexpected (" + x.toString() + " divided by " + y.toString() + ")\n";
       			flag = true;
       	}    
		crt_tst++;
		        	
		if (! x.equals(xdivy.times(y))) // x = y times ( x divides y)  
		{
			result += "\ttest number: " + crt_tst + "x!=1/1/y (" + x.toString() + " , " + y.toString() + ") = " + xdivy +" \n";
			flag = true;
		}
		crt_tst++;
		 	    
		try { xdivy = new Rational(x.numerator() * y.denominator(),y.numerator() * x.denominator());}
	    catch (Exception e) {        			
	    	result += "\ttest number: " + crt_tst + " EXCEPTION CONSTRUCTOR unexpected\n";
			flag = true;
		}
		crt_tst++;
		 		
		if (! x.equals(xdivy.times(y))) // x = y times ( Rational ( xn * yd, yn * xd ) ) 
		{
			result += "\ttest number: " + crt_tst + " divided (" + x.toString() + " , " + y.toString() + ") = " + xdivy +" \n";
			flag = true;
		}
		crt_tst++;
		 	
		one = new Rational (1,1); // x. times ( one .divides (y)) = x divides (y) 
		try { xdivy = new Rational(x.times(one.divides(y)));}
	    catch (Exception e) {System.out.println("\t EXCEPTION TIMES/DIVIDES "+ x.toString()+ " + " + y.toString()); }
		
		if (! x.equals(xdivy.times(y))) {
			result += "\ttest number: " + crt_tst + " divided (" + x.toString() + " , " + y.toString() + ") = " + xdivy +" \n";
			flag = true;
		}
		crt_tst++;
	}    

	if (flag) 
		System.out.println(result);
	assertFalse(flag);
}
//*************************************************** GCD INDIRECT **************************************************
public void testM_gcd() {

	boolean flag = false;
	String result = "GCD: \n";
	Rational two = new Rational(2,1);
	Rational x = new Rational(1,1);
	Rational y = new Rational(1,1);
	Rational sum = new Rational(1,1);
	int crt_tst=0;
	//-------------------------------------------------------------
	// testing the gcd indirectly via plus which will generate a gcd of num and a gcd of denum of the numbers 
	// gcd(0, 0) = 0
	
	int a_tst_nb=6; 
	int[][][] a  = {
			{{2,3},{2,3}},    																
			{{2,3},{0,0xC0000000}},					// gcd/plus/equal/times when a value is a zero
			{{0xFFFFFFFF,0xFFFFFFFF},{-1,-1}},	    // gcd/plus/equal/times to identify the gcd at MAX values
			{{0,1},{0,-1}},							// gcd/plus/equal/times with zeros
			{{1,-1},{-1,1}},						// gcd/plus/equal/times with mixed negative/positive 
			{{0xC0000000,0xFFFFFFF0},{67108864,-1}} // gcd/plus/equal/times with large numbers 
			};
		 
	for (int i = 0; i < a_tst_nb; i++) {
 
    	x = new Rational (a[i][0][0], a[i][0][1]);
       	y = new Rational (a[i][1][0], a[i][1][1]);
 
		// gcd(a,a) = |a|,  gcd(a,0) = |a|
       	try { sum = x.plus(y);}  
       	catch (Exception e) {System.out.println("\t EXCEPTION GCD "+ x.toString()+ " + " + y.toString()); /*e.printStackTrace();*/;}    

    	if (! sum.equals(two.times(x))){ 
    		result += "\ttest number: " + crt_tst + " plus (" + x.toString() + "," + y.toString() +"\n";
    		flag = true; 
    	} 
		crt_tst++;
	}
	
	//-------------------------------------------------------------
	// testing the gcd indirectly via Rational constructor
	int b_tst_nb=6;
	int[][] b  = {
					{1,0},{2,0}, 														
					{0xFFFFFFFF,0xFFFFFFFF},{0xFFFFFFFF,-1},
					{0xFFFFFFFF,0xFFFFFFFF},{-1,-1},
					{1,1},{-1,-1},
					{1,-1},{-1,1},
					{0xC0000000,0xFFFFFFF0},{67108864,1}
					};
	

	int k_nb=3;
	int [] k = { //0, 
				1, -1, 
				0xFFFFFFF, 
				0xC0000000
				};

	// supposed to find correct gcd and the rational number to end up being the same 
	for (int i = 0; i < b_tst_nb; i++) {
		x = new Rational (b[i][0], b[i][1]);
		for (int j = 0; j < k_nb; j++) {
			crt_tst++;
			
			y = new Rational (k[j]*b[i][0], k[j]*b[i][1]);
			
			
			if (!x.equals(y)) {
				result += "\ttest number: " + crt_tst + " gcd (" + x.toString() + " , " + y.toString() + ") \n";
				flag = true;
			}
		
			crt_tst++;
			if (k[j]!=0) { 
				try{y = new Rational (b[i][0]/k[j], b[i][1]/k[j]);}
				catch (Exception e) { flag=true; System.out.println("exception i ="+i+" k[j]=" +k[j]);}
			/*
				if (!x.equals(y)) {
					result += "\ttest number: " + crt_tst + " gcd (" + x.toString() + " , " + y.toString() + ") \n";
					flag = true;
				} */
			}
	
		}
    }	
	
	if (flag) 
		System.out.println(result);
	assertFalse(flag);

}
//*************************************************** LCM INDIRECT **************************************************
public void testM_lcm() {

	boolean flag = false, exception=false;
	String result = "LCM: \n";
	Rational two = new Rational(2,1);
	Rational x = new Rational(1,1);
	Rational y = new Rational(1,1);
	Rational sum = new Rational(1,1);
	// not correct input
	int crt_tst=0;
	if (x.equals(null)) {
		result += "\ttest number: " + crt_tst + " got (" + x.toString() + " == null ) \n";
		flag = true; 
	}
	crt_tst++;
	
	//-------------------------------------------------------------
	// testing the lcm indirectly via plus which will generate a lcm(a._denominator, b._denominator)  
	// lcm(0, 0) - shoud generate Exception if any is = zero  
	// lcm (x,y) > positive all the time 
	// lcm(a,b) = |a*b| / gcd(a,b)  - at least one of a,b should be non-zero
	int a_tst_nb=5; 
	int[][][] a  = {
			{{2,3},{2,3}}, 														
			{{0xFFFFFFFF,0xFFFFFFFF},{-1,-1}},
			{{0,1},{0,-1}},
			{{1,-1},{-1,1}},
			{{0xC0000000,0xFFFFFFF0},{67108864,1}}
			};
		 
	for (int i = 0; i < a_tst_nb; i++) {
		sum = new Rational (1,1);
		x = new Rational (a[i][0][0], a[i][0][1]);
     	y = new Rational (a[i][1][0], a[i][1][1]);

		// lcd(a,a) = |a|,
     	exception =false;
       	try { sum = x.plus(y);}  
       	catch (Exception e) {
       					exception=true; 
       					result +="\ttest number: " + crt_tst + " EXCEPTION RECEIVED and not expected( "+ x.toString()+ " + " + y.toString()+" ) \n"; 
       					flag=true;
       					}
		crt_tst++; 
      	if (!exception) {
      		if (! sum.equals(two.times(x))){ 
      			result += "\ttest number: " + crt_tst + " ( " + x.toString() + " plus " + y.toString() +" ) = ?  " +sum.toString()+" \n";
      			flag = true; 
      		}
      		crt_tst++; 
      	}
	} 
	if (flag) 
		System.out.println(result);
	assertFalse(flag);
}

//*************************************************** ROOT INDIRECT **************************************************
public void testM_SQRoot() {

	boolean flag = false;
	String result = "SQRoot: \n";
	Rational two = new Rational(2,1);
	Rational x = new Rational(1,1);
	Rational r = new Rational(1,1);
	
	// not correct input
	int crt_tst=0;
	if (x.equals(null)) {
		result += "\ttest number: " + crt_tst + " root (" + x.toString() + " == null ) \n";
		flag = true; 
	}
	crt_tst++;
	
	//-------------------------------------------------------------
	// root range values are 0,         
	// Rational low = new Rational(0,1);
    // Max Int value is 2147483647, square root is 46341
    // Rational high = new Rational(46341,1);
	
    Rational high = new Rational(46340,1);

	int a_tst_nb=10; 
	int[][] a  = {{4,1}, { 9 ,4},
			{46340,1},{-46340,-1}, 														
			{2147395600,46340},{-2147395600,-46340},
			{0,0xFFFFFFFF},{-1,-1},
			{0,1},{0,-1}
			};
		 
	for (int i = 0; i < a_tst_nb; i++) {
		crt_tst++; 
		x = new Rational (a[i][0], a[i][1]);

     	try { r = x.root();} 
     	catch (Exception e) {System.out.println("\t EXCEPTION ROOT "+ x.toString()); /*e.printStackTrace();*/}
     	
     	System.out.println(r.toString() + "=>" + x.toString());
     	if (! x.equals(r.times(r))){ 
     		result += "\ttest number: " + crt_tst + " root ( " + x.toString() + " )= " + r.toString()+ " \n";
     		flag = true; 
     	}

	} 
	if (flag) 
		System.out.println(result);
	assertFalse(flag);
}

public void testM_Tolerance() {

	boolean flag = false;
	String result = "Tolearance: \n";
	Rational x = new Rational(1,1);
	boolean no_exception=true;
	// not correct input 
	int crt_tst=0;
	
	//-------------------------------------------------------------
	// tolerance range values are 0,1000          
	
    Rational high_t = new Rational(1000,1);
    Rational low_t  = new Rational(1,1);
    
	int a_tst_nb=10; 
	
	int[][] a  = {{4,1}, { 9 ,4},
			{46340,1},{-46340,-1}, 														
			{2147395600,46340},{-2147395600,-46340},
			{0,0xFFFFFFFF},{-1,-1},
			{0,1},{1000,1}
			};
		 
	for (int i = 0; i < a_tst_nb; i++) {

		x = new Rational (a[i][0], a[i][1]);
        no_exception = true;
        if (x.isLessThan(low_t) || high_t.isLessThan(x) || x.equals(low_t)) {
        	try {Rational.setTolerance(x);} 
        	catch (Exception e) {no_exception=false;}
        	if (no_exception) {flag=true; result +="\ttest number: " + crt_tst + " EXCEPTION SET TOLERANCE expected and not seen ( " + x.toString() + " ) \n" ;}
        }
		crt_tst++;
		
		if (!no_exception) {if (!Rational.getTolerance().equals(x)){ 
			flag=true; 
			result+= "\ttest number: " + crt_tst + "getTolerance doesn't see the tolerance we set with seTolerance "+ x.toString()+" \n";
		}
		crt_tst++;
		}
	}
	if (flag) 
		System.out.println(result);
	assertFalse(flag);
}
public void testM_isLessThan() {

	boolean flag = false;
	String result = "isLessThan: \n";
	Rational x = new Rational(1,1);
	Rational y = new Rational(1,1);
	boolean no_exception=true;
	// not correct input 
	int crt_tst=0;
	
	//-------------------------------------------------------------
	// testing isLessThan knowing it is supposed to return false

	int a_tst_nb=6; 
	int[][][] a  = {
			{{2,3},{2,3}},    																
			{{2,3},{0,0xC0000000}},					
			{{0xFFFFFFFF,0xFFFFFFFF},{-1,-1}},	    
			{{0,1},{0,-1}},							
			{{1,-1},{-1,1}},						
			{{0xC0000000,0xFFFFFFF0},{67108864,-1}} 
			};
		 
	for (int i = 0; i < a_tst_nb; i++) {
 
    	x = new Rational (a[i][0][0], a[i][0][1]);
       	y = new Rational (a[i][1][0], a[i][1][1]);
       	if (x.isLessThan(y)) {
       		result += "\ttest number: " + crt_tst + "; LessThan wrong (" + x.toString() + " < " + y.toString() +")\n";
   			flag = true; 
       	}
       	crt_tst++;
	}	
	
	
	//-------------------------------------------------------------
	// testing isLessThan knowing it is supposed to return true

	int b_tst_nb=6; 
	int[][][] b  = {
			{{1,3},{2,3}},    																
			{{0,3},{1,0xC0000000}},					
			{{0xFFFFFFFF,1},{1,1}},
			{{1,0x7FFFFFFF},{1,0x7FFFFFFE}},									
			{{0x7FFFFFFE,1},{0x7FFFFFFF,1}},						
			{{0x80000000,1},{0x80000000,11}}
			};
		 
	for (int i = 0; i < b_tst_nb; i++) {
 
    	x = new Rational (b[i][0][0], b[i][0][1]);
       	y = new Rational (b[i][1][0], b[i][1][1]);
       	if (!x.isLessThan(y)) {
       		result += "\ttest number: " + crt_tst + "; LessThan wrong (" + x.toString() + " >= " + y.toString() +")\n";
   			flag = true; 
       	}
       	crt_tst++;
	}	
	
	if (flag) 
		System.out.println(result);
	assertFalse(flag);
	}

public void testM_plus_minus() {

	boolean flag = false, no_exception=true;
	String result = "PLUS / MINUS: \n";
	Rational xnew = new Rational(2,1);
	Rational x = new Rational(1,1);
	Rational y = new Rational(1,1);
	Rational sum = new Rational(1,1);
	int crt_tst=0;
	
	//-------------------------------------------------------------
	// testing the plus with the minus - no exception expected 
	
	int a_tst_nb=6; 
	int[][][] a  = {
			{{2,3},{2,3}},    																
			{{2,3},{0,0xC0000000}},					// plus when a value is a zero
			{{0xFFFFFFFF,0xFFFFFFFF},{-1,-1}},	    // plus MAX values
			{{0,1},{0,-1}},							// plus with zeros
			{{1,-1},{-1,1}},						// plus mixed negative/positive 
			{{0xC0000000,0xFFFFFFF0},{67108864,-1}} // plus large numbers 
			};
		 
	for (int i = 0; i < a_tst_nb; i++) {
 
    	x = new Rational (a[i][0][0], a[i][0][1]);
       	y = new Rational (a[i][1][0], a[i][1][1]);
 
		// gcd(a,a) = |a|,  gcd(a,0) = |a|
       	no_exception=true;
       	
       	try { sum = x.plus(y);}  
       	catch (Exception e) 
       		{	result += "\ttest number: " + crt_tst + "; EXCEPTION PLUS not expected but thrown(" + x.toString() + " + " + y.toString() +")\n";
       			flag = true; 
       			no_exception=false;
       		}    
   		crt_tst++;
   	   	
   		if (no_exception) {
   			try { xnew=sum.minus(y);}  
   			catch (Exception e) 
       			{	
   				result += "\ttest number: " + crt_tst + "; EXCEPTION MINUS not expected but thrown(" + sum.toString() + " - " + y.toString() +")\n";
       			flag = true; 
       			no_exception=false;
       			}    
   			crt_tst++;
   		}
   	   	
    	if (no_exception) {
    		if (!x.equals(xnew)) {
    			flag=true; 
    			result += "\ttest number: " + crt_tst + "; x!= (x+y)-x; for: " +x.toString() +" , " +y.toString() + "; xnew="+sum.minus(y).toString()+"\n";
    		}
    		crt_tst++;
    	}  	
 	}
	
	//-------------------------------------------------------------
	// testing the plus - exception expected
	
	int b_tst_nb=2; 
	int[][][] b  = {																
			{{0x7FFFFFFF,1},{-1,-1}},	    // plus MAX values
			{{0x7FFFFFFF,1},{0x7FFFFFFF,1}} // plus large numbers 
			};
		 
	for (int i = 0; i < b_tst_nb; i++) {
 
    	x = new Rational (b[i][0][0], b[i][0][1]);
       	y = new Rational (b[i][1][0], b[i][1][1]);
 
    	try{sum = x.plus(y);}
    	catch(Exception e) {no_exception=false;}
    	if (no_exception) { 
    					flag=true;
    					result += "\ttest number: " + crt_tst + "; EXCEPTION PLUS expected and not seen "+ 
    							x.toString()+ " + " + y.toString()+ " = "+sum.toString()+"\n"; 				
    	}
    	crt_tst++;
  }
	//-------------------------------------------------------------
	// testing the minus - exception expected
	
	int c_tst_nb=2; 
	int[][][] c  = {																
			{{0x80000000,1},{-1,-1}},	    // minus MAX values
			{{0x7FFFFFFF,1},{0x7FFFFFFF,-1}} // minus large numbers 
			};
		 
	for (int i = 0; i < c_tst_nb; i++) {
 
    	x = new Rational (c[i][0][0], c[i][0][1]);
       	y = new Rational (c[i][1][0], c[i][1][1]);
 
    	try{sum = x.minus(y);}
    	catch(Exception e) {no_exception=false;}
    	if (no_exception) { 
    					flag=true;
    					result += "\ttest number: " + crt_tst + "; EXCEPTION MINUS expected and not seen "+ 
    							x.toString()+ " - " + y.toString()+ " = "+sum.toString()+"\n"; 				
    	}
    	crt_tst++;
  }
		
		
		
	if (flag) 
		System.out.println(result);
	assertFalse(flag);

}

}
	