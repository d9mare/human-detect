import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

public class Person {
	
	final int bodyLen = 5 ;
	final int partLen = 2;
	final int WINDOW_SIZE = 4 * bodyLen;
	final double PI = Math.PI;
	final int N = 9;
	final Color[] colors = {Color.PINK, Color.RED, Color.RED, Color.RED, Color.RED, Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN};
	
	static String newline = System.getProperty("line.separator");
	
	/*
	 * leftHandF : left hand first part
	 * leftHandS : left hand second part
	 * rh* : right hand
	 * lf* : left foot
	 * rf* : right foot
	 */
	//Line body, leftHandF, leftHandS, rightHandF, rightHandS, leftFootF, leftFootS, rightFootF, rightFootS;
	Vector<Line> bodyLines;
	/*
	 * same, but for the angles of articulation; the angle it's measured by the vertical axe
	 */
	//Angle ab, leftHandFa, leftHandSa, rightHandFa, rightHandSa, leftFootFa, leftFootSa, rightFootFa, rightFootSa;
	Vector<Angle> angles;
	
	       
	public Person(double angles[])
	{
		bodyLines = new Vector<Line>();
		this.angles = new Vector<Angle>();
		for (int i = 0; i < N;i++)
		{
			bodyLines.add(new Line());
			this.angles.add(new Angle());
		}
		makeDefault(angles);
	}
	
	public String toString()
	{
		String st = "";
		/*
		 * for each person
		 * first line  : body line coordinates
		 * second line : hand coordinates (left f and s, right f and s)
		 * third line : legt coordinates  (left, right)
		 * empty line
		 */
		st = bodyLines.elementAt(0).toString() + newline;
		for (int i = 1; i < N; i++)
		st += bodyLines.elementAt(i).toString() + newline;
		
		return st;
		
	}
	
	private void makeDefault(double[] angles
			/*double lhf, double lhs, double rhf, double rhs,
			   double lff, double lfs, double rff, double rfs*/)
	{
		Line body = bodyLines.elementAt(0);
		
		body.set(new Point(2*bodyLen, 2*bodyLen),new Angle(angles[0]), bodyLen);
		
		for (int i = 1; i < N; i = i + 2)
		{
			if (i < 5)
			bodyLines.elementAt(i).set(new Point(body.pStart.x, body.pStart.y), new Angle(angles[i]), partLen);
			else bodyLines.elementAt(i).set(new Point(body.pEnd.x, body.pEnd.y), new Angle(angles[i]), partLen);
			
		}
		for (int i = 2; i < N; i = i + 2)
		{
			bodyLines.elementAt(i).set(new Point(bodyLines.elementAt(i-1).pEnd.x, bodyLines.elementAt(i-1).pEnd.y), new Angle(angles[i]), partLen);
			
		}
		
		for (int i = 0; i < N;i++)
		{
			this.angles.elementAt(i).set(angles[i]);
		}
		
	}
	
	private void drawTransformed (String fileName, double angle, double x, double y)
	{
		BufferedImage img = new BufferedImage(WINDOW_SIZE, WINDOW_SIZE, BufferedImage.TYPE_INT_RGB); 
		Graphics2D graphics = img.createGraphics();
		
		File outputFile = new File(fileName);
		
		graphics.clearRect(0, 0, WINDOW_SIZE, WINDOW_SIZE);
		graphics.rotate(angle, WINDOW_SIZE/2, WINDOW_SIZE/2);
		graphics.translate(x, y);
		
		
		for (int i = 0; i < N; i++)
		{
			graphics.setColor(colors[i]);
			Line line = bodyLines.elementAt(i);
			graphics.drawLine((int)(line.pStart.x ),(int)(line.pStart.y ), (int)(line.pEnd.x ), (int)(line.pEnd.y ) );
			
		}
		try 
		{
			ImageIO.write(img, "bmp", outputFile);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	public void draw(String fileName)
	{
		drawTransformed(fileName, Math.PI, 0, 0);
	}
	
	public void drawAll (String fileName)
	{
		for (double i = 0; i < 2*Math.PI; i = i + Math.PI/2)
		{
			String[] st = fileName.split("/");
			String fileName2 = "";
			for (int j = 0; j < st.length-1;j++)
			{
				fileName2 = fileName2 + st[j]+"/";
			}
			 fileName2 = fileName2 + (int)(i*2/Math.PI) + "r"+st[st.length-1];
			drawTransformed(fileName2, i, 0, 0);
		}
	}
	
}
