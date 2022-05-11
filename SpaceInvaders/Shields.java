import java.awt.Rectangle;

public class Shields///Not Done
{
  int sizeW=0, sizeH=0, x=0, y=0,lives=0,alive=1;
  Rectangle boundaryS;
  
  public void createS(int w, int h)
  {
    sizeW=30;
    sizeH=30;
    x=50;
    y=450;
    lives=3;
    
    boundaryS=new Rectangle(x,y,sizeW,sizeH);
  }
}

