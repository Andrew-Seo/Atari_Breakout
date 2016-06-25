

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Animation_Panel extends JPanel implements ActionListener
{
	Pinger pinger;
	Blocks blocks;
	Ball ball;
	Atari_Breakout frum;
	Font Big;
	Font Win;
	Font Small;
	public static final int spawnX = new Random().nextInt(1875) + 25;
	public static final int spawnY = 750;
	public static int brokenBlox = 0;
	public static int totalBlox;
	public final int menuState = 0;
	public final int gameState = 1;
	public final int menu2State = 2;
	public final int drawMenu2State = 3;
	public final int gameWinState = 4;
	public final int gameLoseState = 5;
	public final int scoreState = 6;
	public final int blockwidth = 100;
	public final int blockheight = 100;
	// public final int blockwidth = Atari_Breakout.panelWidth;
	// public final int blockheight = 100;
	public final int row = 4;
	public final int column = 19;
	// public final int row = 1;
	// public final int column = 1;
	public int holdposition = 0;
	int currentState = menuState;
	int lives = 5;
	boolean sound = true;
	String[] names = new String[5];
	int[] time = new int[5];

	Timer timer;

	ArrayList<Blocks> blocklist;

	Animation_Panel(Atari_Breakout x)
	{

		frum = x;
		pinger = new Pinger(800, 900, 400, 15);
		ball = new Ball(spawnX, spawnY, 20, 20);
		// blocks = new Blocks(925, 450, 125, 50, false, Color.BLUE);
		Big = new Font("Times New Roman", Font.BOLD, 100);
		Win = new Font("Times New Roman", Font.BOLD, 300);
		Small = new Font("Times New Roman", Font.BOLD, 50);

		blocklist = new ArrayList<Blocks>();
		generateBlocks();
		totalBlox = row * column;
		
		String data = "";
			try
			{
				String filename ="BestTime,txt";
				FileReader fr = new FileReader(filename);
				BufferedReader br = new BufferedReader(fr);
				String line; int cur=0; 
				while ((line=br.readLine())!=null)
				{
					data = "\n" + line;
					String[]sdata=line.split("");
					names[cur]=sdata[0];
					time[cur]=Integer.parseInt(sdata[1]);
					++cur;
				}
					
				fr.close();
				br.close();
				
			} catch (Exception e)
			{
				
				// TODO: handle exception
			}
			System.out.println(data);

		timer = new Timer(1000 / 240, this);
		timer.start();

	}

	private void generateBlocks()
	{
		for (int i = 0; i < row; i++)
		{
			for (int j = 0; j < column; j++)
			{
				Blocks b = new Blocks(j * blockwidth, i * blockheight, blockwidth, blockheight, true);
				blocklist.add(b);
			}
		}
	}

	public void paint(Graphics g)
	{
		if (currentState == gameState)
		{
			drawGameState(g);

		} else if (currentState == menuState)
		{
			drawMenuState(g);
		} else if (currentState == menu2State)
		{
			drawMenu2State(g);
		} else if (currentState == gameWinState)
		{
			drawWinState(g);
		} else if (currentState == gameLoseState)
		{
			drawLoseState(g);
		}
		 else if (currentState == scoreState)
			{
				drawScoreState(g);
			}
	}

	public void drawGameState(Graphics g)
	{
		g.fillRect(0, 0, 1900, 10000);

		for (Blocks c : blocklist)
		{
			c.paint(g);

		}

		pinger.paint(g);
		// blocks.paint(g);
		ball.paint(g);
		g.setColor(Color.WHITE);
		g.setFont(Big);
		g.drawString("Lives:" + lives, 50, 100);
		g.setFont(Small);
		g.drawString("Blocks Broken:" + brokenBlox + "/76", 1400, 50);
		
	}

	public void drawMenuState(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Atari_Breakout.panelWidth, Atari_Breakout.panelHeight);
		g.setColor(Color.WHITE);
		g.setFont(Big);
		g.drawString("To Start Playing,", 100, 150);
		g.drawString("Hit the Right Arrow Key.", 100, 250);
		g.drawString("To Pause,", 100, 350);
		g.drawString("Hit the Left Arrow Key.", 100, 450);
		g.drawString("To Move the Pinger,", 100, 550);
		g.drawString("Move the Mouse.", 100, 650);
		g.drawString("To restart the game,", 100, 750);
		g.drawString("Press the Up Arrow Key.", 100, 850);
	}

	public void drawMenu2State(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Atari_Breakout.panelWidth, Atari_Breakout.panelHeight);
		g.setColor(Color.WHITE);
		g.setFont(Win);
		g.drawString("Paused", 800, 500);
		g.setFont(Small);
		g.drawString("Directions:", 100, 100);
		g.drawString("To Start Playing,", 100, 150);
		g.drawString("Hit the Right Arrow Key.", 100, 200);
		g.drawString("To Pause,", 100, 350);
		g.drawString("Hit the Left Arrow Key.", 100, 400);
		g.drawString("To Move the Pinger,", 100, 550);
		g.drawString("Move the Mouse.", 100, 600);
		g.drawString("To restart the game,", 100, 750);
		g.drawString("Press the Up Arrow Key.", 100, 800);
	}

	public void drawWinState(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Atari_Breakout.panelWidth, Atari_Breakout.panelHeight);
		g.setColor(Color.WHITE);
		g.setFont(Win);
		g.drawString("You Win!!!", 100, 500);
		g.setFont(Big);
		g.drawString("Thanks For Playing!", 150, 600);
	}

	public void drawLoseState(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Atari_Breakout.panelWidth, Atari_Breakout.panelHeight);
		g.setColor(Color.WHITE);
		g.setFont(Win);
		g.drawString("You Lose. ", 100, 500);
		g.setColor(Color.WHITE);
		g.setFont(Big);
		g.drawString("Thanks For Playing Though.", 150, 600);
		g.drawString("You Broke: " + brokenBlox + "/76 Blocks", 150, 700);
		
	}
	public void drawScoreState(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Atari_Breakout.panelWidth, Atari_Breakout.panelHeight);
		g.setColor(Color.WHITE);
		g.setFont(Win);
		g.drawString("You Lose. ", 100, 500);
		g.setColor(Color.WHITE);
		g.setFont(Big);
		g.drawString("Thanks For Playing Though.", 150, 600);
		g.drawString("You Broke: " + brokenBlox + "/76 Blocks", 150, 700);
	}

	public void gameReset()
	{
		blocklist.clear();
		blocklist = new ArrayList<Blocks>();
		generateBlocks();
		ball = new Ball(spawnX, spawnY, 20, 20);
		currentState = gameState;
		brokenBlox = 0;
		lives = lives + 5;
		if (lives >= 5)
		{
			lives = 5;
		}
		ball.speed = 2;
	}

	public void changeState(int a)
	{
		if (a < 0 || a > 5)
		{
			JOptionPane.showMessageDialog(null, "Please put in a valid (positive) number");
			currentState = menuState;

		} else
			currentState = a;
	}

	private void checkcollisionbox()
	{
		Rectangle r1 = pinger.getCollisionBox();
		Rectangle r2 = ball.getCollisionBox();

		if (r1.intersects(r2))
		{
			ball.hitBlox(r1);
			new Thread(new soundPlayer("18528.wav")).start();
			// frum.playSound("18529.wav");
		}
//		if (r2.getY()>Atari_Breakout.panelHeight)
//		{
//			ball.x = spawnX;
//			ball.y = spawnY;
//			lives = lives - 1;
//			ball.setlost(false);
//			ball.changeDirection();
//		}

		for (Blocks b : blocklist)
		{
			Rectangle rb = b.getCollisionBox();
			if (rb.intersects(r2) && b.isBroken() == false)
			{
				brokenBlox++;
				b.breakblox();
				ball.hitBlox(rb);
				if (brokenBlox == totalBlox)
				{
					currentState = gameWinState;
					new Thread(new soundPlayer("18533.wav")).start();
					;
				}
				if (brokenBlox % 20 == 0)
				{
					ball.speed += 1;
				}
				// frum.playSound("18528.wav");
				new Thread(new soundPlayer("18529.wav")).start();
				;
			}
		}
		// frum.playSound("18529.wav"); pinger intersects blox

	}

	public void update()
	{
		if (currentState == gameState)
		{

			pinger.move(holdposition);
			ball.update();
			pinger.update();
			checkcollisionbox();
			if (ball.lost() == true)
			{
				ball.x = spawnX;
				ball.y = spawnY;
				lives = lives - 1;
				ball.setlost(false);
				ball.changeDirection();
			}

			if (lives == 0)
			{
				currentState = gameLoseState;
			}
		}
	}
	
	public void baller(){
		ball.hitHorizontal();
		ball.hitVertical();
	}
	public void baller1(){
		ball.hitHorizontal();
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		update();
		repaint();
	}

}
