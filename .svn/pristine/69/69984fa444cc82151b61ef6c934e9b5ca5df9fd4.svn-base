package com.example.contactlist;

import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.SimpleCursorAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter
{

	final int PAGE_COUNT = 20;
	SimpleCursorAdapter mAdapter;
	MatrixCursor mMatrixCursor;
	Cursor mycursor;
	int count;

	/** Constructor of the class */
	public MyFragmentPagerAdapter(FragmentManager fm, int count)
	{
		super(fm);
		this.count = count;
	}

	/** This method will be invoked when a page is requested to create */
	@Override
	public Fragment getItem(int arg0)
	{
		MyFragment myFragment = new MyFragment();
		Bundle data = new Bundle();
		data.putInt("current_page", arg0 + 1);
		myFragment.setArguments(data);
		return myFragment;
	}

	/** Returns the number of pages */
	@Override
	public int getCount()
	{
		return count;
	}

	@Override
	public CharSequence getPageTitle(int position)
	{
		return "Page #" + (position + 1);
	}

}
