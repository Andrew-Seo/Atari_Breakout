

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Pinger
{
	public int x;
	public int y;
	public int height;
	public int width;
	private Rectangle collisionbox;
	int direction = 2;

	public Pinger(int a, int b, int c, int d)
	{
		x = a;
		y = b;
		width = c;
		height = d;
		collisionbox = new Rectangle(a, b, c, d);
	}

	public void update()
	{
		collisionbox.setBounds(x, y, width, height);
	}

	public void ChangeDirection()
	{
		if (direction == 0)
		{
			direction = 1;
			return;
		} else if (direction == 1)
		{
			direction = 2;
			return;
		} else if (direction == 2)
		{
			direction = 4;
			return;
		} else if (direction == 3)
		{
			direction = 1;
			return;
		}
	}

	public Rectangle getCollisionBox()
	{
		return collisionbox;
	}

	public void move(int mouseX){
		x = mouseX - (width/2);
	}
	

	public void paint(Graphics g)
	{
		g.setColor(Color.GRAY);
		g.fillRect(x, y, width, height);

	}
}
