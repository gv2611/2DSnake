import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener{
	private int[] snakeXLength = new int[750];
	private int[] snakeYLength = new int[750];
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;

	private ImageIcon rightMouth;
	private ImageIcon upMouth;
	private ImageIcon downMouth;
	private ImageIcon leftMouth;
	
	private int lengthOfSnake =3;
	
	private Timer timer;
	private int delay =100;
	
	private ImageIcon snakeImage;
	private ImageIcon titleImage;
	private int moves=0;
	private int speed=25;

	
	private int [] enemyXPosition= {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
	private int[] enemyYPosition = {75,100,125,150,175,200, 225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650};
	
	private ImageIcon enemyImage;
	
	private Random random =new Random();
	
	private int xPos=random.nextInt(34);
	private int yPos = random.nextInt(23);
	
	private int score = 0;
	
	
	public Gameplay()
	{
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		
	}
	
	public void paint (Graphics g)
	{
		if(moves==0)
		{
			snakeXLength[2]=50;
			snakeXLength[1]=75;
			snakeXLength[0]=100;
			
			snakeYLength[2]=100;
			snakeYLength[1]=100;
			snakeYLength[0]=100;
		}
		
		//draw title image border
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);
		
		//draw title image
		titleImage = new ImageIcon ("snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		
		//draw border for gameplay
		g.setColor(Color.white);
		g.drawRect(24,74,851,577);
		
		//draw background for the gameplay
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
		
		//draw score
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.PLAIN, 14));
		g.drawString("Score:"+score, 780, 50);
		
		//draw length
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.PLAIN, 14));
		g.drawString("Length:"+lengthOfSnake, 780, 30);
		
		rightMouth = new ImageIcon("rightmouth.png");
		rightMouth.paintIcon(this, g, snakeXLength[0], snakeYLength[0]);
		
		for(int a=0;a<lengthOfSnake;a++)
		{
			if(a==0 && right)
			{
				rightMouth = new ImageIcon("rightmouth.png");
				rightMouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
			}
			if(a==0 && left)
			{
				leftMouth = new ImageIcon("leftmouth.png");
				leftMouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
			}
			if(a==0 && down)
			{
				downMouth = new ImageIcon("downmouth.png");
				downMouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
			}
			if(a==0 && up)
			{
				upMouth = new ImageIcon("upmouth.png");
				upMouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
			}
			
			if(a!=0)
			{
				snakeImage = new ImageIcon("snakeimage.png");
				snakeImage.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
			}
		}
		
		enemyImage = new ImageIcon("enemy.png");
		
		if(enemyXPosition[xPos] == snakeXLength[0] && enemyYPosition[yPos] == snakeYLength[0])
		{
			score++;
			lengthOfSnake++;
			xPos= random.nextInt(34);
			yPos = random.nextInt(23);
		}
		enemyImage.paintIcon(this, g, enemyXPosition[xPos], enemyYPosition[yPos]);
		
		for(int b=1;b<lengthOfSnake;b++)
		{
			if(snakeXLength[b]==snakeXLength[0] && snakeYLength[b] == snakeYLength[0])
			{
				right=false;
				left=false;
				up=false;
				down=false;
				
				g.setColor(Color.white);
				g.setFont(new Font("arial", Font.BOLD, 17));
				g.drawString("GAME OVER, YOU ARE A NOOB!", 300, 300);
				
				g.setFont(new Font("arial", Font.BOLD, 17));
				g.drawString("Press space button to restart!", 300, 350);
			}
		}
		g.dispose();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(right)
		{
			for(int r=lengthOfSnake-1;r>=0;r--)
			{
				snakeYLength[r+1] = snakeYLength[r];
			}
			for(int r = lengthOfSnake; r>=0;r--)
			{
				if(r==0)
				{
					snakeXLength[r]= snakeXLength[r]+25;
				}
				else
				{
					snakeXLength[r]=snakeXLength[r-1];
				}
				if(snakeXLength[r]>850)
				{
					snakeXLength[r]=25;
				}
			}
			repaint();
		}
		if(left)
		{

			for(int r=lengthOfSnake-1;r>=0;r--)
			{
				snakeYLength[r+1] = snakeYLength[r];
			}
			for(int r = lengthOfSnake; r>=0;r--)
			{
				if(r==0)
				{
					snakeXLength[r]= snakeXLength[r]-25;
				}
				else
				{
					snakeXLength[r]=snakeXLength[r-1];
				}
				if(snakeXLength[r]<25)
				{
					snakeXLength[r]=850;
				}
			}
			repaint();
			
		}
		if(up)
		{
			for(int r=lengthOfSnake-1;r>=0;r--)
			{
				snakeXLength[r+1] = snakeXLength[r];
			}
			for(int r = lengthOfSnake; r>=0;r--)
			{
				if(r==0)
				{
					snakeYLength[r]= snakeYLength[r]-25;
				}
				else
				{
					snakeYLength[r]=snakeYLength[r-1];
				}
				if(snakeYLength[r]< 75)
				{
					snakeYLength[r]=625;
				}
			}
			repaint();
			
		}
		if(down)
		{

			for(int r=lengthOfSnake-1;r>=0;r--)
			{
				snakeXLength[r+1] = snakeXLength[r];
			}
			for(int r = lengthOfSnake; r>=0;r--)
			{
				if(r==0)
				{
					snakeYLength[r]= snakeYLength[r]+25;
				}
				else
				{
					snakeYLength[r]=snakeYLength[r-1];
				}
				if(snakeYLength[r]>625)
				{
					snakeYLength[r]=75;
				}
			}
			repaint();
			
			
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_SPACE)
		{
			moves=0;
			score=0;
			lengthOfSnake=3;
			repaint();
			
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			moves++;
			right=true;
			if(!left)
			{
				right = true;
			}
			else
			{
				right=false;
				left=true;
			}
			up=false;
			down=false;
			
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			moves++;
			left=true;
			if(!right)
			{
				left = true;
			}
			else
			{
				left=false;
				right=true;
			}
			up=false;
			down=false;
			
		}
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			moves++;
			up=true;
			if(!down)
			{
				up = true;
			}
			else
			{
				up=false;
				down=true;
			}
			left=false;
			right=false;
			
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			moves++;
			down=true;
			if(!up)
			{
				down = true;
			}
			else
			{
				down=false;
				up=true;
			}
			left=false;
			right=false;
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
