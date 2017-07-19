package com.example.contactlist;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.example.contactlist.db.DBAdapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.renderscript.RenderScript.Priority;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver
{
	public static String outgoingno;
	DBAdapter db;

	@Override
	public void onReceive(final Context context, Intent intent)
	{
		db = new DBAdapter(context);
		outgoingno = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
		String checknumber = db.Exist(outgoingno);
		if(outgoingno.equals(checknumber))
		{
			TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			telephonyManager.listen(new CustomPhoneStateListener(context), PhoneStateListener.LISTEN_CALL_STATE);	
		}
		try
		{
			Thread pageTimer = new Thread() {
				public void run()
				{
					try
					{
						sleep(500);
						Log.v("key", "Sleep Methods");

					} catch (InterruptedException e)
					{
						e.printStackTrace();
					} finally
					{
						String checknumbers = db.Exist(outgoingno);
						if(outgoingno.equals(checknumbers))
						{
							Intent callEnd = new Intent(context, EndCall.class);
							//callEnd.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
							//callEnd.putExtra("Close", true);
							callEnd.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK );
							callEnd.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							context.startActivity(callEnd);
						}
						else
						{
							System.out.println("Hi............... ");
							
						}
						
					}
				}
			};
			pageTimer.start();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	};

}
