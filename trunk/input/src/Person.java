import java.util.*;

public class Person {
	
	final double bodyLen = 50 ;
	final double partLen = 25;
	final double PI = Math.PI;
	final int N = 9;
	static String newline = System.getProperty("line.separator");
	
	/*
	 * leftHandF : left hand first part
	 * leftHandS : left hand second part
	 * rh* : right hand
	 * lf* : left foot
	 * rf* : right foot
	 */
	//Line body, leftHandF, leftHandS, rightHandF, rightHandS, leftFootF, leftFootS, rightFootF, rightFootS;
	Vector<Line> body;
	/*
	 * same, but for the angles of articulation; the angle it's measured by the vertical axe
	 */
	//Angle ab, leftHandFa, leftHandSa, rightHandFa, rightHandSa, leftFootFa, leftFootSa, rightFootFa, rightFootSa;
	Vector<Angle> angles;

	public Person()
	{
		body = new Vector<Line>();
		angles = new Vector<Angle>();
		for (int i = 0; i < N;i++)
		{
			body.add(new Line());
			angles.add(new Angle());
		}
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
		st = body.toString() + newline;
		st += leftHandF.toString() + " " + leftHandS.toString() + " " + rightHandF.toString() + " " + rightHandS.toString() + newline;
		st += leftFootF.toString() + " " + leftFootS.toString() + " " + rightFootF.toString() + " " + rightFootS.toString() + newline;
		
		return st;
		
	}
	
	
	private void makeDefault(double bodyA, double lhf, double lhs, double rhf, double rhs,
			   double lff, double lfs, double rff, double rfs)
	{
		body.set(new Point(2*partLen + bodyLen, 2*partLen + bodyLen),new Angle(bodyA), bodyLen);
		
		leftHandF.set(new Point(body.pStart.x, body.pStart.y),new Angle(lhf), partLen);
		leftHandS.set(new Point(leftHandF.pEnd.x, leftHandF.pEnd.y),new Angle(lhs), partLen);
		rightHandF.set(new Point(body.pStart.x, body.pStart.y),new Angle(rhf), partLen);
		rightHandS.set(new Point(rightHandF.pEnd.x, rightHandF.pEnd.y),new Angle(rhs), partLen);
		
		leftFootF.set(new Point(body.pEnd.x, body.pEnd.y),new Angle(lff), partLen);
		leftFootS.set(new Point(leftFootF.pEnd.x, leftFootF.pEnd.y),new Angle(lfs), partLen);
		rightFootF.set(new Point(body.pEnd.x, body.pEnd.y),new Angle(rff), partLen);
		rightFootS.set(new Point(leftFootF.pEnd.x, leftFootF.pEnd.y),new Angle(rfs), partLen);
		
		ab.set(bodyA);
		leftHandFa.set(lhf); leftHandSa.set(lhs); rightHandFa.set(rhf); rightHandSa.set(rhs);
		leftFootFa.set(lff); leftFootSa.set(lfs); rightFootFa.set(rff); rightFootSa.set(rfs);
	}
	
	private void moveDefault(double bodyA, double lhf, double lhs, double rhf, double rhs,
	 		 double lff, double lfs, double rff, double rfs)
	{
		ab.set(bodyA);
		body.set(body.pStart, ab, bodyLen);
		
		leftHandF.pStart.set(body.pStart.x, body.pStart.y);
		rightHandF.pStart.set(body.pStart.x, body.pStart.y);
		
		leftHandF.set(leftHandF.pStart, new Angle(leftHandFa.rad + lhf), partLen);
		rightHandF.set(rightHandF.pStart, new Angle(rightHandFa.rad + rhf), partLen);
		leftHandFa.set(leftHandFa.rad + lhf);
		rightHandFa.set(rightHandFa.rad + rhf);
		
		leftHandS.pStart.set(leftHandF.pEnd.x, leftHandF.pEnd.y);
		rightHandS.pStart.set(rightHandF.pEnd.x, rightHandF.pEnd.y);
		
		leftHandS.set(leftHandS.pStart, new Angle(leftHandSa.rad + lhs), partLen);
		rightHandS.set(rightHandS.pStart, new Angle(rightHandSa.rad + rhs), partLen);
		leftHandSa.set(leftHandSa.rad + lhs);
		rightHandSa.set(rightHandSa.rad + rhs); 
		
		leftFootF.pStart.set(body.pEnd.x, body.pEnd.y);
		rightFootF.pStart.set(body.pEnd.x, body.pEnd.y);
		
		leftFootF.set(leftFootF.pStart, new Angle(leftFootFa.rad + lff), partLen);
		rightFootF.set(rightFootF.pStart, new Angle(rightFootFa.rad + rff), partLen);
		leftFootFa.set(leftFootFa.rad + lff);
		rightFootFa.set(rightFootFa.rad + rff);
		
		leftFootS.pStart.set(leftFootF.pEnd.x, leftFootF.pEnd.y);
		rightFootS.pStart.set(rightFootF.pEnd.x, rightFootF.pEnd.y);
		
		leftFootS.set(leftFootS.pStart, new Angle(leftFootSa.rad + lfs), partLen);
		rightFootS.set(rightFootS.pStart, new Angle(rightFootSa.rad + rfs), partLen);
		leftFootSa.set(leftFootSa.rad + lfs);
		rightFootSa.set(rightFootSa.rad + rfs); 	 
	}

