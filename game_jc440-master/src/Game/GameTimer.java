package Game;

public class GameTimer
{
	private int time;
	private int limit;
	private long startTime;
	
	public GameTimer (int t, long start) //creates the GameTimer with time limit and starting time
	{
		time = t;
		limit = t;
		startTime = start;
	}
	
	public void update (long current) //updates time but getting the difference between startTime and the current time
	{
		int difference = (int) (current - startTime) / 1000;
		time = limit - difference;
	}
	
	public int getTime() //returns time. time cannot be negative.
	{
		if (time <= 0)
			time = 0;
		return time;
	}
}
