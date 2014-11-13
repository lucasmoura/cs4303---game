package com.game;

import java.security.KeyStore.ProtectionParameter;
import java.util.Random;

import com.engine.GameObject;
import com.engine.Processing;

public class EnemyFactory 
{
	
	public static EnemyFactory instance = null;
	
	public static EnemyFactory getInstance()
	{
		if(instance == null)
			instance = new EnemyFactory();
		
		return instance;
	}
	
	private EnemyFactory()
	{
		
	}
	
	public GameObject createEnemy(int enemy)
	{
		switch(enemy)
		{
			case Enemy.ASTEROID:
				return createAsteroid();
			
			case Enemy.KODANCWCH:
				return createKodancwch();
				
			default:
				return null;
		}
	}
	
	private GameObject createAsteroid()
	{
		GameObject asteroid = new Asteroid(0, 0, 0, 0, "asteroidsMedium.png",
				"mediumAsteroid", 16);
		
		int width = Processing.getInstance().getParent().width;
		
		Random rand = new Random();
		int asteroidx = rand.nextInt(width);
		
		asteroid.setX(asteroidx);
		return asteroid;
	}
	
	private GameObject createKodancwch()
	{
		GameObject kodancwch = new Asteroid(0, 0, 0, 0,
				"enemyBlack1.png", "kodancwch", 1);
		
		int width = Processing.getInstance().getParent().width;
		
		Random rand = new Random();
		int asteroidx = rand.nextInt(width);
		
		kodancwch.setX(asteroidx);
		return kodancwch;
		
	}

}
