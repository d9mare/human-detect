
public class Angle {
	double rad;
	
	public Angle()
	{
		rad = 0;
	}
	
	public Angle(double value) 
	{
		rad = value > (2 * Math.PI)? (value - Math.floor(value / (2 * Math.PI))) : (value < 0? 2 * Math.PI + value: value);
	}
	
	public void set(double value)
	{
		rad = value > (2 * Math.PI)? (value - Math.floor(value / (2 * Math.PI))) : (value < 0? 2 * Math.PI + value: value);
	}

}
