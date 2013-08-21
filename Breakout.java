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

public class Breakout extends GraphicsProgram {

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
		
	}
	
	private void playGame() {
		
		createBouncingBall();
		//checkForCollisions();
		//pause (DELAY);
		//checkForLastBrick ();
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
				
				GRect brick = new GRect (x, y, BRICK_WIDTH, BRICK_HEIGHT);
				
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
		
		GRect paddle = new GRect (x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled (true);
		add (paddle);
		addMouseListeners();
	}
	
	/* I used code from p. 204 of Art & Science of Java, preliminary PDF draft*/
	public void mousePressed(MouseEvent e) {
		lastX = e.getX ();
		lastY = y;
		gobj = getElementAt (lastX, lastY);
		
	}
	/* I used code from p. 204 of Art & Science of Java, preliminary PDF draft*/ 
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
	
	private void createBouncingBall() {
		ball = new GOval (HEIGHT/2 - BALL_RADIUS,  WIDTH/2 -BALL_RADIUS, BALL_RADIUS*2,BALL_RADIUS*2 );
		ball.setFilled(true);
		add (ball);
		
	}
	
	/* Instance variables*/
	private GObject gobj; //the object being dragged
	private double lastX; //the last mouse X position
	private double lastY;
	private int y = (HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
	private GOval ball;
	
}



