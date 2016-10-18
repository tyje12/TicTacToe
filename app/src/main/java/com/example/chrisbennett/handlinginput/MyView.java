/*
TO DO:
    - improve appearance
    - add restart button
    - draw line through winning game
    - create database to save/load previous games?

 */


package com.example.chrisbennett.handlinginput;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.io.Console;
import java.util.ArrayList;

/**
 * Created by chris.bennett on 10/13/16.
 */
public class MyView extends View  {

    private Context c;
    private Paint p;
    private Paint black;
    private Paint white;
    private Paint text;
    private Canvas canvas;
    private Bitmap bitmap;
    private float x,y;
    private int radius;
    private ArrayList<Circle> circles;

    private TicTacToe game;
    private int top, bottom, left, right;

    private final int RECTANGLE = 2;
    private final int CIRCLE = 1;

    private int mode;

    private long lastMove=0;

    private long delay = 1000 / 30;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        c = context;
        p = new Paint();
        p.setColor(Color.rgb(123,234,23));

        black = new Paint();
        black.setStrokeWidth(5);
        p.setColor(Color.BLACK);

        white = new Paint();


        text = new Paint();
        text.setTextAlign(Paint.Align.CENTER);
        text.setTextSize(75);
        text.setColor(Color.BLACK);

        x = 100;
        y = 100;
        radius = 50;

        circles = new ArrayList<Circle> ();
        mode = CIRCLE;

        game = new TicTacToe();
        top=0;
        left=0;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);
        // your Canvas will draw onto the defined Bitmap
        bitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        right=w;
        bottom=h;
        canvas = new Canvas(bitmap);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*
        for(int i=0;i<circles.size();i++) {
            p.setColor(circles.get(i).c);
            canvas.drawCircle(circles.get(i).x,
                              circles.get(i).y,
                              circles.get(i).r,
                              p);
        }
        */
        //top = 0;
        //bottom=canvas.getHeight();
        //left = 0;
        //right = canvas.getWidth();

        //draw lines
        canvas.drawLine(left,(bottom-top)/3, right,(bottom-top)/3, black);
        canvas.drawLine(left,2*(bottom-top)/3, right,2*(bottom-top)/3, black);
        canvas.drawLine((right-left)/3,top, (right-left)/3,bottom, black);
        canvas.drawLine(2*(right-left)/3,top, 2*(right-left)/3,bottom, black);


        //draw Xs and Os
        String s = game.toString();
        int x = right/3;
        int y = bottom/3;



        for(int i=0;i<s.length();i++) {
            Log.e("drawing", "i,x,y: " + i + " , " + x +  " , " + y);
            if(s.charAt(i)=='X') {
                canvas.drawText("X", i%3*x +x/2, i/3*y + y/2,text);
            }
            else if(s.charAt(i)=='O') {
                canvas.drawText("O", i%3*x  +x/2, i/3*y + y/2,text);
            }
        }

        //check for winner
        int winner = game.checkForWin();
        if(winner == TicTacToe.X) {
            canvas.drawText("X Wins!",right/2,bottom/2,text);
            canvas.drawRect((right-left), (bottom-top),right/2,bottom/2,black);
        }
        else if(winner==TicTacToe.O) {
            canvas.drawText("O Wins!",right/2,bottom/2,text);
        }
        else if(winner==3) {
            canvas.drawText("Draw!",right/2,bottom/2,text);
        }

        Log.e("tostring", s + " , " + winner);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        x = event.getX();
        y = event.getY();
        int x_i = (int) x;
        int y_i = (int) y;
        int cell = (int) (3*(y_i / (bottom/3))  + (x_i / (right/3)));
//        circles.add(new Circle(x,y,50.0f,Color.rgb((int) (Math.random()*256),(int) (Math.random()*256),(int) (Math.random()*256))));
        if(game.getMode() != TicTacToe.STOP) {
            game.makeMove(cell);
        }
        invalidate();

        return true;
    }



    protected void startTouch(float x, float y) {
        this.x = x;
        this.y = y;
    }


    class Circle {
        public float x;
        public float y;
        public float r;
        public int c;

        public Circle(float x, float y, float r,int c) {
            this.x = x;
            this.y = y;
            this.r = r;
            this.c = c;
        }

    }

}
