import java.io.*;
public class Main {
	
	public static void main(String args[])
	{
		Person p = new Person();
		
		p.makeStanding();
		double bodyA =  Math.PI/180;
		System.out.println(p.toString());
		int i = 0;
		while (p.moveStanding(bodyA, 0, 0, 0, 0, 0, 0, 0, 0) == 0)
		{
			try
			{
				BufferedWriter out = new BufferedWriter(new FileWriter("standing/standing_"+i+".out"));
				out.write(p.toString());
				out.close();
			}
			catch (IOException e) {System.out.println(e.getMessage());}
			i++;
			System.out.println(i);
	
		}		
	}
}
