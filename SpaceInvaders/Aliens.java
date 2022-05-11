import java.awt.Rectangle;
import javax.swing.*;
import java.awt.*;

public class Aliens
{
  int sizeW=0, sizeH=0, y_speed=0, x_speed=0, x=0, y=0, delay=0, alive=1,shot=0;
  Rectangle boundaryA;
  
  public void createA(int w, int h)
  {
    sizeW=40;
    sizeH=20;
    y_speed=0;
    x_speed=2;
    x=10;
    y=50;
    
    if(alive==1)
    {
    boundaryA=new Rectangle(x,y,sizeW,sizeH);
    }
  }
  
  public void moveA()
  {
    delay++;
    if (delay==10)
    {
    x+=x_speed;
    delay=0;
    }
    boundaryA=new Rectangle(x,y,sizeW,sizeH);
  }
  
  public void deadA()
  {
    y=750;
    alive=0;
  }
  
  public void checkBoundaryA(int w, int h)
  {
    if (x>550)
    {
      x_speed=-1;
    }
    if (x<0)
    {
      x_speed=1;
    }
  }
}