public class Line {
	
	Point pStart;
	Point pEnd;
	double size;
	Angle angle;
	
	public Line() 
	{		
		pStart = new Point();
		pEnd = new Point();
		size = 0.0;
		angle = new Angle();
	}
	
	public Line(Point p1, Point p2)
	{
		pStart = new Point(p1.x, p1.y);
		pEnd = new Point(p2.x, p2.y);
		size = Math.hypot(pStart.x - pEnd.x, pStart.y - pEnd.y);
		angle.set(Math.acos((pEnd.y - pStart.y)/ size));
	}
	
	public Line(Point p, Angle r, double s)
	{
		pStart = new Point(p.x, p.y);
		angle = r;
		size = s;
		pEnd = new Point(p.x + (size * Math.sin(r.rad)), p.y - (size * Math.cos(r.rad)));
	}
	
	public void set(Point p1, Point p2)
	{
		pStart = p1;
		pEnd = p2;
	}
	
	public void set(Point p, Angle r, double s)
	{
		pStart.x = p.x; pStart.y = p.y;
		angle = r;
		size = s;
		pEnd.x = p.x + (size * Math.cos(r.rad));
		pEnd.y = p.y + (size * Math.sin(r.rad));
	}
	
	public void setSize(double s)
	{
		this.size = s;
	}
	
	public String toString()
	{
		return  this.pStart.x + " " + this.pStart.y + " " + this.pEnd.x + " " + this.pEnd.y ;
	}

}
