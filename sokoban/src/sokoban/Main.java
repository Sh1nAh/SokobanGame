/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sokoban;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;

/**
 *
 * @author Aspire
 */
public class Main extends JFrame
{
    JLabel lblName,lblLevel,lblArrow,lblCount;
    JLabel lblActor,lblBox,lblDiamond,lblWall;
    JLabel lblGBoardImage;
    JPanel pnlGBoard;

    private ArrayList alWalls=new ArrayList();
    private ArrayList alBags=new ArrayList();
    private ArrayList alAreas=new ArrayList();
    private Actor Pussinboots;

    Wall objWall;
    Box objBox;
    Diamond objDiamond;
    Map m=new Map();

    String CompleteLevel="";
    private boolean completestatus=false;
    private int movecount=0;
    private final int OFFSET=180;
    private final int SPACE=30;

    private int width=0;
    private int height=0;

    private final int LEFT_COLLISION=1;
    private final int RIGHT_COLLISION=2;
    private final int TOP_COLLISION=3;
    private final int BOTTOM_COLLISION=4;

    public static void main(String[] args)throws IOException
    {
        Main m=new Main();
        m.nextLevel();
    }

    public Main()
    {
        this.setBounds(100,100,700,550);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblName=new JLabel("Sokoban");
        lblName.setBounds(10,400,70,50);
        this.add(lblName);

        CompleteLevel=m.level_1;
        lblLevel=new JLabel();
        lblLevel.setText("Level1");
        startGameBoard(CompleteLevel);
        lblLevel.setBounds(80,400,70,50);
        this.add(lblLevel);

        lblArrow=new JLabel("Use Arrow Keys");
        lblArrow.setBounds(150,400,100,50);
        this.add(lblArrow);

        lblCount=new JLabel("Count");
        lblCount.setBounds(270,400,70,50);
        this.add(lblCount);

        pnlGBoard=new JPanel();
        pnlGBoard.setLayout(null);
        pnlGBoard.setBounds(0,0,700,400);
        pnlGBoard.setBackground(Color.pink);
        this.add(pnlGBoard);

        Pussinboots=new Actor(30,30);
        lblActor=new JLabel(new ImageIcon(Pussinboots.getImage()));
        lblActor.setSize(30,30);

        Box b=new Box(30,30);
        lblBox=new JLabel(new ImageIcon(b.getImage()));
        lblBox.setSize(30,30);

        Diamond d=new Diamond(30,30);
        lblDiamond=new JLabel(new ImageIcon(d.getImage()));
        lblDiamond.setSize(30,30);

        Wall w=new Wall(30,30);
        lblWall=new JLabel(new ImageIcon(w.getImage()));
        lblWall.setSize(30,30);

        lblGBoardImage=new JLabel();
        pnlGBoard.add(lblGBoardImage);

        pnlGBoard.addKeyListener(new MovementKeyAdapter());
        pnlGBoard.setFocusable(true);

        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2d=(Graphics2D) g;
        super.paint(g2d);
        buildLevel_Map(g2d);
    }

    public final void startGameBoard(String level)
    {
        int x=OFFSET;
        int y=130;
        for(int i=0;i<level.length();i++)
        {
            char item=level.charAt(i);
            if(item=='\n')
            {
                y+=SPACE;
                if(this.width<x)
                {
                    this.width=x;
                }
                x=OFFSET;
            }
            else if(item=='w')
            {
                objWall=new Wall(x,y);
                alWalls.add(objWall);
                x+=SPACE;
            }
            else if(item=='b')
            {
                objBox=new Box(x,y);
                alBags.add(objBox);
                x+=SPACE;
            }
            else if(item=='d')
            {
                objDiamond=new Diamond(x,y);
                alAreas.add(objDiamond);
                x+=SPACE;
            }
            else if(item=='a')
            {
                Pussinboots=new Actor(x,y);
                x+=SPACE;
            }
            else if(item==' ')
            {
                x+=SPACE;
            }
            height=y;
        }
    }

