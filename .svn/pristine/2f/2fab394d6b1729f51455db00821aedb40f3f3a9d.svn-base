package com.example.contactlist;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v4.widget.SimpleCursorAdapter;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

public class FullImageActivity extends Activity {
	
	String mobilePhone;
	private Context ctx=this;
	String sanjay;
	String path;
	String name;
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.full_image);
		
		Bundle bundle = this.getIntent().getExtras();
		path = bundle.getString("photoPath");
		Log.v("photoPath ", "Show the Image path............ " + path);
		mobilePhone = bundle.getString("mobilePhone");
		Log.v("mobilePhone ", "Show the mobilePhone ............ " + mobilePhone);
		
		name = bundle.getString("name");
		Log.v("mobilePhone ", "Show the name ............ " + name);
		
		
		TextView view = (TextView)findViewById(R.id.myImageViewText);
		view.setText(name);
		
		ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
		imageView.setImageURI(Uri.parse(path));
		
		StringBuffer sb1 = new StringBuffer(mobilePhone);
		sb1.delete(0,14);
		
		sanjay = sb1.toString();	
	    System.out.println("Test Application..... " + sanjay);
	    //call(sanjay);
	    
	    
	    Button buttonaccept = (Button) findViewById(R.id.accept);
		Button buttonreject = (Button) findViewById(R.id.reject);
		
		buttonaccept.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			   Context cxt = getApplicationContext();
			   onReceive(cxt);
			  
			}
		});
		
		  buttonreject.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				   rejectCall();
				  
				}
			});
	}
	
	/* void call(String num1)
     {
		 
		 new Thread() 
	     {
	         public void run() 
	         {
	             try 
	             {
	                 sleep(4000);
	                 Intent callIntent=new Intent(Intent.ACTION_CALL);
	        	     callIntent.setData(Uri.parse("tel:"+ sanjay));
	                 startActivity(callIntent);
	                 Log.v("key", "================Under call function");
	             
	             } catch (InterruptedException e) 
	             {
	                 e.printStackTrace();
	             }
	         }
	     }.start();
		 
		
 		
     }*/
	 
	 private void rejectCall() 
	 {
		 TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		 MediaPlayer mp = new MediaPlayer();
		 
		 try 
			{
				Log.v("", "Get getTeleService...");
				Class<?> c = Class.forName(tm.getClass().getName());
				Method m = c.getDeclaredMethod("getITelephony");
				m.setAccessible(true);
				com.example.contactlist.ITelephony telephonyService = (ITelephony) m
						.invoke(tm);
				telephonyService.endCall();
				
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				Log.e("", "FATAL ERROR: could not connect to telephony subsystem");
				Log.e("", "Exception object: " + e);
			}
	 }
	 
	 public void onReceive(Context context) 
	 {
		   try 
		   {
		       Intent buttonDown = new Intent(Intent.ACTION_MEDIA_BUTTON);
			   buttonDown.putExtra(Intent.EXTRA_KEY_EVENT, 
			   new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_HEADSETHOOK));
			   context.sendOrderedBroadcast(buttonDown, "android.permission.CALL_PRIVILEGED");
		 
		 
			   Intent buttonUp = new Intent(Intent.ACTION_MEDIA_BUTTON);
			   buttonUp.putExtra(Intent.EXTRA_KEY_EVENT, 
			   new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HEADSETHOOK));
			   context.sendOrderedBroadcast(buttonUp, "android.permission.CALL_PRIVILEGED");
		   } 
			catch (Exception e) 
			{
				e.printStackTrace();
				Log.e("", "FATAL ERROR: could not connect to telephony subsystem");
				Log.e("", "Exception object: " + e);
			}
		    	
	  }
	 
	 public void onbackpressed() 
	 {
			finish();
	 }
		
	
	 

}
