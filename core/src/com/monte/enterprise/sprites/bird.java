package com.monte.enterprise.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import static com.badlogic.gdx.Input.Keys.T;

/**
 * Created by singh on 21/01/2017.
 */

public class bird {
    private Sound flap;

    private static final int GRAV = -10; //tHE GRAVITY
    private static final int mov = 100;

    private Vector3 pos; //The position of the bird
    private Vector3 vel; //Velocity

    private Rectangle bound;

    private Texture bird;
    private Texture texture;
    private Animation birdAnimation;

    public bird(int x, int y){
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
        pos = new Vector3(x, y,0);
        vel = new Vector3(0,0, 0);
        texture = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(texture),3, 0.5f);
        bound = new Rectangle(x, y, texture.getWidth()/3, texture.getHeight());//Makes an invisible rectangle around the bird so that it can be used to detect the collision with the pipes and the ground
    }
    public void update(float delta){
        birdAnimation.update(delta);//Updates the animation ot move the frame and stuff like that
        if (pos.y > 0)
            vel.add(0, GRAV, 0);
        vel.scl(delta);
        pos.add(mov *delta,vel.y, 0);//This makes the bird go forward- The mov*delta part
        vel.scl(1/delta);
        if(pos.y < 0){
            pos.y = 0;//If the position of the bird is equal to or less that zero then it sets it on the surface. This should not happen because there is a collison with the ground.
        }
        bound.setPosition(pos.x, pos.y);//Updates the position of the bird so that it can be rendered in the correct position
    }
    public Vector3 getPos(){
        return pos;
    }
    public TextureRegion getTexture(){
        return birdAnimation.getFrame();
    }
    public void jump(){
        vel.y = 250;
        flap.play();
        //vel.x = 300; //For debugging purposes. REMOVE LATER
    }
    public Rectangle getBound(){
        return bound;
    }
    public void dispose(){//Disposes all the textures and useless stuff
       texture.dispose();
       flap.dispose();
    }
}
