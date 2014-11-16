package com.game;

import java.util.ArrayList;

public class EnemyBulletPool 
{
	
	private static EnemyBulletPool instance = null;
	private BulletPool enemyBullets;
	
	public static EnemyBulletPool getInstance()
	{
		if(instance == null)
			instance = new EnemyBulletPool();
		
		return instance;
	}
	
	private EnemyBulletPool()
	{
		enemyBullets = new BulletPool(60);
		enemyBullets.init("laserGreen10.png", "enemyLaser", 1);
	}

	public void update()
	{
		enemyBullets.update();
	}
	
	public void getBullet(int x, int y, int speed, int damage)
	{
		enemyBullets.getBullet(x, y, speed, damage);
	}
	
	public ArrayList<DestructableObject> getPool()
	{
		return enemyBullets.getPool();
	}

}
