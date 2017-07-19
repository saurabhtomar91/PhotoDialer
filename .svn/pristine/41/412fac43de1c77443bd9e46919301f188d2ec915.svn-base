package com.example.contactlist.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Utils
{
	public static String PREFERENCE_Name = "photoDialer";
	public static final String USERNAME = "username";
	public static final String CONTACT_NUMER = "number";
	public static final String URI = "uri";

	public static void setMobileNumber(Context context, String number)
	{
		SharedPreferences sharpreference = context.getSharedPreferences(PREFERENCE_Name, Context.MODE_WORLD_WRITEABLE);
		SharedPreferences.Editor editor = sharpreference.edit();
		editor.putString(CONTACT_NUMER, number);
		editor.commit();
	}

	public static String getGetMobileNumber(Context context)
	{
		SharedPreferences sharpreference = context.getSharedPreferences(PREFERENCE_Name, Context.MODE_WORLD_READABLE);
		return sharpreference.getString(CONTACT_NUMER, "");
	}

	public static void setName(Context context, String number)
	{
		SharedPreferences sharpreference = context.getSharedPreferences(PREFERENCE_Name, Context.MODE_WORLD_WRITEABLE);
		SharedPreferences.Editor editor = sharpreference.edit();
		editor.putString(USERNAME, number);
		editor.commit();
	}

	public static String getName(Context context)
	{
		SharedPreferences sharpreference = context.getSharedPreferences(PREFERENCE_Name, Context.MODE_WORLD_READABLE);
		return sharpreference.getString(USERNAME, "");
	}

	public static void setURi(Context context, String number)
	{
		SharedPreferences sharpreference = context.getSharedPreferences(PREFERENCE_Name, Context.MODE_WORLD_WRITEABLE);
		SharedPreferences.Editor editor = sharpreference.edit();
		editor.putString(URI, number);
		editor.commit();
	}

	public static String getURI(Context context)
	{
		SharedPreferences sharpreference = context.getSharedPreferences(PREFERENCE_Name, Context.MODE_WORLD_READABLE);
		return sharpreference.getString(URI, "");
	}
}
