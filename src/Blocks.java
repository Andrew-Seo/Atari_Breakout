

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.util.Random;

public class Blocks
{
	public int x;
	public int y;
	public int height;
	public int width;
	public boolean breakable;
	public boolean broken;
	public Color randommy;
	public static int Blockamount;
	private Rectangle collisionbox;
	
	

	public Blocks(int a, int b, int c, int d, boolean sup)
	{
		x = a;
		y = b;
		width = c;
		height = d;
		Blockamount++;
		breakable = sup;
		randommy = new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256));
		broken = false; 
		collisionbox = new Rectangle(a, b, c, d);
	}
	
	public void update()
	{
		collisionbox.setBounds(x, y, width, height);
	}


	public void paint(Graphics g)
	{
		Graphics2D g2= (Graphics2D) g;
		if (broken == false)
		{	
			g.setColor(Color.BLACK);
			Stroke f = g2.getStroke();
			g2.setStroke(new BasicStroke(10));
			g.drawRect(x, y, width, height);
			g2.setStroke(f);
			g.setColor(randommy);
			g.fillRect(x,y,width, height);
		}
		

	}
	public Rectangle getCollisionBox(){
		return collisionbox;
	}
	public boolean isBroken(){
		return broken;
	}
	
	public void breakblox(){
		broken=true;
	}
}
