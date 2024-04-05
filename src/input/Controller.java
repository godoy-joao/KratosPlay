/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

/**
 *
 * @author Joao Guilherme
 */
public class Controller {

    public double x;

    public void tick(boolean left, boolean right) {
        double xMove = 0;
        if (left) {
            xMove--;           
        }
        if (right) {
            xMove++;           
        }
    }
}
