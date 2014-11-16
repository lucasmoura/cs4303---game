package com.game;

public class AdmiralShip extends DestructableObject
{
	
	private boolean moveRight;
	private boolean moveLeft;
	private int speedMovement = 12;
	private int width = 1814;
	private BulletPool bulletPool;
	private int counter;
	private int fireRate;
	private boolean alive;
	private boolean explode;
	private Explosion explosion;

	public AdmiralShip(int x, int y, int objectWidth, int objectHeight,
			String imagePath, String imageId, int numFrames)
	{
		super(x, y, objectWidth, objectHeight, imagePath, imageId, numFrames);
		
		moveLeft = moveRight = explode = false;
		fireRate =3;
		counter = fireRate;
		bulletPool = new BulletPool(30);
		bulletPool.init("laserBlue01.png", "laser", 0);
		
		alive = true;
		explosion = new Explosion(0, 0, 0, 0, "explosion.png", "explosion", 17);
		setCollidable("mediumAsteroid");
		setCollidable("enemyLaser");
	}
	
	public void drawObject()
	{
		if(!explode && alive)
		{
			bulletPool.drawObject();
			super.drawObject();
		}
		else if(explode && alive)
			explode();
		
	}

	@Override
	public void update() 
	{
		
		counter++;
		
		if(explode)
			return;
		
		if(isColliding())
		{
			health -= damageReceived;
			isColliding = false;
			
//			System.out.println("Health: "+health);
//			System.out.println("Damage received: "+damageReceived);
			damageReceived = 0;
			
			if(health <= 0)
			{
				health =0;
				explode = true;
				return;
			}	
		}
		
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
			//shoot();
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
		bulletPool.getBullet(getX()+37, getY()-this.getHeight()+30, 15, 10);
	}

	public BulletPool getBulletPool()
	{
		return bulletPool;
	}
	
	private void explode()
	{
		explosion.setX(getX());
		explosion.setY(getY());
		
		if(!explosion.isOver())
		{
			explosion.update();
			explosion.drawObject();
		}	
		else
			alive = false;
	}

}
