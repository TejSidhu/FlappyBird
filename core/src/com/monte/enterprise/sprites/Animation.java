package com.monte.enterprise.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by singh on 22/01/2017.
 */

public class Animation {
    //This class is probably the most self explanatory and easy to understand
    private Array<TextureRegion> frames;
    private float frame_length;
    private float frame_TIMER;
    private int frame_COUNT;
    private int frame;

    public Animation(TextureRegion region, int frame_COUNT, float cycle_TIME){//Cycle_TIME is how long it is gonna take to get though the entire thing uno?
       frames = new Array<TextureRegion>();
        int frame_WIDHT = region.getRegionWidth() / frame_COUNT; //The width of just a single frame
        for(int i = 0; i < frame_COUNT; i++){
            frames.add(new TextureRegion(region, i * frame_WIDHT,0, frame_WIDHT, region.getRegionHeight()));
        }
        this.frame_COUNT = frame_COUNT;
        frame_length = cycle_TIME/frame_COUNT;
    }
    public void update(float delta){
        frame_TIMER+= delta;
        if(frame_TIMER >= frame_length ){
            frame++;
            frame_TIMER = 0;
        }
        if(frame >= frame_COUNT){
            frame = 0;
        }
    }
    public TextureRegion getFrame() {
        return frames.get(frame);
    }
}
