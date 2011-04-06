
public class Person {
	
	final double bodyLen = 5.0 ;
	final double partLen = 2.5;
	final double PI = Math.PI;
	
	static String newline = System.getProperty("line.separator");
	
	/*
	 * leftHandF : left hand first part
	 * leftHandS : left hand second part
	 * rh* : right hand
	 * lf* : left foot
	 * rf* : right foot
	 */
	Line body, leftHandF, leftHandS, rightHandF, rightHandS, leftFootF, leftFootS, rightFootF, rightFootS;
	/*
	 * same, but for the angles of articulation; the angle it's measured by the vertical axe
	 */
	Angle ab, leftHandFa, leftHandSa, rightHandFa, rightHandSa, leftFootFa, leftFootSa, rightFootFa, rightFootSa;

	public Person()
	{
		body = new Line();
		leftHandF = new Line(); leftHandS = new Line(); rightHandF = new Line(); rightHandS = new Line();
		leftFootF = new Line(); leftFootS = new Line(); rightFootF = new Line(); rightFootS = new Line();
		
		ab = new Angle();
		leftHandFa = new Angle(); leftHandSa = new Angle(); rightHandFa = new Angle(); rightHandSa = new Angle();
		leftFootFa = new Angle(); leftFootSa = new Angle(); rightFootFa = new Angle(); rightFootSa = new Angle();
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
	
	private double to2pi( double value) 
	{
		return value > (2 * Math.PI)? (value - Math.floor(value / (2 * Math.PI))) : (value < 0? 2 * Math.PI + value: value);
	}
	
	public void makeStanding()
	{
		// standing up , every line is vertical every angle is 0
		body.set(new Point(2 * partLen , 2 * partLen +  bodyLen),new Angle(3*PI/2), bodyLen);
		
		leftHandF.set(new Point(2 * partLen, 2 * partLen +  bodyLen),new Angle(3*PI/2), partLen);
		leftHandS.set(new Point(2 * partLen, partLen +  bodyLen ),new Angle(3*PI/2), partLen);
		rightHandF.set(new Point(2 * partLen, 2 * partLen +  bodyLen),new Angle(3*PI/2), partLen);
		rightHandS.set(new Point(2 * partLen, partLen +  bodyLen ),new Angle(3*PI/2), partLen);
		
		leftFootF.set(new Point(2 * partLen, 2 * partLen),new Angle(3*PI/2), partLen);
		leftFootS.set(new Point(2 * partLen, partLen),new Angle(3*PI/2), partLen);
		rightFootF.set(new Point(2 * partLen, 2 * partLen),new Angle(3*PI/2), partLen);
		rightFootS.set(new Point(2 * partLen, partLen),new Angle(3*PI/2), partLen);
		
		ab.set(3 * PI / 2);
		leftHandFa.set(3*PI/2); leftHandSa.set(3*PI/2); rightHandFa.set(3*PI/2); rightHandSa.set(3*PI/2);
		leftFootFa.set(3*PI/2); leftFootSa.set(3*PI/2); rightFootFa.set(3*PI/2); rightFootSa.set(3*PI/2);
	}
	
	public int moveStanding(double bodyA, double lhf, double lhs, double rhf, double rhs,
										   double lff, double lfs, double rff, double rfs)
	{
		/*
		 * Conditions : 
		 * 		ab < PI / 4 OR ab > 7PI/4
		 * 		feet first angles in range 0 - PI/4 and 7PI/4 - 2PI
		 * 		the other value are not significant
		 *  Returns 0 when succeeds, -1 otherwise 
		 */
		double val = ab.rad + bodyA;
		
		if ( (to2pi(val - 3*PI/2) > (PI / 4) && to2pi(val - 3*PI/2) < (7 * PI / 4)) ||
			 (to2pi(leftFootFa.rad + lff - 3*PI/2) > PI/4 && to2pi(leftFootFa.rad + lff - 3*PI/2) < (7*PI/4)) ||
			 (to2pi(rightFootFa.rad + rff - 3*PI/2) > PI/4 && to2pi(rightFootFa.rad + rff - 3*PI/2) < (7*PI/4)))				
			return -1;
		
		ab.set(val);
		// consider body pStart being fix
		//when computing any new position, the new value ( pEnd ) will be on the circle of radius bodyPart/lenPart centered in pStart
		body.set(body.pStart, ab, bodyLen);
		
		// hands being from body pStart, which is fixed
		//have to move feet
		
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
		
		return 0;
	}
	
	public void makeSitting()
	{
		// sitting, all lines are vertical but feet first parts, which are horizontal
		body.set(new Point(partLen, partLen + bodyLen),new Angle(3*PI/2), bodyLen);
		
		leftHandF.set(new Point(partLen,  partLen + bodyLen),new Angle(3*PI/2), partLen);
		leftHandS.set(new Point(partLen, 2 * partLen),new Angle(3*PI/2), partLen);
		rightHandF.set(new Point(partLen, partLen +  bodyLen),new Angle(3*PI/2), partLen);
		rightHandS.set(new Point(partLen, 2 * partLen),new Angle(3*PI/2), partLen);
		
		leftFootF.set(new Point(partLen, partLen),new Angle(0), partLen);
		leftFootS.set(new Point(2 * partLen, partLen),new Angle(3*PI/2), partLen);
		rightFootF.set(new Point(partLen, partLen),new Angle(0), partLen);
		rightFootS.set(new Point(2 * partLen, partLen),new Angle(3*PI/2), partLen);
		
		ab.set(0);
		leftHandFa.set(3*PI/2); leftHandSa.set(3*PI/2); rightHandFa.set(3*PI/2); rightHandSa.set(3*PI/2);
		leftFootFa.set(0); leftFootSa.set(3*PI/2); rightFootFa.set(0); rightFootSa.set(3*PI/2);
	}
	
	public int moveSitting(double bodyA, double lhf, double lhs, double rhf, double rhs,
								 		 double lff, double lfs, double rff, double rfs)
	{
		/*
		* Conditions : 
		* 		ab < PI / 6 or ab > 11 PI / 6
		* 		feet first angles in range 0 - PI/2 and 3PI/2 - 2PI
		* 		the other value are not significant
		*  Returns 0 when succeeds, -1 otherwise 
		*/
		double val = ab.rad + bodyA;
		
		if ( (to2pi(val) > (PI / 6) && to2pi(val) < (11 * PI / 6)) ||
				 ((to2pi(leftFootFa.rad + lff) > PI/6) && to2pi(leftFootFa.rad + lff) < 11*PI/6) ||
				 ((to2pi(rightFootFa.rad + rff) > PI/6) && to2pi(rightFootFa.rad + rff) < 11*PI/6))				
				return -1;
			
		
		ab.set(val);
		// consider body pStart being fix
		//when computing any new position, the new value ( pEnd ) will be on the circle of radius bodyPart/lenPart centered in pStart
		body.set(body.pStart, new Angle(val), bodyLen);
		
		// hands being from body pStart, which is fixed
		//have to move feet
		
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
		
		return 0;
	}
	
	public void makeLying()
	{
		// sitting, all lines are vertical but feet first parts, which are horizontal
		body.set(new Point(0, 2 * partLen ),new Angle(0), bodyLen);
		
		leftHandF.set(new Point(0, 2* partLen),new Angle(3*PI/2), partLen);
		leftHandS.set(new Point(0, partLen),new Angle(3*PI/2), partLen);
		rightHandF.set(new Point(0, 2 * partLen ),new Angle(3*PI/2), partLen);
		rightHandS.set(new Point(0, partLen),new Angle(3*PI/2), partLen);
		
		leftFootF.set(new Point(bodyLen,2 * partLen),new Angle(0), partLen);
		leftFootS.set(new Point(bodyLen + partLen, 2 * partLen),new Angle(0), partLen);
		rightFootF.set(new Point(bodyLen, 2 * partLen),new Angle(0), partLen);
		rightFootS.set(new Point(bodyLen + partLen, 2 * partLen),new Angle(0), partLen);
		
		ab.set(0);
		leftHandFa.set(3*PI/2); leftHandSa.set(3*PI/2); rightHandFa.set(3*PI/2); rightHandSa.set(3*PI/2);
		leftFootFa.set(0); leftFootSa.set(0); rightFootFa.set(0); rightFootSa.set(0);
	}
	
	public int moveLying(double bodyA, double lhf, double lhs, double rhf, double rhs,
								 		 double lff, double lfs, double rff, double rfs)
	{
		/*
		* Conditions : 
		* 		ab < PI / 6 or ab > 11 PI / 6
		* 		feet first angles in range 0 - PI/2 and 3PI/2 - 2PI
		* 		the other value are not significant
		*  Returns 0 when succeeds, -1 otherwise 
		*/
		double val = ab.rad + bodyA;
		
		if ( (to2pi(val) < (4 * PI / 9) || to2pi(val) > (PI / 2)))			
				return -1;
			
		
		ab.set(val);
		// consider body pStart being fix
		//when computing any new position, the new value ( pEnd ) will be on the circle of radius bodyPart/lenPart centered in pStart
		body.set(body.pStart, new Angle(val), bodyLen);
		
		// hands being from body pStart, which is fixed
		//have to move feet
		
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
		
		return 0;
	}

}
