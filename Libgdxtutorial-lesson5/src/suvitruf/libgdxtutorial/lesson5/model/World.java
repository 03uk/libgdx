package suvitruf.libgdxtutorial.lesson5.model;
import java.util.Map;

import suvitruf.libgdxtutorial.lesson5.controller.WalkingControl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import android.util.Log;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class World extends Stage{

	
	public float ppuX;	
	public float ppuY;
	//��������� ����
	public Actor selectedActor = null;
	//�������
	public Map<String, TextureRegion> textureRegions;
	public static float CAMERA_WIDTH = 12f;
	public static  float CAMERA_HEIGHT = 8f;
	//���������� ��������� ��������
	public void update(float delta){
		for(Actor actor: this.getActors())
			if(actor instanceof Player)
				((Player)actor).update(delta);
	}

	public World(int x, int y, boolean b, SpriteBatch spriteBatch, Map<String, TextureRegion> textureRegions){
		super(x,y, b, spriteBatch);
		this.textureRegions = textureRegions;
		ppuX = getWidth() / CAMERA_WIDTH;
		ppuY = getHeight() / CAMERA_HEIGHT;
		//������� ���� ����������
		addActor(new Player(new Vector2(4,2), this));
		addActor(new Player(new Vector2(4,4), this));
		
		
		//������� ��� ����	
		addActor(new WalkingControl (new Vector2(0F,0F),this));
	}
	
	public void setPP(float x, float y){
		ppuX = x;
		ppuY = y;
	}
	
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		super.touchDown(x, y, pointer, button);

		// ����������� ���������� �����
		//moveSelected(x, y);

		return true;
	}

	/*
	private void moveSelected(float x, float y){
		if(selectedActor != null && selectedActor instanceof Player)
		{
			((Player)selectedActor).ChangeNavigation(x,this.getHeight() -y);
		}
	}*/
	
	/**
	 * ���������� ������� ������ � ����������� ��������
	 */
	public void resetSelected(){
		if(selectedActor != null && selectedActor instanceof Player)
		{
			((Player)selectedActor).resetWay();
		}
	}
	
	@Override
	public boolean touchUp (int x, int y, int pointer, int button) {
		super.touchUp(x, y, pointer, button);
		 resetSelected();
    	return true;
    }
	
	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		if(selectedActor != null) 
			super.touchDragged(x, y, pointer);
	
    	return true;
    }
	
	public Actor hit(float x, float y, boolean touchable) {

		Actor  actor  = super.hit(x,y,touchable);
		//���� ������� �����
		if(actor != null &&  actor instanceof Player)
			//����������
			selectedActor = actor;
		return actor;

	}

}
