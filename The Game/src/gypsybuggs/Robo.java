package gypsybuggs;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Robo
{
//    Screen Size: 799, 470
//    The ground is defined at about 440 pixels.
//    => character's centerY is at about 382 pixels and his feets should touch ground at 440 pixels
//    If character's centerX is less than 60 his left hand will go out

    // Constants
    final int JUMPSPEED = -15;
    final int MOVESPEED = 5;
//    final int GROUND= 377;


    private int centerX = 100;          // x-cordinate of Robot's centre
    private int centerY = 377;          // y-cordinate of Robot's centre

    private boolean jumped = false;     // true: when the Robo is in Air
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean ducked = false;

    private static  Background bg1 = StartingClass.getBg1();
    private static  Background bg2 = StartingClass.getBg2();

    // For -ve speedX the character will move left
    private int speedX = 0;
    private int speedY = 0;
    public static Rectangle rect1 = new Rectangle(0,0,0,0);
    public static Rectangle rect2 = new Rectangle(0,0,0,0);
    public static Rectangle gunRect = new Rectangle(0,0,0,0);
    public static Rectangle yellowRed = new Rectangle(0,0,0,0); // 25 tile region
    //red -> x-coordinate collision virtual marker
    //yellow -> x-coordinate collision virtual marker
    public static  Rectangle leftFoot = new Rectangle(0,0,0,0); // 25 tile region;
    public static  Rectangle rightFoot = new Rectangle(0,0,0,0); // 25 tile region;

    //Direct intialization and use of ArrayList than List
    private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

//    The origin (0.0) pixel is at TOP LEFT.
//    => a character having POSITIVE speedY, is FALLING NOT RISING
    public void update() {

        // Moves Character or Scrolls Background accordingly.
        if (speedX < 0) {
            centerX += speedX;
        }
        if (speedX==0 || speedX<0)
        {
            bg1.setSpeedX(0);
            bg2.setSpeedX(0);
        }
        if (centerX<=200&& speedX>0)
            centerX += speedX;
        if (speedX>0&&centerX>200)
        {
            //background movement at 1/5 Robo speed to give Parallax Depth
            bg1.setSpeedX(-MOVESPEED/5);
            bg2.setSpeedX(-MOVESPEED/5);
        }

        // Updates Y Position
        centerY += speedY;      //Add speedY to centerY to determine its new position
//        if (centerY + speedY >= GROUND)
//            setCenterY(GROUND);
//        // 382 is where the character's centerY would be if her were standing on the ground


        // Handles Jumping
        speedY += 1;    //while the character is in air, add 1 to his speedY.
        //NOTE: This will bring the character downwards!

        if (speedY>3)
            setJumped(true);

        // Prevents going beyond X coordinate of 0
        if (centerX + speedX <= 60) //if speedX plus centerX would bring the character outside the screen
            setCenterX(61);           //Fix the character's centerX at 60 pixels.

        rect1.setRect(centerX-34,centerY-63,65,63);
        rect2.setRect(rect1.getX(), rect1.getY()+63,65,64);
        gunRect.setRect(rect1.getX()+65,rect1.getY()+32,20,20);
        leftFoot.setRect(centerX-50,centerY+20,50,15);
        rightFoot.setRect(centerX,centerY+20,50,15);
    }

    public void moveRight() {
        if (!ducked)
            setSpeedX(MOVESPEED);
    }

    public void moveLeft() {
        if (!ducked)
            setSpeedX(-MOVESPEED);
    }

    public void stopLeft() {
        setMovingLeft(false);
        stop();
    }

    public void  stopRight()
    {
        setMovingRight(false);
        stop();
    }

    public void stop()
    {
        if (isMovingLeft()==false && isMovingRight()==false)
            setSpeedX(0);
        if (isMovingLeft() && isMovingRight()==false)
            moveLeft();
        if (isMovingLeft()==false && isMovingRight())
            moveRight();
    }

    public void jump() {
        if (jumped == false)
        {
            setSpeedY(JUMPSPEED);
            setJumped(true);
        }

    }


    public void shoot()
    {
        Projectile p = new Projectile(centerX + 50, centerY - 25);
        projectiles.add(p);
    }



    // Getters and Setters
    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public boolean isJumped() {
        return jumped;
    }

    public void setJumped(boolean jumped) {
        this.jumped = jumped;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public boolean isDucked() {
        return ducked;
    }

    public void setDucked(boolean ducked) {
        this.ducked = ducked;
    }
}