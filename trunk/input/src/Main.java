

public class Main {
	static final double PI = Math.PI;
	static final String[] typeName = {"falling", "lying", "sitting", "standing"};
	
	static final double lowerBase = -PI/36;
	static final double upperBase = PI/36;
	
	static final String path = "training_set_ext/";
	static final double[][] initial = {{3*PI/4+lowerBase,3*PI/2+lowerBase,3*PI/2+lowerBase,3*PI/2+lowerBase,3*PI/2+lowerBase, 3*PI/4+lowerBase,3*PI/4+lowerBase, 3*PI/4+lowerBase,3*PI/4+lowerBase},
									   {0+lowerBase, 0+lowerBase, 0+lowerBase, 0+lowerBase, 0+lowerBase, 0+lowerBase, 0+lowerBase, 0+lowerBase, 0+lowerBase},
									   {3*PI/2+lowerBase,3*PI/2+lowerBase,3*PI/2+lowerBase,3*PI/2+lowerBase,3*PI/2+lowerBase, 0+lowerBase,3*PI/2+lowerBase, 0+lowerBase,3*PI/2+lowerBase},
									   {3*PI/2+lowerBase,3*PI/2+lowerBase,3*PI/2+lowerBase,3*PI/2+lowerBase,3*PI/2+lowerBase,3*PI/2+lowerBase,3*PI/2+lowerBase,3*PI/2+lowerBase,3*PI/2+lowerBase}};
	
	static final double[][] limits = {{3*PI/4+upperBase,3*PI/2+upperBase,3*PI/2+upperBase,3*PI/2+upperBase,3*PI/2+upperBase, 3*PI/4+upperBase,3*PI/4+upperBase, 3*PI/4+upperBase,3*PI/4+upperBase},
									  {0+upperBase, 0+upperBase, 0+upperBase, 0+upperBase, 0+upperBase, 0+upperBase, 0+upperBase, 0+upperBase, 0+upperBase},
		   							  {3*PI/2+upperBase,3*PI/2+upperBase,3*PI/2+upperBase,3*PI/2+upperBase,3*PI/2+upperBase, 0+upperBase,3*PI/2+upperBase, 0+upperBase,3*PI/2+upperBase},
		   							{3*PI/2+upperBase,3*PI/2+upperBase,3*PI/2+upperBase,3*PI/2+upperBase,3*PI/2+upperBase,3*PI/2+upperBase,3*PI/2+upperBase,3*PI/2+upperBase,3*PI/2+upperBase}};
	public static void main(String args[])
	{
		
		for (int i = 0; i < typeName.length; i++)
		{
			Generator gen = new Generator
			(initial[i], limits[i], path+typeName[i],i, PI/18);
			gen.generate();
		}
		
	}
}

