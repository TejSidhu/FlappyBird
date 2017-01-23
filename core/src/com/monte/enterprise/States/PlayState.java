package com.monte.enterprise.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.monte.enterprise.Flappy;
import com.monte.enterprise.sprites.bird;
import com.monte.enterprise.sprites.tube;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import static com.badlogic.gdx.Input.Keys.V;


/**
 * Created by singh on 21/01/2017.
 */

public class PlayState extends State {
    private static final int Tube_SPACING = 175; //The gap inbetween the series of tubes
    private static final int TUBE_COUNT = 4; //Max number of spawned lubes
    private static final int ground_OFFSET_y = -50;
    private Array<tube> tubes;
    private bird bird;
    private Texture bg;
    private Texture ground;
    public tube tube;
    private Vector2 groundPOS1, groundPOS2;
    public PlayState(GameStateManager gsm) {
        super(gsm);
        ground = new Texture("ground.png");
        bird = new bird(50, 200);
        bg = new Texture("bg.png");
        cam.setToOrtho(false, Flappy.Width/2, Flappy.Height/2);//The boolean asks if the y starts in the top left-hand corner or the bottom left-hand corner
        //tube = new tube(100);
        tubes = new Array<tube>();
        for(int i = 1; i <= TUBE_COUNT; i++){
            tubes.add(new tube(i * Tube_SPACING + tube.WIDTH));//That is the width off the tube; This is the spawn code, well...Kinda
        }
        groundPOS1 = new Vector2(cam.position.x - cam.viewportWidth/2, ground_OFFSET_y);
        groundPOS2 = new Vector2((cam.position.x - cam.viewportWidth/2) + ground.getWidth(), ground_OFFSET_y);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    public void update(float delta) {
        cam.position.x = bird.getPos().x + 80;
        handleInput();
        bird.update(delta);
        for(int i = 0; i < tubes.size; i++){
            tube tube = tubes.get(i);
            if(cam.position.x - (cam.viewportWidth/2) > tube.getTopTUBE_pos().x + tube.getTube_TOP().getWidth()) {
                tube.re_pos(tube.getTopTUBE_pos().x + tube.WIDTH / 2.5f + (Tube_SPACING * TUBE_COUNT));
            }
            if(tube.collides(bird.getBound())){
                gsm.set(new PlayState(gsm));
            }
            if(bird.getPos().x > tube.getBottomTUBE_pos().x && bird.getPos().x < tube.getBottomTUBE_pos().x + tube.getWidth()/20){//This scoring system kinda works but need a more accurate and reliable way of measuring the score
                System.out.println("1 POINT");
            }
        }

        updateGround();
        if(bird.getPos().y <= ground_OFFSET_y + ground.getHeight()){
            gsm.set(new PlayState(gsm));
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(cam.combined);
        batch.draw(bg, cam.position.x - cam.viewportWidth/2, 0);
        batch.draw(bird.getTexture(), bird.getPos().x, bird.getPos().y);
        for (tube tube: tubes){
            batch.draw(tube.getTube_TOP(), tube.getTopTUBE_pos().x, tube.getTopTUBE_pos().y);
            batch.draw(tube.getTube_BOTTOM(), tube.getBottomTUBE_pos().x, tube.getBottomTUBE_pos().y);
        }
        batch.draw(ground, groundPOS1.x, groundPOS1.y);
        batch.draw(ground, groundPOS2.x, groundPOS2.y);
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        ground.dispose();
        for(tube tube: tubes){
            tube.dispose();
    }
        System.out.println("Tubes + PlayState Disposed");
    }
    private void updateGround(){
        if(cam.position.x - cam.viewportWidth/2 > groundPOS1.x + ground.getWidth()){
            groundPOS1.add(ground.getWidth() * 2, 0);
        }
        if(cam.position.x - cam.viewportWidth/2 > groundPOS2.x + ground.getWidth()){
            groundPOS2.add(ground.getWidth() * 2, 0);
        }
    }
}
