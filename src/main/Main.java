package main;

import graphics.Render;
import graphics.Screen;
import input.Controller;
import input.InputHandler;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;

/**
 * @author Joao Guilherme
 */
public class Main extends Canvas implements Runnable {

    public static final int WIDTH = 1366;
    public static final int HEIGHT = 768;
    public static final String TITLE = "JOGO DE ESTOURAR COISA";

    private static int fps;
    private Thread thread;
    private static Boolean running = false;
    private Render render;
    private Screen screen;
    private BufferedImage img;
    private int[] pixels;
    private Game game;
    private InputHandler input = new InputHandler();

    public Main() {
        Dimension size = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
        screen = new Screen(WIDTH, HEIGHT);
        img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
        game = new Game();
        input = new InputHandler();
        addKeyListener(input);
    }
    
    private void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private void stop() {
        if (!running) return;
        running = false;
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    @Override
    public void run() {
       
        int frames = 0;
        double unprocessedSeconds = 0;
        long previousTime = System.nanoTime();
        double secondsPerTick = 1 / 60.0;
        int tickCount = 0;
        boolean ticked = false;
        System.out.println(running);
        while (running) {
            long currentTime = System.nanoTime();
            long passedTime = currentTime - previousTime;
            previousTime = currentTime;
            unprocessedSeconds += passedTime / 1000000000.0;
            while (unprocessedSeconds > secondsPerTick) {
                tick();
                unprocessedSeconds -= secondsPerTick;
                ticked = true;
                tickCount++;

                if (tickCount % 60 == 0) {
                    fps = frames;
                    previousTime += 1000;
                    frames = 0;
                }
            }
            if (ticked) {
                render();
                frames++;
            }
            render();
            frames++;

        }
    }

    private void tick() {
        game.tick(input.key);
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.render();

        for (int i = 0; i < WIDTH * HEIGHT; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(img, 0, 0, WIDTH+20, HEIGHT+20, null);
        g.setFont(new Font("Arial", 1, 50));
        g.setColor(Color.decode("#FFFF00"));
        g.drawString("FPS: " + Integer.toString(fps), 20, 50);
        g.dispose();
        bs.show();

    }

    public static void main(String[] args) {
        Main game = new Main();
        JFrame frame = new JFrame();
        frame.add(game);
        frame.pack();       
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle(TITLE);
        frame.setVisible(true);
        frame.setResizable(false);
        game.start();
    }

}
