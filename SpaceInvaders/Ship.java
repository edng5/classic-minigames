import java.awt.Rectangle;

public class Ship
{
  int sizeW=0, sizeH=0, x_speed=0, x=0, y=0, delay=0,lives=0, shot=0;
  Rectangle boundaryS;
  
  public void createS(int w, int h)
  {
    sizeW=50;
    sizeH=30;
    x_speed=0;
    x=250;
    y=600;
    lives=3;
    
    boundaryS=new Rectangle(x,y,sizeW,sizeH);
  }
  
  public void moveS()
  {
    delay++;
    if (delay==1)
    {
      x+=x_speed;
      boundaryS=new Rectangle(x,y,sizeW,sizeH);
      delay=0;
    }
  }
  
  public void checkBoundaryS(int w, int h)
  {
    if (x<0)
      x=0;
    if (x>540-sizeW)
      x=490;
  }
}



