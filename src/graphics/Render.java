/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import main.Main;

/**
 *
 * @author Joao Guilherme
 */
public class Render {
    public final int width;
    public final int height;
    public final int[] pixels;

    public Render(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }

    public void draw(Render render, int xOffset, int yOffset) {
        for (int y = 0; y < render.height; y++) {
            int yPix = (y + yOffset);
            if (yPix < (height / 2) * 1.05 && yPix > (height / 2) / 1.05) continue;
            
            for (int x = 0; x < render.width; x++) {
                int xPix = (x + xOffset);
                if (xPix < 0 || xPix >= Main.WIDTH) {
                    continue;
                }
                pixels[xPix + yPix * width] = render.pixels[x + y * render.width];
                int alpha = render.pixels[x + y * render.width];
                if (alpha >= 0) {
                    pixels[xPix + yPix * width] = render.pixels[x + y * render.width];
                }

            }
        }
    }
}
