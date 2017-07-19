package com.example.contactlist;

import com.example.contactlist.db.DBAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;

public class ContactPage extends FragmentActivity
{
	DBAdapter db;
	Context context;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showcontact);

		try{
		db = new DBAdapter(getApplicationContext());

		/** Getting a reference to the ViewPager defined the layout file */
		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		/** Getting fragment manager */
		FragmentManager fm = getSupportFragmentManager();
		/** Instantiating FragmentPagerAdapter */
		MyFragmentPagerAdapter pagerAdapter = new MyFragmentPagerAdapter(fm,db.getCount());
		/** Setting the pagerAdapter to the pager object */
		pager.setAdapter(pagerAdapter);
		}
		catch(Exception e)
		{
			Log.e("ContactPage", "Error"+e);
		}
	}
	public void onBackPressed()
    {
		try
		{
			Intent i = new Intent(getBaseContext(), MainActivity.class);
			startActivity(i);
			this.finish();
			
			
		} 
		catch (Exception e)
		{
			Log.v("key", "=========Call End For All====" + e);
		}
    }
	
}
