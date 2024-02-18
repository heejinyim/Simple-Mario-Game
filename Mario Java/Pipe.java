import java.util.ArrayList; 
import java.awt.Graphics;
import java.awt.image.BufferedImage; 

public class Pipe 
{
    int x, y, w, h, px, py;
    static BufferedImage image;
    public Pipe(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.w = 55;
        this.h = 400;

        if(image == null)
        {
            image = View.loadImage("pipe.png"); 
        }
    }

    public Pipe(Json ob)
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        w = 55;
        h = 400;
        if(image == null)
        {
            image = View.loadImage("pipe.png"); 
        }
    }

    boolean amIClickingOnYou(int mousex, int mousey)
    {
        if((mousex <= (x + w) && mousex >= x) && (mousey <= (y + h) && mousey >= y))
        {
            return true;
        }
        return false;
    }

    Json marshal()
    {
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("height", h);
        ob.add("y", y);
        ob.add("w", w);
        return ob;
    }

    public void drawYourself(Graphics g, int scroll)
    {
        g.drawImage(image, x-scroll, y, w, h, null);
        
    }

    @Override 
    public String toString()
    {
        return "Pipe (x,y) = (" + x + ", " + y + "), width = " + w + ", height = " + h;
    }
}