    private void buildLevel_Map(Graphics2D g2d)
    {
        ImageIcon ii=new ImageIcon("wallpaper1.jpg");
        Image img=ii.getImage();
        g2d.drawImage(img, 130,20, null);
        g2d.fillRect(0, 0, lblGBoardImage.getWidth(),lblGBoardImage.getHeight());

        ArrayList alLevelMap=new ArrayList();
        alLevelMap.addAll(alWalls);
        alLevelMap.addAll(alAreas);
        alLevelMap.addAll(alBags);
        alLevelMap.add(Pussinboots);

        for(int i=0; i<alLevelMap.size(); i++)
        {
            Movement item=(Movement) alLevelMap.get(i);
            if((item instanceof Actor)||(item instanceof Box))
            {
                g2d.drawImage(item.getImage(), item.getX()+2, item.getY()+2, lblGBoardImage);
            }
            else
            {
                g2d.drawImage(item.getImage(), item.getX(), item.getY(), lblGBoardImage);
            }

            if(completestatus)
            {
                g2d.setColor(new Color(0, 0, 0));
                g2d.drawString("Completed", 25, 20);
                if (CompleteLevel.equals("level1"))
                {
                    CompleteLevel="level2";
                    startGameBoard(CompleteLevel);
                }
                else if (CompleteLevel.equals("level2"))
                {
                    CompleteLevel="level3";
                    startGameBoard(CompleteLevel);
                }
                else if (CompleteLevel.equals("level3"))
                {
                    CompleteLevel="level4";
                    startGameBoard(CompleteLevel);
                }
                else
                {
                    CompleteLevel="level5";
                    startGameBoard(CompleteLevel);
                }
            }
        }
    }

    public void isLevelComplete()
    {
        int num=alBags.size();
        int compl=0;

        for(int i=0; i<num; i++)
        {
            Box box=(Box) alBags.get(i);
            for(int j=0; j<num; j++)
            {
                Diamond area=(Diamond) alAreas.get(j);
                if(box.getX()==area.getX() && box.getY()==area.getY())
                {
                    compl +=1;
                }
            }
        }
        if(compl==num)
        {
            completestatus=false;
            if(CompleteLevel.equals(m.level_1))
            {
                CompleteLevel=m.level_2;
                lblLevel.setText("Level 2");
                startGameBoard(CompleteLevel);
            }
            else if(CompleteLevel.equals(m.level_2))
            {
                CompleteLevel=m.level_3;
                lblLevel.setText("Level 3");
                startGameBoard(CompleteLevel);
            }
            else if(CompleteLevel.equals(m.level_3))
            {
                CompleteLevel=m.level_4;
                lblLevel.setText("Level 4");
                startGameBoard(CompleteLevel);
            }
            else
            {
                CompleteLevel=m.level_5;
                lblLevel.setText("Level 5");
                startGameBoard(CompleteLevel);
            }

            JOptionPane.showMessageDialog(null, "Level Complete");
            nextLevel();
            repaint();
        }
    }

    private void nextLevel()
    {
        alAreas.clear();
        alBags.clear();
        alWalls.clear();
        startGameBoard(CompleteLevel);
        movecount=0;
        if(completestatus)
        {
            completestatus=false;
        }
    }

    class MovementKeyAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            if(completestatus)
            {
                return;
            }
            int key=e.getKeyCode();
            if(key==KeyEvent.VK_LEFT)
            {
                if(checkWallCollision(Pussinboots,LEFT_COLLISION))
                {
                    JOptionPane.showMessageDialog(null, "Left Wall");
                    return;
                }
                if(checkBoxCollision(LEFT_COLLISION))
                {
                    JOptionPane.showMessageDialog(null, "Left Wall");
                    return;
                }
                movecount +=1;
                Pussinboots.move(-SPACE, 0);
            }

            else if(key==KeyEvent.VK_RIGHT)
            {
                if(checkWallCollision(Pussinboots,RIGHT_COLLISION))
                {
                    JOptionPane.showMessageDialog(null, "Right Wall");
                    return;
                }
                if(checkBoxCollision(RIGHT_COLLISION))
                {
                    JOptionPane.showMessageDialog(null, "Right Wall");
                    return;
                }
                movecount +=1;
                Pussinboots.move(SPACE, 0);
            }

            else if(key==KeyEvent.VK_UP)
            {
                if(checkWallCollision(Pussinboots,TOP_COLLISION))
                {
                    JOptionPane.showMessageDialog(null, "Top Wall");
                    return;
                }
                if(checkBoxCollision(TOP_COLLISION))
                {
                    JOptionPane.showMessageDialog(null, "Top Wall");
                    return;
                }
                movecount +=1;
                Pussinboots.move(0, -SPACE);
            }

