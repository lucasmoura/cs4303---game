package com.game;


import com.engine.GameObject;

public class AdmiralShip extends GameObject
{
	
	private boolean moveRight;
	private boolean moveLeft;
	private int speedMovement = 12;
	private int width = 1814;
	private BulletPool bulletPool;
	private int counter;
	private int fireRate;

	public AdmiralShip(int x, int y, int objectWidth, int objectHeight,
			String imagePath, String imageId, int numFrames)
	{
		super(x, y, objectWidth, objectHeight, imagePath, imageId, numFrames);
		
		moveLeft = moveRight = false;
		fireRate =3;
		counter = fireRate;
		bulletPool = new BulletPool(30);
		bulletPool.init();
	}
	
	public void drawObject()
	{
		bulletPool.drawObject();
		super.drawObject();
		
	}

	@Override
	public void update() 
	{
		
		counter++;
		
		if(moveRight)
		{
			if(position.getX() + speedMovement <= width)
			{
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
		
		bulletPool.update();
		
		if(counter>=fireRate)
		{
			//System.out.println("Shooting");
			shoot();
			counter=0;
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
	
	private void shoot()
	{
		bulletPool.getBullet(getX()+37, getY()-this.getHeight()+30, 15);
	}

	public BulletPool getBulletPool()
	{
		return bulletPool;
	}

}
