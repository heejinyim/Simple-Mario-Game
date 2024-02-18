import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener
{
	View view;
	Model model;
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean keySpace;
	boolean editMode = false;

	Controller(Model m)
	{
		model = m;
	}

	void setView(View v)
	{
		view = v;
	}

	public void actionPerformed(ActionEvent e)
	{
	}
    
	public void mousePressed(MouseEvent e)
	{
		model.addPipe(e.getX()+view.scrollPos, e.getY());
	}
	
	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	
	public void mouseClicked(MouseEvent e) 
	{
		Pipe p = new Pipe(e.getX(), e.getY());
	}

	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
			case KeyEvent.VK_SPACE: keySpace = true; break;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; break;
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			case KeyEvent.VK_UP: keyUp = false; break;
			case KeyEvent.VK_DOWN: keyDown = false; break;
			case KeyEvent.VK_SPACE: keySpace = false; break;
			case KeyEvent.VK_ESCAPE: System.exit(0);
		}

		char c = Character.toLowerCase(e.getKeyChar());
		if(c == 'q')
		{
			System.exit(0);
		}

		if(c == 's')
		{
			Json saveObject = model.marshal();
			saveObject.save("pipemap.json");
			System.out.println("File is saved.");
		}

		if(c == 'l')
		{
			Json j = Json.load("pipemap.json");
			model.unmarshal(j);
			System.out.println("File is loaded!");
		}

		if(c == 'e')
		{
			editMode = !editMode;
			System.out.println("Edit mode: " + editMode);
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{
		model.mario.setPreviousPosition();

		if(keyRight)
		{
			model.mario.x += 4;
			//view.scrollPos += 4;
			model.mario.changeImageState();
			model.mario.rightFacing = true;
		}

		if(keyLeft)
		{
			//view.scrollPos -= 4;
			model.mario.x -= 4;
			model.mario.changeImageState();
			model.mario.rightFacing = false;
		}

		if(keySpace)
		{
			model.mario.jump();
		}
	}
}

