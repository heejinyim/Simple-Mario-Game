import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;

class View extends JPanel
{
    BufferedImage pipe_image;
	Model model;
	int scrollPos;
	
	View(Controller c, Model m)
	{
		scrollPos = 0; 
		model = m;
		c.setView(this);
	}
    
	static BufferedImage loadImage(String filename)
	{
		BufferedImage im = null;
		try
		{
			im = ImageIO.read(new File(filename));
		}	
		catch(Exception e) 
		{
			e.printStackTrace(System.err);
			System.exit(1);
		}
		System.out.println("Successfully loaded " + filename + " image."); 
		return im;
	}

	Json marshal()
    {
        Json ob = Json.newObject();
        ob.add("scroll", scrollPos);
        return ob;
    }

	public void paintComponent(Graphics g)
	{
		scrollPos = model.mario.x - 100;
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		//Ground
		g.setColor(new Color(100,255,80));
		g.fillRect(0,400,this.getWidth(), 100);

		
		if(model.mario.rightFacing)
		{
			g.drawImage(model.mario.images[model.mario.currentImage], model.mario.x-scrollPos, model.mario.y, model.mario.w, model.mario.h, null);
		}
		else
		{
			g.drawImage(model.mario.images[model.mario.currentImage], model.mario.x-scrollPos + model.mario.w, model.mario.y, -model.mario.w, model.mario.h, null);
		}
		
		for(int i = 0; i < model.pipes.size(); i++)
        {
			model.pipes.get(i).drawYourself(g, scrollPos);
		}
    }

}


