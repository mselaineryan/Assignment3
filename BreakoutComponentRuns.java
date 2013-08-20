/*
 * File: BreakoutComponentRuns.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file is being used to test components (methods) of the Breakout game.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class BreakoutComponentRuns extends GraphicsProgram {

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
		
		/* This method runs individual components of the breakout game for testing
		 * purposes */
		int x = (WIDTH - PADDLE_WIDTH)/2;
		
		
		GRect paddle = new GRect (x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled (true);
		paddle.setColor(Color.BLACK);
		add (paddle);
		addMouseListeners();
	}
	
	/* I used code from p. 204 of Art & Science of Java, prelim pdf draft*/
	public void mousePressed(MouseEvent e) {
		lastX = e.getX ();
		lastY = y;
		gobj = getElementAt (lastX, lastY);
		
	}
	/* I used code from p. 204 of Art & Science of Java, prelim pdf draft*/ 
	public void mouseDragged (MouseEvent e) {
		if (gobj != null) {
			gobj.move (e.getX () - lastX, lastY- y);
			lastX = e.getX ();
			lastY = y;
		}
		
		/*Keeps paddle from going off the edges*/
		if (e.getX () > WIDTH) {
			lastX = WIDTH;
		}
	}
	
	/* Instance variables*/
	private GObject gobj; //the object being dragged
	private double lastX; //the last mouse X position
	private double lastY;
	private int y = (HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
	
		
}
	

		
	
	


