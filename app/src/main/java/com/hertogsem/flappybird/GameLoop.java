package com.hertogsem.flappybird;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Sem Rekkers on 15-1-2017.
 */

public class GameLoop {
    private static final String TAG = "GameLoop";

    private Object loopLock = new Object();
    private GameSurfaceView gameSurfaceView;

    // Thread safe
    private Paint paint;
    private Background background;
    private Ground ground;
    private Player player;
    private Point playerPoint;
    private Pipe pipe;
    private PipeManager pipeManager;

    public GameLoop(GameSurfaceView gameSurfaceView) throws Exception {
        this.gameSurfaceView = gameSurfaceView;

        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        this.background = new Background(gameSurfaceView.getContext());
        this.ground = new Ground(gameSurfaceView.getContext());
        this.player = new Player(new Rect(100,100, 200,200), Color.RED);
        this.playerPoint = new Point(200,200);

        this.pipe = new Pipe(gameSurfaceView.getContext(), 400, 400, 400);
        pipeManager = new PipeManager(gameSurfaceView.getContext(), 400, 2*Constants.SCREEN_WIDTH/3);
    }

    public void run() {
        synchronized (loopLock) {
            Canvas canvas = gameSurfaceView.getHolder().lockCanvas();
            if (canvas != null) {
                synchronized (gameSurfaceView.getHolder()) {
                    loopOnce(canvas);
                }
                gameSurfaceView.getHolder().unlockCanvasAndPost(canvas);
            }
        }
    }

    private void loopOnce(Canvas canvas) {
        long time = System.currentTimeMillis();
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        background.update(time, width, height);
        pipeManager.update();
        //pipe.update();
        ground.update(time, width, height);
        player.update(time, width, height);

        background.draw(canvas, paint);
        pipeManager.draw(canvas, paint);
        //pipe.draw(canvas, paint);
        ground.draw(canvas, paint);
        player.draw(canvas, paint);
    }

}
