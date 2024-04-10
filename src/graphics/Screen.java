/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import java.util.Random;
import main.Display;
import main.Game;

/**
 *
 * @author joaog
 */
public class Screen extends Render {
    private Game game;
    private Render render;
    private int count;

    public Screen(int width, int height) {
        super(width, height);
        Random random = new Random();
        game = new Game();
        render = new Render(256, 256);
        for (int i = 0; i < 256 * 256; i++) {
            int[] cores = new int[11];
            cores[0] = 0xFFFFFF;
            cores[1] = 0xDF3AF0;
            cores[2] = 0xA978A9;
            cores[3] = 0x30AF4F;
            cores[4] = 0x234BAF;
            cores[5] = 0xFF0000;
            cores[6] = 0x00FF00;
            cores[7] = 0x0000FF;
            cores[8] = 0xFF00FF;
            cores[9] = 0xFFFF00;
            cores[10] = 0x00FFFF;

            if (i % 257 == 0 || i % 255 == 0) {

                render.pixels[i] = 0x000000;

            } else {
                render.pixels[i] = cores[3];
            }

        }
    }

    public void render() {
        Random random = new Random();

        for (int i = 0; i < width * height; i++) {
            
            if (i % (width / 6) == 0) {
                pixels[i] = 0xFFFFFF;
            } else {
                pixels[i] = 0;
            }
        }

        for (int i = 0; i < 100; i++) {
            boolean maxBoundTouched = false;
            final int minPos = 0, maxY = height, maxX = width;
            int yPos, xPos, x = 1, y = 1;
            
            int anim0 = (int) (Math.cos((System.currentTimeMillis() - i) % 2000.0 / 2000 * Math.PI * 2) * 100);
            int fall = (int) (Math.tan((System.currentTimeMillis() - i) % 2000.0 / 2000 * Math.PI * 2) * 100);
            
            
            
            yPos = y + fall;
            if (yPos > maxY - 256) {
                yPos = maxY - 256;
            }
            if (yPos <= minPos) {
                yPos = minPos;
                maxBoundTouched = true;
            }
            xPos = x + anim0;
            if (xPos > maxX - 256) {
                xPos = maxX - 256;
            }
            if (yPos <= minPos) {
                yPos = minPos;
                maxBoundTouched = true;
            }
            draw(render, xPos , yPos);
        }
    }
    public void blockFall() {
        
    }
}
