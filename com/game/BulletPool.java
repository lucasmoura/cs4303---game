package com.game;

import java.util.ArrayList;
import java.util.LinkedList;

public class BulletPool
{
	private int size;
	private LinkedList<Bullet> bullets;
	
	public BulletPool(int size)
	{
		this.size = size;
		bullets = new LinkedList<Bullet>();
	}
	
	public boolean init(String bulletType, String id, int type)
	{
		for(int i =0; i<size; i++)
		{
			Bullet bullet = new Bullet(0, 0, 0, 0, bulletType, id,1, type);
			bullets.add(bullet);
		}
		
		return true;
	}
	
	public void getBullet(int x, int y, int speed, int damage)
	{
		
		if(!bullets.getLast().isActive())
		{
			bullets.getLast().setX(x);
			bullets.getLast().setY(y);
			bullets.getLast().setSpeed(speed);
			bullets.getLast().setDamageDealt(damage);
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
	
	public ArrayList<DestructableObject> getPool()
	{
		ArrayList<DestructableObject> objects = new ArrayList<>();
		for (int i = 0; i < size; i++) 
		{
			if (bullets.get(i).isActive())
				objects.add(bullets.get(i));
		}
		
		return objects;
	}

}
