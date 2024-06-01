/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sokoban;

import java.awt.*;

/**
 *
 * @author Aspire
 */
public class Movement
{
    private final int space=30;
    private int x;
    private int y;
    private Image image;

    public Image getImage()
    {
        return image;
    }
    public void setImage(Image image)
    {
        this.image=image;
    }

    public int getX()
    {
        return x;
    }
    public void setX(int x)
    {
        this.x=x;
    }

    public int getY()
    {
        return y;
    }
    public void setY(int y)
    {
        this.y=y;
    }

    public Movement(int x, int y)
    {
        this.x=x;
        this.y=y;
    }

    public boolean isleft(Movement actor)
    {
        if ((this.getX()-space==actor.getX())&&(this.getY()==actor.getY()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isright(Movement actor)
    {
        if ((this.getX()+space==actor.getX())&&(this.getY()==actor.getY()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean istop(Movement actor)
    {
        if ((this.getY()-space==actor.getY())&&(this.getX()==actor.getX()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isbottom(Movement actor)
    {
        if ((this.getY()+space==actor.getY())&&(this.getX()==actor.getX()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}