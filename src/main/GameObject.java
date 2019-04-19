package main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

    protected int x, y;
    protected float velocityX = 0, velocityY = 0;
    protected ID id;

    public GameObject(int x, int y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public ID getId() {
        return id;
    }

    public void setId(ID id){
        this.id = id;
    }

    public int getX(){
        return x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return y;
    }

    public void setY(int y){
        this.y = y;
    }

    public float getVelocityX(){
        return velocityX;
    }

    public void setVelocityX(float velocityX){
        this.velocityX = velocityX;
    }

    public float getVelocityY(){
        return velocityY;
    }

    public void setVelocityY(float velocityY){
        this.velocityY = velocityY;
    }


}
