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
public class Wall extends Movement
{
    public Wall(int x, int y)
    {
        super(x, y);
        ImageIcon ico=new ImageIcon("wall.jpg");
        Image img=ico.getImage();
        this.setImage(img);
    }
}