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

        collision();

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
        g.setColor(Color.blue);
        g.fillRect(x, y, 32,32);

    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempPlayer = handler.object.get(i);

            if(tempPlayer.getId() == ID.Block) {
                if(getBounds().intersects(tempPlayer.getBounds())) {
                    // This is essentially reversing the expected movement when the Player hits a wall
                    x += velocityX * -1;
                    y += velocityY * -1;
                }
            }
        }
    }
}