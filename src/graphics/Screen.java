/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import java.util.Random;
import main.Game;

/**
 *
 * @author Joao Guilherme
 */
public class Screen extends Render{
    private Render render;
    

    public Screen(int width, int height) {
        super(width, height);
        Random random = new Random();
        render = new Render(256, 256);
        
        for (int i = 0; i < 256 * 256; i++) {
            render.pixels[i] = random.nextInt() * (random.nextInt(5) / 4);
        }
    }
    
    public void render() {
        draw(render,0,0);
    }

    public void render(Game game) {
        for (int i = 0; i < width * height; i++) {
            pixels[i] = 0;
        }
       // render.floor(game);
       // render.renderDistanceLimiter();
        draw(render, 0, 0);
    }
}
