import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {
	
	public static void main(String args[])
	{
		Person p = new Person();
		Draw output = new Draw();
		int i = 0;
		double bodyA =  Math.PI/180;
		
		p.makeStanding();
		i = 0;
		while (p.moveStanding(bodyA, 3*bodyA, 2*bodyA,0 , 0, 0, 0, 0, 0) == 0)
		{
			output.draw("../../training_set/standing/"+i+"_3.bmp", p);
			i++;
		}
		
		p.makeSitting();
		i = 0;
		output.draw("../../training_set/sitting/"+i+"_2.bmp", p);
		while (p.moveSitting(bodyA, 0, 0, 0, 0, 0, 0, 0, 0) == 0)
		{
			output.draw("../../training_set/sitting/"+i+"_2.bmp", p);
			i++;
		}		
		
		p.makeLying();
		i = 0;
		output.draw("../../training_set/lying/"+i+"_1.bmp", p);
		while (p.moveLying(bodyA, 0, 0, 0, 0, 0, 0, 0, 0) == 0)
		{
			output.draw("../../training_set/lying/"+i+"_1.bmp", p);
			i++;
		}
		
		p.makeFalling();
		i = 0;
		output.draw("../../training_set/falling/"+i+"_0.bmp", p);
		
		while (p.moveFalling(bodyA, 0, 0, 0, 0, 0, 0, 0, 0) == 0)
		{
			output.draw("../../training_set/falling/"+i+"_0.bmp", p);
			i++;
		}
	}
}

class Draw
{
	BufferedImage img;
	Graphics2D graphics;
	final int windowSize = 200;
	
	public Draw()
	{
		this.img = new BufferedImage(windowSize, windowSize, BufferedImage.TYPE_INT_RGB);
		this.graphics = img.createGraphics();
		
	}
	
	public void draw(String fileName, Person p)
	{
		File outputFile = new File(fileName);
		
		graphics.clearRect(0, 0, windowSize, windowSize);
		
		graphics.setColor(Color.PINK);
		graphics.drawLine(windowSize - (int)(p.body.pStart.x ), windowSize - (int)(p.body.pStart.y ), windowSize - (int)(p.body.pEnd.x ), windowSize - (int)(p.body.pEnd.y) );
		
		graphics.setColor(Color.RED);
		graphics.drawLine(windowSize - (int)(p.leftHandF.pStart.x ), windowSize - (int)(p.leftHandF.pStart.y ), windowSize - (int)(p.leftHandF.pEnd.x ), windowSize - (int)(p.leftHandF.pEnd.y) );
		graphics.drawLine(windowSize - (int)(p.leftHandS.pStart.x ), windowSize - (int)(p.leftHandS.pStart.y ), windowSize - (int)(p.leftHandS.pEnd.x ), windowSize - (int)(p.leftHandS.pEnd.y) );
		graphics.drawLine(windowSize - (int)(p.rightHandF.pStart.x ), windowSize - (int)(p.rightHandF.pStart.y ), windowSize - (int)(p.rightHandF.pEnd.x ), windowSize - (int)(p.rightHandF.pEnd.y) );
		graphics.drawLine(windowSize - (int)(p.rightHandS.pStart.x ), windowSize - (int)(p.rightHandS.pStart.y ), windowSize - (int)(p.rightHandS.pEnd.x ), windowSize - (int)(p.rightHandS.pEnd.y) );
		
		graphics.setColor(Color.GREEN);
		graphics.drawLine(windowSize - (int)(p.leftFootF.pStart.x ), windowSize - (int)(p.leftFootF.pStart.y ), windowSize - (int)(p.leftFootF.pEnd.x ), windowSize - (int)(p.leftFootF.pEnd.y) );
		graphics.drawLine(windowSize - (int)(p.leftFootS.pStart.x ), windowSize - (int)(p.leftFootS.pStart.y ), windowSize - (int)(p.leftFootS.pEnd.x ), windowSize - (int)(p.leftFootS.pEnd.y) );
		graphics.drawLine(windowSize - (int)(p.rightFootF.pStart.x ), windowSize - (int)(p.rightFootF.pStart.y ), windowSize - (int)(p.rightFootF.pEnd.x ), windowSize - (int)(p.rightFootF.pEnd.y) );
		graphics.drawLine(windowSize - (int)(p.rightFootS.pStart.x ), windowSize - (int)(p.rightFootS.pStart.y ), windowSize - (int)(p.rightFootS.pEnd.x ), windowSize - (int)(p.rightFootS.pEnd.y) );		
		
		try 
		{
			ImageIO.write(img, "bmp", outputFile);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
}
