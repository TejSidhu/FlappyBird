package com.monte.enterprise.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Align;
import com.monte.enterprise.Flappy;
import com.sun.org.apache.xpath.internal.functions.FuncFalse;

import sun.font.TrueTypeFont;

import static com.badlogic.gdx.Input.Keys.G;


/**
 * Created by singh on 21/01/2017.
 */

public class MenuState extends State {
    //public BitmapFont font;
    private Texture bg;
    private Texture playbtn;
    //GlyphLayout title;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, Flappy.Width/2, Flappy.Height/2);//The boolean asks if the y starts in the top left-hand corner or the bottom left-hand corner
        bg = new Texture(Gdx.files.internal("bg.png"));
        playbtn = new Texture(Gdx.files.internal("playbtn.png"));
        //initFonts();
    }

    @Override
    public void handleInput() {
        if(Gdx.input.isTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float delta) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(cam.combined);
        batch.draw(bg, 0, 0);
        batch.draw(playbtn, cam.position.x - playbtn.getWidth()/2, cam.position.y);
        //font.draw(batch, title, cam.position.x - title.width/2, cam.position.y);
    }
    public void dispose(){
        bg.dispose();
        playbtn.dispose();
        System.out.println("MenuState Disposed");
    }
    private void initFonts(){
        //font = new BitmapFont(Gdx.files.internal("font.fnt"));
        //title= new GlyphLayout(font, "FLAPPPY BIRDY");

        //title.setText(font, "Flappy Birdy", Color.BLACK,0, Align.center, false);

        //FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("FlappyBirdy.tff"));
        //FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        //generator.generateData(16, "Flappy Birdy", false);
    }
}
