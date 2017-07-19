package com.example.contactlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);
		int OrderNumber;
		Thread timer = new Thread() {
			@Override
			public void run()
			{

				try
				{
					int time = 0;
					while (time < 3000)
					{
						sleep(100);
						time += 100;
					}

				} catch (Exception e)
				{
					e.printStackTrace();
				} finally
				{
					Intent i = new Intent(SplashScreen.this, MainActivity.class);
					startActivity(i);
					finish();
				}

			}
		};
		timer.start();
	}
}
