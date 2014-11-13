package com.game;

import java.util.ArrayList;
import java.util.LinkedList;

import com.engine.GameObject;

public class BulletPool
{
	private int size;
	private LinkedList<Bullet> bullets;
	
	public BulletPool(int size)
	{
		this.size = size;
		bullets = new LinkedList<Bullet>();
	}
	
	public boolean init()
	{
		for(int i =0; i<size; i++)
		{
			Bullet bullet = new Bullet(0, 0, 0, 0, "laserBlue01.png", "laser",1);
			bullets.add(bullet);
		}
		
		return true;
	}
	
	public void getBullet(int x, int y, int speed)
	{
		
		if(!bullets.getLast().isActive())
		{
			bullets.getLast().setX(x);
			bullets.getLast().setY(y);
			bullets.getLast().setSpeed(speed);
			bullets.getLast().setActive(true);
			
			bullets.push(bullets.pollLast());
			
		}
	}
	
	public void update()
	{
		
		int size = bullets.size();
		
		for(int i =0; i<size; i++)
		{
			if(bullets.get(i).isActive())
			{
				bullets.get(i).update();
				
				if(!bullets.get(i).getIsInScreen())
				{
					bullets.get(i).clear();
					bullets.addLast(bullets.get(i));
					bullets.remove(i);
				}
			
			}
		}
		
	}
	
	public void drawObject()
	{
		for(int i =0; i<bullets.size(); i++)
		{
			if(bullets.get(i).isActive())
				bullets.get(i).drawObject();
		}
	}
	
	public ArrayList<GameObject> getPool()
	{
		ArrayList<GameObject> objects = new ArrayList<>();
		for (int i = 0; i < size; i++) 
		{
			if (bullets.get(i).isActive())
				objects.add(bullets.get(i));
		}
		
		return objects;
	}

}
