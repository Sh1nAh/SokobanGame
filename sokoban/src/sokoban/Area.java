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
public class Area extends Movement
{
    public Area(int x, int y)
    {
        super(x, y);
        ImageIcon ico=new ImageIcon();
        Image img=ico.getImage();
        this.setImage(img);
    }
}