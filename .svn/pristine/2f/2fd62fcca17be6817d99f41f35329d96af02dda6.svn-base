package com.example.contactlist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Helper class to detect incoming and outgoing calls.
 * 
 */
public class CallHelper
{

	/**
	 * Listener to detect incoming calls.
	 */

	/**
	 * Broadcast receiver to detect the outgoing calls.
	 */
	public class OutgoingReceiver extends BroadcastReceiver
	{
		public OutgoingReceiver()
		{
		}

		@Override
		public void onReceive(final Context context, Intent intent)
		{
			String number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
			Log.v("key", "call is going from your phone");

			// =================================
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
						Intent i = new Intent();
						i.setClass(context, EndCall.class);
						i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.startActivity(i);
						Log.v("key", "Comes under Activity" + i.setClass(context, EndCall.class));
					}
				}
			};
			pageTimer.setPriority(Thread.MAX_PRIORITY);
			pageTimer.start();

			// =================================

			Log.v("key", "It is under thread3");
		}

	}

	private Context ctx;
	private TelephonyManager tm;

	private OutgoingReceiver outgoingReceiver;

	public CallHelper(Context ctx)
	{
		this.ctx = ctx;
		outgoingReceiver = new OutgoingReceiver();
	}

	/**
	 * Start calls detection.
	 */
	public void start()
	{
		tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);

		IntentFilter intentFilter = new IntentFilter(Intent.ACTION_NEW_OUTGOING_CALL);
		ctx.registerReceiver(outgoingReceiver, intentFilter);
	}

	/**
	 * Stop calls detection.
	 */
	public void stop()
	{
		ctx.unregisterReceiver(outgoingReceiver);
	}
}
