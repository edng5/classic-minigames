import java.awt.Rectangle;
import javax.swing.*;
import java.awt.*;

public class Bullets
{
  int size=0, y_speed=0, x_speed=0, x=0, y=0, delay=0, alive=1,shot=0,reset=0, changesize=0,timer=0,launch=0;
  Rectangle boundary;
  
  public void create(int w, int h)
  {
    size=15;
    y_speed=0;
    x_speed=0;
    changesize=0;
  
    boundary=new Rectangle(x,y,size,size);
  }
  
  public void move()
  {
    x+=x_speed;
    y+=y_speed;
    delay++;
    if (delay==3){
    size+=changesize;
    delay=0;
    }
    
    boundary=new Rectangle(x,y,size,size);
  }
  
  
  public void checkBoundary(int w, int h)
  {
    if (y<170)
    {
      x_speed=0;
      y_speed=0;
      y=300;
      reset=1;
      changesize=0;
      size=15;
    }
  }
}