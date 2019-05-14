package main;

import javafx.scene.paint.Color;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {

    private boolean isRunning = false;
    private Thread thread;
    private ObjectHandler handler;
    private Menu menu;

    private BufferedImage map = null;
    
    public static enum STATE {
    	MENU,
    	GAME
    };
    
    public static STATE State = STATE.MENU;

    public Game(){
        new Window(1600, 900, "ULTRAVIOLET", this);
        start();

        handler = new ObjectHandler();
        this.addKeyListener(new KeyInput(handler));
        menu = new Menu();

        //ImageLoader loader = new ImageLoader();
        //map = loader.loadImage("/minorMaze.png");

        setUpMaze();
        handler.addObject(new Player(780, 430, ID.Player, handler));
        handler.addObject(new Block(900, 500, ID.Block));
        //loadMap(map);
        
        this.addMouseListener(new MouseInput());
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
    	if(State == STATE.GAME) { 
    		handler.tick();
    	}
    }

    public void render(){
       // Toolkit.getDefaultToolkit().sync();
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        //Toolkit.getDefaultToolkit().sync();

        g.fillRect(0, 0, 1600, 900);
        if(State == STATE.GAME) {
        	handler.render(g);
        } else if (State == STATE.MENU) {
        	menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    private void setUpMaze() {
        for (int i = 0; i < 1600; i += 20) {
            handler.addObject(new Block(i, 0, ID.Block));
        }
        handler.addObject(new Block(0, 0, ID.Block));
    }

    public static void main(String args[]){
        new Game();
    }
}
