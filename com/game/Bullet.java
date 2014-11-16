package com.game;

import com.engine.Processing;

public class Bullet extends DestructableObject
{
	
	private boolean active;
	private boolean isInScreen;
	private int speed;
	private int type;
	private int height;

	public Bullet(int x, int y, int objectWidth, int objectHeight,
			String imagePath, String imageId, int numFrames, int type) 
	{
		super(x, y, objectWidth, objectHeight, imagePath, imageId, numFrames);
		
		active = isInScreen = false;
		this.type = type;
		setCollidable("asteroid");
		speed = 0;
		height = Processing.getInstance().getParent().displayHeight;
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
		
		if(type == 0)
		{
			if(this.getY() - speed >= 0)
				this.setY(this.getY() - speed);
			else
				isInScreen = false;
		}
		else
		{
			
			if((this.position.getY() + speed) <= height)
				this.position.setY(this.position.getY() + speed);
			else
			{
//				System.out.println("Height: "+height+ ", Speed:  "+speed+" "+
//							"Position: "+this.position.getY()+" "+(this.position.getY() + speed));
				isInScreen = false;
			}	
		}
		
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
		isColliding = false;
		active = false;
		setX(0);
		setY(0);
		speed = 0;
	}
	
}
