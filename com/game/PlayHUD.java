package com.game;

import processing.core.PApplet;
import processing.core.PFont;

import com.engine.Processing;
import com.engine.TextureManager;

public class PlayHUD
{
	private String hudBase;
	private String healthIcon;
	private int playerLife;
	private int score;
	private PFont playerLifeText;
	private PApplet applet;
	
	public PlayHUD()
	{
		hudBase = "hudBase";
		healthIcon = "healthIcon";
		
		playerLife = 100;
		score = 0;
	}
	
	public void update(int playerLife, int score)
	{
		this.playerLife = playerLife;
		this.score = score;
	}
	
	public void drawObject()
	{
		TextureManager.getInstance().drawObject(hudBase, 0, 0);
		TextureManager.getInstance().drawObject(healthIcon, 80, 10);
		
		applet.textFont(playerLifeText, 85);
		applet.fill(255);
		
		applet.text(String.valueOf(playerLife), 180, 85);
	}
	
	public void init()
	{
		applet = Processing.getInstance().getParent();
		TextureManager.getInstance().loadGameImage("hudBase.png", "hudBase");
		TextureManager.getInstance().loadGameImage("healthIcon.png", healthIcon);
		
		playerLifeText = applet.createFont("Arial", 16, false);
		
	}
	
	public void clean()
	{
		TextureManager.getInstance().clearFromTextureMap(hudBase);
		TextureManager.getInstance().clearFromTextureMap(healthIcon);
	}

}
