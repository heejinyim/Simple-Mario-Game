import java.awt.Graphics;
import java.awt.image.BufferedImage; 

public class Mario 
{
    int x, y, w, h, px, py;
    double vert_velocity; 
    int currentImage;
    static BufferedImage[] images;
    int numFramesInAir = 0;
    boolean rightFacing = true;
    int jumpFrames;

    public Mario(int x, int y)
    {
        this.x = x;
        this.y = y;
        currentImage = 0;
        vert_velocity = -14;
        jumpFrames = 0;
        images = new BufferedImage[5];
        for(int i = 0; i < images.length; i++)
        {
            images[i] = View.loadImage("mario" + (i+1) + ".png");
        }
        this.h = images[0].getHeight();
        this.w = images[0].getWidth();
    }

    void jump()
    {
        jumpFrames++;
        if(jumpFrames > 5)
        {
            vert_velocity = 10;
            if(y == 349)
            {
                jumpFrames = 0;
            }
        }
        else if(jumpFrames < 5)
        {
            vert_velocity = -45;
        }
    }

    void update()
	{
		vert_velocity += 6.9;
		y += vert_velocity;
        numFramesInAir++;
       
        if(y > 500)
        {
            vert_velocity = 0;
            y = 500;
        }

        if(y > 400 - h)
		{
			vert_velocity = -2;
		}

        if(y < 9)
        {
            y = 0;
        }
        if(y > 350)
        {
            y = 349;
        } 
    
        changeImageState();
	}

    public void setPreviousPosition()
    {
        px = x;
        py = y;
    }

    public void getOutOfPipe(Pipe p)
    {
        if(x <= p.x+p.w && px >= p.x+p.w)
        {
            x = p.x + p.w;
        }
        if(x+w >= p.x && px+w <= p.x)
        {
            x = p.x - w;
        }
        if(y+h >= p.y && py+h <= p.y)
        {
            y = p.y-h-1;
            jumpFrames = 0;
            vert_velocity = 0;
        }
        if(y <= p.y+p.h && py >=p.y+p.h)
        {
            y = p.y+p.h;
        }
    }

    void changeImageState()
    {
        currentImage++;
        if(currentImage > 4)
        {
            currentImage = 0;
        } 
        //System.out.println("current image = " + currentImage);
    }

    void drawYourself(Graphics g, int scroll)
    {
        if(rightFacing)
        {
            g.drawImage(images[currentImage], x-scroll, y, w, h, null);
        }
        else
        {
            g.drawImage(images[currentImage], x-scroll+w, y, -w, h, null);
        }
    }

    @Override 
    public String toString()
    {
        return "Pipe (x,y) = (" + x + ", " + y + "), width = " + w + ", height = " + h;
    }
}