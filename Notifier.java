package seFinal;

import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.text.Text;

public class Notifier
{
	private static Notifier instance;
	
	private Text text;
	private Timer timer;
	
	public Notifier (Text txt)
	{
		text = txt;
		instance = this;
	}
	
	public static Notifier getInstance()
	{
		return instance;
	}
	
	public void cancelTimer() {
		timer.cancel();
	}
	
	public void setText(String msg)
	{
		//set the text to the message and removes it after a few of seconds
		text.setText(msg);
		timer = new Timer();
		timer.schedule(new TimerTask()
		{	
			@Override
			public void run()
			{
				text.setText("");
			}
		}, 4000);
	}
}
