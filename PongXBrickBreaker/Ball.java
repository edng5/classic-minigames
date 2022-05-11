import java.awt.Rectangle;

public class Ball
{
  int size=20, y_speed=0, x_speed=0, x=0, y=0;
  Rectangle boundary;
  
  public void create(int w, int h)
  {
    size=20;
    x=260;
    y=330;
    
    boundary=new Rectangle(x,y,size,size);
    
  }
}