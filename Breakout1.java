/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout1 extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		/* This method runs an outstanding and exciting version of
		 * the arcade game Breakout! */
		
		setUpGame();
		playGame();
		
	}
	
	private void setUpGame() {
	
		createBricks();
		createPaddle();
		createBall();
		
	}
	
	private void playGame() {
	
		
		
		moveBall();
		checkForCollisions();
		pause (DELAY);
		//checkForLastBrick ();
		//game over or congratulations message
		
	}
	
	private void createBricks() {
		
		/* top left corner of upper leftmost brick */
		int startX = (WIDTH - ((BRICK_WIDTH * NBRICKS_PER_ROW) + (BRICK_SEP * (NBRICKS_PER_ROW - 1))))/2;
		int startY = BRICK_Y_OFFSET;
		
		for (int i = 0; i < NBRICK_ROWS; i ++) {
			
			/* The loop below lays down 10 bricks in a row, the outer loop lays down the columns.
			 * The two loops could have been combined because the number rows and bricks per row 
			 * are both 10, but they are separated so that the set up works even if one or both of
			 * those parameters gets changed.
			 */
			for (int j = 0; j < NBRICKS_PER_ROW; j++) {
			
				int x = startX + ((BRICK_WIDTH + BRICK_SEP) * j);
				int y = startY;
				
				brick = new GRect (x, y, BRICK_WIDTH, BRICK_HEIGHT);
				
				/* These cascading if statements fill in the different colors
				 * two rows at a time.
				 */
				if ((i == 0 || i == 1) && (j >= 0)) {
					brick.setFilled(true);
					brick.setColor(Color.RED);
				} else if ((i == 2 || i == 3) && (j >=0)) {
					brick.setFilled(true);
					brick.setColor(Color.ORANGE);
				} else if ((i == 4 || i == 5) && (j >=0)) {
					brick.setFilled(true);
					brick.setColor(Color.YELLOW);
				} else if ((i == 6 || i == 7) && (j >=0)) {
					brick.setFilled(true);
					brick.setColor(Color.GREEN);
				} else if ((i == 8 || i == 9) && (j >=0)) {
					brick.setFilled(true);
					brick.setColor(Color.CYAN);
				}
				
				
				add (brick);
			
			}
			/* Now that one row is completed, we need to change the y coordinate
			 * so we can start the next row.
			 */
			startY += ((BRICK_HEIGHT + BRICK_SEP));
		
	
		}
	}
	
	private void createPaddle () {
		
		int x = (WIDTH - PADDLE_WIDTH)/2;
		
		paddle = new GRect (x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled (true);
		add (paddle);
		addMouseListeners();
	}
	
	/* Records where the mouse was pressed.
	 * I used code from p. 204 of Art & Science of Java, preliminary PDF draft*/
	public void mousePressed(MouseEvent e) {
		lastX = e.getX ();
		lastY = y;
		gobj = getElementAt (lastX, lastY);
		
	}
	/* Drags the paddle to a new place on the x-axis.
	 * I used code from p. 204 of Art & Science of Java, preliminary PDF draft*/ 
	public void mouseDragged (MouseEvent e) {
		if (gobj != null) {
			gobj.move (e.getX () - lastX, lastY- y);
			lastX = e.getX ();
			lastY = y;
			
			/*Keeps paddle from running off the left side of the screen*/
			if (lastX < 0) {
				gobj.setLocation (0,y);
			}
			/*Keeps paddle from running off the right side of the screen*/
			if (lastX + PADDLE_WIDTH >= WIDTH) {
				gobj.setLocation (WIDTH-PADDLE_WIDTH, y);
			}	
		}
		
	}
	
	private void createBall() {
		/* creates a black ball and puts it at the center of the screen*/
		ball = new GOval (X_START, Y_START, BALL_RADIUS*2, BALL_RADIUS*2 );
		ball.setFilled(true);
		add (ball);
		
	}
	
	private void moveBall() {
		vx = rgen.nextDouble (1.0, 3.0);
		if (rgen.nextBoolean (0.5)) vx = -vx;
		
		while (NTURNS > 0) {
		ball.move (vx,vy);
		pause (DELAY);
		}
		
	}
	
	private void checkForCollisions () {
		
		checkForWalls();
		checkForObjects();
		
	}
	
	private void checkForWalls() {
	
		/* checking for walls */
			if (ball.getX () + (BALL_RADIUS*2) > WIDTH) { 
				vx = -vx;
			//double diff = ball.getX() + 2 * BALL_RADIUS - WIDTH;
	           // ball.move(-2 * diff, 0);
			}
			else if (ball.getX () < 0) {
				vx = -vx;
			//double diff = ball.getX();
				//ball.move(2* diff, 0);
				}
			else if (ball.getY () + (BALL_RADIUS*2) > HEIGHT) {
				vy = -vy;
			//double diff = ball.getY() + (BALL_RADIUS*2);
				//ball.move(0, -2*diff);
			}
			else if (ball.getY() < 0)  {
				vy = -vy;
			//double diff = ball.getY();
				//ball.move(0, 2*diff);
				
			}
		
		
	}
	
	    private void checkForObjects () {
		
		GObject collider = getElementAt (ball.getX(), ball.getY());
			if (collider == paddle) {
				
				vy = -vy;
			}
			collider = getElementAt ((ball.getX() + BALL_RADIUS *2), ball.getY());
			if (collider == paddle) {
				vy=-vy;
			}
			collider = getElementAt (ball.getX(), (ball.getY() + BALL_RADIUS*2));
			if (collider == paddle) {
				vy = -vy;
			}
			collider = getElementAt ((ball.getX() + BALL_RADIUS *2), (ball.getY() + BALL_RADIUS *2));
			if (collider == paddle) {
				vy = -vy;
			}
	}
	
	
		
		
				
		
	
	
	
		
	
		
		
			
			
		
	
	
	//private void 
	
	/* Instance variables*/
	private GObject gobj; //the object being dragged
	private double lastX; //the last mouse X position
	private double lastY;
	private double y = (HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
	private GOval ball;
	private static final double X_START = (WIDTH - (BALL_RADIUS*2))/2;
	private static final double Y_START = (HEIGHT/2 - BALL_RADIUS);
	private RandomGenerator rgen = RandomGenerator.getInstance ();
	private double vx;
	private double vy = 1.0;
	private static final int DELAY = 200;
	private GRect paddle; 
	private GRect brick;
	
}



