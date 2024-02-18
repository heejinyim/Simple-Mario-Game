import java.util.ArrayList; 
import java.util.Iterator;

import javax.lang.model.util.ElementScanner14;

class Model
{
    int pipe_x;
    int pipe_y;
	static int speed = 4;
    ArrayList<Pipe> pipes;
	Mario mario;

	Model()
	{
        pipes = new ArrayList<Pipe>();
		mario = new Mario(100, 100);
	}

	void unmarshal(Json ob)
	{
		pipes = new ArrayList<Pipe>();
		Json tmpList = ob.get("pipes");
		for(int i = 0; i < tmpList.size(); i++)
		{
			pipes.add(new Pipe(tmpList.get(i)));
		}
	}

	Json marshal()
	{
		Json ob = Json.newObject();
		ob.add("pipe x", pipe_x);
		ob.add("pipe y", pipe_y);
		Json tmpList = Json.newList();
		ob.add("pipes", tmpList);
		for(int i = 0; i < pipes.size(); i++)
		{
			tmpList.add(pipes.get(i).marshal());
		}
		return ob;
	}

	public void addPipe(int mx, int my)
	{
		boolean foundPipe = false;
		for(int i = 0; i < pipes.size(); i++)
		{
			if(pipes.get(i).amIClickingOnYou(mx, my) == true) //This means there is a pipe where I clicked
			{
				foundPipe = true;
				pipes.remove(i);
				break;
			}	
		}
		if(!foundPipe)
		{
			pipes.add(new Pipe(mx, my));
		}
	}

	boolean isThereACollision(Pipe p) 
	{
		if(mario.x+mario.w < p.x)
			return false;
		if(mario.x > p.x+p.w)
			return false;
		if(mario.y+mario.h < p.y) // assumes bigger is downward
			return false;
		if(mario.y > p.y+p.h) // assumes bigger is downward
			return false;
		return true;
	}

	public void update()
	{
		Iterator<Pipe> it = pipes.iterator();
		mario.update();

		for(int i = 0; i < pipes.size(); i++)
		{
			boolean check = isThereACollision(pipes.get(i));

			if(check == true)
			{
				mario.getOutOfPipe(pipes.get(i));
			}
		}
	}
}



