package com.example.contactlist;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import com.example.contactlist.utils.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class EndCall extends Activity
{

	Button btnEndCall, btnLoaudSpeaker,btn_delete,btn_edit, btnlaudspeakeroff;
	ImageView ivImage, ivTitle;
	TextView tvMobileNumber;
	Context cxt;
	String serviceManagerName = "android.os.ServiceManager";
	String serviceManagerNativeName = "android.os.ServiceManagerNative";
	String telephonyName = "com.android.internal.telephony.ITelephony";

	Class<?> telephonyClass;
	Class<?> telephonyStubClass;
	Class<?> serviceManagerClass;
	Class<?> serviceManagerStubClass;
	Class<?> serviceManagerNativeClass;
	Class<?> serviceManagerNativeStubClass;

	Method telephonyCall;
	Method telephonyEndCall;
	Method telephonyAnswerCall;
	Method getDefault;

	Method[] temps;
	@SuppressWarnings("rawtypes")
	Constructor[] serviceManagerConstructor;

	// Method getService;
	Object telephonyObject;
	Object serviceManagerObject;
	protected int requestCode;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.myfragment_layout);
		btnEndCall = (Button) findViewById(R.id.showcontact_button_call);
		btnEndCall.setText(getResources().getString(R.string.End));
		tvMobileNumber = (TextView) findViewById(R.id.showcontact_textview_name);
		tvMobileNumber.setTextSize(25);   
		tvMobileNumber.setGravity(Gravity.CENTER);
		
		ivImage = (ImageView) findViewById(R.id.showimage_imagevie_im);
		btnLoaudSpeaker = (Button) findViewById(R.id.showcontact_button_laudspeaker);
		
		btnlaudspeakeroff = (Button) findViewById(R.id.off_button_laudspeaker);
		
		ivTitle = (ImageView) findViewById(R.id.showimage_imagevie_im1);
		
		
		//ivTitle.setVisibility(View.GONE);
		btn_delete = (Button) findViewById(R.id.btn_delete);
		btn_delete.setVisibility(View.GONE);
		
		btn_edit = (Button) findViewById(R.id.btn_edit);
		btn_edit.setVisibility(View.GONE);
		
		ivTitle.setVisibility(View.VISIBLE);
		btnLoaudSpeaker.setVisibility(View.VISIBLE);
		
		btnEndCall.setBackgroundResource(R.drawable.reject);
		tvMobileNumber.setText(Utils.getName(getApplicationContext()) + "\n" + Utils.getGetMobileNumber(getApplicationContext()));
		//Bitmap bitmap = BitmapFactory.decodeFile(Utils.getURI(getApplicationContext()));
		//ivImage.setImageBitmap(bitmap);
		//////////////////////////////////////
		////////////////////////////////////
		Bitmap bitmaps = BitmapFactory.decodeFile(Utils.getURI(getApplicationContext()));
		int width=800;
		int height=800;
		Bitmap resizedbitmap=Bitmap.createScaledBitmap(bitmaps, width, height, true);
		ivImage.setImageBitmap(resizedbitmap);
		callListener();
		
	}

	private void OnLaudSpeaker()
	{
		AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		audioManager.setMode(AudioManager.MODE_IN_CALL);
		audioManager.setSpeakerphoneOn(false);
		
	}
	
	private void OnLaudSpeakerON()
	{
		AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		audioManager.setMode(AudioManager.MODE_IN_CALL);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,20,0);
        audioManager.setSpeakerphoneOn(true);
	}
	
	public void CallEndForAll()
	{
		try
		{
			Log.v("key", "=========Call End For All====");
			telephonyClass = Class.forName(telephonyName);
			telephonyStubClass = telephonyClass.getClasses()[0];
			serviceManagerClass = Class.forName(serviceManagerName);
			serviceManagerNativeClass = Class.forName(serviceManagerNativeName);

			Method getService = serviceManagerClass.getMethod("getService", String.class);

			Method tempInterfaceMethod = serviceManagerNativeClass.getMethod("asInterface", IBinder.class);

			Binder tmpBinder = new Binder();
			tmpBinder.attachInterface(null, "fake");

			serviceManagerObject = tempInterfaceMethod.invoke(null, tmpBinder);
			IBinder retbinder = (IBinder) getService.invoke(serviceManagerObject, "phone");
			Method serviceMethod = telephonyStubClass.getMethod("asInterface", IBinder.class);

			telephonyObject = serviceMethod.invoke(null, retbinder);
			telephonyEndCall = telephonyClass.getMethod("endCall");
			telephonyEndCall.invoke(telephonyObject);
		
			
			

		} 
		catch (Exception e)
		{
			Log.v("key", "=========Call End For All====" + e);
		}
	}

	public void callListener()
	{
		// Cut the call
		btnEndCall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				CallEndForAll();
				finish();
			}
		});
		// on the laoudSpeaker
		btnLoaudSpeaker.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v)
			{
				  btnLoaudSpeaker.setVisibility(View.GONE);
				  btnlaudspeakeroff.setVisibility(View.VISIBLE);
				  OnLaudSpeaker();
			}
		});
		
		btnlaudspeakeroff.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v)
			{
				  btnlaudspeakeroff.setVisibility(View.GONE);
				  btnLoaudSpeaker.setVisibility(View.VISIBLE);
				  OnLaudSpeakerON();
			}
		});
	}

	@Override
    public void onBackPressed()
    {}
	
	
}
