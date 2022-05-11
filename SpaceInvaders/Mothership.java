import java.awt.Rectangle;
import javax.swing.*;
import java.awt.*;

public class Mothership
{
  int sizeW=0, sizeH=0, y_speed=0, x_speed=0, x=0, y=0,alive=1,shot=0;
  Rectangle boundaryA;
  
  public void createA(int w, int h)
  {
    sizeW=40;
    sizeH=20;
    y_speed=0;
    x_speed=2;
    x=-300;
    y=10;
    
    if(alive==1)
    {
    boundaryA=new Rectangle(x,y,sizeW,sizeH);
    }
  }
  
  public void moveA()
  {
    x+=x_speed;
    boundaryA=new Rectangle(x,y,sizeW,sizeH);
  }
  
  public void deadA()
  {
    x=-300;
    x_speed=2;
  }
  public void checkBoundaryA(int w, int h)
  {
    if (x>1000)
    {
      x=-300;
    }
  }
}