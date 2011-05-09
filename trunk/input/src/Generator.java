
public class Generator {
	private int N;
	private double [] limits;
	private double [] current;
	private double [] init;
	private String dir;
	private double step;
	private int counter;
	private int type;
	
	public Generator (double[] init, double[] limits, String dir, int type, double step)
	{
		this.dir = dir;
		this.limits = limits;
		this.init = init;
		N = limits.length;
		this.step = step;
		counter = 0;
		this.type = type;
		
		current = new double[N];
		for (int i = 0; i < N; i++)
		{
			current[i] = init[i]; 
		}
		System.out.println("init");
	}
	
	private void backGenerate(int k)
	{
		if ( k == N)
		{
			Person p = new Person(current);
			counter++;
			String st = "";
			for (int i = 0; i < N;i++)
			{
				st = st + current[i] + " ";
			}
			System.out.println(counter + " " + st);
			p.draw(dir+"/"+counter+"_"+type+".bmp");
			
		}
		else
		{
				for (double i = init[k]; i <= limits[k]; i = i + step)
				{
					current[k] = i;
					backGenerate(k+1);
				}					
		}
	}
	
	public void generate()
	{
		backGenerate(0);
	}
	
	
}
