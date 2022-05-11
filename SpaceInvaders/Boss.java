import java.awt.Rectangle;
import javax.swing.*;
import java.awt.*;

public class Boss
{
  int sizeW=0, sizeH=0, y_speed=0, x_speed=0, x=0, y=0, delay=0, alive=1,health=50,shot=0,battle=0;
  Rectangle boundaryB;
  
  public void createB(int w, int h)
  {
    sizeW=400;
    sizeH=200;
    y_speed=0;
    x=75;
    y=-250;
    
    if(alive==1)
    {
    boundaryB=new Rectangle(x,y,sizeW,sizeH);
    }
  }
  
  public void moveB()
  {
    delay++;
    if (delay==10)
    {
    y+=y_speed;
    x+=x_speed;
    delay=0;
    }
    if(alive==1)
    {
      if(alive==1)
    {
    boundaryB=new Rectangle(x,y,sizeW,sizeH);
  }
    }}
  
  public void deadB()
  {
    y=-250;
    x=75;
    alive=0;
    health=50;
    shot=0;
    x_speed=0;
    battle=0;
  }
  
  public void checkBoundaryB(int w, int h)
  {
    if (y>50)
    {
      y=50;
      y_speed=0;
      battle=1;
    }
  }
}