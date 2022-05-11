import java.awt.Rectangle;
import javax.swing.*;
import java.awt.*;

public class EnemiesR
{
  int sizeW=0, sizeH=0, y_speed=0, x_speed=0, x=0, y=0, delay=0, alive=1,shot=0;
 
  public void create(int w, int h)
  {
    sizeW=10;
    sizeH=10;
    y_speed=3;
    x_speed=0;
    x=280;
    y=350;
    
  }
  
  public void move()
  {
    delay++;
    if (delay==10)
    {
    y+=y_speed;
    delay=0;
    }
  }
  
  public void checkBoundary(int w, int h)
  {
    if (x>360)
    {
      x=360;
    }
    if (x<280)
    {
      x=280;
    }
  }
}