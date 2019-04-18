package main;

import java.awt.*;

public class Player extends GameObject{

    ObjectHandler handler;

    public Player(int x, int y, ID id, ObjectHandler handler) {
        super(x, y, id);
        this.handler = handler;

    }

    public void tick() {
        x += velocityX;
        y += velocityY;

        if(handler.isUp()) velocityY = -5;
        else if(!handler.isDown()) velocityY = 0;

        if(handler.isDown()) velocityY = 5;
        else if(!handler.isUp()) velocityY = 0;

        if(handler.isRight()) velocityX = 5;
        else if(!handler.isLeft()) velocityX = 0;

        if(handler.isLeft()) velocityX = -5;
        else if(!handler.isRight()) velocityX = 0;

    }

    public void render(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillOval(x, y, 32,32);

    }

    public Rectangle getBounds() {
        return null;
    }
}
