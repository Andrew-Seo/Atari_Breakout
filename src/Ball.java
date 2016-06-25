

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball
{

	public static int speed = 2;
	public int x;
	public int y;
	private int height;
	private int width;
	public int direction = 2;
	boolean moveRight;
	boolean moveUp;
	int angle = -50;
	float fx;
	float fy;
	boolean lose;
	Random r = new Random();
	private Rectangle collisionbox;

	public Ball(int a, int b, int c, int d)
	{
		x = a;
		y = b;
		width = c;
		height = d;
		collisionbox = new Rectangle(a, b, c, d);
		moveUp = true;
		moveRight = true;
		lose = false;
		fx = x;
		fy = y;
	}

	public void hitVertical()
	{
		angle -= 2 * angle;
	}

	public void hitHorizontal()
	{
		angle += 180 - (2 * angle);
	}

	void speedChange(int s)
	{
		speed = s;
	}

	public void update()
	{
		// moveBall();

		// if (moveRight)
		// {
		// x+=speed;
		// }
		// else {
		// x-=speed;
		// }
		// if (moveUp)
		// {
		// y-=speed;
		// }
		// else {
		// y+=speed;
		// }
		fx += (float) (speed * Math.cos(Math.toRadians(angle)));
		fy += (float) (speed * Math.sin(Math.toRadians(angle)));

		if (fx <= 0)
		{
			fx = 1;
			hitHorizontal();
		}
		if (fx >= Atari_Breakout.panelWidth - width)
		{
			fx = Atari_Breakout.panelWidth - 1 - width;
			hitHorizontal();
		}
		if (fy >= Atari_Breakout.panelHeight - height)
		{
			lose = true;
			fx = Animation_Panel.spawnX;
			fy = Animation_Panel.spawnY;
			speed = 2;
		}
		if (fy <= 0)
		{
			fy = 1;
			hitVertical();
		}

		x = (int) fx;
		y = (int) fy;
		angle %= 360;

		collisionbox.setBounds(x, y, width, height);

	}

	void hitBlox(Rectangle r)
	{
		if (x > r.x && x + width < r.x + r.width)
		{
			hitVertical();
		} else if (y > r.y && y + height < r.y + r.height)
		{
			hitHorizontal();
		} else
		{
			angle -= new Random().nextInt(90) + 90;
		}
	}

	void changeUpDirection()
	{
		moveUp = !moveUp;
	}

	public Rectangle getCollisionBox()
	{
		return collisionbox;
	}

	public boolean lost()
	{
		return lose;
	}

	public void setlost(boolean d)
	{
		lose = d;
	}

	public void changeDirection()
	{
		// if (moveUp)
		// {
		// moveUp=false;
		// }
		//
		// else {
		// moveUp=true;
		// }
		if (angle >= 0 && angle <= 180)
		{
			angle = -r.nextInt(180);
		} else if (angle >= -180 && angle < 0)
		{
			angle = r.nextInt(180);
		}

	}

	public void moveBall()
	{
		if (direction == 0)
		{
			x = x + 1;
			y = y + 1;
		}
		if (direction == 1)
		{
			x = x - 1;
			y = y + 1;
		}
		if (direction == 2)
		{
			x = x - 1;
			y = y - 1;
		}
		if (direction == 3)
		{
			x = x + 1;
			y = y - 1;
		}
	}

	public void paint(Graphics g)
	{
		g.setColor(Color.GRAY);
		g.fillOval(x, y, height, width);

	}
}
