package main;

import javafx.scene.paint.Color;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    private boolean isRunning = false;
    private Thread thread;
    private ObjectHandler handler;

    public Game(){
        new Window(1000, 700, "ULTRAVIOLET", this);
        start();

        handler = new ObjectHandler();
        this.addKeyListener(new KeyInput(handler));

        handler.addObject(new Player(400, 500, ID.Player, handler));

    }

    private void start(){
        isRunning = true;
        thread = new Thread(this);
        thread.start();

    }

    private void stop(){
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e){
                e.printStackTrace();
        }
    }

    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while(delta >= 1) {
                tick();
                delta --;
            }

            render();
            frames ++;

            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }

    public void tick(){
        handler.tick();

    }

    public void render(){
        Toolkit.getDefaultToolkit().sync();
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Toolkit.getDefaultToolkit().sync();

        g.setColor(java.awt.Color.BLACK);
        g.fillRect(0, 0, 1000, 700);

        handler.render(g);

        g.dispose();
        bs.show();

    }

    public static void main(String args[]){
        new Game();
    }
}
