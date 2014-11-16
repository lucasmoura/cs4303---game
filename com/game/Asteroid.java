package com.game;

import java.util.Random;

import com.engine.GameObject;
import com.engine.Processing;

import processing.core.PApplet;

public class Asteroid extends GameObject implements Enemy
{
	
	private int speed;
	private boolean alive;
	private boolean explode;
	private Explosion explosion;
	private int life;

	public Asteroid(int x, int y, int objectWidth, int objectHeight,
			String imagePath, String imageId, int numFrames) 
	{
		super(x, y, objectWidth, objectHeight, imagePath, imageId, numFrames);
		
		explosion = new Explosion(0, 0, 0, 0, "explosion.png", "explosion", 17);
	
		Random rand = new Random();
		PApplet pApplet = Processing.getInstance().getParent();
		
		setX(rand.nextInt(pApplet.width));
		explosion.setX(getX());
		
		setCollidable("laser");
		speed = 8;
		alive = true;
		explode = false;
	}

	@Override
	public void move()
	{
		this.position.setY(this.position.getY()+speed);
		PApplet applet = Processing.getInstance().getParent();
		
		if ( position.getY() > applet.displayHeight)
		{
			position.setX((int)applet.random(applet.displayWidth - objectWidth));
			position.setY(0);
		}
		
		int interval = 40;
		currentRow = (applet.millis()/interval)%numFrames;
		
	}

	@Override
	public void shoot() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() 
	{
		
		
		if(isColliding())
		{
			explode = true;
			return;
		}
		
		move();
		
	}
	
	public void drawObject()
	{
		if(!isColliding())
			super.drawObject();	
		else if(explode && alive)
			explode();
	}
	
	@Override
	public boolean isAlive()
	{
		return alive;
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
