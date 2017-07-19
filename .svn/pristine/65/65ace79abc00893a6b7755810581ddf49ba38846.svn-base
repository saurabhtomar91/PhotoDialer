package com.example.contactlist;

import com.example.contactlist.db.DBAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity
{

	DBAdapter db;
	Context context;
	Cursor c;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent callservice = new Intent(MainActivity.this, CallHelper.class);
		startService(callservice);

		TextView textview = (TextView) findViewById(R.id.addcontact);
		textview.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v)
			{
				Intent i = new Intent(getApplicationContext(), AddContact.class);
				startActivity(i);
			}
		});

		TextView textviews = (TextView) findViewById(R.id.contactlist);
		textviews.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v)
			{
				try
				{
					db = new DBAdapter(getApplicationContext());
					if(db.getCount() > 0)
			        {
						Intent i = new Intent(getApplicationContext(), ContactPage.class);
						startActivity(i);
			        }
				    else
				    {
				    	Toast.makeText(getApplicationContext(), getResources().getString(R.string.nocontact), Toast.LENGTH_SHORT).show();
				    }
				}
			        catch (Exception e)
					{
						e.printStackTrace();
						Log.e("DBAdapter...", "Exception .....");
					}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void onBackPressed()
    {
		try
		{
			finish();
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			startActivity(intent);
			
		} 
		catch (Exception e)
		{
			Log.v("key", "=========Call End For All====" + e);
		}
    }

}
