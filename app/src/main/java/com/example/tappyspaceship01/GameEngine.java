package com.example.tappyspaceship01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

public class GameEngine extends SurfaceView implements Runnable {

    // Android debug variables
    final static String TAG="TAPPY-SPACESHIP";

    // screen size
    int screenHeight;
    int screenWidth;

    // game state
    boolean gameIsRunning;

    // threading
    Thread gameThread;


    // drawing variables
    SurfaceHolder holder;
    Canvas canvas;
    Paint paintbrush;



    // -----------------------------------
    // GAME SPECIFIC VARIABLES
    // -----------------------------------

    // ----------------------------
    // ## SPRITES
    // ----------------------------

    // represents the top left corner of your player
    int playerXPosition;
    int playerYPosition;

    int enemyXPosition;
    int enemyYPosition;


    // ----------------------------
    // ## GAME STATS
    // ----------------------------

    public GameEngine(Context context, int w, int h) {
        super(context);


        this.holder = this.getHolder();
        this.paintbrush = new Paint();

        this.screenWidth = w;
        this.screenHeight = h;


        this.printScreenInfo();

        this.printScreenInfo();

        // @TODO: Add your sprites
               // @TODO: Any other game setup

                        // put initial starting postion of enemy
                                this.enemyXPosition = 1300;
                this.enemyYPosition = 120;

                        // put the initial starting position of your player
                                this.playerXPosition = 100;
               this.playerYPosition = 120;
    }


    private void printScreenInfo() {

        Log.d(TAG, "Screen (w, h) = " + this.screenWidth + "," + this.screenHeight);
    }

    private void spawnPlayer() {
        //@TODO: Start the player at the left side of screen
    }
    private void spawnEnemyShips() {
        Random random = new Random();

        //@TODO: Place the enemies in a random location

    }

    // ------------------------------
    // GAME STATE FUNCTIONS (run, stop, start)
    // ------------------------------
    @Override
    public void run() {
        while (gameIsRunning == true) {
            this.updatePositions();
            this.redrawSprites();
            this.setFPS();
        }
    }


    public void pauseGame() {
        gameIsRunning = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            // Error
        }
    }

    public void startGame() {
        gameIsRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }


    // ------------------------------
    // GAME ENGINE FUNCTIONS
    // - update, draw, setFPS
    // ------------------------------



    public void updatePositions() {
        // @TODO: Update position of player based onn mouse tap
        // if mousedown,



    }

    public void redrawSprites() {
        if (this.holder.getSurface().isValid()) {
            this.canvas = this.holder.lockCanvas();

            //----------------

            // configure the drawing tools
            this.canvas.drawColor(Color.argb(255,255,255,255));
            paintbrush.setColor(Color.WHITE);


            // DRAW THE PLAYER HITBOX
            // ------------------------
            // 1. change the paintbrush settings so we can see the hitbox
            paintbrush.setColor(Color.BLUE);
            paintbrush.setStyle(Paint.Style.STROKE);
            paintbrush.setStrokeWidth(5);
        // draw player graphic on the screen
            // draw player graphic on screen
            Bitmap playerImage = BitmapFactory.decodeResource(this.getContext().getResources(),
                    R.drawable.player_ship);
                       canvas.drawBitmap(playerImage, 100, 120, paintbrush);
                        canvas.drawBitmap(playerImage, playerXPosition, playerYPosition, paintbrush);

            // draw the enemy graphic on the screen
            Bitmap ememyImage = BitmapFactory.decodeResource(this.getContext().getResources(),
                    R.drawable.alien_ship2);
                        canvas.drawBitmap(ememyImage, 1300, 120, paintbrush);
                        canvas.drawBitmap(ememyImage, enemyXPosition, enemyYPosition, paintbrush);



            //----------------
            this.holder.unlockCanvasAndPost(canvas);
        }
    }

    public void setFPS() {
        try {
            gameThread.sleep(120);
        }
        catch (Exception e) {

        }
    }

    // ------------------------------
    // USER INPUT FUNCTIONS
    // ------------------------------
    String fingerAction = "";
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int userAction = event.getActionMasked();
        //@TODO: What should happen when person touches the screen?
        if (userAction == MotionEvent.ACTION_DOWN) {
            Log.d(TAG, "Person tapped the screen");
            // user is pressing , so move the player up

            fingerAction = "mousedown";


        }
        else if (userAction == MotionEvent.ACTION_UP) {
            Log.d(TAG, "Person lifted finger");
            // user has released the finger, so move the player down
            fingerAction = "mouseup";

        }

        return true;
    }
}
