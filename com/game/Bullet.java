package com.game;

import com.engine.GameObject;

public class Bullet extends GameObject
{
	
	private boolean active;
	private boolean isInScreen;
	private int speed;

	public Bullet(int x, int y, int objectWidth, int objectHeight,
			String imagePath, String imageId, int numFrames) 
	{
		super(x, y, objectWidth, objectHeight, imagePath, imageId, numFrames);
		
		active = isInScreen = false;
		setCollidable("asteroid");
		speed = 0;
	}

	
	public void drawObject()
	{
		super.drawObject();
	}
	
	@Override
	public void update() 
	{
		if(isColliding())
		{
			isInScreen = false;
			return;
		}
		
		if(this.getY() - speed >= 0)
		{
			this.setY(this.getY() - speed);
		}
		else
			isInScreen = false;
		
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
		this.isInScreen = active;
	}

	public int getSpeed()
	{
		return speed;
	}

	public void setSpeed(int speed) 
	{
		this.speed = speed;
	}
	
	
	public boolean getIsInScreen() 
	{
		return isInScreen;
	}

	public void setNotInScreen(boolean notInScreen) 
	{
		this.isInScreen = notInScreen;
	}


	public void clear()
	{
		setColliding(false);
		active = false;
		setX(0);
		setY(0);
		speed = 0;
	}
	
}
