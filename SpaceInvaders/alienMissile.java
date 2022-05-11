import java.awt.Rectangle;

public class alienMissile
{
  int sizeW=0, sizeH=0, y_speed=0, x=0, y=0, counter=0, launchA=0;
  Rectangle boundaryAM;
  
  public void createAM(int w, int h)
  {
    sizeW=3;
    sizeH=10;
    y_speed=0;
    x=25;
    y=50;
    
    boundaryAM=new Rectangle(x,y,sizeW,sizeH);
  }
  
  public void moveAM()
  {
    y+=y_speed;
    boundaryAM=new Rectangle(x,y,sizeW,sizeH);
  }
  
  public void checkBoundaryAM(int w, int h)
  {
      y=50;
      y_speed=0;
  }
}