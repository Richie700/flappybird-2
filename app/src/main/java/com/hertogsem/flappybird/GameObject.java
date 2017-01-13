package com.hertogsem.flappybird;

import android.graphics.Canvas;

public interface GameObject {
    /**
     * Update updates the properties of the game object.
     * @param time Elapsed time in milliseconds.
     * @param width Width of the canvas.
     * @param height Height of the canvas.
     */
    void update(long time, int width, int height);

    /**
     * Draw draws the object to the canvas.
     * @param canvas The game canvas.
     */
    void draw(Canvas canvas);
}
