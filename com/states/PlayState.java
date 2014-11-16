package com.states;

import java.util.ArrayList;
import java.util.Iterator;

import android.util.Log;

import com.engine.Button;
import com.engine.GameObject;
import com.engine.Processing;
import com.engine.QuadTree;
import com.game.AdmiralShip;
import com.game.EnemyFactory;
import com.game.EnemySpawn;
import com.game.Kodancwch;
import com.game.Starfield;
import com.game.Enemy;

import processing.core.PApplet;

public class PlayState implements GameState
{
	
	private Starfield starfield;
	private ArrayList<GameObject> playObjects;
	private ArrayList<GameObject> enemies;
	private EnemySpawn spawn;
	private final String playID = "PLAY";
	private Button leftButton; 
	private Button rightButton;
	private AdmiralShip admiralShip;
	private QuadTree quadTree;
	
	private String LOG_TAG = "PlayState";

	@Override
	public void update() 
	{
		
		handleQuadTree();
		
		for (Iterator<GameObject> iterator = enemies.iterator(); iterator.hasNext();)
		{
			GameObject object = iterator.next();
			
		    if (!((Enemy) object).isAlive())
		        iterator.remove();
		    else
		    	object.update();
		}
		
		starfield.update();
		
		enemies.addAll(spawn.spawn(enemies.size()));
		
		if(rightButton.isPressed())
			admiralShip.moveRight();
		else if(leftButton.isPressed())
			admiralShip.moveLeft();
		
		admiralShip.update();
		
	}

	@Override
	public void render() 
	{
		
		starfield.drawObject();
		admiralShip.drawObject();
		
		for(GameObject enemy: enemies)
			enemy.drawObject();
		
		leftButton.drawObject();
		rightButton.drawObject();
		
	}

	@Override
	public boolean onEnter() 
	{
		PApplet applet = Processing.getInstance().getParent();
		playObjects = new ArrayList<GameObject>();
		enemies = new ArrayList<GameObject>();
		spawn = new EnemySpawn();
		
		//GameObject kodan = EnemyFactory.getInstance().createEnemy(Enemy.KODANCWCH);
		
		starfield = new Starfield(20);
		//System.out.println("Starfield ready");
		rightButton = new Button(0, 0, "rightMove.png", "rightMove", 1, false);
		int rightx = applet.width - rightButton.getWidth();
		int righty = applet.height - rightButton.getHeight();
		rightButton.setX(rightx);
		rightButton.setY(righty);
		
		leftButton = new Button(0, 0, "leftMove.png", "leftMove", 1, false);
		int leftx = 0;
		int lefty = applet.height - leftButton.getHeight();
		leftButton.setX(leftx);
		leftButton.setY(lefty);
		
		admiralShip = new AdmiralShip(0, 0, 0, 0, "admiralship.png",
				"admiralship", 1);
		int shipx = applet.width/2 - admiralShip.getWidth()/2;
		int shipy = applet.height - admiralShip.getHeight();
		admiralShip.setX(shipx);
		admiralShip.setY(shipy);
		
		quadTree = new QuadTree(0, 0, 0, applet.width, applet.height);
		
		playObjects.add(admiralShip);
		//enemies.add(kodan);
		
		return true;
	}

	@Override
	public boolean onExit() 
	{
		leftButton.clean();
		rightButton.clean();
		starfield = null;
		return true;
	}
	
	private void detectCollision()
	{
		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		ArrayList<GameObject> collision = new ArrayList<GameObject>();
		
		quadTree.getAllObjects(objects);
		
		for (int x = 0, len = objects.size(); x < len; x++)
		{
			collision.clear();
			quadTree.retrieve(collision, objects.get(x));
			
			for (int y = 0, length = collision.size(); y < length; y++)
			{

				if ( objects.get(x).isCollidableWith(collision.get(y)) &&
						objects.get(x).getX() < collision.get(y).getX() + collision.get(y).getWidth() &&
					     objects.get(x).getX() + objects.get(x).getWidth()  > collision.get(y).getX() &&
					     objects.get(x).getY() < collision.get(y).getY() + collision.get(y).getHeight() &&
						 objects.get(x).getY() + objects.get(x).getHeight() > collision.get(y).getY()) 
					{
						
						objects.get(x).setColliding(collision.get(y).getDamage());
						collision.get(y).setColliding(objects.get(x).getDamage());
					}
				}
			}
	}
	
	private void handleQuadTree()
	{
		quadTree.clear();
		
		for(int i =0; i<enemies.size(); i++)
			quadTree.insert(enemies.get(i));
		
		quadTree.insert(admiralShip);
		
		ArrayList<GameObject> bullets = admiralShip.getBulletPool().getPool();
		
		for(int i =0; i<bullets.size(); i++)
			quadTree.insert(bullets.get(i));
		
		detectCollision();
	}
				
	@Override
	public void enable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseReleased(int x, int y) 
	{
		
		leftButton.setPressed(false);
		rightButton.setPressed(false);
		
	}
	
	public void mousePressed(int x, int y)
	{
		 if(leftButton.touchOnMe(x, y))
		 {
			 //System.out.println("Left pressed");
			 
				if (leftButton.isPressed()==false) 
				{
				  leftButton.setPressed(true);
				  rightButton.setPressed(false);
				}
			
		 }
		 else
		 {
			 leftButton.setPressed(false);
		 }
		
		 if(rightButton.touchOnMe(x, y))
		 {
			 //System.out.println("Right pressed");
			 
				if (rightButton.isPressed()==false) 
				{
				  rightButton.setPressed(true);
				  leftButton.setPressed(false);
				}
			
		 }
		 else
		 {
			 rightButton.setPressed(false);
		 }
	
	}

	@Override
	public String getStateId() 
	{
		return playID;
	}

}
