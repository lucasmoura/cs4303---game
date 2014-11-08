package com.game;

import processing.core.PApplet;

import com.engine.GameObject;
import com.engine.Processing;

public class AdmiralShip extends GameObject
{
	
	private boolean moveRight;
	private boolean moveLeft;
	private int speedMovement = 8;
	private int width = 1814;

	public AdmiralShip(int x, int y, int objectWidth, int objectHeight,
			String imagePath, String imageId, int numFrames)
	{
		super(x, y, objectWidth, objectHeight, imagePath, imageId, numFrames);
		
		moveLeft = moveRight = false;
	}
	
	public void drawObject()
	{
		super.drawObject();
	}

	@Override
	public void update() 
	{
		//PApplet applet = Processing.getInstance().getParent();
		
		if(moveRight)
		{
			if(position.getX() + speedMovement <= width)
			{
				//System.out.println("X: "+position.getX() + ", width: "+applet.width);
				position.setX(position.getX() +speedMovement);
				moveRight = false;
			}
				
		}
		
		if(moveLeft)
		{
			if(position.getX() - speedMovement > 0)
			{
				position.setX(position.getX() -speedMovement);
				moveLeft = false;
			}
		}
		
	}
	
	public void moveRight()
	{
		moveRight = true;
	}
	
	public void moveLeft()
	{
		moveLeft = true;
	}
}
