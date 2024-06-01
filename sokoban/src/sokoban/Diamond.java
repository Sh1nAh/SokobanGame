/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sokoban;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Aspire
 */
public class Diamond extends Movement
{
    public Diamond(int x, int y)
    {
        super(x, y);
        ImageIcon ico=new ImageIcon("diamond.png");
        Image img=ico.getImage();
        this.setImage(img);
    }

    public void move(int x, int y)
    {
        int newx=this.getX()+x;
        int newy=this.getY()+y;
        this.setX(newx);
        this.setY(newy);
    }
}