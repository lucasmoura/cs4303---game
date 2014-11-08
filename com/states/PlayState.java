package com.states;

import android.util.Log;

import com.engine.Button;
import com.engine.Processing;
import com.game.AdmiralShip;
import com.game.Asteroid;
import com.game.Starfield;

import processing.core.PApplet;

public class PlayState implements GameState
{
	
	private Starfield starfield;
	private Asteroid asteroid;
	private final String playID = "PLAY";
	private Button leftButton; 
	private Button rightButton;
	private AdmiralShip admiralShip;
	
	private String LOG_TAG = "PlayState";

	@Override
	public void update() 
	{
		
		
		starfield.update();
		asteroid.update();
		
		if(rightButton.isPressed())
		{
			admiralShip.moveRight();
			admiralShip.update();
		}
		
		else if(leftButton.isPressed())
		{
			admiralShip.moveLeft();
			admiralShip.update();
		}
		
		
		
	}

	@Override
	public void render() 
	{
		
		starfield.drawObject();
		admiralShip.drawObject();
		asteroid.drawObject();
		leftButton.drawObject();
		rightButton.drawObject();
		
	}

	@Override
	public boolean onEnter() 
	{
		PApplet applet = Processing.getInstance().getParent();
		System.out.println("On enter");
		
		starfield = new Starfield(20);
		System.out.println("Starfield ready");
		asteroid = new Asteroid(10, 0, 0, 0, "asteroidsMedium.png",
				"mediumAsteroid", 16);
		System.out.println("Asteroid ready");
		
		rightButton = new Button(0, 0, "rightMove.png", "rightMove", 1, false);
		int rightx = applet.width - rightButton.getWidth();
		int righty = applet.height - rightButton.getHeight();
		rightButton.setX(rightx);
		rightButton.setY(righty);
		
		leftButton = new Button(0, 0, "leftMove.png", "leftMove", 1, false);
		int leftx = 0;
		int lefty = applet.height - leftButton.getHeight();
		leftButton.setX(leftx);
		leftButton.setY(lefty);
		
		admiralShip = new AdmiralShip(0, 0, 0, 0, "admiralship.png",
				"admiralship", 1);
		int shipx = applet.width/2 - admiralShip.getWidth()/2;
		int shipy = applet.height - admiralShip.getHeight();
		admiralShip.setX(shipx);
		admiralShip.setY(shipy);
		
		return true;
	}

	@Override
	public boolean onExit() 
	{
		leftButton.clean();
		rightButton.clean();
		starfield = null;
		return true;
	}

	@Override
	public void enable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseReleased(int x, int y) 
	{
		
		leftButton.setPressed(false);
		rightButton.setPressed(false);
		
	}
	
	public void mousePressed(int x, int y)
	{
		 if(leftButton.touchOnMe(x, y))
		 {
			 System.out.println("Left pressed");
			 
				if (leftButton.isPressed()==false) 
				{
				  leftButton.setPressed(true);
				  rightButton.setPressed(false);
				}
			
		 }
		 else
		 {
			 leftButton.setPressed(false);
		 }
		
		 if(rightButton.touchOnMe(x, y))
		 {
			 System.out.println("Right pressed");
			 
				if (rightButton.isPressed()==false) 
				{
				  rightButton.setPressed(true);
				  leftButton.setPressed(false);
				}
			
		 }
		 else
		 {
			 rightButton.setPressed(false);
		 }
	
	}

	@Override
	public String getStateId() 
	{
		return playID;
	}

}
