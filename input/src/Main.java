

public class Main {
	static final double PI = Math.PI;
	static final String[] TYPE_NAME = {"falling", "lying", "sitting", "standing"};
	
	static final double LOWER_BASE = -PI/18;
	static final double UPPER_BASE = PI/18;
	
	static final String path = "trainingsetext2/";
	static final double[][] initial = {{3*PI/4+LOWER_BASE,3*PI/2+LOWER_BASE,3*PI/2+LOWER_BASE,3*PI/2+LOWER_BASE,3*PI/2+LOWER_BASE, 3*PI/4+LOWER_BASE,3*PI/4+LOWER_BASE, 3*PI/4+LOWER_BASE,3*PI/4+LOWER_BASE},
									   {0+LOWER_BASE, 0+LOWER_BASE, 0+LOWER_BASE, 0+LOWER_BASE, 0+LOWER_BASE, 0+LOWER_BASE, 0+LOWER_BASE, 0+LOWER_BASE, 0+LOWER_BASE},
									   {3*PI/2+LOWER_BASE,3*PI/2+LOWER_BASE,3*PI/2+LOWER_BASE,3*PI/2+LOWER_BASE,3*PI/2+LOWER_BASE, 0+LOWER_BASE,3*PI/2+LOWER_BASE, 0+LOWER_BASE,3*PI/2+LOWER_BASE},
									   {3*PI/2+LOWER_BASE,3*PI/2+LOWER_BASE,3*PI/2+LOWER_BASE,3*PI/2+LOWER_BASE,3*PI/2+LOWER_BASE,3*PI/2+LOWER_BASE,3*PI/2+LOWER_BASE,3*PI/2+LOWER_BASE,3*PI/2+LOWER_BASE}};
	
	static final double[][] limits = {{3*PI/4+UPPER_BASE,3*PI/2+UPPER_BASE,3*PI/2+UPPER_BASE,3*PI/2+UPPER_BASE,3*PI/2+UPPER_BASE, 3*PI/4+UPPER_BASE,3*PI/4+UPPER_BASE, 3*PI/4+UPPER_BASE,3*PI/4+UPPER_BASE},
									  {0+UPPER_BASE, 0+UPPER_BASE, 0+UPPER_BASE, 0+UPPER_BASE, 0+UPPER_BASE, 0+UPPER_BASE, 0+UPPER_BASE, 0+UPPER_BASE, 0+UPPER_BASE},
		   							  {3*PI/2+UPPER_BASE,3*PI/2+UPPER_BASE,3*PI/2+UPPER_BASE,3*PI/2+UPPER_BASE,3*PI/2+UPPER_BASE, 0+UPPER_BASE,3*PI/2+UPPER_BASE, 0+UPPER_BASE,3*PI/2+UPPER_BASE},
		   							{3*PI/2+UPPER_BASE,3*PI/2+UPPER_BASE,3*PI/2+UPPER_BASE,3*PI/2+UPPER_BASE,3*PI/2+UPPER_BASE,3*PI/2+UPPER_BASE,3*PI/2+UPPER_BASE,3*PI/2+UPPER_BASE,3*PI/2+UPPER_BASE}};
	public static void main(String args[])
	{
		
		for (int i = 0; i < TYPE_NAME.length; i++)
		{
			Generator gen = new Generator
			(initial[i], limits[i], path+TYPE_NAME[i],i, PI/18);
			gen.generate();
		}
		/*Generator gen = new Generator
		(initial[2], limits[2], path+typeName[2],2, PI/18);
		gen.generate();*/
		
	}
}

