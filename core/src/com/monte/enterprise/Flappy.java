package com.monte.enterprise;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.monte.enterprise.States.GameStateManager;
import com.monte.enterprise.States.MenuState;


public class Flappy extends ApplicationAdapter {

	public static final int Width = 480;
	public static final int Height = 800;

	public static final String title = "FlappyBird";

	private GameStateManager gsm;

	SpriteBatch batch;
	Texture img;
	private Music beats;
	@Override
	public void create () {
		beats = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));//Adds the path the the audio and the rest is pretty self explanatory
		beats.setLooping(true);
		beats.setVolume(0.5f);
		beats.play();
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		img = new Texture("badlogic.jpg");
		Gdx.gl.glClearColor(1, 0, 0, 1); //Clears the screen and sets the default color
		gsm.push(new MenuState(gsm));//This tells the game state manager to create a new state called the main menu.
	}

	@Override
	public void render () {//This render method is called 60 times a second.

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);//Once the images and textures are rendered on the screen, it removes everything after each frame so that the images can be redrawn.
		gsm.update(Gdx.graphics.getDeltaTime());//Calls the gsm so that it can update all the states.

		batch.begin();	//Spritebatch is responsible for drawing everything on the screen
		//batch.draw(img, 0, 0);
		gsm.render(batch);//Tells the game state manager to draw the current game state. Passes in the argument of the batch. Because if it is created everytime then it would waste a lot of memory
		batch.end();
	}
	
	@Override
	public void dispose () { //Gets rid of everything after it has used it. Makes sure there is no memory leaks or anyhting of that kind to slow down the performance.
		batch.dispose();
		img.dispose();
		beats.dispose();
	}
}
