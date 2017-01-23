package com.monte.enterprise.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by singh on 21/01/2017.
 */

public class tube {
    public static final int WIDTH = 52;
    private Texture tube_TOP, tube_BOTTOM;
    private Vector2 topTUBE_pos, bottomTUBE_pos;
    private Random dice; //For the height of the tubes/ the differences in their heigh(the gap in between them)
    private static final int fluc = 130; //The fluctuation. Basically it can move between 0 and 130
    private static final int tubeGap = 100;
    private static final int Opening_LOW = 120;
    private Rectangle boundsTop, boundsBot; //This will create an invisible rectangle around the pipe textures

    public tube(float x) {
        tube_TOP = new Texture("toptube.png");
        tube_BOTTOM = new Texture("bottomtube.png");
        dice = new Random();//Used for randomly generation numbers so that the pipe can have variations
        topTUBE_pos = new Vector2(x, dice.nextInt(fluc) + tubeGap + Opening_LOW);//Position of the top tube
        bottomTUBE_pos = new Vector2(x, topTUBE_pos.y - tubeGap - tube_BOTTOM.getHeight());//Position of the bottom tube
        boundsTop = new Rectangle(topTUBE_pos.x, topTUBE_pos.y, tube_TOP.getWidth(), tube_TOP.getHeight());
        boundsBot = new Rectangle(bottomTUBE_pos.x, bottomTUBE_pos.y, tube_BOTTOM.getWidth(), tube_BOTTOM.getHeight());
    }

    public Texture getTube_TOP() {
        return tube_TOP;
    }

    public Texture getTube_BOTTOM() {
        return tube_BOTTOM;
    }

    public Vector2 getTopTUBE_pos() {
        return topTUBE_pos;
    }

    public Vector2 getBottomTUBE_pos() {
        return bottomTUBE_pos;
    }

    public void re_pos(float x) {//After the tubes go off of the screen they are repositioned to go to the back of the que. This way i dont have code labour
        topTUBE_pos.set(x, dice.nextInt(fluc) + tubeGap + Opening_LOW);
        bottomTUBE_pos.set(x, topTUBE_pos.y - tubeGap - tube_BOTTOM.getHeight());
        boundsTop.setPosition(topTUBE_pos.x, topTUBE_pos.y);//The invisible rectangles around the pipes
        boundsBot.setPosition(bottomTUBE_pos.x, bottomTUBE_pos.y);
    }
    public boolean collides(Rectangle playr){//Returns a true or false statement to check if the bird has collided with any of the pipes.
        return playr.overlaps(boundsTop) || playr.overlaps(boundsBot);
    }
    public void dispose(){//Disposes everything
        tube_TOP.dispose();
        tube_BOTTOM.dispose();
    }
    public int getWidth(){
        return WIDTH;
    }
}