	private double to2pi( double value) 
	{
		return value > (2 * Math.PI)? (value - Math.floor(value / (2 * Math.PI))) : (value < 0? 2 * Math.PI + value: value);
	}
	
	public void makeStanding()
	{
		makeDefault(3*PI/2,3*PI/2,3*PI/2,3*PI/2,3*PI/2,3*PI/2,3*PI/2,3*PI/2,3*PI/2);
	}
	
	public int moveStanding(double bodyA, double lhf, double lhs, double rhf, double rhs,
										   double lff, double lfs, double rff, double rfs)
	{
		double val = ab.rad + bodyA;
		
		if ( (to2pi(val - 3*PI/2) > (PI / 4) && to2pi(val - 3*PI/2) < (7 * PI / 4)) /*||
			 (to2pi(leftFootFa.rad + lff - 3*PI/2) > PI/4 && to2pi(leftFootFa.rad + lff - 3*PI/2) < (7*PI/4)) ||
			 (to2pi(rightFootFa.rad + rff - 3*PI/2) > PI/4 && to2pi(rightFootFa.rad + rff - 3*PI/2) < (7*PI/4))*/)				
			return -1;
		
		moveDefault(val,lhf, lhs, rhf, rhs, lff, lfs, rff, rfs);	
		return 0;
	}
	
	public void makeSitting()
	{
		// sitting, all lines are vertical but feet first parts, which are horizontal
		makeDefault(3*PI/2,3*PI/2,3*PI/2,3*PI/2,3*PI/2, 0,3*PI/2, 0,3*PI/2);
	}
	
	public int moveSitting(double bodyA, double lhf, double lhs, double rhf, double rhs,
								 		 double lff, double lfs, double rff, double rfs)
	{
		double val = ab.rad + bodyA;
		
		if ( (to2pi(val) > (7*PI / 4) || to2pi(val) < (5 * PI / 4)) /*||
				 ((to2pi(leftFootFa.rad + lff) > PI/6) && to2pi(leftFootFa.rad + lff) < 11*PI/6) ||
				 ((to2pi(rightFootFa.rad + rff) > PI/6) && to2pi(rightFootFa.rad + rff) < 11*PI/6)*/)				
				return -1;
			
		moveDefault(val,lhf, lhs, rhf, rhs, lff, lfs, rff, rfs);		
		return 0;
	}
	
	public void makeLying()
	{
		makeDefault(11 * PI / 6, 0, 0, 0, 0, 0, 0, 0, 0);
	}
	
	public int moveLying(double bodyA, double lhf, double lhs, double rhf, double rhs,
								 		 double lff, double lfs, double rff, double rfs)
	{
		double val = ab.rad + bodyA;
		if ( to2pi(val) < (11 * PI / 6) && to2pi(val) > 0 )			
				return -1;
		
		moveDefault(val,lhf, lhs, rhf, rhs, lff, lfs, rff, rfs);
		
		return 0;
	}
	
	public void makeFalling()
	{
		makeDefault(3*PI/4,3*PI/2,3*PI/2,3*PI/2,3*PI/2, 3*PI/4,3*PI/4, 3*PI/4,3*PI/4);
	}
	 
	public int moveFalling(double bodyA, double lhf, double lhs, double rhf, double rhs,
								 		 double lff, double lfs, double rff, double rfs)
	{
		double val = ab.rad + bodyA;
		if ( to2pi(val) < (3*PI/4) || to2pi(val) > 17*PI/18 )			
				return -1;
		
		moveDefault(val,lhf, lhs, rhf, rhs, lff, lfs, rff, rfs);
		
		return 0;
	}

}
