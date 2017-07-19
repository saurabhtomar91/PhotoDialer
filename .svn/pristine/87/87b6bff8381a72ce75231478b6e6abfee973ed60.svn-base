package com.example.contactlist;

import com.example.contactlist.db.DBAdapter;

import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class CustomPhoneStateListener extends PhoneStateListener
{
	EndCall endcall;
	// private static final String TAG = "PhoneStateChanged";
	Context context; // Context to make Toast if required
	private boolean isCheck;
	MyReceiver myreciver = new MyReceiver();
	DBAdapter db;

	public CustomPhoneStateListener(Context context)
	{
		super();
		this.context = context;
		endcall = new EndCall();
	}

	@Override
	public void onCallStateChanged(int state, String incomingNumber)
	{
		super.onCallStateChanged(state, incomingNumber);
		db = new DBAdapter(context);
		String checknumber = myreciver.outgoingno;
		String checknumbers = db.Exist(checknumber);
		switch (state) {

			case TelephonyManager.CALL_STATE_RINGING:

				// when Ringing
				// Toast.makeText(context, "Phone state Ringing",
				// Toast.LENGTH_LONG).show();

				break;
			case TelephonyManager.CALL_STATE_IDLE:
				//Toast.makeText(context, "Phone state Idle", Toast.LENGTH_LONG).show();

				if (isCheck == true)
				{
					if(checknumber.equals(checknumbers))
					{
						endcall.finish();
						Intent in = new Intent(context, ContactPage.class);
						//in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
						in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
						in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.startActivity(in);
						isCheck = false;
					}
					
				}

				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:
				// when Off hook i.e in call
				// Make intent and start your service here
				// Toast.makeText(context, "Phone state Off hook",
				// Toast.LENGTH_LONG).show();
				Log.e("mykey", "=================CALL_STATE_OFFHOOK");
				isCheck = true;
				break;

			default:
				break;
		}
	}
}