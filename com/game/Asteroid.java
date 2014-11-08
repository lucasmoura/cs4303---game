package com.game;

import com.engine.GameObject;
import com.engine.Processing;

import processing.core.PApplet;

public class Asteroid extends GameObject implements Enemy
{
	
	private int speed;

	public Asteroid(int x, int y, int objectWidth, int objectHeight,
			String imagePath, String imageId, int numFrames) 
	{
		super(x, y, objectWidth, objectHeight, imagePath, imageId, numFrames);
		
		speed = 3;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shoot() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() 
	{
		this.position.setY(this.position.getY()+speed);
		PApplet applet = Processing.getInstance().getParent();
		
		if ( position.getY() > applet.displayHeight)
		{
			position.setX((int)applet.random(applet.displayWidth - objectWidth));
			position.setY(0);
		}
		
		int interval = 100;
		currentRow = (applet.millis()/interval)%numFrames;
		
	}
	
	public void drawObject()
	{
		super.drawObject();
	}

}
