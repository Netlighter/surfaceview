package com.example.surfaceview;

import android.animation.ArgbEvaluator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.util.Random;

public class MyThread extends Thread{

    private boolean flag;

    private long startTime;

    private long prevReadTime;

    private ArgbEvaluator argb;

    private Paint paint;
    private SurfaceHolder sh;
    MyThread(SurfaceHolder sh) {
        this.sh = sh;
        this.flag = false;
        paint  = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        argb = new ArgbEvaluator();

    }

    public long  getTime() {
        return System.nanoTime()/1000;
    }

    public void setRunning(boolean f) {
        flag = f;
        prevReadTime =  getTime();
    }

    @Override
    public void run() {
        Canvas canvas;
//        startTime = getTime();
        while(flag) {
           canvas = sh.lockCanvas();
//            Random r = new Random();
//            int color = Color.rgb(r.nextInt(255),
//                                    r.nextInt(255),
//                                    r.nextInt(255));
            int color = (int)argb.evaluate((float)Math.random(), Color.RED, Color.GREEN);
            paint.setColor(color);
            canvas.drawCircle(canvas.getWidth()/2,
                            canvas.getHeight()/2,
                            200, paint);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sh.unlockCanvasAndPost(canvas);

        }
    }
}