            else if(key==KeyEvent.VK_DOWN)
            {
                if(checkWallCollision(Pussinboots,BOTTOM_COLLISION))
                {
                    JOptionPane.showMessageDialog(null, "BOTTOM Wall");
                    return;
                }
                if(checkBoxCollision(BOTTOM_COLLISION))
                {
                    JOptionPane.showMessageDialog(null, "BOTTOM Wall");
                    return;
                }
                movecount +=1;
                Pussinboots.move(0, SPACE);
            }
            else if(key==KeyEvent.VK_R)
            {
                nextLevel();
            }
            lblCount.setText(String.valueOf(movecount));
            repaint();
        }
    }

    private boolean checkWallCollision(Movement actor, int type)
    {
        if (type==LEFT_COLLISION)
        {
            for (int i=0; i<alWalls.size(); i++)
            {
                Wall wall=(Wall) alWalls.get(i);
                if(actor.isleft(wall))
                {
                    return true;
                }
            }
            return false;
        }

        else if (type==RIGHT_COLLISION)
        {
            for (int i=0; i<alWalls.size(); i++)
            {
                Wall wall=(Wall) alWalls.get(i);
                if(actor.isright(wall))
                {
                    return true;
                }
            }
            return false;
        }

        else if (type==TOP_COLLISION)
        {
            for (int i=0; i<alWalls.size(); i++)
            {
                Wall wall=(Wall) alWalls.get(i);
                if(actor.istop(wall))
                {
                    return true;
                }
            }
            return false;
        }

        else if (type==BOTTOM_COLLISION)
        {
            for (int i=0; i<alWalls.size(); i++)
            {
                Wall wall=(Wall) alWalls.get(i);
                if(actor.isbottom(wall))
                {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private boolean checkBoxCollision(int collision_type)
    {
        if(collision_type==LEFT_COLLISION)
        {
            for(int i=0; i<alBags.size(); i++)
            {
                Box box=(Box) alBags.get(i);
                if(Pussinboots.isleft(box))
                {
                    for(int j=0; j<alBags.size(); j++)
                    {
                        Box item=(Box) alBags.get(j);
                        if(!box.equals(item))
                        {
                            if(box.isleft(item))
                            {
                                return true;
                            }
                        }
                        if(checkWallCollision(box, LEFT_COLLISION))
                        {
                            return true;
                        }
                    }
                    box.move(-SPACE, 0);
                    isLevelComplete();
                }
            }
            return false;
        }

        else if(collision_type==RIGHT_COLLISION)
        {
            for(int i=0; i<alBags.size(); i++)
            {
                Box box=(Box) alBags.get(i);
                if(Pussinboots.isright(box))
                {
                    for(int j=0; j<alBags.size(); j++)
                    {
                        Box item=(Box) alBags.get(j);
                        if(!box.equals(item))
                        {
                            if(box.isright(item))
                            {
                                return true;
                            }
                        }
                        if(checkWallCollision(box, RIGHT_COLLISION))
                        {
                            return true;
                        }
                    }
                    box.move(SPACE, 0);
                    isLevelComplete();
                }
            }
            return false;
        }

        else if(collision_type==TOP_COLLISION)
        {
            for(int i=0; i<alBags.size(); i++)
            {
                Box box=(Box) alBags.get(i);
                if(Pussinboots.istop(box))
                {
                    for(int j=0; j<alBags.size(); j++)
                    {
                        Box item=(Box) alBags.get(j);
                        if(!box.equals(item))
                        {
                            if(box.istop(item))
                            {
                                return true;
                            }
                        }
                        if(checkWallCollision(box, TOP_COLLISION))
                        {
                            return true;
                        }
                    }
                    box.move(0, -SPACE);
                    isLevelComplete();
                }
            }
            return false;
        }

        else if(collision_type==BOTTOM_COLLISION)
        {
            for(int i=0; i<alBags.size(); i++)
            {
                Box box=(Box) alBags.get(i);
                if(Pussinboots.isbottom(box))
                {
                    for(int j=0; j<alBags.size(); j++)
                    {
                        Box item=(Box) alBags.get(j);
                        if(!box.equals(item))
                        {
                            if(box.isbottom(item))
                            {
                                return true;
                            }
                        }
                        if(checkWallCollision(box, BOTTOM_COLLISION))
                        {
                            return true;
                        }
                    }
                    box.move(0, SPACE);
                    isLevelComplete();
                }
            }
        }
        return false;
    }
}